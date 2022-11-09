package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String  name;
	private String  address;
	private Date 	dob;
	private Integer phoneNumber;
	private String  eMail;
	private MedicalHistory medicalHistory;
	private List<Doctor> doctors;
	private String username;
        private String password;
        private List<DailyRegister> dailyRegisters;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
 
	
	public Patient(Integer id, String name, String address, Date dob, Integer phoneNumber, String eMail, List<DailyRegister> dailyRegisters) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
                this.dailyRegisters = dailyRegisters;
	}
	

	public Patient() {
		super();
	}

	public Patient(Integer id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}

	public Patient(Integer id, String name, String address, Date dob, Integer phoneNumber, String eMail,MedicalHistory mh
			,List<Doctor> docs)
	{
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.medicalHistory=mh;
		this.doctors = docs;
	}

	public Patient(String name, String address, Date dob, Integer phoneNumber, String eMail) {
		super();
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
	this.phoneNumber = phoneNumber;
	}


	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}


	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}


	
	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}


	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

    public List<DailyRegister> getDailyRegisters() {
        return dailyRegisters;
    }

    public void setDailyRegisters(List<DailyRegister> dailyRegisters) {
        this.dailyRegisters = dailyRegisters;
    }

        
        
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [ID=" + id + ",Name=" + name + ",Address=" + address + ",DOB=" + dob + ",Phone number="
				+ phoneNumber + ",Email=" + eMail +"]";
	}
	
	
}
