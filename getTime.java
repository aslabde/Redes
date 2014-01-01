import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Date;

public class getTime {
	                                     
	public static final long EPOCH_GAP = 2208988800L; //segundos entre 1900(TP) y 1970(JAVA)
	
	public static void main(String argv[]) {
		
	    //Declaracion de variables
		DatagramSocket socket;  
		InetAddress address;
		byte[] mensaje_bytes = new byte[256];
		String mensaje = "gimme da time!!";
		DatagramPacket paquete;
		boolean fin = false;

		
		try { //Inicio del bloque que puede causar excepciones
				socket = new DatagramSocket(); //Crea una variable de tipo datagrama
				address = InetAddress.getByName("nist1-ny.ustiming.org"); //Obtiene la direccion IP asociada a un nombre.

				mensaje_bytes = mensaje.getBytes(); // Codifica el mensaje en cadena de bytes
				paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 37); //Genera un datagrama que contiene la secuencia
				                                                                              //de bytes a enviar, la longitud de esta, y la 
				                                                                              //dirección IP y puerto de destino 
				socket.send(paquete); //Envia el datagrama generado
		
			do { //Inicio bucle
					byte[] response_bytes = new byte[4]; //Buffer para almacenar los bytes entrantes (4bytes = 32bits)
					DatagramPacket response = new DatagramPacket(response_bytes, 4); //variable para almacenar el datagrama entrante
					socket.receive(response); //Recibir datagrama
				
					StringBuilder sb = new StringBuilder(response_bytes.length * Byte.SIZE); //Analisis de la respuesta
				    for( int i = 0; i < Byte.SIZE * response_bytes.length; i++ )             
				        sb.append((response_bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1'); //Se convierte en una cadena en base 2
				    	String binario = sb.toString();
			  
				    Long segundos = Long.parseLong(binario, 2);  //Conversion de la cadena binaria a entero
 				    Date currentTime = new Date((segundos - EPOCH_GAP) * 1000); //Generar fecha actual
 				    System.out.println("Enviando cadena a " + address );
 				    System.out.println("Puerto 37, protocolo TP");
 				    System.out.println(segundos + " segundos transcurridos");
 				    System.out.println("Fecha actual: " + currentTime);	
					
					if (segundos != 0){
						fin = true;
					} 
				
			} while (!fin);		
				
			
		
		
		}
		catch (Exception e) { //Control de la excepcion
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}