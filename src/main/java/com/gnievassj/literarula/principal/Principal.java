package com.gnievassj.literarula.principal;

import com.gnievassj.literarula.model.Authors;
import com.gnievassj.literarula.model.Books;
import com.gnievassj.literarula.model.DataResults;
import com.gnievassj.literarula.model.Languages;
import com.gnievassj.literarula.service.*;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final BooksService booksService;
    private final AuthorsService authorsService;
    public Principal(BooksService booksService,AuthorsService authorsService){
        this.booksService = booksService;
        this.authorsService = authorsService;
    }

    public void menu(){
        var opcion =-1;
        do {
            var menu = """
                    -----------------------------
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                    0 - Salir
                    -----------------------------
                    """;
            System.out.println(menu);
            while(!teclado.hasNextInt()){
                System.out.println("Invalido. Ingrese un numero.");
                teclado.next();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivoByAnio();
                    break;
                case 5:
                    listarLibrosByIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }while(opcion != 0);
    }

    private <T extends IFormatoDatos> void listarDatos(List<T> lista){
        if (!lista.isEmpty()){
            lista.stream()
                    .map(T::formato)
                    .forEach(System.out::println);
        }else {
            System.out.println("No hay registros");
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ","%20"));
        //System.out.println(json);
        DataResults datos;
        try {
            datos = conversor.obtenerDatos(json, DataResults.class);
            Books libro = new Books(datos.results().get(0));
            booksService.save(libro);
            System.out.println(libro.formato());
            System.out.println("Libro guardado.");
            teclado.nextLine();
        }
        catch (IndexOutOfBoundsException e){
            //e.printStackTrace();
            System.out.println("No existe el libro.");
        }
        catch(UnexpectedRollbackException e) {
            //e.printStackTrace();
            System.out.println("Error al guarda el libro.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Books> listaLibros = booksService.getAllBooks();
        listarDatos(listaLibros);
    }

    private void listarAutoresRegistrados() {
        List<Authors> listaAutores = authorsService.getAllAuthors();
        listarDatos(listaAutores);
    }

    private void listarAutoresVivoByAnio() {
        System.out.println("Ingrese un año: ");
        while(!teclado.hasNextInt()){
            System.out.println("Invalido. Ingrese un numero.");
            teclado.next();
        }
        var opcion = teclado.nextInt();
        List<Authors> listaAutoresVivos = authorsService.getAuthorsAliveByYear(opcion);
        listarDatos(listaAutoresVivos);
    }

    private void listarLibrosByIdioma() {
        List<Books> listaLibrosPorIdioma;
        var option=-1;
        do {
            var submenu = """
                Idioma:
                1 - Ingles
                2 - Español
                0 - Volver al menu
                """;
            System.out.println(submenu);
            while(!teclado.hasNextInt()){
                System.out.println("Invalido. Ingrese un numero.");
                teclado.next();
            }
            option = teclado.nextInt();
            teclado.nextLine();
            Languages choise;
            switch (option){
                case 1:
                    choise = Languages.fromEspanol("Ingles");
                    listaLibrosPorIdioma = booksService.getAllBooksByLanguage(choise);
                    listarDatos(listaLibrosPorIdioma);
                    break;
                case 2:
                    choise = Languages.fromEspanol("Español");
                    listaLibrosPorIdioma = booksService.getAllBooksByLanguage(choise);
                    listarDatos(listaLibrosPorIdioma);
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }while(option != 0);
    }
}
