package com.gnievassj.literarula.principal;

import com.gnievassj.literarula.model.Authors;
import com.gnievassj.literarula.model.Books;
import com.gnievassj.literarula.model.DataResults;
import com.gnievassj.literarula.repository.AuthorsRepository;
import com.gnievassj.literarula.repository.BooksRepository;
import com.gnievassj.literarula.service.ConsumoAPI;
import com.gnievassj.literarula.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private BooksRepository booksRepository;
    private AuthorsRepository authorsRepository;
    public Principal(BooksRepository booksRepository,AuthorsRepository authorsRepository){
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
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
                    listarAutoresVivoByAño();
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

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ","%20"));
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, DataResults.class);
        //System.out.println(datos);
        var libro = datos.results().get(0);
        var autor = libro.authors().get(0);
        System.out.println(autor);
        System.out.println(new Authors(autor).formato());
        var author = new Authors(autor);
        try{
            authorsRepository.save(author);
        }catch(Exception e){
            e.getStackTrace();
            System.out.println("Ya existe el autor.");
        }
        System.out.println(libro);
        System.out.println(new Books(libro).formato());
        var book = new Books(libro);
    }

    private void listarLibrosRegistrados() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarAutoresVivoByAño() {
    }

    private void listarLibrosByIdioma() {
    }
}
