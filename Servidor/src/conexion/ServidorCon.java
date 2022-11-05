package conexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCon {
	private final ServerSocket socket;
	
	public ServidorCon(ServerSocket socket) {
		this.socket = socket;
	}
	
	public void startServer() {
		try {
			while(!socket.isClosed()) {
				System.out.println("Waiting for clients");
				Socket socket = this.socket.accept();
				System.out.println("New client connected");
				ClientHandler clientHandler = new ClientHandler(socket);
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}
		catch(Exception ex) {
			System.out.println("Error al conectar con el cliente");
			try {
				this.socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void closeServer() {
		try {
			if(this.socket != null) {
				this.socket.close();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
