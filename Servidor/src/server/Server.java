/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teresa Romero
 */
public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = null;
        Socket sc = null; //socket del cliente
        InputStream is = null;
        ObjectInputStream objectIS = null;
        final int PUERTO = 6000;

        try {
            server = new ServerSocket(PUERTO);
            //System.out.println("Servidor iniciado");

            while (true) { //siempre a la espera de los clientes
                sc = server.accept(); //accept = esperar
                is = sc.getInputStream();

                objectIS = new ObjectInputStream(is);
                //Object tmp;
                while (objectIS.readObject() != null) {
                    System.out.println("I arrived safely");
                    MeasuredData dataPatient = (MeasuredData) objectIS.readObject();
                    System.out.println(dataPatient.toString());
                }
                sc.close();
                System.out.println("Disconnected patient, ready to receive more patients");

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
