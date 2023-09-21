package user.management;

public class Main {
	public static void main(String[] args) {
		UserList userList = new UserList();
		userList.loadDataFromFile();
//		userList.generateUser();
		while(true) {
			System.out.println("WELCOME TO USER MANAGEMENT");
			System.out.println("""
                    \t1. Create user account
                    \t2. Check exist user
                    \t3. Search user information by name
                    \t4. Update user
                    \t5. Save account to file
                    \t6. Print list of users from file
                    \t7. Quit""");
			int option = Validation.inputInt("Enter option: ", 1, 7, "Invalid option. Please enter an integer from 1 to 7.");
			switch(option) {
				case 1:
					userList.createUserAccount();
					break;
				case 2:
					userList.checkUserExist();
					break;
				case 3:
					userList.searchUserInfoByName();
					break;
				case 4:
					System.out.println("\t\tUPDATE");
					System.out.println("\t4.1. Update user information\n"
									 + "\t4.2. Delete user information");
					int choice = Validation.inputInt("Enter suboption: ", 1, 2, "Invalid option. Please enter an integer from 1 to 2.");
					if(choice == 1) {
						userList.updateUserInfo();
					}else {
						userList.deleteUserInfo();
					}
					break;
				case 5:
					userList.saveDataToFile();
					System.out.println("Data saved!");
					break;
				case 6:
					userList.printAllListFromFile();
					break;
				case 7:
					return;
			}
		}
	}
}
