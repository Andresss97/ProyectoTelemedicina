/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import pojos.Doctor;
import pojos.Patient;

/**
 *
 * @author andre
 */
public class ClientHandler implements Runnable{
    
    public static ArrayList<ClientHandler> clients = new ArrayList();
    private Patient patient;
    private Doctor doctor;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            
        }
        catch(Exception ex) {
            
        }
    } 
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public void run() {
        
    }
    
}
