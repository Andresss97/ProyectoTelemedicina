package pojos;
import java.io.*;
import java.sql.Date;

public class ClinicalHistory implements Serializable {

	private Integer ID;
	
	public enum ADDICTIONS {
		YES, NONE
	};
	
	private ADDICTIONS addictionsAlcohol;
	
	private ADDICTIONS addictionsDrugs;
	
	private ADDICTIONS addictionsOthers;

	private Patient patient;
	
	public enum BLOODGROUP {
		AP, BP, ABP, AN, BN, ABN, ZP, ZN
	};

	private BLOODGROUP bloodgroup;

	private String medicalInsurance;

	public ClinicalHistory() {
		this.ID = null;
		this.bloodgroup = null;
		this.addictionsAlcohol = null;
		this.addictionsDrugs = null;
		this.addictionsOthers = null;
		this.medicalInsurance = null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public BLOODGROUP getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(BLOODGROUP bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ADDICTIONS getAddictionAlcohol() {
		return addictionsAlcohol;
	}

	public void setAddictionAlcohol(ADDICTIONS addictionAlcohol) {
		this.addictionsAlcohol = addictionAlcohol;
	}

	public ADDICTIONS getAddictionsDrugs() {
		return addictionsDrugs;
	}

	public void setAddictionsDrugs(ADDICTIONS addictionsDrugs) {
		this.addictionsDrugs = addictionsDrugs;
	}

	public ADDICTIONS getAddictionsOthers() {
		return addictionsOthers;
	}

	public void setAddictionsOthers(ADDICTIONS addictionsOthers) {
		this.addictionsOthers = addictionsOthers;
	}

	public String getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicalHistory other = (ClinicalHistory) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
}
