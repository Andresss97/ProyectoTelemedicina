package pojos;

import java.io.Serializable;
import java.sql.Date;

public class Illness implements Serializable {

	private Integer ID;
	
	private String description;
	
	private Date date;

	private Patient patient;

	private String value;
	
	private String name;

	private Treatment treatment;

	
	public Illness() {
		this.name = null;
		this.description = null;
		this.date = null;
		this.value = null;
		this.ID = null;
	}

	public Illness(String name, String des, Date date, String type) {
		this.name = name;
		this.description = des;
		this.date = date;
		this.value = type;
	}

	public int getID() {
		return this.ID;
	}

	public Treatment getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public void setID(int num) {
		this.ID = num;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String type) {
		this.value = type;
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
		Illness other = (Illness) obj;
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

	@Override
	public String toString() {
		return "Illness [description=" + description + ", date_disease=" + date + ", type=" + value + ", name="
				+ name + "]";
	}

}
