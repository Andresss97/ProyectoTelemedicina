/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author andre
 */
public class Client {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    public Client(Socket socket) {
        this.socket = socket;
    }
    
    
}
