package creacion;

import java.sql.PreparedStatement;
import pojos.*;

import java.sql.SQLException;

import Interfaz.MenuPrincipal;

public class QuerysUpdate {

	private Conector conn = (Conector) MenuPrincipal.conector;	
	
	public void addressDoctorAssigment(Address address, Doctor doctor) throws SQLException {
		String query;

		query = "UPDATE doctor "
				+ "SET  addressID =?,"
				+ "WHERE id=?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, address.getID());
		st.setInt(2, doctor.getID());
		
		st.executeUpdate();
		st.close();
	}

	public void updateAppointment (Appointment appointment) throws SQLException {
		String query;
		query = "UPDATE appointment " 
				+ "SET date = ?,"
				+ "hour = ?,"
				+ "reason = ?,"
				+ "done = ?,"
				+ "iddoctor = ?,"
				+ "idpatient = ?"
				+ "WHERE id = ?";
		
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setDate(1, appointment.getDate());
		st.setString(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		st.setBoolean(4, appointment.isDone());
		st.setInt(5, appointment.getDoctor().getID());
		st.setInt(6, MenuPrincipal.patient.getID());
		st.setInt(7, appointment.getID());
		
	    st.executeUpdate();
	    st.close();
	}
	
	public void updateAppointment2 (Appointment appointment) throws SQLException {
		String query;
		query = "UPDATE appointment " 
				+ "SET done = ?"
				+ "WHERE id = ?";
		
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setBoolean(1, appointment.isDone());
		st.setInt(2, appointment.getID());
		
	    st.executeUpdate();
	    st.close();
	}
	
	public void updateAppointment3 (Appointment appointment) throws SQLException {
		String query;
		query = "UPDATE appointment " 
				+ "SET date = ?,"
				+ "hour = ?,"
				+ "reason = ?,"
				+ "done = ?,"
				+ "iddoctor = ?"
				+ "WHERE id = ?";
		
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setDate(1, appointment.getDate());
		st.setString(2, appointment.getHour());
		st.setString(3, appointment.getReason());
		st.setBoolean(4, appointment.isDone());
		st.setInt(5, appointment.getDoctor().getID());
		st.setInt(6, appointment.getID());
		
	    st.executeUpdate();
	    st.close();
	}
	
	private void updateAllergy (Allergies allergy) throws SQLException {
		String query;
		query = "UPDATE allergy "
				+ " SET group = ? "
				+ " SET observations = ? "
				+ " SET idtreatment = ? "
				+ " WHERE id = ?";
		PreparedStatement st;
		st = conn.getConnect().prepareStatement(query);
		st.setString(1, allergy.getType());
		st.setString(2, allergy.getObservations());
		st.setInt(3, allergy.getTreatment().getIDtreatment());
		st.setInt(4, allergy.getID());
		
		st.executeUpdate();
		st.close();
	}

private void updateTreatment (Treatment treatment) throws SQLException {
		String query;
		query = "UPDATE treatment"
				+ "SET  name = ?,"
				+ "SET  description = ?,"
				+ "SET  type = ?,"
				+ "SET  StartDate = ?,"
				+ "SET  EndDate = ?,"
				+ "SET  Results = ?,"	
				+ "WHERE id =?";
			
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		//st.setString(1,treatment.getName());
		st.setString(2, treatment.getDescrpition());
		
		/*if(treatment.getTreatment().equals("MEDICATION")) {
			st.setString(3,"MEDICATION");
		}
		else {
			st.setString(3, "REHAB");
		}*/
		/*st.setDate(4, treatment.getStartDate());
		st.setDate(5, treatment.getEndDate());*/
		st.setString(6, treatment.getResults());
		st.setInt(7,treatment.getIDtreatment());
		
		st.executeUpdate();
		st.close();
			
	}
	
	public void updateDoctor(Doctor doctor) throws SQLException {
		QuerysSelect qs= new QuerysSelect();
		String query = "UPDATE doctor "
				+ "SET name = ?,"
				+ "surname = ?,"
				+ "nif = ?,"
				+ "dob = ?,"
				+ "photo = ?,"
				+ "mobilephone = ?,"
				+ "username = ?,"
				+ "password = ?,"
				+ "email = ?,"
				+ "gender = ?,"
				+ "idspeciality = ? "
				+ "where id = ?";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, doctor.getName());
		st.setString(2, doctor.getSurname());
		st.setString(3, doctor.getNIF());
		st.setDate(4, doctor.getDob());
		st.setBytes(5, doctor.getPhoto());
		st.setInt(6, doctor.getMobilePhone());
		st.setString(7, doctor.getUsername());
		st.setString(8, doctor.getPassword());
		st.setString(9, doctor.getEmail());
		if(doctor.getGender().equals("Male")) {
			st.setString(10, "Male");
		}
		else {
			st.setString(10, "Female");
		}
		
		int id = qs.selectIdSpeciality(doctor.getSpeciality());
		
		st.setInt(11, id);
		st.setInt(12, doctor.getID());
		
		st.executeUpdate();
		
		st.close();		
	}
	
