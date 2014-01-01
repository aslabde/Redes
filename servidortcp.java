import java.net.*;
import java.io.*;

public class servidortcp {

	public static void main(String argv[]) {
		ServerSocket socket;
		boolean fin = false;

		try { //Inicio bloque que puede generar excepción
			socket = new ServerSocket(6001); //Creción del socket del lado servidor vinculado al puerto 6001
			Socket socket_cli = socket.accept(); //Inicia IDLE esperando a recibir una peticion
												 //Los valores de la petición se asignarán a la var. socket_cli
			
			DataInputStream in = new DataInputStream(socket_cli.getInputStream());//Lee datos del cliente
			do { //Inicia bucle infinito
				String mensaje = ""; //Crea variable mensaje como string vacia
				mensaje = in.readUTF(); //Lee caracteres unicode
				System.out.println(mensaje);//Imprime por pantalla el mensaje 
			} while (1 > 0); //Condición infinita mientras de mantenga abierta la conexión
		}
		catch (Exception e) { //Control de excepción
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
