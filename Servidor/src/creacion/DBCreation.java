/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    Created by Andrés Daniel de Pereda Cruz
 */
package creacion;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public abstract class DBCreation {    
	private static ArrayList<String> specialities;
	
	public static void createDB(ConnInterface conn) {
		if (conn.getClass().equals(Conector.class))  {
		Conector con = (Conector) conn;
		QuerysInsert qi = new QuerysInsert();
		fillSpecialities();
		
		cTAddress(con);
		cTAllergies(con);
		cTDoctor(con);
		cTMapping(con);
		cTPatient(con);
		cTSurgeries(con);
		cTTreatment(con);
		cTVaccine(con);
		cTIllnesses(con);
		cTAppointment(con);
		cTClinicalHistory(con);
		cTMappingLogIn(con);
		cTSpeciality(con);
		try {
			qi.insertUser2("admin", "indi");
			for(int i = 0; i < specialities.size(); i++) {
				qi.insertSpecialities(specialities.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

	private static void cTAddress(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();

			in = "CREATE TABLE ADDRESS " + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL," + "CITY varchar(50),"
					+ "STREET varchar(50)," + "CP bigint," + "HOUSENUMBER bigint)";

			st.execute(in);
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Address");
		}
	}

	private static void cTAllergies(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();

			in = "CREATE TABLE ALLERGIES" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL," + "TYPE varchar(50),"
					+ "OBSERVATIONS text,"
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON UPDATE CASCADE ON DELETE SET NULL)";

			st.execute(in);
			Statement stmtSeq = con.getConnect().createStatement();

			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('ALLERGIES', 1)";

			stmtSeq.executeUpdate(sqlSeq);
			
			stmtSeq.close();
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Allergies");
		}
	}

	private static void cTDoctor(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();

			in = "CREATE TABLE DOCTOR" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "USERNAME varchar(50) NOT NULL UNIQUE," + "PASSWORD varchar(50) NOT NULL,"
					+ "EMAIL varchar(100) NOT NULL," + "GENDER varchar(20) NOT NULL,"
					+ "IDSPECIALITY int CONSTRAINT rSpeciality REFERENCES SPECIALITY (ID) ON UPDATE CASCADE ON DELETE SET NULL," + "MOBILEPHONE int," + "NAME varchar(50) NOT NULL,"
					+ "SURNAME varchar(50) NOT NULL," + "NIF varchar(25) NOT NULL," + "DOB date NOT NULL,"
					+ "PHOTO blob,"
					+ "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS ON UPDATE CASCADE ON DELETE SET NULL)";

			st.execute(in);
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Doctor");
		}
	}

	private static void cTMapping(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE MAPPING" + "(IDDOCTOR int," + "IDPATIENT int)";

			st.execute(in);
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Mapping");
		}
	}

	private static void cTSurgeries(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE SURGERIES" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "DATE date," + "TYPE varchar(50),"
					+ "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON UPDATE CASCADE ON DELETE SET NULL)";

			st.execute(in);
			Statement stmtSeq = con.getConnect().createStatement();

			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('SURGERIES', 1)";

			stmtSeq.executeUpdate(sqlSeq);
			
			stmtSeq.close();
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Surgeries");
		}
	}

	private static void cTVaccine(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE VACCINE" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "NAME varchar(25) NOT NULL," + "DATE date," + "OBSERVATIONS text,"
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL)";
			st.execute(in);
			Statement stmtSeq = con.getConnect().createStatement();

			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('VACCINE', 1)";

			stmtSeq.executeUpdate(sqlSeq);
			
			stmtSeq.close();
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Vaccine");
		}
	}

	private static void cTPatient(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE PATIENT" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "NAME varchar(25) NOT NULL," + "SURNAME varchar(25) NOT NULL," + "NIF varchar (15) NOT NULL,"
					+ "EMAIL varchar(50) NOT NULL," + "MOBILEPHONE bigint," + "HOMEPHONE bigint," + "DOB date NOT NULL,"
					+ "GENDER varchar (15) NOT NULL," + "USERNAME varchar(50) NOT NULL UNIQUE,"
					+ "PASSWORD varchar (20) NOT NULL," + "WEIGHT float," + "HEIGHT float," + "PHOTO blob,"
					+ "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS ON UPDATE CASCADE ON DELETE SET NULL)";
			st.execute(in);
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Patient");
		}
	}

	private static void cTTreatment(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE TREATMENT" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL," + "START date NOT NULL,"
					+ "END date NOT NULL," + "TYPE varchar(50)," + "DESCRIPTION text,"
					+ "TRESULTS text,"
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID) ON UPDATE CASCADE ON DELETE SET NULL)";
			st.execute(in);
			
			Statement stmtSeq = con.getConnect().createStatement();

			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('TREATMENT', 1)";

			stmtSeq.executeUpdate(sqlSeq);
			
			stmtSeq.close();
			
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Treatment");
		}
	}

	private static void cTIllnesses(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE ILLNESSES" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL," + "DESCRIPTION text,"
					+ "DATE date," + "TYPE varchar(50),"+"NAME text,"
					+ "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL)";
			st.execute(in);
			Statement stmtSeq = con.getConnect().createStatement();

			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('ILLNESSES', 1)";

			stmtSeq.executeUpdate(sqlSeq);
			
			stmtSeq.close();
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Illnesses");
		}
	}

	private static void cTAppointment(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE APPOINTMENT" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL," 
					+ "HOUR varchar(10) NOT NULL,"
					+ "DATE date NOT NULL,"
					+ "DONE boolean,"
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "IDDOCTOR int CONSTRAINT rDoctor REFERENCES DOCTOR ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "REASON text NOT NULL)";
			st.execute(in);
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Appointment");
		}
	}

	private static void cTClinicalHistory(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE CLINICALHISTORY" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL," + "ADDICTIONSALCOHOL int, ADDICTIONSDRUGS int, ADDICTIONSOTHERS int, "
					+ "BLOODGROUP int, INSURANCECOMPANY varchar(50), "
					+ "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL)";
			st.execute(in);
			
			Statement stmtSeq = con.getConnect().createStatement();

			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('CLINICALHISTORY', 1)";

			stmtSeq.executeUpdate(sqlSeq);
			
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "ClinicalHistory");
		}
	}

	private static void cTMappingLogIn(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;

		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE MAPPINGLOGIN" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "USERNAME varchar(50) UNIQUE," + "PASSWORD varchar (50)," + "USERTYPE int NOT NULL, EMAIL varchar(100))";
			st.execute(in);
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage() + "Users");
		}
	}
	
	private static void cTSpeciality(Conector conn) {
		Conector con = conn;
		Statement st = null;
		String in = null;
		
		try {
			st = con.getConnect().createStatement();
			in = "CREATE TABLE SPECIALITY" + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "TYPE varchar(50) NOT NULL)";
			st.execute(in);
			st.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static void fillSpecialities() {
		specialities = new ArrayList<>();
		specialities.add("Allergy and Immunollogy");
		specialities.add("General Pathology");
		specialities.add("Cardiology");
		specialities.add("Clinical Neurophisiology");
		specialities.add("Endocrinology");
		specialities.add("General Practice");
		specialities.add("Internal Medicine");
		specialities.add("Nephrology");
		specialities.add("Neurology");
		specialities.add("Ophthalmology");
		specialities.add("Orthopaedics");
		specialities.add("Paediatrics");
		specialities.add("Neonatology");
		specialities.add("Physical Medicine Rehabilitation");
		specialities.add("Pulmonology");
		specialities.add("Psychiatry");
		specialities.add("Radiology");
		specialities.add("General Surgery");
		specialities.add("Urology");
	}
}
