package conexion;

import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Dictionary;

import creacion.QuerysInsert;
import creacion.QuerysSelect;
import pojos.*;

public class ClientHandler implements Runnable {
	public static Dictionary<ClientHandler, Cliente> clientes;
	private Socket socket;
	private ObjectOutputStream buffW;
	private ObjectInputStream buffR;
	private Cliente cliente;
	
	public ClientHandler(Socket socket) {
		QuerysSelect qs = new QuerysSelect();
		
		try {
			this.socket = socket;
			this.buffW = new ObjectOutputStream(socket.getOutputStream());
			this.buffR= new ObjectInputStream(socket.getInputStream());
			
			String user = buffR.readUTF();
			String pass = buffR.readUTF();
			
			int iSec = qs.checkSecurityLevel(user, pass);
			
			if(iSec == 1) {
				String info[] = new String[2];
				info[0] = user;
				info[1] = pass;
				Patient p = qs.selectPatient(info);
				this.cliente.setPatient(p);
				
				buffW.writeObject(p);
				
			}
			else if(iSec == 2) {
				String info[] = new String[2];
				info[0] = user;
				info[1] = pass;
				Doctor d = qs.selectDoctor(info);
				this.cliente.setDoctor(d);
				
				buffW.writeObject(d);
			}
			else {
				buffW.writeUTF("Usuario o contraseña incorrectos");
				return;
			}
			
			clientes.put(this, cliente);
		}
		catch(Exception ex) {
			
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		while(this.socket.isConnected()) {

		}
	}

}
