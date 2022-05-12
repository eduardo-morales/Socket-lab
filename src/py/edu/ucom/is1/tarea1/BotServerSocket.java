package py.edu.ucom.is1.tarea1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * Servidor socket para comunicaci�n constante UNIDIRECCIONAL
 * 
 * @author Eduardo Morales
 * 
 *         Servidor se queda esperando mensajes hasta recibir comando de
 *         finalizaci�n
 *
 */

public class BotServerSocket {
	Socket socket = null;
	ServerSocket serverSocket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	Integer estado = 0;

	public BotServerSocket() {
		try {
			serverSocket = new ServerSocket(5000); // INICIAMOS SERVIDOR
			System.out.println("Server iniciado");

		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public void iniciar() throws IOException {

		try {
			// Esperamos clientes y cuando aceptemos uno
			// ese canal de comunicaci�n ser� la instancia "socket"
			socket = serverSocket.accept();
			System.out.println("Cliente aceptado");
			// leeremos del canal de entrada hasta que se tenga el comando de finalizacion
			String inputString = "";
			String ouputString = "";

			// recuperamos el canal de ENTRADA del socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());

			while (inputString != null) {
				try {

					ouputString = generarRespuesta(inputString);
					out.writeUTF(ouputString);
					if (estado == -1)
						break;
					inputString = in.readUTF(); // esperamos leer mensaje del cliente

				} catch (IOException i) {
					System.out.println(i);
				}
			}

			System.out.println("Cerrando conexion");
			// close connection
			socket.close();
			in.close();
		} catch (Exception e) {
			// close connection
			socket.close();
			in.close();
		}

	}

	public String generarRespuesta(String respuestaCliente) {
		String cedulaValida = "123456";
		String respuestaBot = "";
		String reclamo = "";
		switch (estado) {
		case 0: {
			estado = 1;
			respuestaBot = "Bienvenido a SocketCEL, favor ingrese número de cédula:";
			break;
		}
		case 1: {
			if (respuestaCliente.equalsIgnoreCase(cedulaValida)) {
				respuestaBot = "Bienvenido XXXX, en qué podemos ayudarte (ingrese número de opción):\r\n"
						+ "1 - Consultar factura\r\n" + "2- Realizar reclamo\r\n" + "3- Salir";
				estado = 2;
			} else {
				respuestaBot = "Disculpe, ingrese de nuevo número de cedula:";
			}

			break;
		}
		case 2: { // opcion de menu
			switch (Integer.parseInt(respuestaCliente)) {
			case 1: {
				respuestaBot = "Ingrese número de cuenta: ";
				estado = 31;
				break;
			}
			case 2: {
				respuestaBot = "Ingrese reclamo: ";
				estado = 32;
				break;
			}
			case 3: {
				respuestaBot = "Gracias por su consulta ";
				estado = 33;
				estado = -1;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + Integer.parseInt(respuestaCliente));
			}
			break;
		}
		case 31: {
			respuestaBot = "La cuenta pendiente de <la línea> es 150.000 gs. Gracias por su consulta";
			estado = -1;
			break;
		}
		case 32: {
			respuestaBot = "Gracias, hemos registrado su reclamo: " + respuestaCliente;
			estado = -1;
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + estado);
		}
		return respuestaBot;

	}

	// constructor with port
	public static void main(String args[]) throws IOException {

		BotServerSocket bot = new BotServerSocket();
		bot.iniciar();

	}

}