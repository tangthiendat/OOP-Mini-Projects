package user.management;

public class User {
	private String username, firstName, lastName, password, confirm, phone, email;
	
	public User() {
		username = "";
		firstName = "";
		lastName = "";
		password = "";
		confirm = "";
		phone = "";
		email = "";
	}

	public User(String username, String firstName, String lastName, String password, String confirm, String phone, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirm = confirm;
		this.phone = phone;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return username + "," + firstName + "," + lastName + "," + password + "," + confirm + "," + phone + "," + email;
	}
}
