import java.io.*;
import java.util.*;

public class FileInput {
	
	String fullName;
	String firstName;
	String lastName;
	String id;
	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<String> exams = new ArrayList<String>();
	ArrayList<Student> students = new ArrayList<Student>();
	String total;
	String finalExam;
	String letterGrade;

	
	FileReader fileReader = null;
	String line = null;
	Scanner reader = null;

	int numOfStudents = 0;
	int count = 0;
	
	
	public void readFile(String fileName){
			
		
		try{
			
			reader = new Scanner(new File(fileName));
			
			int count = 0;
			int numOfStudents = 0;
			
			while (reader.hasNextLine()){
				reader.nextLine();
				numOfStudents++;
			}
			numOfStudents = numOfStudents -1;
			reader.close();
			reader = new Scanner(new File(fileName));

			
			
			int studentNum = 0;
			
			String headerLine = reader.nextLine();
			String[] headerToken = headerLine.split(",");
			
			ArrayList<Assignment> assignments = new ArrayList<Assignment>();
			ArrayList<Project> projects = new ArrayList<Project>();
			ArrayList<String> exams = new ArrayList<String>();
			ArrayList<Student> students = new ArrayList<Student>();
			
			String studentLine = reader.nextLine();
			String[] data = studentLine.split(",");
			
			while (reader.hasNextLine()){
				
				if (!reader.hasNext()){
					studentLine = reader.nextLine();
					data = studentLine.split(",");
					count = 0;
				}
				
				
				if (headerToken[count].contains("Student Name")){
					fullName = (data[count]);
				}
				if (headerToken[count].contains("FIRST NAME")){
					firstName = (data[count]);
				}
				if (headerToken[count].contains("LAST NAME")){
					lastName = (data[count]);
				}
				if (headerToken[count].contains("Id") || headerToken[count].contains("ID")){
					id = (data[count]);
				}
				if (headerToken[count].contains("Assignment")){
					
					Assignment assignment = new Assignment(headerToken[count], data[count], data[count +1]);
					
					assignments.add(assignment);
					count ++;
				}
				if (headerToken[count].contains("project") || headerToken[count].contains("Project") ||headerToken[count].contains("ray")
						|| headerToken[count].contains("Sprint") || headerToken[count].contains("Paper")){
					
					Project project = new Project(headerToken[count], data[count], data[count +1]);
					
					projects.add(project);
					count ++;
				}
				if (headerToken[count].contains("Exam")){
					
					exams.add(data[count]);
				}
				if (headerToken[count].contains("Final")){
					finalExam = (data[count]);
				}
				if (headerToken[count].contains("Total")){
					total = (data[count]);
				}
				if (headerToken[count].contains("grade") || headerToken[count].contains("Grade")){
					letterGrade = (data[count]);
				}
				

				
				if (!reader.hasNext()){
					Student student = new Student(firstName, lastName, id, assignments, projects, exams,
							total, finalExam, letterGrade);
					
					
					students.add(student);
				}
				
				count++;
				studentNum++;
					
			}
			
			reader.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file: " 
                + fileName);                  
            System.exit(1);
        }
	}
	
}
	