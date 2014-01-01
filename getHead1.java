import java.net.*;
import java.io.*;

public class getHead1 {

	public static void main(String argv[]) {
		//Declaracion de variables
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String host="";
		String page="";
		Socket socket;
		String mensaje="";
		String NL = System.getProperty( "line.separator" );
		StringBuilder sb = new StringBuilder();
		String respuesta = "";
		PrintStream out;
		
		try{//Inicio del código que puede generar excepciones

			//Solicita al usuario el host y recurso del que obtener la cabecera
			System.out.println("Introduzca el nombre del servidor:  (ejemplo: www.uoc.edu)");
			host = in.readLine();
			System.out.println("Introduzca la ruta de la pagina solicitada:  (ejemplo: /portal/ca/index.html)");
			page = in.readLine();
			
			//Crear el mensaje de petición HTTP
			sb.append("HEAD " + page + " HTTP/1.1");
			sb.append(NL);
			sb.append("Host: " + host);
			sb.append(NL);
			mensaje=sb.toString();
			System.out.println(mensaje);//Muestra por la salida estandar el comando formado
			
			
			socket = new Socket(InetAddress.getByName(host), 80); //Inicia una peticion de conexion al host, puerto 80
			out = new PrintStream(socket.getOutputStream()); //Crea flujo saliente de bytes
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));//Crea flujo de entrada de bytes
			out.println(mensaje);//Enviar mensaje al servidor solicitado
			
			while ((respuesta = socketIn.readLine()) != null) { //Mientras se reciba respuesta
			    System.out.println(respuesta); //Mostrar por la salida estandar cada línea de respuesta
			}
		
		}
		catch (Exception e) {//Control de excepcion
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
