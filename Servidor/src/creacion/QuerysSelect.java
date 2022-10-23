package creacion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;*/

import Interfaz.MenuPrincipal;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pojos.Address;
import pojos.Allergies;
import pojos.Appointment;
import pojos.ClinicalHistory;
import pojos.ClinicalHistory.BLOODGROUP;
import pojos.Doctor;
import pojos.Patient;
import pojos.Person;
import pojos.Surgeries;
import pojos.Treatment;
import pojos.Treatment.typeTreatment;

public class QuerysSelect {
	private Conector conn = (Conector) MenuPrincipal.conector;
	
	public String[] selectUser(String user, String psw) throws SQLException {
		String[] data = new String[2];
		String query = "SELECT USERNAME, PASSWORD from mappinglogin where username = '" + user + "' and password = '"
				+ psw + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		while (set.next()) {
			data[0] = set.getString("username");
			data[1] = set.getString("password");
		}

		st.close();
		set.close();

		return data;
	}
	
	public Patient selectPatient(String[] data) throws SQLException {
		Patient patient = null;
		String query = "SELECT * from patient where username = '" + data[0] + "' and password = '" + data[1] + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		ResultSet set = st.executeQuery();
		while(set.next()) {
			patient = new Patient();
			
			patient.setName(set.getString("Name"));
			patient.setSurname(set.getString("Surname"));
			patient.setEmail(set.getString("email"));
			patient.setNIF(set.getString("nif"));
			patient.setMobilePhone(set.getInt("mobilephone"));
			patient.setHousePhone(set.getInt("homephone"));
			patient.setDob(set.getDate("dob"));
			if(set.getString("gender").equals("Male")) {
				patient.setGender("Male");
			}else {
				patient.setGender("Female");
			}
			patient.setWeight(set.getFloat("weight"));
			patient.setHeight(set.getFloat("height"));
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
			patient.setPhoto(set.getBytes("photo"));
			int id = set.getInt("idaddress");
			Address address = this.selectAddress(id);
			patient.setAddress(address);
			patient.setID(set.getInt("id"));
		}
		
		st.close();
		set.close();
		
		return patient;
	}
	
	public Doctor selectDoctor(String[] data) throws SQLException {
		Doctor doctor = new Doctor();
		String query = "SELECT * from doctor where username = '" + data[0] + "' and password = '" + data[1] + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		int idAddress;
		Address address;
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			String sp = this.selectIdSpeciality(set.getInt("idspeciality"));
			doctor.setSpeciality(sp);
			if(set.getString("gender").equals("Male")) {
				doctor.setGender("Male");
			}
			else {
				doctor.setGender("Female");
			}
			doctor.setPhoto(set.getBytes("photo"));
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setID(set.getInt("id"));
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			doctor.setAddress(address);
		}
		
		set.close();
		st.close();
		
		return doctor;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList checkEmailUser(String mail) throws SQLException {
		String query = "SELECT USERNAME, PASSWORD from mappinglogin where email = '" + mail + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ArrayList<String> data = new ArrayList<>();
		
		ResultSet set = st.executeQuery();
		while(set.next()) {
			data.add(set.getString("username"));
			data.add(set.getString("password"));
		}
		
		st.close();
		set.close();
		
		return data;
	}
	
	public ArrayList appStats(int idspec) throws SQLException {
		String query = "SELECT a1.hour from appointment as a1 JOIN doctor as d1 on a1.iddoctor = d1.id where d1.idspeciality = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, idspec);
		ArrayList<String> list = new ArrayList<>();
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			list.add(set.getString("hour"));
		}
		
