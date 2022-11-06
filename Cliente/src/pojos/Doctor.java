package pojos;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;

public class Doctor extends Person  {

	private Integer ID;

	private String speciality;

	private LinkedList<Patient>patients;
	
	public Doctor() {
		super();
		this.speciality = null;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	public String toString() {
		return "Dr:" + name + " " + surname;
	}
}
