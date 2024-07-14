package com.gnievassj.literarula.principal;

import com.gnievassj.literarula.service.ConsumoAPI;
import com.gnievassj.literarula.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books";

    public void menu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    -----------------------------
                    1 - Buscar libro por titulo
                    2 - 
                    3 - 
                    4 - 
                    5 - 
                    6 - 
                    7 - 
                    8 - 
                    9 - 
                    0 - Salir
                    -----------------------------
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private void buscarLibroPorTitulo() {
    }
}
