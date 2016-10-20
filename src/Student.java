import java.util.ArrayList;

public class Student {
	
	
	private String fullName;
	private String firstName;
	private String lastName;
	private String id;
	private ArrayList<Assignment> assignments;
	private ArrayList<Project> projects;
	private ArrayList<String> exams;
		
	private String total;
	private String finalExam;
	private String letterGrade;
	
	Assignment assignment;
	
	
	public Student(){
	}
	
	public Student(String firstName, String lastName, String id, ArrayList<Assignment> assignments,
			ArrayList<Project> projects, ArrayList<String> exams, String total, String finalExam, String letterGrade){
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.assignments = assignments;
		this.projects = projects;
		this.exams = exams;
		this.total = total;
		this.finalExam = finalExam;
		this.letterGrade = letterGrade;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignment(ArrayList<Assignment> assignments) {
		this.assignments = assignments;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProject(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public ArrayList<String> getExams() {
		return exams;
	}

	public void setExam(ArrayList<String> exams) {
		this.exams = exams;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFinalExam() {
		return finalExam;
	}

	public void setFinalExam(String finalExam) {
		this.finalExam = finalExam;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	
	public void showStudent(){
		System.out.println("Last name: " + getLastName());
		System.out.println("First name: " + getFirstName());
		System.out.println("ID: " + getId());
		//System.out.println("Assignment: " + assignments.getName());
		System.out.println("Final Exam Score: " + getFinalExam());
		System.out.println("Total: " + getTotal());
		System.out.println("Grade: " + getFinalExam());
		System.out.println();
	} 
	
	public String toString()
	{
		String header = new String("Student Id,First Name,Last Name,");
		
		for(int i = 0; i < assignments.size(); i++)
		{
			header += assignments.get(i).getName() + "," + assignments.get(i).getName() +" Comment,";
		}
		
		for(int j = 0; j < projects.size(); j++)
		{
			header += projects.get(j).getName() +"," +projects.get(j).getName() +" Comment,";
		}
		
		for(int l = 0; l < exams.size(); l++)
		{
			if(l+1 >= exams.size())
				header += "Exam " +exams.size() +",";
			else
				header += "Exam " +l+1 +",";
		}
		
		header += "Final Exam, Total, Letter Grade\n";
		
		//------------------
		
		String body = new String(getId() +"," +getFirstName() +"," +getLastName() +",");
		
		for(int i = 0; i < assignments.size(); i++)
		{
			body += assignments.get(i).getScore() +"," +assignments.get(i).getComment() +",";
		}
		
		for(int j = 0; j < projects.size(); j++)
		{
			body += projects.get(j).getScore() +"," +projects.get(j).getComment() +",";
		}
		
		for(int l = 0; l < exams.size(); l++)
		{
			body += exams.get(l) +",";
		}
		
		String[] lGrade = letterGrade.split(",");
		
		body += finalExam +"," +total +"," +lGrade[0] +"\n";
		
		String out = header + body;
		
		return out;
	}
}
