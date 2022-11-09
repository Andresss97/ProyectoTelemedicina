/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import Interfaz.HomeClients;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;
import pojos.Doctor;
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
    
    public void registerUser(String[] data) {
        try {
            this.oos.writeObject(data);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listenForMessage() {
        System.out.println("Hola");
        while (socket.isConnected()) {
            try {
                Object object = ois.readObject();
                System.out.println("Llego aqui con la info");
                if (object instanceof Patient) {
                    System.out.println("El paciente llego");
                    HomeClients.p = (Patient) object;
                    HomeClients.patientsView = true;
                    return;
                } else if (object instanceof Doctor) {
                    System.out.println("El doctor llego llego");
                    HomeClients.d = (Doctor) object;
                    HomeClients.doctorsView = true;
                    return;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
