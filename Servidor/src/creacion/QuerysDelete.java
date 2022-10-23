package creacion;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import Interfaz.MenuPrincipal;
import pojos.*;

public class QuerysDelete {
	
	private Conector con;
	
	public QuerysDelete() {
		this.con = (Conector) MenuPrincipal.conector;
	}	

	public void deleteAppointment (Appointment appointment) throws SQLException {
		String query;
		query = "DELETE FROM appointment WHERE id = ?";
		PreparedStatement st;
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, appointment.getID());
			st.executeUpdate();
			st.close();
	}
	
	private void deleteClinicalHistory (ClinicalHistory clinicalHistory) throws SQLException {
		String query;
		query = "DELETE FROM clinicalHistory WHERE id = ?";
	    PreparedStatement st;
		st= con.getConnect().prepareStatement(query);
		st.setInt(1, clinicalHistory.getID());
		st.executeUpdate();
		st.close();
	}
	
	private void deleteSurgery (Surgeries surgery) throws SQLException {
		String query;
		query = "DELETE FROM surgeries WHERE id = ?";
		PreparedStatement st;
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, surgery.getID());
			st.executeUpdate();
			st.close();
	}
	
	private void deleteAllergy (Allergies allergy) throws SQLException {
		String query;
		query = "DELETE FROM allergies WHERE id = ?";
		PreparedStatement st;
			st = con.getConnect().prepareStatement(query);
			st.setInt(1, allergy.getID());
			st.executeUpdate();
			st.close();		
	}
	
	public void deleteDoctorAccount(Doctor doctor) throws SQLException {
		String query = "DELETE from doctor where id = " + doctor.getID();
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void deletePatientAccount(Patient patient) throws SQLException {
		String query = "DELETE from patient where id = " + patient.getID();
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void deleteUser(int id) throws SQLException {
		String query = "DELETE from mappinglogin where id = " + id;
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	
	public void deleteIllness(Illness illness)throws SQLException {
		String query ="DELETE FROM illness WHERE id = ?";
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.setInt(1, illness.getID());
		st.executeQuery();
		st.close();
		
	}
	
	
	public void deleteVaccine(Vaccine vaccine) throws SQLException {
		String query = "DELETE from vaccine where id = " + vaccine.getIDvaccine();
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void deleteTreatment(Treatment treatment) throws SQLException {
		String query = "DELETE from treatment where id = " + treatment.getIDtreatment();
		PreparedStatement st = con.getConnect().prepareStatement(query);
		
		st.executeUpdate();
		
		st.close();
	}
	
	public void deleteAddress(int dateID)throws SQLException {
		String query = "DELETE FROM address WHERE id=?";
		PreparedStatement st = con.getConnect().prepareStatement(query);
		st.setInt(1, dateID);
		
		st.executeUpdate();
		
		st.close();
		
	}
	
	
}
