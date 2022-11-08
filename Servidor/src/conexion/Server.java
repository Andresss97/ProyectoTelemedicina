/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andre
 */
public class Server {
    private ServerSocket serverSocket;
    private final int PUERTO = 6000;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public void startServer() {
        try  {
            while(!this.serverSocket.isClosed()) {
                System.out.println("Esperando clientes");
                Socket socket = this.serverSocket.accept();
                System.out.println("Se ha conectado un cliente");
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch(Exception ex) {
            
        }
    
    }
    
    public void closeServerSocket() {
        try {
            if(this.serverSocket != null) {
                this.serverSocket.close();
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
