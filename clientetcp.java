import java.net.*;
import java.io.*;

public class clientetcp {

	public static void main(String argv[]) {
		//Verifica que se invoca la aplicación pasando la dirección IP como argumento
		if (argv.length == 0) {
			System.err.println("java clientetcp servidor");
			System.exit(1);
		}

		//Declaración de variables
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Socket socket;
		InetAddress address;
		byte[] mensaje_bytes = new byte[256];
		String mensaje="";

		try {//Inicio del código que puede generar excepciones

			address=InetAddress.getByName(argv[0]); //Extrae la dirección ip del parametro usado para invocar
			socket = new Socket(address, 6001); //Inicia una petición de conexión a la direccion ip, puerto 6001
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());//Crea flujo saliente de bytes

			do {//Inicio blucle
				mensaje = in.readLine(); //lee linea de la entrada estandar
				out.writeUTF(mensaje); //Codifica como unicode
			} while (!mensaje.startsWith("end"));//Mantiene bucle hasta que se escribe "end"
		}
		catch (Exception e) {//Control de excepción
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
