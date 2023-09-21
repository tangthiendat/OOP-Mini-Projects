package student.management;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Comparator;
import java.util.TreeMap;

public class StudentList {
	private final ArrayList<Student> students;
	
	public StudentList() {
		students = new ArrayList<>();
	}
	
	public boolean isStudentExist(String id, String name, String semester, String course) {
		for(Student student : students) {
			if(student.getId().equals(id) && student.getStudentName().equals(name) && student.getSemester().equals(semester) && student.getCourseName().equals(course))
				return true;
		}
		return false;
	}
	
	public void createStudent() {
		while(true) {
			if(students.size() >= 10) {
				String answer = Validation.inputString("Do you want to continue(Y/N)? Choose Y to continue, choose N to return main screen.\n", "(Y|y|N|n)", "Please enter Y/y or N/n.");
				if(answer.equalsIgnoreCase("N"))	
					break;
			}
			System.out.println("Enter record " + (students.size()+1) + ":");
			String id = Validation.inputString("Enter student ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			String name = Validation.inputString("Enter student name: ", "[A-Za-z\\s]+", "Invalid student name. Please enter again.");
			String semester = Validation.inputString("Enter semester: ", "[A-Za-z0-9\\s]+", "Invalid semester. Please enter again.");
			String course = Validation.inputString("Enter course: ", "(Java|.NET|C/C\\++)", "There are only three courses: Java, .NET, C/C++. Please enter again.");
			if(!isStudentExist(id, name, semester, course)) {
				students.add(new Student(id, name, semester, course));
			}else {
				System.err.println("This record is existed.");
			}
		}
	}
	
	public ArrayList <Student> findStudentByName(String name){
		ArrayList <Student> studentsFoundByName = new ArrayList<>();
		for(Student student : students) {
			if(student.getStudentName().toLowerCase().contains(name.toLowerCase())) 
				studentsFoundByName.add(student);
		}
		return studentsFoundByName;
	}
	
	public ArrayList <Student> findStudentByID(String id){
		ArrayList <Student> studentsFoundByID = new ArrayList<>();
		for(Student student : students) {
			if(student.getId().equalsIgnoreCase(id)) 
				studentsFoundByID.add(student);
		}
		return studentsFoundByID;
	}
	
	public void findAndSort() {
		if(students.isEmpty()) {
			System.err.println("No student.");
		} else {
			String name = Validation.inputString("Enter student name: ", "[A-Za-z\\\\s]+", "Invalid student name. Please enter again.");
			ArrayList <Student> studentsFoundByName = findStudentByName(name);
			if(studentsFoundByName.isEmpty()) {
				System.err.println("This student does not exist.");
			} else {
				//sort list of students by name
				Collections.sort(studentsFoundByName, (Student s1, Student s2) -> s1.getStudentName().compareTo(s2.getStudentName()));
				System.out.println("List of student(s) found: ");
				System.out.printf("%-20s%-10s%-10s\n", "Student name", "Semester", "Course name");
				for(Student student : studentsFoundByName)
					System.out.printf("%-20s%-10s%-10s\n", student.getStudentName(), student.getSemester(), student.getCourseName());
			}

		}
	}
	
	public void updateOrDelete() {
		if(students.isEmpty()) {
			System.err.println("No student.");
		} else {
			String id = Validation.inputString("Enter student ID: ", "[A-Za-z0-9\\s]+", "Invalid ID. Please enter again.");
			ArrayList <Student> studentsFoundByID = findStudentByID(id);
			if(studentsFoundByID.isEmpty()) {
				System.err.println("This student does not exist.");
			} else {
				System.out.println("List of student(s) found: ");
				System.out.printf("%-10s%-20s%-10s%-10s\n", "No.", "Student name", "Semester", "Course name");
				for(Student student : studentsFoundByID) {
					System.out.printf("%-10d%-20s%-10s%-10s\n", studentsFoundByID.indexOf(student)+1, student.getStudentName(), student.getSemester(), student.getCourseName());
				}
				//choose student that you want to update/delete
				int choice = Validation.inputInt("Enter No.: ", 1, studentsFoundByID.size(), "Invalid number. Please enter again.");
				Student chosenStudent = studentsFoundByID.get(choice-1);
				String answer = Validation.inputString("Do you want to update (U) or delete (D) student? ", "(U|u|D|d)", "Please enter U/u or D/d." );
				if(answer.equalsIgnoreCase("U")){
					while(true) {
						String name = Validation.inputString("Enter new student name: ", "[A-Za-z\\s]+", "Invalid student name. Please enter again.");
						String semester = Validation.inputString("Enter new semester: ", "[A-Za-z0-9\\s]+", "Invalid semester. Please enter again.");
						String course = Validation.inputString("Enter new course: ", "(Java|.NET|C/C\\++)", "There are only three courses: Java, .NET, C/C++. Please enter again.");
						if(isStudentExist(id, name, semester, course))
							System.err.println("Duplicate.");
						else {
							//if name is changed -> change all identical names
							if(!name.equalsIgnoreCase(chosenStudent.getStudentName())) {
								for(Student student : students) {
									if(student.getId().equalsIgnoreCase(id)) {
										student.setStudentName(name);
									}
								}
							}
							//change other attributes
							students.get(students.indexOf(chosenStudent)).setSemester(semester);
							students.get(students.indexOf(chosenStudent)).setCourseName(course);
							break;
						}
					}
				}else
					students.remove(chosenStudent);
			}
		}
		
	}
	
	public void report() {
		if(students.isEmpty()) {
			System.err.println("No student.");
		} else {
			TreeMap <String, Integer> reports = new TreeMap<>();
			for(Student student : students) {
				String key = student.getStudentName() + "#" +student.getCourseName();
				int totalCourse = reports.getOrDefault(key, 0);
				reports.put(key, totalCourse+1);
			}
			//print report
			System.out.println("|--------------------|-------------|---------|");
			System.out.println("|    Student name    | Course name |  Total  |");
			System.out.println("|--------------------|-------------|---------|");
			for(String key : reports.keySet()) {
				System.out.printf("|%-20s|%-13s|%9d|\n",key.split("#")[0], key.split("#")[1], reports.get(key));
			}
			System.out.println("|--------------------|-------------|---------|");
		}
	}

	//Sample data
	public void generateStudent() {
		students.add(new Student("B2103494", "Tang Thien Dat", "1st", "Java"));
		students.add(new Student("B2103494", "Tang Thien Dat", "3rd", ".NET"));
		students.add(new Student("B2103494", "Tang Thien Dat", "1st", ".NET"));
		students.add(new Student("B2110081", "Pham Quoc Khang", "1st", "Java"));
		students.add(new Student("B2110081", "Pham Quoc Khang", "2nd", "Java"));
		students.add(new Student("B2110093", "Ha La Phin", "1st", "C/C++"));
		students.add(new Student("B2110068", "Luu Hua Thien An", "1st", "Java"));
		students.add(new Student("B2111802", "Duong Lap Khang", "1st", ".NET"));
		students.add(new Student("B2111802", "Duong Lap Khang", "3rd", "Java"));
		students.add(new Student("B2103507", "Ngo Phuoc Loi", "1st", "C/C++"));
	}
}
