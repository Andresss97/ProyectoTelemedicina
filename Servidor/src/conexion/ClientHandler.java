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
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.DailyRegister;
import pojos.Doctor;
import pojos.Patient;

/**
 *
 * @author andre
 */
public class ClientHandler implements Runnable {

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
        } catch (Exception ex) {

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
        while (this.socket.isConnected()) {
            Patient patient;
            Doctor doctor;

            try {

                Object tmp;
                QuerysInsert qi = new QuerysInsert();
                QuerysSelect qs = new QuerysSelect();
                tmp = ois.readObject();

                if (tmp instanceof Patient) {
                    patient = (Patient) tmp;
                    this.patient = patient;
                    qi.insertUserType(patient.getUsername(), patient.getPassword(), 1);
                    qi.insertPatientComplete(patient);
                } else if (tmp instanceof DailyRegister) {
                    System.out.println("I arrived safely");
                    DailyRegister dataPatient = (DailyRegister) tmp;
                    System.out.println(dataPatient.toString());
                    qi.insertData(dataPatient);
                } else if (tmp instanceof String[]) {
                    String[] data = (String[]) tmp;

                    int[] values = qs.selectUser2(data[0], data[1]);

                    if (values[0] != 0 && values[1] == 1) {
                        this.patient = qs.selectPatientByID(values[1]);
                        this.patient.setDailyRegisters(qs.getDailyRegisters(this.patient.getId()));
                        this.doctor = null;
                        System.out.println("Llego aqui paciente");
                        this.oos.writeObject(this.patient);

                    } else if (values[0] != 0 && values[1] == 2) {
                        this.patient = null;

                        //this.doctor = qs.selectDoctorByUsername(data[0]);
                        //ArrayList<Patient> p = qs.selectPatients();
                        //this.doctor.setPatients(p);
                        this.oos.writeObject(this.doctor);
                        System.out.println("Llego aqui doctor");
                    } else if (tmp instanceof Integer) {
                        System.out.println("Pacientes pedidos");
                        ArrayList<Patient> patients = qs.selectPatients();
                        this.oos.writeObject(patients);
                        return;
                    }
                }

            } catch (IOException ex) {
                try {
                    this.socket.close();
                    this.oos.close();
                    this.ois.close();
                    clients.remove(this);
                    System.out.println("El cliente se desconecto");
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
