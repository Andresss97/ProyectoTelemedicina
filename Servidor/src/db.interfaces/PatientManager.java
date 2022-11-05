package db.interfaces;

import java.util.ArrayList;
import java.util.List;

import pojos.*;

public interface PatientManager {

	public List<Patient> searchPatientName (String patientName);
	public int getLastId();
	public void addPatientandMedicalHistory (Patient p,MedicalHistory mh);
	public List<Treatment> listTreatment(Patient patient);
	public Patient getPatient(Integer id);	
	public MedicalHistory getMedicalHistory(Patient p);
	public Integer searchPatientByEmail(String email);
	public boolean checkDoctor(Integer patID, Integer docID);
	public void addDoctorToPatient(Patient pat, Doctor doc);

}
