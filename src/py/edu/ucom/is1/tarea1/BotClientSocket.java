package py.edu.ucom.is1.tarea1;

import java.io.*;
import java.net.*;
import java.util.*;

/***
 * Cliente socket para comunicaci�n constante BIDIRECCIONAL
 * 
 * @author Eduardo Morales
 * 
 *         Nos conectamos al servidor y seguimos enviando mensajes hasta que se
 *         cumpla una condici�n de cierre de conexi�n. En este caso la
 *         comunicaci�n con el servidor es turno a turno es decir, luego de
 *         enviar un mensaje, el cliente aguarda respuesta del servidor.
 * 
 */

class BotClientSocket {

	
	public static void main(String[] args) {
		//establecemos conexion
		try (Socket socket = new Socket("localhost", 5000)) {

			//canal de salida hacia el servidor
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			//canal de entrada desde el servidor
			DataInputStream in = new DataInputStream(socket.getInputStream());

			// input del teclado
			Scanner entradaConsola = new Scanner(System.in);
			String line = null;

			while (!"fin".equalsIgnoreCase(line)) {
				System.out.println(">" + in.readUTF());
				//lectura de teclado
				line = entradaConsola.nextLine();

				// enviamos al servidor 
				out.writeUTF(line);
				out.flush();

				//imprimimos la respuesta
				
			}

			// cerramos conexion
			entradaConsola.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}