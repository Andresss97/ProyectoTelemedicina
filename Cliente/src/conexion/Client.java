/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;
import pojos.Patient;
import server.BITalino;
import server.Frame;
import server.MeasuredData;

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
        try {
            this.oos = new ObjectOutputStream(this.socket.getOutputStream()); 
            this.ois = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void registerPatient(Patient patient) {
        try {
            this.oos.writeObject(patient);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerData(MeasuredData data) {
        ArrayList<Integer> EMGData = new ArrayList<Integer>();
        ArrayList<Integer> ECGData = new ArrayList<Integer>();
    }
    
    public static ArrayList<ArrayList<Integer>> readData() throws Throwable {
        Frame[] frame = null;
        BITalino bitalino = null;
        ArrayList<Integer> analogECGData = new ArrayList<Integer>();
        ArrayList<Integer> analogEMGData = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> analogData = new ArrayList<ArrayList<Integer>>();

        try {
            bitalino = new server.BITalino();
            // find devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            String macAddress = "98:D3:11:FD:1E:CC";
            int SamplingRate = 10;
            bitalino.open(macAddress, SamplingRate);

            // start acquisition on analog channels A2 and A6
            //If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {0, 1};
            bitalino.start(channelsToAcquire);

            //read 10000 samples
            for (int j = 0; j < 5; j++) {

                //Read a block of 100 samples
                frame = bitalino.read(10);

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    analogEMGData.add(frame[i].analog[0]);
                    analogECGData.add(frame[i].analog[1]);
                }
            }
            //stop acquisition
            bitalino.stop();
        } catch (server.BITalinoException ex) {
            Logger.getLogger(server.BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (server.BITalinoException ex) {
                Logger.getLogger(server.BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        analogData.add(analogEMGData);
        analogData.add(analogECGData);
        return analogData;
    }
    
    private static void releaseResources(ObjectOutputStream objectOS, Socket socket) {
        try {
            objectOS.close();
        } catch (IOException ex) {
            Logger.getLogger(server.Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(server.Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
