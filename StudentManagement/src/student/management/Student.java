package student.management;

public class Student {
	private String id, studentName, semester, courseName;

	public Student(String id, String studentName, String semester, String courseName) {
		this.id = id;
		this.studentName = studentName;
		this.semester = semester;
		this.courseName = courseName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
