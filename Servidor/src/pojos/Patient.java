
package pojos;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PATIENT")
public class Patient extends Person {
	
	@Id
	@GeneratedValue(generator = "PATIENT")
	@TableGenerator(name = "PATIENT", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PATIENT")
	private Integer ID;

	private float weight;
	
	private float height;
	
	@Column(name = "homephone")
	private int housePhone;
	
	@ManyToMany
	@JoinTable(name="MAPPING",
			joinColumns={@JoinColumn(name="IDDOCTOR", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="IDPATIENT", referencedColumnName="id")})
	private LinkedList<Doctor> doctors;
	
	@OneToMany(mappedBy="patient")
	private LinkedList<Appointment> appointments;
	
	@OneToMany(mappedBy="patient", cascade = CascadeType.PERSIST)
	private LinkedList<Illness> illnesses;
	
	
	@OneToMany(mappedBy="patient")
	private LinkedList<Allergies> allergies;
	
	@OneToMany(mappedBy="patient")
	private LinkedList<Surgeries> surgeries;
	
	@OneToMany(mappedBy="patient", cascade = CascadeType.PERSIST)
	private LinkedList<Treatment> treatment;
	
	@OneToMany(mappedBy="patient")
	private LinkedList<Vaccine> vaccines;
	
	@OneToOne(mappedBy="patient", cascade = CascadeType.PERSIST)
	private ClinicalHistory cHistory;
	
	public Patient() { 
		super();
		this.weight = 0.0F;
		this.height = 0.0F;
		this.housePhone = 0;
		this.doctors = new LinkedList<>();
		this.appointments = new LinkedList<>();
		this.illnesses = new LinkedList<>();
		this.allergies = new LinkedList<>();
		this.surgeries = new LinkedList<>();
		this.vaccines = new LinkedList<>();
		this.cHistory = new ClinicalHistory();
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getHousePhone() {
		return housePhone;
	}

	public void setHousePhone(int housePhone) {
		this.housePhone = housePhone;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(LinkedList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public LinkedList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(LinkedList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public LinkedList<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(LinkedList<Illness> illnesses) {
		this.illnesses = illnesses;
	}

	public List<Allergies> getAllergies() {
		return allergies;
	}

	public void setAllergies(LinkedList<Allergies> allergies) {
		this.allergies = allergies;
	}

	public List<Surgeries> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(LinkedList<Surgeries> surgeries) {
		this.surgeries = surgeries;
	}

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(LinkedList<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	
	public void addDoctor(Doctor doctor){
		this.doctors.add(doctor);
	} 
	
	public void removeDoctor(Doctor doctor){
		this.doctors.remove(doctor);
	}
	
	public void addIllness(Illness illness){
		this.illnesses.add(illness);
	} 
	
	public void removeIllness(Illness illness){
		this.illnesses.remove(illness);
	}
	
	public void addAppointment(Appointment appointment){
		this.appointments.add(appointment);
	} 
	
	public void removeAppointment(Appointment appointment){
		this.appointments.remove(appointment);
	}
	
	public void addAllergy(Allergies allergies){
		this.allergies.add(allergies);
	} 
	
	public void removeAllergy(Allergies allergies){
		this.allergies.remove(allergies);
	}
	
	public void addSurgery(Surgeries surgery){
		this.surgeries.add(surgery);
	} 
	
	public void removeSurgery(Surgeries surgery){
		this.surgeries.remove(surgery);
	}
	
	public void addVaccine(Vaccine vaccine){
		this.vaccines.add(vaccine);
	} 
	
	public void removeVaccine(Vaccine vaccine){
		this.vaccines.remove(vaccine);
	}
	
	public int getID2() {
		return ID;
	}

	public void setID2(Integer iD) {
		this.ID = iD;
	}

	public LinkedList<Treatment> getTreatment() {
		return treatment;
	}

	public void setTreatment(LinkedList<Treatment> treatment) {
		this.treatment = treatment;
	}
	
	public ClinicalHistory getcHistory() {
		return cHistory;
	}

	public void setcHistory(ClinicalHistory cHistory) {
		this.cHistory = cHistory;
	}

	@Override
	public String toString() {
		return "Patient: "+ name + " " + surname;
	}
}
