/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 *
 * @author andre
 */
public abstract class DBCreation {
    
    public static void createDB(ConnInterface conn) {
        if(conn.getClass().equals(Conector.class)) {
            Conector con = (Conector) conn;
            QuerysInsert qi = new QuerysInsert();
            cTPatient(con);
            cTDoctor(con);
            cTDailyRegister(con);
            cTMappingLogIn(con);
            
            try {
                qi.insertAdmin("admin", "indi");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void cTPatient(Conector con){
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in =  "CREATE TABLE IF NOT EXISTS PATIENT "
                + "(ID          integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "USERNAME     varchar(50) NOT NULL UNIQUE, "
                + "PASSWORD     varchar(50) NOT NULL,"
                + "NAME         varchar(50) NOT NULL,"
                + "ADDRESS      varchar(50),"
                + "DOB          date NOT NULL,"
                + "PHONE        integer,"
                + "EMAIL        varchar(50) NOT NULL,"
                + "MHID         int CONSTRAINT rMedicalHistory REFERENCES MEDICALHISTORY (ID) ON UPDATE CASCADE ON DELETE SET NULL"
                + ")";

            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println("Error tabla pacientes");
        }
    }
    
    private static void cTDoctor(Conector con) {
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in =  "CREATE TABLE IF NOT EXISTS DOCTOR "
                + "(ID          integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "USERNAME     varchar(50) NOT NULL UNIQUE, "
                + "PASSWORD     varchar(50) NOT NULL,"
                + "NAME         varchar(50) NOT NULL,"
                + "ADDRESS      varchar(50),"
                + "DOB          date,"
                + "PHONE        integer NOT NULL,"
                + "EMAIL        varchar(50) NOT NULL,"
                + "SPECIALTY    varchar(50) NOT NULL,"
                + "SALARY       double"
                +")"; 
              
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println("Error tabla doctores");
        }
    }
    private static void cTDailyRegister(Conector con) {
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in =  "CREATE TABLE IF NOT EXISTS DAILYREGISTER"
                + "(ID          integer PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "DAY          date NOT NULL,"
                + "SYMPTOMS     varchar(1024) NOT NULL,"
                + "EMG          varchar(1024) NOT NULL,"
                + "ECG          varchar(1024) NOT NULL,"
                + "IDPATIENT    int CONSTRAINT rPatient REFERENCES PATIENT(ID) ON UPDATE CASCADE ON DELETE SET NULL"
                + ")";
                   
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println("Error tabla registros diarios");
        }
    }
    
    private static void cTDoctorPatient(Conector con) {
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in =  "CREATE TABLE IF NOT EXISTS PATIENTDOCTOR"
                + "(PATID       int CONSTRAINT rPatient REFERENCES PATIENT(ID) ON UPDATE CASCADE ON DELETE SET NULL,"
                + " DOCID       int CONSTRAINT rDoctor REFERENCES DOCTOR(ID) ON UPDATE CASCADE ON DELETE SET NULL"
                + ")";
                   
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println("Error tabla Pacientes-Doctores");
        }
    }
    
    private static void cTMedicalHistory(Conector con) {
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in =  "CREATE TABLE IF NOT EXISTS MEDICALHISTORY "
                + "(ID          integer PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "NAME         varchar(50) NOT NULL,"
                + "DOB          date NOT NULL,"
                + "DISEASES     varchar(1024),"
                + "ALLERGIES    varchar(1024),"
                + "SURGERIES    varchar(1024),"
                + "WEIGHTKG     float NOT NULL,"
                + "HEIGHTCM     integer NOT NULL,"
                + ")";                    
                   
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println("Error tabla tratamientos");
        }
    }
    
    private static void cTMappingLogIn(Conector conn) {
        Conector con = conn;
        Statement st = null;
        String in = null;

        try {
            st = con.getConnect().createStatement();
            in =  "CREATE TABLE IF NOT EXISTS MAPPINGLOGIN"
                + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "USERNAME varchar(50) UNIQUE," 
                + "PASSWORD varchar (50) NOT NULL," 
                + "USERTYPE int NOT NULL"
                + ")";
            st.execute(in);
            st.close();
        } catch (Exception ex) {
            System.out.println("Error tabla mapeo");
        }
    }
}
