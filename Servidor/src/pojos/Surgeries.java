package pojos;
import java.io.*;
import java.sql.Date;
import javax.persistence.*;


@Entity
@Table (name = "SURGERIES")
public class Surgeries implements Serializable {
	
	@Id
	@GeneratedValue(generator = "SURGERIES")
	@TableGenerator(name = "SURGERIES", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "SURGERIES")
	private Integer ID;
	private Date date;
	private String type;
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "IDTREATMENT")
	private Treatment treatment;
	
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn (name = "IDPATIENT")
	private Patient patient;
	
	public Surgeries() {
		this.ID = null;
		this.date = null;
		this.type = null;
		this.treatment = null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
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
		Surgeries other = (Surgeries) obj;
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
		return "Surgeries [ID=" + ID + ", date=" + date + ", type=" + type + "]";
	}
	
}
