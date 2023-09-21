package user.management;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class UserList {
private final ArrayList <User> users;
	
	public UserList() {
		users = new ArrayList<>();
	}
	
	public void createUserAccount() {
		while(true) {
			String username;
			User duplicateUser;
			do {
				username = Validation.inputString("Enter username: ", "[\\w]{5,}", "Username must have at least 5 characters (only letters, numbers and underscores) and no space.");
				duplicateUser = findUserByUsername(username);
				if(duplicateUser != null) 
					System.err.println("This username exists.");
			}while(duplicateUser != null);
			String firstName = Validation.inputString("Enter first name: ", "[A-Za-z\\s]+", "Invalid first name. Please enter again.");
			String lastName = Validation.inputString("Enter last name: ", "[A-Za-z\\s]+", "Invalid last name. Please enter again.");
			String password = Validation.inputString("Enter password: ", "[^\\s]{6,}", "Password must have at least 6 characters and no space.");
			String confirm;
			do {
				confirm = Validation.inputString("Confirm password: ", "[^\\s]{6,}", "Confirm password must have at least 6 characters and no space.");
				if(!confirm.equals(password))
					System.err.println("Confirm password and password does not match.");
			}while(!confirm.equals(password));
			
			String phone = Validation.inputString("Enter phone number: ", "\\d{10}", "Phone number must contain 10 numbers.");
			String email;
			boolean validLocalPart;
			do {
				email = Validation.inputString("Enter email: ", "[a-z]+\\.[a-z]+@[a-z]+\\.com", "Email must follow standard format: firstname.lastname@domain.com");
				validLocalPart = checkValidLocalPart(email, firstName, lastName, "Email must follow standard format: firstname.lastname@domain.com");
			}while(!validLocalPart);
			users.add(new User(username, firstName, lastName, password, confirm, phone, email));
            String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
            if(confirmToContinue.equalsIgnoreCase("N"))
                break;
        }
	}
	
	public boolean checkValidLocalPart(String email, String firstName, String lastName, String errorMess) {
		String localPart = email.split("@")[0];
		if(!localPart.split("\\.")[0].equalsIgnoreCase(firstName) || !localPart.split("\\.")[1].equalsIgnoreCase(lastName)) {
			System.err.println(errorMess);
			return false;
		}
		return true;
	}
	
	public User findUserByUsername(String username) {
		if(!users.isEmpty()) {
			for(User user : users)
				if(user.getUsername().equals(username))
					return user;
		}
		return null;
	}
	
	public void loadDataFromFile(){
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\user.txt");
		if(file.isFile()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while(true) {
					line = br.readLine();
					if(line == null)
						break;
					String[] info = line.split(",");
					String username = info[0];
					String firstName = info[1];
					String lastName = info[2];
					String password = info[3];
					String confirm = info[4];
					String phone = info[5];
					String email = info[6];
					users.add(new User(username, firstName, lastName, password, confirm, phone, email));
				}
				fr.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveDataToFile() {
		String projectDirectory = System.getProperty("user.dir");
		File file = new File(projectDirectory + "\\data\\user.txt");
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(User updatedUser : users) {
				bw.write(updatedUser.toString());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkUserExist() {
		while(true) {
			String username = Validation.inputString("Enter username: ", "[A-Za-z0-9]{5,}", "Username must have at least 5 characters and no space.");
			if(findUserByUsername(username) == null)
				System.err.println("No User Found!");
			else
				System.out.println("Exist User.");
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
		
	}
	
	public void printUserList(ArrayList <User> userList) {
		System.out.printf("%-6s%-14s%-14s%-14s%-14s%-14s%-34s\n", "No.","Username", "First name", "Last name", "Password", "Phone number", "Email");
		for(User user : userList) {
			System.out.printf("%3d   %-14s%-14s%-14s%-14s%-14s%-34s\n", userList.indexOf(user)+1, user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getPhone(), user.getEmail());
		}	
	}
	
	public void searchUserInfoByName() {
		while(true) {
			String name = Validation.inputString("Enter name: ", "^[A-Za-z]+", "Invalid name. Please enter again.");
			ArrayList <User> usersFoundByName = new ArrayList<>();
			for(User user : users) {
				if(user.getFirstName().toLowerCase().contains(name.toLowerCase()) || user.getLastName().toLowerCase().contains(name.toLowerCase()))
					usersFoundByName.add(user);
			}
			if(usersFoundByName.isEmpty()) {
				System.err.println("Have no any user.");
			}else {
				//sort list of users by first name
				usersFoundByName.sort(Comparator.comparing(User::getFirstName));
				printUserList(usersFoundByName);
			}
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void printAllListFromFile() {
		ArrayList <User> data = new ArrayList<>(users);
		data.sort(Comparator.comparing(User::getFirstName));
		printUserList(data);
	}
	
	public boolean logIn(String username, String password) {
		if(findUserByUsername(username) == null) {
			System.err.println("Username does not exist.");
		} else {
			for(User user : users)
				if(user.getPassword().equals(password)) {
					System.out.println("Login successfully!");
					return true;
				}		
			System.err.println("Incorrect password.");
		}
		return false;
	}
	
	public void updateUserInfo() {
		while(true) {
			String username;
			String password;
			while(true) {
				System.out.println("\tLOG IN");
				username = Validation.inputString("Username: ", "[\\w]{5,}", "Username must have at least 5 characters (only letters, numbers and underscores) and no space.");
				password = Validation.inputString("Password: ", "[^\\s]{6,}", "Password must have at least 6 characters and no space.");
				if(logIn(username, password))
					break;
			} 
			User loggedInUser = findUserByUsername(username);
			String newFirstName = Validation.inputString("Enter new first name: ", "([\\s]{0,}|[A-Za-z\\s]+)", "Invalid first name. Please enter again.");
			String newLastName = Validation.inputString("Enter new last name: ", "([\\s]{0,}|[A-Za-z\\s]+)", "Invalid last name. Please enter again.");
			String newPassword = Validation.inputString("Enter new password: ", "([\\s]{0,}|[^\\s]{6,})", "Password must have at least 6 characters and no space.");
			String newConfirm;
			do {
				newConfirm = Validation.inputString("Confirm password: ", "([\\s]{0,}|[^\\s]{6,})", "Confirm password must have at least 6 characters and no space.");
				if(!newConfirm.equals(newPassword))
					System.err.println("Confirm password and password does not match.");
			}while(!newConfirm.equals(newPassword));
			String newPhone = Validation.inputString("Enter new phone number: ", "([\\s]{0,}|\\d{10})", "Phone number must contain 10 numbers.");
			String newEmail;
			String emailToCheck;
			boolean validLocalPart = false;
			do {
				newEmail = Validation.inputString("Enter new email: ", "([\\s]{0,}|[a-z]+\\.[a-z]+@[a-z]+\\.com)", "Email must follow standard format: firstname.lastname@domain.com");
				emailToCheck = newEmail.isBlank() ? loggedInUser.getEmail() : newEmail;
				if(!newFirstName.isBlank() && newLastName.isBlank())
					validLocalPart = checkValidLocalPart(emailToCheck, newFirstName, loggedInUser.getLastName(), "You've change first name. Email must follow standard format: firstname.lastname@domain.com");
				else if(newFirstName.isBlank() && !newLastName.isBlank())
					validLocalPart = checkValidLocalPart(emailToCheck, loggedInUser.getFirstName(), newLastName, "You've change last name. Email must follow standard format: firstname.lastname@domain.com");
				else if(!newFirstName.isBlank())
					validLocalPart = checkValidLocalPart(emailToCheck, newFirstName, loggedInUser.getLastName(), "You've change first name and lastname. Email must follow standard format: firstname.lastname@domain.com");
				else if(newEmail.isBlank())
					break;
			}while(!validLocalPart);
			String confirmToUpdate = Validation.inputString("Are you sure you want to update this user (Y/N)? Choose Y to update, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToUpdate.equalsIgnoreCase("Y")) {
				if(!newFirstName.isBlank())
					loggedInUser.setFirstName(newFirstName);
				if(!newLastName.isBlank())
					loggedInUser.setLastName(newLastName);
				if(!newPassword.isBlank())
					loggedInUser.setPassword(newPassword);
				if(!newConfirm.isBlank())
					loggedInUser.setConfirm(newConfirm);
				if(!newPhone.isBlank())
					loggedInUser.setPhone(newPhone);
				if(!newEmail.isBlank())
					loggedInUser.setEmail(newEmail);
				System.out.println("Success!");
				saveDataToFile();
			}else {
				System.err.println("Fail!");
			}
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}
	
	public void deleteUserInfo() {
		while(true) {
			String username;
			String password;
			while(true) {
				System.out.println("\tLOG IN");
				username = Validation.inputString("Username: ", "[\\w]{5,}", "Username must have at least 5 characters (only letters, numbers and underscores) and no space.");
				password = Validation.inputString("Password: ", "[^\\s]{6,}", "Password must have at least 6 characters and no space.");
				if(logIn(username, password))
					break;
			}
			User loggedInUser = findUserByUsername(username);
			String confirmToDelete = Validation.inputString("Are you sure you want to delete this user (Y/N)? Choose Y to delete, choose N to cancel.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToDelete.equalsIgnoreCase("Y")) {
				users.remove(loggedInUser);
				System.out.println("Success!");
				saveDataToFile();
			}else {
				System.err.println("Fail!");
			}
			String confirmToContinue = Validation.inputString("Do you want to continue (Y/N)? Choose Y to continue, choose N to return to the menu.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
			if(confirmToContinue.equalsIgnoreCase("N"))
				break;
		}
	}

	//Sample data
	public void generateUser() {
		users.add(new User("jwhite", "Johnson", "White", "zhFsKLN&", "zhFsKLN&", "4084967223", "johnson.white@gmail.com"));
		users.add(new User("aborden", "Ashley", "Borden", "oBuzMQTO", "oBuzMQTO", "7859396046", "ashley.borden@gmail.com"));
		users.add(new User("mgreen", "Majorie", "Green", "m2XMLcRp", "m2XMLcRp", "4159867020", "majorie.green@gmail.com"));
		users.add(new User("raragon", "Robert", "Aragon", "D2wcEl37", "D2wcEl37", "8166456936", "robert.aragon@gmail.com"));
		users.add(new User("jrussell", "Jacki", "Russell", "QgHdBCF9", "QgHdBCF9", "9132276106", "jacki.russell@gmail.com"));
		users.add(new User("lvenson", "Lillian", "Venson", "YwGwVmAO", "YwGwVmAO", "3085838759", "lillian.venson@gmail.com"));
		users.add(new User("tconley", "Thomas", "Conley", "TsXPQYht", "TsXPQYht", "9196566779", "thomas.conley@gmail.com"));
		users.add(new User("cjackson", "Charles", "Jackson", "1acy56Ss", "1acy56Ss", "2128474915", "charles.jackson@gmail.com"));
		users.add(new User("sdavis", "Susan", "Davis", "ag78j72k", "ag78j72k", "2052219156", "susan.davis@gmail.com"));
		users.add(new User("lgarrison", "Lisa", "Garrison", "lqFMDnEr", "lqFMDnEr", "3379652982", "lisa.garrison@gmail.com"));
		users.add(new User("vfaulkner", "Victor", "Faulkner", "3XZ7T7rG", "3XZ7T7rG", "4193403832", "victor.faulkner@gmail.com"));
		users.add(new User("anelson", "Agnes", "Sanford", "Ix2mvJdm", "Ix2mvJdm", "6269913620", "agnes.sanford@gmail.com"));
	}

	
}
