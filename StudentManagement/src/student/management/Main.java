package student.management;

public class Main {

	public static void main(String[] args) {
		StudentList studentList = new StudentList();
		studentList.generateStudent();
		while(true) {
			System.out.println("WELCOME TO STUDENT MANAGEMENT");
			System.out.println("\t1. Create\n"
							 + "\t2. Find and Sort\n"
							 + "\t3. Update/Delete\n"
							 + "\t4. Report\n"
							 + "\t5. Exit");
			System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).");
			int choice = Validation.inputInt("Enter choice: ", 1, 5, "Invalid choice. Please enter again.");
			switch(choice) {
				case 1:
					studentList.createStudent();
					break;
				case 2:
					studentList.findAndSort();
					break;
				case 3:
					studentList.updateOrDelete();
					break;
				case 4:
					studentList.report();
					break;
				case 5:
					return;
			}
		}

	}

}
