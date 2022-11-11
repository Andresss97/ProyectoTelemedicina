package pojos;

import java.io.Serializable;
import java.sql.Date;


public class DailyRegister implements Serializable 
{
	
	private Integer ID;
	private Date Day;
	private String Symptoms;
	private String EMG;
	private String ECG;
	private Integer idPatient;
	
	
	/*public DailyRegister(Integer ID, Date day, String symptoms, String EMG, String ECG)
	{
		ID = ID;
		Day = day;
		Symptoms = symptoms;
		EMG = EMG;
		ECG = ECG;
	}*/
	
	public DailyRegister(Integer ID, Date day, String symptoms, String EMG, String ECG)
	{
		super();
		this.ID = ID;
		this.Day = day;
		this.Symptoms = symptoms;
		this.EMG = EMG;
		this.ECG = ECG;
	}



	public DailyRegister()
	{
		super();
	}
	
	public Integer getID() 	{return ID;	}
	public void setID(Integer iD) {	this.ID = iD;}
	
	public Date getDay() {	return Day;	}
	public void setDay (Date day) {	this.Day = day;	}
	
	public String getSymptoms() {	return Symptoms;}
	public void setSymptoms(String symptoms) {this.Symptoms = symptoms;	}
	
	public String getEMG() {return EMG;	}
	public void setEMG(String EMG) {this.EMG = EMG;	}
	
	public String getECG() {	return ECG;	}
	public void setECG(String ECG) {this.ECG = ECG;	}
	

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
		DailyRegister other = (DailyRegister) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DailyRegister ID = " + ID;
	}

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }
	
	
	
}
