package mrs.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usr")
public class User implements Serializable {
	@Id
	private String userId;

	private String password;

	private String firstName;

	private String lastName;

	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("userId='").append(userId).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", roleName='").append(roleName).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (firstName != null ? !firstName.equals(user.firstName)
				: user.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
			return false;
		if (password != null ? !password.equals(user.password) : user.password != null)
			return false;
		if (roleName != null ? !roleName.equals(user.roleName) : user.roleName != null)
			return false;
		if (userId != null ? !userId.equals(user.userId) : user.userId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
		return result;
	}
}
