import java.net.*;
import java.io.*;

public class servidorudp {

	public static void main(String argv[]) {
		//Declaración de variables
		DatagramSocket socket;
		boolean fin = false;

		try { //Inicio del código que puede causar excepciones
			
			socket = new DatagramSocket(6000); //Abre el puerto 6000 para recibir datagramas
			                                   //según el protocolo UDP 
			do { //Inicio bucle
				byte[] mensaje_bytes = new byte[256]; //Buffer para almavenar los bytes entrantes
				DatagramPacket paquete = new DatagramPacket(mensaje_bytes, 256); //variable para almacenar el datagrama entrante
				socket.receive(paquete); //Recibir datagrama
				String mensaje = "";     //Declaración de variable string
				mensaje = new String(mensaje_bytes); //Convierte la secuencia de bytes en una cadena de caracteres
				System.out.println(mensaje); //Imprime mensaje entrante por la salida estandar
				if (mensaje.startsWith("end")) //Mantiene el bucle hasta que reciba la cadena "end"
					fin = true;
			} while (!fin);
		}
		catch (Exception e) { //Control de excepciones
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
