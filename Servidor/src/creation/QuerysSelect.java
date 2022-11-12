/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import Interfaz.HomeServer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
    public Patient selectPatientByID(int id){
        String query = "SELECT * from Patient where id = ?";
        Patient patient = new Patient();
        try
        {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setInt(1, id);
            ResultSet set = st.executeQuery();        
            
            patient.setUsername(set.getString("USERNAME"));
            patient.setPassword(set.getString("PASSWORD"));
            patient.setName(set.getString("NAME"));
            patient.setAddress(set.getString("ADDRESS"));
            patient.setDob(set.getDate("DOB"));
            patient.setPhoneNumber(set.getInt("PHONE"));
            patient.seteMail(set.getString("EMAIL"));
            st.close();
            set.close();  
        }
        catch (SQLException e) 
        {
                e.printStackTrace();
        }        
              
        return patient;
    }
    
    public ArrayList <Patient> selectPatients(){
        String query = "SELECT * from Patient";
        Patient patient = new Patient();
        ArrayList <Patient> patientList = null;
        try
        {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            ResultSet set = st.executeQuery();  
            while(set.next())
            {
                patient.setUsername(set.getString("USERNAME"));
                patient.setPassword(set.getString("PASSWORD"));
                patient.setName(set.getString("NAME"));
                patient.setAddress(set.getString("ADDRESS"));
                patient.setDob(set.getDate("DOB"));
                patient.setPhoneNumber(set.getInt("PHONE"));
                patient.seteMail(set.getString("EMAIL"));
                patientList.add(patient);
            }
            st.close();
            set.close();  
        }
        catch (SQLException e) 
        {
                e.printStackTrace();
        }        
              
        return patientList;
    }
    
    
    public MedicalHistory getMedicalHistory(int id){
        MedicalHistory mh = new MedicalHistory();
        try
        {
            String query = "SELECT MHID from PATIENT where id = ?";
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setInt(1, id);
            ResultSet set = st.executeQuery();        
            Integer mhid = set.getInt("MHID");
            st.close();
            set.close(); 
            String querymh = "SELECT * from MEDICALHISTORY where id = ?";
            PreparedStatement ps = conn.getConnect().prepareStatement(querymh);
            ps.setInt(1, mhid);
            ResultSet result = ps.executeQuery(); 
            mh.setName(set.getString("NAME"));
            mh.setDOB(set.getDate("DOB"));
            mh.setDiseases(result.getString("DISEASES"));
            mh.setAllergies(result.getString("ALLERGIES"));
            mh.setSurgeries(result.getString("SURGERIES"));
            mh.setWeightKg(result.getFloat("WEIGHTKG"));
            mh.setHeightCm(result.getInt("HEIGHTCM"));
            ps.close();
            result.close();  
        }
        catch (SQLException e) 
        {
                e.printStackTrace();
        }                 
        return mh;
    }
    
    public ArrayList<DailyRegister> getDailyRegisters(int pid)throws SQLException {
        ArrayList<DailyRegister> dr = new ArrayList<DailyRegister> ();
        String query = "SELECT * from DAILYREGISTER where IDPATIENT = ?";
        try
        {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                Integer id = rs.getInt("ID");
                Date day = rs.getDate("DAY");
                String symptoms = rs.getString("SYMPTOMS");
                String EMG = rs.getString("EMG");
                String ECG = rs.getString("ECG");
                dr.add(new DailyRegister(id,day,symptoms,EMG,ECG));
            }
            st.close(); 
        }
        catch (SQLException e) 
        {
                e.printStackTrace();
        }       
        return dr;    
    }
    
    
    
    public Doctor selectDoctorByID(int id)  {
        String query = "SELECT * from Doctor where id = ?";
        PreparedStatement st;
        Doctor doctor = new Doctor();
        try {
            st = conn.getConnect().prepareStatement(query); 
            ResultSet set = st.executeQuery();
            doctor.setUsername(set.getString("USERNAME"));
            doctor.setPassword(set.getString("PASSWORD"));
            st.close();
            set.close();
        } 
        
        catch (SQLException e) 
        {
                e.printStackTrace();
        }       
        return doctor;
    }
    public int getLastId() 
    {
        int id = 0;
        try 
        {
            String query = "SELECT last_insert_rowid() AS lastId";
            PreparedStatement p = conn.getConnect().prepareStatement(query);
            ResultSet rs = p.executeQuery();
            id = rs.getInt("lastId");
            p.close();

        }
        catch (SQLException e) 
        {
                e.printStackTrace();
        }
        return id;
    }
    
    public Doctor selectDoctorByUsername(String u) throws SQLException {
        String query = "SELECT * FROM DOCTOR where USERNAME = ?";
        PreparedStatement st = this.conn.getConnect().prepareStatement(query);
        st.setString(1, u);
        ResultSet rs = st.executeQuery();
        Doctor doctor = new Doctor();
        doctor.setUsername(rs.getString("username"));
        doctor.setPassword(rs.getString("password"));
        doctor.setId(rs.getInt("ID"));
        
        return doctor;
    }
}
