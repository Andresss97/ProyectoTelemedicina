/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import Interfaz.HomeServer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.Doctor;
import pojos.Patient;

/**
 *
 * @author andre
 */
public class QuerysSelect {
    private Conector conn = (Conector) HomeServer.conector;
    
    public int selectUser(String user, String psw) throws SQLException {
        String[] data = new String[2];
        String query = "SELECT ID from mappinglogin where username = '" + user + "' and password = '"
                + psw + "'";
        PreparedStatement st = conn.getConnect().prepareStatement(query);
        ResultSet set = st.executeQuery();
        int id = set.getInt("ID");

        st.close();
        set.close();

        return id;
    }
    
    public int[] selectUser2(String user, String psw) throws SQLException {
        String[] data = new String[2];
        String query = "SELECT ID,USERTYPE from mappinglogin where username = '" + user + "' and password = '"
                + psw + "'";
        PreparedStatement st = conn.getConnect().prepareStatement(query);
        ResultSet set = st.executeQuery();
        int[] values = new int[2];
        values[0] = set.getInt("ID");
        values[1] = set.getInt("USERTYPE");
        st.close();
        set.close();

        return values;
    }
    
    public Patient selectPatientByID(int id) throws SQLException {
        String query = "SELECT * from Patient where id = ?";
        PreparedStatement st = conn.getConnect().prepareStatement(query);
        ResultSet set = st.executeQuery();
        
        Patient patient = new Patient();
        patient.setUsername(set.getString("USERNAME"));
        patient.setPassword(set.getString("PASSWORD"));
        
        st.close();
        set.close();
        
        return patient;
    }
    
        public Doctor selectDoctorByID(int id) throws SQLException {
        String query = "SELECT * from Patient where id = ?";
        PreparedStatement st = conn.getConnect().prepareStatement(query);
        ResultSet set = st.executeQuery();
        
        Doctor doctor = new Doctor();
        doctor.setUsername(set.getString("USERNAME"));
        doctor.setPassword(set.getString("PASSWORD"));
        
        st.close();
        set.close();
        
        return doctor;
    }
}
