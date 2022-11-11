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
        try {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setString(1, user);
            st.setString(2, password);
            st.setInt(3, 3);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertData(DailyRegister data) {
        String query =  "INSERT into dailyRegister (day,symptoms,emg, ecg, idpatient) values (?,?,?,?,?)";
                try {
            PreparedStatement st = conn.getConnect().prepareStatement(query);
            st.setDate(1, data.getDay());
            st.setString(2, data.getSymptoms());
            st.setString(3, data.getEMG());
            st.setString(4, data.getECG());
            st.setInt(5, data.getIdPatient());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertDoctorCredentials(String user, String password) {
        String query = "INSERT into doctor (username, password)"
                + "     values (?,?)";
        try {
            PreparedStatement st = this.conn.getConnect().prepareStatement(query);
            st.setString(1, user);
            st.setString(2, password);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertDoctor(String username, String password) {
        String query = "INSERT into DOCTOR (username, password) values(?,?)";
        try {
            PreparedStatement st = this.conn.getConnect().prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
            st.close();
        } catch (Exception ex) {

        }
    }

    public void insertDoctorComplete(Doctor doc) throws SQLException {
        String query = "INSERT into DOCTOR (name,address,dob,phone,email,specialty,salary,username, password)"
                + "     values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = this.conn.getConnect().prepareStatement(query);
        st.setString(1, doc.getName());
        st.setString(2, doc.getAddress());
        st.setDate(3, doc.getDob());
        st.setInt(4, doc.getPhoneNumber());
        st.setString(5, doc.geteMail());
        st.setString(6, doc.getSpeciality());
        st.setDouble(7, doc.getSalary());
        st.setString(8,doc.getUsername());
        st.setString(9, doc.getPassword());
        st.executeUpdate();
        st.close();
    }

    public void insertPatientComplete(Patient pat) throws SQLException {
        String query = "INSERT into PATIENT (name,address,dob,phone,email,username, password)"
                + "     values (?,?,?,?,?,?,?)";
        PreparedStatement st = this.conn.getConnect().prepareStatement(query);
        st.setString(1, pat.getName());
        st.setString(2, pat.getAddress());
        st.setDate(3, pat.getDob());
        st.setInt(4, pat.getPhoneNumber());
        st.setString(5, pat.geteMail());
        st.setString(6,pat.getUsername());
        st.setString(7,pat.getPassword());
        st.executeUpdate();
        st.close();
    }

    public void addPatientandMedicalHistory(Patient p, MedicalHistory mh) throws SQLException {
        String sqlMedicalHistory = "INSERT INTO medicalHistory (Name,DOB,Diseases,Allergies,Surgeries,WeightKg,HeightCm)"
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = this.conn.getConnect().prepareStatement(sqlMedicalHistory);
        ps.setString(1, mh.getName());
        ps.setDate(2, mh.getDOB());
        ps.setString(3, mh.getDiseases());
        ps.setString(4, mh.getAllergies());
        ps.setString(5, mh.getSurgeries());
        ps.setFloat(6, mh.getWeightKg());
        ps.setInt(7, mh.getHeightCm());
        ps.executeUpdate();
        ps.close();
        int mhid = qs.getLastId();
        String sqlpatient = "INSERT INTO patient (MHID) WHERE ID=? "
                + "VALUES (?,?)";
        PreparedStatement prep = this.conn.getConnect().prepareStatement(sqlpatient);
        prep.setInt(1, mhid);
        prep.setInt(1, p.getId());
        prep.executeUpdate();
        prep.close();

    }

    public void addDoctorToPatient(Patient pat, Doctor doc) throws SQLException {
        String sql = "INSERT INTO PATIENTDOCTOR (PATID, DOCID)"
                + "VALUES (?,?)";
        PreparedStatement ps = this.conn.getConnect().prepareStatement(sql);
        ps.setInt(1, pat.getId());
        ps.setInt(2, doc.getId());
        ps.executeUpdate();
        ps.close();
    }

    public void addPatientDailyRegister(Patient p, DailyRegister dr) throws SQLException {
        String sqlDailyRegister = "INSERT INTO DAILYREGISTER (day,SYMPTOMS,EMG,ECG,IDPATIENT)"
                + "VALUES (?,?,?,?,?)";
        PreparedStatement ps = this.conn.getConnect().prepareStatement(sqlDailyRegister);
        ps.setDate(1, dr.getDay());
        ps.setString(2, dr.getSymptoms());
        ps.setString(3, dr.getEMG());
        ps.setString(4, dr.getECG());
        ps.setInt(5, p.getId());
        ps.executeUpdate();
        ps.close();
    }

    public void insertUserType(String user, String password, int type) throws SQLException {
        String query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
        PreparedStatement st = conn.getConnect().prepareStatement(query);
        st.setString(1, user);
        st.setString(2, password);
        st.setInt(3, type);
        st.executeUpdate();
        st.close();
    }
}
