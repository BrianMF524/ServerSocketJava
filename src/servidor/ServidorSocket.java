package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

    public static void main(String[] args) {
        final int PUERTO = 5000;
        try(ServerSocket sv=new ServerSocket(PUERTO)){
            System.out.println("Servidor funcionando en el puerto "+ PUERTO);
            /*System.out.println("Usuario prueba: "+ GeneradorNombre.generar(7));
            System.out.println("Usuario prueba: "+ GeneradorNombre.generar(35));
            System.out.println("Usuario prueba: "+ GeneradorNombre.generar(18));
            System.out.println("Usuario prueba: "+ GeneradorNombre.generar(3));
            System.out.println("mail prueba: "+ GeneradorCorreo.generar(GeneradorNombre.generar(5)));
            System.out.println("mail prueba: "+ GeneradorCorreo.generar(GeneradorNombre.generar(5)));
            System.out.println("mail prueba: "+ GeneradorCorreo.generar(GeneradorNombre.generar(5)));*/
             while (true){
                 Socket client=sv.accept();
                 new Thread(()->manejarCliente(client)).start();
             }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void manejarCliente(Socket client){
        try(
                BufferedReader entrada= new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter salida=new PrintWriter(client.getOutputStream(),true)
                ) {
                int longitud=Integer.parseInt(entrada.readLine());

                if(longitud<5||longitud>20){
                    salida.println("Longitud invalida");
                    return;
                }
                String usuario = GeneradorNombre.generar(longitud);
                salida.println("Usuario generado: "+usuario);
                String correo = GeneradorCorreo.generar(usuario);
                salida.println("Correo generado: "+correo);
                if(ValidarCorreo.esValido(correo)){
                    salida.println("Correo valido.");
                }else {
                    salida.println("Correo invalido.");
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