	public void updatePatient(Patient patient) throws SQLException {
		String query = "UPDATE patient "
				+ "SET name = ?,"
				+ "surname = ?,"
				+ "nif = ?,"
				+ "dob = ?,"
				+ "photo = ?,"
				+ "mobilephone = ?,"
				+ "homephone = ?,"
				+ "username = ?,"
				+ "password = ?,"
				+ "email = ?,"
				+ "gender = ?,"
				+ "weight = ?,"
				+ "height = ?"
				+ "where id = ?";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, patient.getName());
		st.setString(2, patient.getSurname());
		st.setString(3, patient.getNIF());
		st.setDate(4, patient.getDob());
		st.setBytes(5, patient.getPhoto());
		st.setInt(6, patient.getMobilePhone());
		st.setInt(7, patient.getHousePhone());
		st.setString(8, patient.getUsername());
		st.setString(9, patient.getPassword());
		st.setString(10, patient.getEmail());
		if(patient.getGender().equals("Male")) {
			st.setString(11, "Male");
		}
		else {
			st.setString(11, "Female");
		}
		st.setFloat(12, patient.getWeight());
		st.setFloat(13, patient.getHeight());
		st.setInt(14, patient.getID());
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void updateAddress(Address address) throws SQLException {
		String query = "UPDATE address SET "
				+ "city = ?,"
				+ "street = ?,"
				+ "cp = ?,"
				+ "housenumber = ? where id = ?";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, address.getCity());
		st.setString(2, address.getStreet());
		st.setInt(3, address.getPostalCode());
		st.setInt(4, address.getHouseNumber());
		st.setInt(5, address.getID());
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void updateAdmin(String user, String password) throws SQLException {
		String query = "UPDATE mappinglogin SET "
				+ "username = ?,"
				+ "password = ? where id = 1";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, user);
		st.setString(2, password);
		
		st.executeUpdate();
		st.close();
	}
	
	public void updateUserDoctor(Doctor doctor, int id) throws SQLException {
		String query = "UPDATE mappinglogin SET "
				+ "username = ?,"
				+ "password = ?,"
				+ "email = ? where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, doctor.getUsername());
		st.setString(2, doctor.getPassword());
		st.setString(3, doctor.getEmail());
		st.setInt(4, id);
		
		st.executeUpdate();
		st.close();
	}
	
	public void updateUserPatient(Patient patient, int id) throws SQLException {
		String query = "UPDATE mappinglogin SET "
				+ "username = ?,"
				+ "password = ?,"
				+ "email = ? where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setString(1, patient.getUsername());
		st.setString(2, patient.getPassword());
		st.setString(3, patient.getEmail());
		st.setInt(4, id);
		
		st.executeUpdate();
		st.close();
	}
}
