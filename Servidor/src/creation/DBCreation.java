/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import java.sql.SQLException;
import java.sql.Statement;

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
            cTTreatment(con);
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
            in = "CREATE TABLE PATIENT (ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + " USERNAME varchar(50) NOT NULL UNIQUE, PASSWORD varchar(50) NOT NULL)";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void cTDoctor(Conector con) {
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in = "CREATE TABLE DOCTOR (ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + " USERNAME varchar(50) NOT NULL UNIQUE, PASSWORD  varchar(50) NOT NULL)";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println("Error tabla doctores");
        }
    }
    
    private static void cTTreatment(Conector con) {
        Conector conn = con;
        Statement st = null;
        String in = null;
        
        try {
            st = conn.getConnect().createStatement();
            in = "CREATE TABLE TREATMENT (ID integer PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "DATA varchar(50), DOB date NOT NULL, IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT "
                    + "(ID) ON UPDATE CASCADE ON DELETE SET NULL, IDDOCTOR int CONSTRAINT rDoctor REFERENCES DOCTOR ON UPDATE CASCADE ON DELETE SET NULL)";
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
            in = "CREATE TABLE MAPPINGLOGIN" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
		+ "USERNAME varchar(50) UNIQUE," + "PASSWORD varchar (50) NOT NULL," + 
                    "USERTYPE int NOT NULL)";
            st.execute(in);
            st.close();
        } catch (Exception ex) {
            System.out.println("Error tabla mapeo");
        }
    }
}
