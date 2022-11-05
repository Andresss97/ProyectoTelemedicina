package db.sqlite;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import db.interfaces.AppointmentManager;
import db.interfaces.DBManager;
import db.interfaces.DoctorManager;
import db.interfaces.PatientManager;
import db.interfaces.PhysicalTherapistManager;

public class SQLiteManager implements DBManager 
{

	private Connection c;
	private PatientManager patient;
	private DoctorManager doctor;
	private PhysicalTherapistManager physicalTherapist;
	private AppointmentManager appointment;

	public SQLiteManager()
	{
		super();
	}

	public Connection getConnection()
	{
		return c;
	}
	public PatientManager getPatientManager() 
	{
		return patient;
	}
	public DoctorManager getDoctorManager() 
	{
		return doctor;
	}

	public PhysicalTherapistManager getPhysicalTherapistManager()
	{
		return physicalTherapist;
	}

	public AppointmentManager getAppointmentManager(){
		return appointment;
	}

	@Override
	public void connect()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			this.c=DriverManager.getConnection("jdbc:sqlite:./Database/Telemedicine.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			patient = new SQLitePatientManager(c);
			doctor = new SQLiteDoctorManager(c);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void disconnect()
	{
		try
		{
			c.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void createTables()
	{
		try
		{
			Statement st1=c.createStatement();
			String sq1= "CREATE TABLE IF NOT EXISTS  patient "
					+	"(ID			INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ 	"Name 	        TEXT  NOT NULL,"
					+   "Address        TEXT  NOT NULL,"
					+   "DOB            DATE  NOT NULL,"
					+   "Phone 		    INTEGER NOT NULL,"
					+   "Email 		    TEXT  NOT NULL,"
					+   "MHID           INTEGER,"
					+   "DRID           INTEGER,"	
					+   "FOREIGN KEY (MHID) REFERENCES medicalHistory(ID),"
					+   "FOREIGN KEY (DRID) REFERENCES dailyRegisters(ID),"
					//TODO, no se si poner la foreign key en el daily register
					+   ")";
			st1.executeUpdate(sq1);
			st1.close();
			Statement st2=c.createStatement();
			String sq2="CREATE TABLE IF NOT EXISTS   medicalHistory "
					+	"(ID			INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ 	" Name 	        TEXT  NOT NULL,"
					+   " DOB           DATE  NOT NULL,"
					+   " Diseases	    TEXT,"
					+   " Allergies     TEXT,"
					+   " Surgeries     TEXT,"
					+   " WeightKg      FLOAT,"
					+   " HeightCm      INTEGER,"
					+ 	")";
			st2.executeUpdate(sq2);
			st2.close();
			Statement st4=c.createStatement();
			String sq4="CREATE TABLE  IF NOT EXISTS  dailyRegisters"
					+  "(ID 		    INTEGER PRIMARY KEY AUTOINCREMENT,"
					+  "Date			DATE NOT NULL,"
					+  "Symptoms		TEXT NOT NULL,"
					+  "EMG				TEXT NOT NULL,"
					+  "ECG				TEXT NOT NULL,"
					+  ")";
			st4.executeUpdate(sq4);
			st4.close();
			Statement st3=c.createStatement();
			String sq3="CREATE TABLE  IF NOT EXISTS  doctor"
					+  "(ID 		    INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	Name			TEXT NOT NULL,"
					+ "	Address			TEXT NOT NULL,"
					+ "	DOB				DATE NOT NULL,"
					+ "	Phone			INTEGER NOT NULL,"
					+ "	Email			TEXT NOT NULL,"
					+ "	Specialty		TEXT NOT NULL,"
					+ "	Salary			DOUBLE NOT NULL)";
			st3.executeUpdate(sq3);
			st3.close();			
			Statement st5=c.createStatement();
			String sq5="CREATE TABLE IF NOT EXISTS	PatientDoctor"
					+ "(PATID			INTEGER REFERENCES patient(ID),"
					+ " DOCID			INTEGER REFERENCES doctor(ID) ON DELETE CASCADE)";
			st5.executeUpdate(sq5);
			st5.close();  
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
