package pojos;

import java.io.Serializable;
import java.util.Objects;

public class Administrator implements Serializable {
	private String username;
	private String password;
	
	public Administrator() {
		this.username = "admin";
		this.password = "indi1111";
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

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	
}
