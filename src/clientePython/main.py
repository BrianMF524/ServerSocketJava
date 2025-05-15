import socket

def menu():
    print("\n Seleccione una opcion")
    print("1-Generar nombre usuario")
    print("2-Generar direccion correo electronico")
    print("3-salir")
    return input("Seleccione una opcion:")

def main():
    HOST= "localhost"
    PUERTO=5000
    try:
        with socket.create_connection((HOST,PUERTO))as s:
            with s.makefile("r") as entrada, s.makefile("w") as salida:
                while True:
                    opcion=menu()
                    match opcion:
                        case "1":
                            longitud=input("Ingrese longitud deseada(5 a 20)\n")
                            salida.write("1\n")
                            salida.write(longitud+"\n")
                            salida.flush()
                            respuesta = entrada.readline().strip()
                            print("Usuario generado "+respuesta)
                        case "2":
                            nombre=input("Ingrese su nombre de usuario\n")
                            salida.write("2\n")
                            salida.write(nombre+"\n")
                            salida.flush()
                            respuesta=entrada.readline().strip()
                            print("Correo generado: "+respuesta)
                        case "3":
                            print("saliendo de la aplicacion")
                            salida.write("3")
                            salida.flush()
                            break
                        case _:
                            print("Opcion invalida")
    except ConnectionRefusedError:
        print("Conexion rechazada") 
    except Exception as e:
        print("Error ", e)

if __name__ == '__main__':
    main()
    