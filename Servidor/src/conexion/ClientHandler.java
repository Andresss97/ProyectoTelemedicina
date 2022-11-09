/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import creation.QuerysInsert;
import creation.QuerysSelect;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Doctor;
import pojos.Patient;
import server.MeasuredData;

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
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            this.clients.add(this);
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
        while(this.socket.isConnected()) {
            Patient patient;
            Doctor doctor;
            
            try {
                
               Object tmp;
               QuerysInsert qi = new QuerysInsert();
               QuerysSelect qs = new QuerysSelect();
               tmp = ois.readObject();
               
               if(tmp instanceof Patient) {
                   patient = (Patient) tmp;
                   this.patient = patient;
                   qi.insertUserType(patient.getUsername(), patient.getPassword(), 1);
               }
               else if(tmp instanceof MeasuredData) {
                    System.out.println("I arrived safely");
                    MeasuredData dataPatient = (MeasuredData) tmp;
                    System.out.println(dataPatient.toString());
               }
               else if(tmp instanceof String[]){
                   String []data = (String[]) tmp;
                   int[] values = qs.selectUser2(data[0], data[1]);
                   
                   if(values[0] != 0 && values[1] == 1) {
                       this.patient = qs.selectPatientByID(values[1]);
                       this.doctor = null;
                       System.out.println("Llego aqui paciente");
                       this.oos.writeObject(this.patient);
                       
                   }
                   else if(values[0] != 0 && values[1] == 2) {
                       this.patient = null;
                       this.doctor = qs.selectDoctorByID(values[1]);
                       System.out.println("Llego aqui doctor");
                       this.oos.writeObject(this.doctor);
                   }
               }
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }    
}
