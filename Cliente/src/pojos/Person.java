package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public abstract class Person implements Serializable {

	protected String username;
	protected String password;
	protected String email;
	protected String NIF;
	protected int mobilePhone;
	protected String name;
	protected String surname;
	protected Date dob;
	protected String gender;
	protected byte[] photo;
	
	protected Address address;

	protected Integer ID;

	@SuppressWarnings("deprecation")
	public Person() {
		this.username = null;
		this.password = null;
		this.email = null;
		this.mobilePhone = 0;
		this.name = null;
		this.surname = null;
		this.dob = null;
		this.gender = null;
		this.address = null;
		this.setID(0);
		this.photo = null;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(int mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public  int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
		Person other = (Person) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	 
}
