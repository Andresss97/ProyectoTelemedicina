package conexion;

import java.net.Socket;

public class ClienteCon {
	private Socket socket;
	
	public ClienteCon(Socket socket) {
		this.socket = socket;
	}
	
	public void listenForMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		}).start();
	}
}
