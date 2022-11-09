/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Interfaz.HomeServer;
import pojos.*;

/**
 *
 * @author andre
 */
public class QuerysInsert {
    private Conector conn = (Conector) HomeServer.conector;
    
    QuerysSelect qs = new QuerysSelect();
    public void insertAdmin(String user, String password) {
        String query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
        try
        {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setString(1, user);
            st.setString(2, password);
            st.setInt(3, 3);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
             e.printStackTrace();
        }
    }
    
    public void insertDoctorCredentials(String user, String password)  {
        String query = "INSERT into doctor (username, password)"
                + "     values (?,?)";
        try
        {
            PreparedStatement st = this.conn.getConnect().prepareStatement(query);
            st.setString(1,user);
            st.setString(2,password); 
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
             e.printStackTrace();
        }
        
    }
    
     public void insertDoctorComplete(Doctor doc) {
        String query = "INSERT into DOCTOR (name,address,dob,phone,email,specialty,salary)"
                + "     values (?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement st = this.conn.getConnect().prepareStatement(query);
            st.setString(1,doc.getName());
            st.setString(2,doc.getAddress());
            st.setDate(3,doc.getDob());
            st.setInt(4, doc.getPhoneNumber());
            st.setString(5, doc.geteMail());
            st.setString(6, doc.getSpeciality());
            st.setDouble(7, doc.getSalary());	      
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
             e.printStackTrace();
        }
        
    }
     
    public void insertPatientCredentials(String user, String password) {
        String query = "INSERT into PATIENT (username, password,name,address,dob,phone,email,specialty,salary)"
                + "     values (?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement st = this.conn.getConnect().prepareStatement(query);
            st.setString(1,user);
            st.setString(2,password); 
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
             e.printStackTrace();
        }
        
    }
    
    public void insertPatientComplete(Patient pat){
        String query = "INSERT into PATIENT (name,address,dob,phone,email)"
               + "     values (?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement st = this.conn.getConnect().prepareStatement(query);
            st.setString(1,pat.getName());
            st.setString(2,pat.getAddress());
            st.setDate(3,pat.getDob());
            st.setInt(4, pat.getPhoneNumber());
            st.setString(5, pat.geteMail());
            st.executeUpdate();
            st.close();
        }
       catch (SQLException e)
        {
             e.printStackTrace();
        }
       
       }
    public void addPatientandMedicalHistory (Patient p,MedicalHistory mh)
    {
        try
        {
            String sqlMedicalHistory="INSERT INTO medicalHistory (Name,DOB,Diseases,Allergies,Surgeries,WeightKg,HeightCm)"
                            +  "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps=this.conn.getConnect().prepareStatement(sqlMedicalHistory);
            ps.setString(1,mh.getName());
            ps.setDate(2,mh.getDOB());
            ps.setString(3,mh.getDiseases());
            ps.setString(4,mh.getAllergies());
            ps.setString(5, mh.getSurgeries());
            ps.setFloat(6,mh.getWeightKg());
            ps.setInt(7,mh.getHeightCm());
            ps.executeUpdate();
            ps.close();
            int mhid=qs.getLastId();
            String sqlpatient="INSERT INTO patient (MHID) WHERE ID=? "
                            +  "VALUES (?,?)";
            PreparedStatement prep = this.conn.getConnect().prepareStatement(sqlpatient);  
            prep.setInt(1, mhid);
            prep.setInt(1, p.getId());                
            prep.executeUpdate();
            prep.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void addDoctorToPatient(Patient pat, Doctor doc) 
    {
        try
        {
            String sql = "INSERT INTO PATIENTDOCTOR (PATID, DOCID)"
                        +"VALUES (?,?)";
            PreparedStatement ps = this.conn.getConnect().prepareStatement(sql);
            ps.setInt(1, pat.getId());
            ps.setInt(2, doc.getId());
            ps.executeUpdate();
            ps.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void addPatientDailyRegister (Patient p,DailyRegister dr)
    {
        try
        {
            String sqlDailyRegister="INSERT INTO DAILYREGISTER (day,SYMPTOMS,EMG,ECG,IDPATIENT)"
                            +  "VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conn.getConnect().prepareStatement(sqlDailyRegister);
            ps.setDate(1,dr.getDay());
            ps.setString(2,dr.getSymptoms());
            ps.setString(3,dr.getEMG());
            ps.setString(4, dr.getECG());
            ps.setInt(5,p.getId());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e)
        {
             e.printStackTrace();
        }
    }
   
    
    public void insertUserType(String user, String password, int type){
        String query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
        try
        {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setString(1, user);
            st.setString(2, password);
            st.setInt(3, type);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException e)
        {
             e.printStackTrace();
        }
    }
}
