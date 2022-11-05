/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;

/**
 *
 * @author Teresa Romero
 */
public class Client {

    public static ArrayList<ArrayList<Integer>> readData() throws Throwable {
        Frame[] frame = null;
        BITalino bitalino = null;
        ArrayList<Integer> analogECGData = new ArrayList<Integer>();
        ArrayList<Integer> analogEMGData = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> analogData = new ArrayList<ArrayList<Integer>>();

        try {
            bitalino = new client.BITalino();
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
            for (int j = 0; j < 100; j++) {

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
        } catch (client.BITalinoException ex) {
            Logger.getLogger(client.BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (client.BITalinoException ex) {
                Logger.getLogger(client.BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        analogData.add(analogEMGData);
        analogData.add(analogECGData);
        return analogData;
    }

    public static String select() {
        System.out.println("Read data? Y/N");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String confirm = null;
        try {
            confirm = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return confirm;
    }

    private static void releaseResources(DataInputStream in, DataOutputStream out, Socket socket) {
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws Throwable {

        final String HOST = "127.0.0.1";//loopback
        final int PUERTO = 6000;
        String userName = "username";
        DataInputStream in;
        DataOutputStream out;
        try {
            Socket sc = new Socket(HOST, PUERTO);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Ready to receive data
            String message = in.readUTF();
            System.out.println(message);

            String confirm = select();
            if ("Y".equals(confirm)) {
                System.out.println("Proceeding to read data from BITalino");
                ArrayList<ArrayList<Integer>> analogData = readData();
                //Hay que coger el username
                //Se podrían convertir todos los datos a una clase y enviarlos como un objeto
                out.writeUTF(userName);
                out.writeUTF(new java.util.Date().toString());
                out.writeLong(analogData.get(0).size());
                //Get EMG data and send to server
                for (int i = 0; i < analogData.get(0).size(); i++) {
                    out.writeInt(analogData.get(0).get(i));
                }
                //Get ECG data and send to server
                out.writeLong(analogData.get(1).size());
                for (int i = 0; i < analogData.get(1).size(); i++) {
                    out.writeInt(analogData.get(1).get(i));
                }
            } else {
                releaseResources(in, out, sc);
            }
            releaseResources(in, out, sc);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
