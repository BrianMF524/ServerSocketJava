package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String host="localhost";
        final int puerto=5000;

        try(
                Socket socket=new Socket(host,puerto);
                BufferedReader entrada=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida=new PrintWriter(socket.getOutputStream(),true);
                Scanner scanner=new Scanner(System.in);
                ) {
            System.out.println("Cliente conectado");
            while(true){
                System.out.println("Menu");
                System.out.println("1-Generar nombre usuario");
                System.out.println("2-Generar direccion correo");
                System.out.println("3-Salir");
                System.out.println("Seleccione una opcion:");
                String opcion =scanner.nextLine();

                switch (opcion){
                    case "1":
                        System.out.println("Ingrese longitud deseada(5 a 20)");
                        String longitud=scanner.nextLine();
                        salida.println(longitud);
                        String respuesta= entrada.readLine();
                        System.out.println(respuesta);
                        break;
                    case "2":
                        System.out.println("Ingrese su nombre de usuario");
                        String nombre=scanner.nextLine();
                        salida.println(nombre);
                        break;
                    case "3":
                        System.out.println("Saliendo");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;

                }
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
