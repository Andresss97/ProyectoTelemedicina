package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Doctor implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String address;
	private Date dob;
	private Integer phoneNumber;
	private String eMail;
	private String specialty;
	private Double salary;
        private String username;
        private String password;
        private ArrayList<Patient> patients;
        
	public Doctor() {
		super();
	}

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ArrayList <Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList <Patient> p) {
        this.patients = p;
    }
        
	public Doctor(Integer id, String name, String address, Date dob, Integer phoneNumber, String eMail,
			String speciality, Double salary, ArrayList <Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.specialty = speciality;
		this.salary = salary;
                this.patients = patients;
	}
	public Doctor(Integer id, String name, Integer phoneNumber, String eMail, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.specialty = speciality;
	}
	public Doctor(String name, String address, Date dob, Integer phoneNumber, String eMail,
			String speciality, Double salary) {
		super();
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.specialty = speciality;
		this.salary = salary;
	}

	public Doctor(Integer id, String name, String eMail){
		super();
		this.id = id;
		this.name = name;
		this.eMail = eMail;
	}
        
        


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() 
	{
		return "Doctor [ID=" + id + ",Name=" + name + ",DOB=" + dob + ",Address=" + address + ",Phone number="
				+ phoneNumber + ", Email=" + eMail + ", Specialty=" + specialty + ", Salary=" + salary + "]";
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
	public void setAddress(String address) {
		this.address = address;
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
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getSpeciality() {
		return specialty;
	}
	public void setSpeciality(String speciality) {
		this.specialty = speciality;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
