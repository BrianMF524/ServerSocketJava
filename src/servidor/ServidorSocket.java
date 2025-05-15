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
                //Se lee la opcion enviada desde la app cliente
                String opcion=entrada.readLine();

                switch (opcion){
                    case "1":
                        int longitud=Integer.parseInt(entrada.readLine());

                        if(longitud<5||longitud>20){
                            salida.println("Longitud invalida");
                            return;
                        }
                        String usuario = GeneradorNombre.generar(longitud);
                        salida.println("Usuario generado: "+usuario);
                        manejarCliente(client);
                        break;
                    case "2":
                        String nombreCorreo = entrada.readLine();
                        String correo = GeneradorCorreo.generar(nombreCorreo);
                        if(ValidarCorreo.esValido(correo)){
                            salida.println("Correo generado: "+correo);
                        }else {
                            salida.println("Correo invalido.");
                        }
                        manejarCliente(client);
                        break;
                    case "3":
                        salida.println("Cerrando conexion");
                        break;
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

