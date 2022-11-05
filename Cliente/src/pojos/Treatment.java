package pojos;
import java.io.*;
import java.sql.Date;


//*CLASE NO ACABADA endate y startdate salen en las tables

public class Treatment implements Serializable{
	
	private Integer ID;
	
	private Date startDate;

	private Date endDate;
	
	private Patient patient;
	
	public enum typeTreatment {
		MEDICATION, REHAB, OTHER
	};
	
	private typeTreatment treatment;

	private Illness illness; 
	
	private String description;
	
	private String tResults;

	private Allergies allergy;

	private Surgeries surgery;

	public Treatment() {
		this.ID = null;
		this.startDate = null;
		this.endDate = null;
		this.treatment = null;
		this.description = null;
		this.tResults = null;
	}

	public int getIDtreatment() {
		return ID;
	}

	public void setIDtreatment(int iDtreatment) {
		ID = iDtreatment;
	}

	public String getResults() {
		return this.tResults;
	}

	public void setResults(String results) {
		this.tResults = results;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public typeTreatment getTreatment() {
		return treatment;
	}

	public void setTreatment(typeTreatment treatment) {
		this.treatment = treatment;
	}

	public String getDescrpition() {
		return description;
	}

	public void setDescrpition(String descrpition) {
		this.description = descrpition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		Treatment other = (Treatment) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Surgeries getSurgery() {
		return surgery;
	}

	public void setSurgery(Surgeries surgery) {
		this.surgery = surgery;
	}
	
	
	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	@Override
	public String toString() {
		return "Treatment [startDate=" + startDate + ", endDate=" + endDate + ", treatment=" + treatment + ", illness="
				+ illness + ", description=" + description + ", tResults=" + tResults + "]";
	}
}
