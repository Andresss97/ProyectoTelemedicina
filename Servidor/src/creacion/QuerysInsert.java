package creacion;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import Interfaz.MenuPrincipalServidor;
//import pojos.Treatment.typeTreatment;
import pojos.*;
import pojos.ClinicalHistory.BLOODGROUP;

public class QuerysInsert {
	
	private Conector conn = (Conector) MenuPrincipalServidor.conector;
	//change the Integer you are changing
	
	public void insertDoctor(Doctor doctor) throws SQLException {
		String query;
		query = "INSERT into doctor (username,password,email,gender,idspeciality,mobilephone,name,surname,nif,dob,idaddress) values (?,?,?,?,?,?,?,?,?,?,?)";
		QuerysSelect qs = new QuerysSelect();
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		int idspeciality = 0;
		
		st.setString(1, doctor.getUsername());
		st.setString(2, doctor.getPassword());
		st.setString(3, doctor.getEmail());
		if(doctor.getGender().equals("Male")) {
			st.setString(4,"Male");	
		}
		else {
			st.setString(4,"Female");
		}
		idspeciality = qs.selectIdSpeciality(doctor.getSpeciality());
		st.setInt(5, idspeciality);
		st.setInt(6,doctor.getMobilePhone());
		st.setString(7,doctor.getName());
		st.setString(8, doctor.getSurname());
		st.setString(9,doctor.getNIF());
		st.setDate(10, doctor.getDob());
		st.setInt(11, doctor.getAddress().getID());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertAddress(Address address) throws SQLException {
		String query;
		ResultSet set = null;
		query = "INSERT into address (city,street,cp,housenumber) values (?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, address.getCity());
		st.setString(2, address.getStreet());
		st.setInt(3, address.getPostalCode());
		st.setInt(4, address.getHouseNumber());
		
		st.executeUpdate();

		st.close();
	}
	
	public void insertPatient(Patient patient) throws Exception {
		String query;
		query = "INSERT into patient (name,surname,nif,email,mobilephone,homephone,dob,gender,username,password,weight,height,idaddress, photo) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, patient.getName());
		st.setString(2, patient.getSurname());
		st.setString(3, patient.getNIF());
		st.setString(4, patient.getEmail());
		st.setInt(5, patient.getMobilePhone());
		st.setInt(6, patient.getHousePhone());
		st.setDate(7, patient.getDob());
		
		if(patient.getGender().equals("Male")) {
			st.setString(8,"Male");	
		}
		else {
			st.setString(8,"Female");
		}
		
		st.setString(9, patient.getUsername());
		st.setString(10, patient.getPassword());
		st.setFloat(11, patient.getWeight());
		st.setFloat(12, patient.getHeight());
		st.setInt(13, patient.getAddress().getID());
		st.setBytes(14, patient.getPhoto());
		st.executeUpdate();
		st.close();
	}
	
	public void insertUser1(Doctor doctor, Patient patient) throws SQLException{
		if(!(patient == null)) {
			String query;
			query = "INSERT into mappinglogin (username, password,usertype,email) values (?,?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query);
			st.setString(1, patient.getUsername());
			st.setString(2, patient.getPassword());
			st.setInt(3, 1);
			st.setString(4,patient.getEmail());
			
			st.executeUpdate();
			st.close();
		}
		else if(!(doctor == null)) {
			String query2;
			query2 = "INSERT into mappinglogin (username, password,usertype,email) values (?,?,?,?)";
			PreparedStatement st = conn.getConnect().prepareStatement(query2);
			st.setString(1, doctor.getUsername());
			st.setString(2, doctor.getPassword());
			st.setInt(3, 2);
			st.setString(4,doctor.getEmail());
			
			st.executeUpdate();
			st.close();
		}
	}
	
	public void insertUser2(String user, String password) throws SQLException {
		String query;
		query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);

		st.setString(1, user);
		st.setString(2, password);
		st.setInt(3, 3);
		st.executeUpdate();
		st.close();
	}
	
	public void insertAppointment(Appointment appointment) throws SQLException {
		String query;

		query = "INSERT into appointment (date, hour, reason, done, iddoctor, idpatient) values (?,?,?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
	    st.setDate(1, appointment.getDate());
		st.setString(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		st.setBoolean(4, appointment.isDone());
		st.setInt(5, appointment.getDoctor().getID());
		st.setInt(6, appointment.getPatient().getID());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertAllergies(Allergies allergies) throws SQLException {
		String query;

		query = "INSERT into allergies (group, observations, idpatient, idtreatment,) values (?, ?, ?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, allergies.getType());
		st.setString(2, allergies.getObservations());
		st.setInt(3, allergies.getPatient().getID());
		st.setInt(4, allergies.getTreatment().getIDtreatment());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertSurgeries (Surgeries surgeries) throws SQLException {
		String query;

		query = "INSERT into surgeries (date, type,idpatient,idtreatment,) values (?,?,?,?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setDate(1, surgeries.getDate());
		st.setString(2, surgeries.getType());
		st.setInt(3, surgeries.getPatient().getID());
		st.setInt(4, surgeries.getTreatment().getIDtreatment());
		
		st.executeUpdate();
		st.close();
	}
	
	public void insertSpecialities(String speciality) throws SQLException {
		String query;
		
		query = "INSERT into speciality (type) values (?)";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, speciality);
		
		st.executeUpdate();
		st.close();
	}
}
