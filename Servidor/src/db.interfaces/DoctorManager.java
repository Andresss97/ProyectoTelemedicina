package db.interfaces;
import java.util.ArrayList;
import java.util.List;

import pojos.*;


public interface DoctorManager 
{
	public void createDoctor (Doctor doc);
	public Integer searchDoctorByEmail(String email);
	public void readMH  (Integer ID);
	public void modifyMH(MedicalHistory m);
	public int getLastId();
	public List <Patient> searchPatientByName(String name,Integer docID);
	public List <Doctor> searchDoctorByName(String name);
	public List<Patient> getDoctorsPatients(Integer docID); //get all the patients of a doctor
	public Doctor getDoctor(int docId);	
	public List<Doctor> listDoctors();
	public void deleteDoctor(Integer ID);
}
