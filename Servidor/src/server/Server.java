/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import plotECG.PlotECG;

/**
 *
 * @author Teresa Romero
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        Socket sc = null; //socket del cliente
        DataInputStream in;
        DataOutputStream out; //
        ObjectInputStream ob;
        ArrayList<Integer> receivedEMGData = new ArrayList<Integer>();
        ArrayList<Integer> receivedECGData = new ArrayList<Integer>();
        int sampling = 10;

        final int PUERTO = 6000;
        try {
            server = new ServerSocket(PUERTO);
            //System.out.println("Servidor iniciado");

            while (true) { //siempre a la espera de los clientes
                sc = server.accept(); //accept = esperar
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                out.writeUTF("Ready to receive data");
                //Get EMG data
                String patient = in.readUTF();
                String dateTime = in.readUTF();
                long sizeEMG = in.readLong();
                System.out.println(sizeEMG);
                for (long i = 0; i < sizeEMG; i++) {
                    receivedEMGData.add(in.readInt());
                }
                System.out.println(patient + "\n" + dateTime + "\n");
                for (int data = 0; data < receivedEMGData.size(); data++) {
                    System.out.println(receivedEMGData.get(data));
                }
                //Get ECG data
                long sizeECG = in.readLong();
                System.out.println(sizeECG);
                for (long i = 0; i < sizeECG; i++) {
                    receivedECGData.add(in.readInt());
                }
                for (int data = 0; data < receivedECGData.size(); data++) {
                    System.out.println(receivedECGData.get(data));
                }
                //PlotECG newPlot = new PlotECG(receivedData, sampling);
                //newPlot.plotData();
                //Falta hacer la conexión a la base de datos
                sc.close();
                System.out.println("Disconnected patient, ready to receive more patients");

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
