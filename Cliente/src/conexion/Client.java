/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import Interfaz.DoctorsView;
import Interfaz.HomeClients;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;
import pojos.DailyRegister;
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
    
    public void registerData(DailyRegister d) throws IOException {
        this.oos.writeObject(d);
    }
    
    public void requestPatients() throws IOException {
        int i = 1;
        this.oos.writeObject(i);
    }
    
    public void listenForMessage() {
        while (socket.isConnected()) {
            try {
                Object object = ois.readObject();
                if (object instanceof Patient) {
                    HomeClients.p = (Patient) object;
                    HomeClients.patientsView = true;
                    return;
                } else if (object instanceof Doctor) {
                    HomeClients.d = (Doctor) object;
                    HomeClients.doctorsView = true;
                    return;
                }
                else {
                    List<Patient> patients = (ArrayList<Patient>) object;
                    DoctorsView.patients.addAll(patients);
                    System.out.println("Pacientes han llegado");
                    System.out.println(patients);
                    return;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void closeResources() throws IOException {
        this.socket.close();
        this.oos.close();
        this.ois.close();
    }
}