		return list;
	}

	public int checkSecurityLevel(String user, String psw) throws SQLException {
		String query = "SELECT usertype from mappinglogin where username = '" + user + "' and password = '" + psw + "'";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		int sLevel = 0;
		while (set.next()) {
			sLevel = set.getInt("usertype");
		}
		
		st.close();
		set.close();
		
		return sLevel;
	}

	public ArrayList<Doctor> selectDoctorNSSpeciality(int speciality) throws SQLException {
		String query = "SELECT name, surname, dob, email, nif, mobilephone, idspeciality, gender, id from doctor where idspeciality = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, speciality);
		
		ResultSet set = st.executeQuery();
		ArrayList<Doctor> list = new ArrayList<>();
		while (set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			String sp = this.selectIdSpeciality(set.getInt("idspeciality"));
			doctor.setSpeciality(sp);
			if(set.getString("gender").equals("Male")) {
				doctor.setGender("Male");
			}
			else {
				doctor.setGender("Female");
			}
			doctor.setID(set.getInt("id"));
			
			list.add(doctor);
		}
		st.close();
		set.close();
		return list;
	}
	
	public ArrayList<Person> selectDoctorsAccount() throws SQLException {
		String query = "SELECT * from doctor";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Person> list = new ArrayList<>();
		Address address;
		int idAddress;
		
		while (set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			String sp = this.selectIdSpeciality(set.getInt("idspeciality"));
			doctor.setSpeciality(sp);
			if(set.getString("gender").equals("Male")) {
				doctor.setGender("Male");
			}
			else {
				doctor.setGender("Female");
			}
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setID(set.getInt("id"));
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			doctor.setAddress(address);
			
			list.add(doctor);
		}
		st.close();
		set.close();
		
		return list;
	}
	
	public ArrayList<Doctor> selectDoctors() throws SQLException {
		String query = "SELECT * from doctor";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Doctor> list = new ArrayList<>();
		Address address;
		int idAddress;
		
		while (set.next()) {
			Doctor doctor = new Doctor();
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			String sp = this.selectIdSpeciality(set.getInt("idspeciality"));
			doctor.setSpeciality(sp);
			if(set.getString("gender").equals("Male")) {
				doctor.setGender("Male");
			}
			else {
				doctor.setGender("Female");
			}
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setID(set.getInt("id"));
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			doctor.setAddress(address);
			
			list.add(doctor);
		}
		st.close();
		set.close();
		
		return list;
	}
	
	public ArrayList<Person> selectPatientsAccounts() throws SQLException {
		String query = "SELECT * from patient";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Person> list = new ArrayList<>();
		int idAddress;
		Address address;
		
		while (set.next()) {
			Patient patient = new Patient();
			patient.setName(set.getString("name"));
			patient.setSurname(set.getString("surname"));
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
			patient.setID(set.getInt("id"));
			patient.setDob(set.getDate("dob"));
			patient.setEmail(set.getString("email"));
			patient.setNIF(set.getString("nif"));
			patient.setWeight(set.getFloat("weight"));
			patient.setHeight(set.getFloat("height"));
			patient.setMobilePhone(set.getInt("mobilephone"));
			patient.setHousePhone(set.getInt("homephone"));
			patient.setPhoto(set.getBytes("photo"));
			if(set.getString("gender").equals("Male")) {
				patient.setGender("Male");
			}
			else {
				patient.setGender("Female");
			}
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			patient.setAddress(address);
			patient.getAddress().setID(idAddress);
			list.add(patient);
		}
		
		st.close();
		set.close();
		
		return list;
	}
	
	public ArrayList<Patient> selectPatients() throws SQLException {
		String query = "SELECT * from patient";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<Patient> list = new ArrayList<>();
		int idAddress;
		Address address;
		
		while (set.next()) {
			Patient patient = new Patient();
			patient.setName(set.getString("name"));
			patient.setSurname(set.getString("surname"));
			patient.setUsername(set.getString("username"));
			patient.setPassword(set.getString("password"));
			patient.setID(set.getInt("id"));
			patient.setDob(set.getDate("dob"));
			patient.setEmail(set.getString("email"));
			patient.setNIF(set.getString("nif"));
			patient.setWeight(set.getFloat("weight"));
			patient.setHeight(set.getFloat("height"));
			patient.setMobilePhone(set.getInt("mobilephone"));
			patient.setHousePhone(set.getInt("homephone"));
			patient.setPhoto(set.getBytes("photo"));
			if(set.getString("gender").equals("Male")) {
				patient.setGender("Male");
			}
			else {
				patient.setGender("Female");
			}
			idAddress = set.getInt("idaddress");
			address = this.selectAddress(idAddress);
			patient.setAddress(address);
			patient.getAddress().setID(idAddress);
			list.add(patient);
		}
		
		st.close();
		set.close();
		
		return list;
	}
	
	public int selectLastId(String table) throws SQLException {
		String query = "SELECT MAX(id) from " + table;
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		int id = set.getInt(1);
		
		st.close();
		set.close();
		
		return id;
	}
	
	public Address selectAddress(int id) throws SQLException {
		String query = "SELECT city, street, housenumber, cp, id from address where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, id);
		ResultSet set = st.executeQuery();
		Address address = new Address();
		
		address.setCity(set.getString("city"));
		address.setStreet(set.getString("street"));
		address.setHouseNumber(set.getInt("housenumber"));
		address.setPostalCode(set.getInt("cp"));
		address.setID(set.getInt("ID"));
		
		st.close();
		set.close();
		
		return address;
	}
	
	public ArrayList<Address> selectAddresses() throws SQLException {
		String query = "SELECT * from address";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ArrayList<Address> addresses = new ArrayList<>();
		
		ResultSet set = st.executeQuery();
		while (set.next()) {
			Address address = new Address();

			address.setCity(set.getString("city"));
			address.setStreet(set.getString("street"));
			address.setHouseNumber(set.getInt("housenumber"));
			address.setPostalCode(set.getInt("cp"));
			address.setID(set.getInt("ID"));
			addresses.add(address);
		}

		st.close();
		set.close();

		return addresses;
	}
	
	public ArrayList<String> selectSpecialities() throws SQLException {
		String query;
		
		query = "SELECT * from speciality";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		ResultSet set = st.executeQuery();
		ArrayList<String> specialities = new ArrayList<>();
		
		while(set.next()) {
			specialities.add(set.getString("type"));
		}
		
		st.close();
		set.close();
		
		return specialities;
	}
	
	public int selectIdSpeciality(String spec) throws SQLException {
		String query;
		
		query = "SELECT id from speciality where type = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, spec);
		ResultSet set = st.executeQuery();
		int id = 0;
		
		id = set.getInt("id");
		
		st.close();
		set.close();
		
		return id;
	}
	
	public String selectIdSpeciality(int id) throws SQLException {
		String query;
		
		query = "SELECT type from speciality where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, id);
		ResultSet set = st.executeQuery();
		String type = set.getString("type");

		st.close();
		set.close();

		return type;
	}
	
	public ArrayList<String> selectHoursDoctorApp(int id) throws SQLException {
		String query = "SELECT hour from appointment where iddoctor = ?";
		ArrayList<String> hours = new ArrayList<>();
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, id);
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			hours.add(set.getString("hour"));
		}
		
		st.close();
		set.close();
		
		return hours;
	}
	
	public ArrayList<Appointment> selectAppointments(int id) throws SQLException {
		ArrayList<Appointment> apps = new ArrayList<>();
		
		String query = "SELECT * from appointment where idpatient = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, id);
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			Appointment app = new Appointment();
			app.setReason(set.getString("reason"));
			app.setDate(set.getDate("date"));
			app.setHour(set.getString("hour"));
			app.setID(set.getInt("id"));
			int iddoc = set.getInt("iddoctor");
			Doctor doc = this.selectDoctorByID(iddoc);
			app.setDoctor(doc);
			apps.add(app);
		}
		
		st.close();
		set.close();
		
		return apps;
	}
	
	public Appointment selectAppointmentById(int id) throws SQLException {
		String query = "SELECT * from appointment where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, id);
		
		Appointment app = new Appointment();
		ResultSet set = st.executeQuery();

		app.setReason(set.getString("reason"));
		app.setDate(set.getDate("date"));
		app.setHour(set.getString("hour"));
		app.setID(set.getInt("id"));
		int iddoc = set.getInt("iddoctor");
		Doctor doc = this.selectDoctorByID(iddoc);
		app.setDoctor(doc);
		
		st.close();
		set.close();
		
		return app;
	}
	
	public ArrayList<Appointment> selectAppointmentsClear() throws SQLException {
		ArrayList<Appointment> apps = new ArrayList<>();
		
		String query = "SELECT * from appointment";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			Appointment app = new Appointment();
			app.setReason(set.getString("reason"));
			app.setDate(set.getDate("date"));
			app.setHour(set.getString("hour"));
			app.setID(set.getInt("id"));
			int iddoc = set.getInt("iddoctor");
			Doctor doc = this.selectDoctorByID(iddoc);
			app.setDoctor(doc);
			apps.add(app);
		}
		
		st.close();
		set.close();
		
		return apps;
	}
	
	public Doctor selectDoctorByID(int id) throws SQLException {
		Doctor doctor = new Doctor();
		
		String query = "SELECT * from doctor where id = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, id);
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			doctor.setName(set.getString("name"));
			doctor.setSurname(set.getString("surname"));
			doctor.setDob(set.getDate("dob"));
			doctor.setEmail(set.getString("email"));
			doctor.setNIF(set.getString("nif"));
			doctor.setMobilePhone(set.getInt("mobilephone"));
			String sp = this.selectIdSpeciality(set.getInt("idspeciality"));
			doctor.setSpeciality(sp);
			if(set.getString("gender").equals("Male")) {
				doctor.setGender("Male");
			}
			else {
				doctor.setGender("Female");
			}
			doctor.setUsername(set.getString("username"));
			doctor.setPassword(set.getString("password"));
			doctor.setID(set.getInt("id"));
		}
		
		st.close();
		set.close();
		
		return doctor;
	}

	public ArrayList <Allergies> selectAllergy (int id) throws SQLException{
		ArrayList <Allergies> allergy = new ArrayList<>();
		
		String query = "SELECT * FROM allergies WHERE idpatient = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		
		st.setInt(1, id);
		
		ResultSet set = st.executeQuery();
		
		while (set.next()) {
			Allergies allerg = new Allergies ();
			allerg.setType(set.getString("type"));
			allerg.setID(set.getInt("id"));
			allerg.setObservations(set.getString("observations"));
			
			allergy.add(allerg);
		}
		
		st.close();
		set.close();
		
		return allergy;
	}
	
	public ArrayList<Appointment> selectAppointmentForDoctor(int id) throws SQLException {
		ArrayList<Appointment> apps = new ArrayList<>();
		String query = "SELECT * from appointment where iddoctor = ? and done = 0";
		
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setInt(1, id);
		
		ResultSet set = st.executeQuery();
		
		while(set.next()) {
			Appointment app = new Appointment();
			app.setReason(set.getString("reason"));
			app.setDate(set.getDate("date"));
			app.setHour(set.getString("hour"));
			app.setID(set.getInt("id"));
			int iddoc = set.getInt("iddoctor");
			Doctor doc = this.selectDoctorByID(iddoc);
			app.setDoctor(doc);
			apps.add(app);
		}
		
		st.close();
		set.close();
		
		return apps;
	}
	
	public int selectIdUser(Doctor doctor) throws SQLException {
		String query = "SELECT id from mappinglogin where email  = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, doctor.getEmail());
		int id2;
		
		ResultSet set = st.executeQuery();
		
		id2 = set.getInt("id");
		
		st.close();
		set.close();
		
		return id2;
	}
	
	public int selectIdUser2(Patient patient) throws SQLException {
		String query = "SELECT id from mappinglogin where email  = ?";
		PreparedStatement st = conn.getConnect().prepareStatement(query);
		st.setString(1, patient.getEmail());
		int id2;
		
		ResultSet set = st.executeQuery();
		
		id2 = set.getInt("id");
		
		st.close();
		set.close();
		
		return id2;
	}
	
}
