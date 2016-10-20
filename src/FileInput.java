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
	
	
	public void readFile(String fileName, String cutOff){
			
		
		try{
			System.out.println("Number of students currently in directory: " +numOfStudents);
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
			String[] headerToken = headerLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			ArrayList<Assignment> assignments = new ArrayList<Assignment>();
			ArrayList<Project> projects = new ArrayList<Project>();
			ArrayList<String> exams = new ArrayList<String>();
			ArrayList<Student> students = new ArrayList<Student>();
			
			String studentLine = reader.nextLine();
			String[] data = studentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			while (true){

				
				
				if (headerToken[count].contains("Student Name")){
					fullName = (data[count]);
					String[] name = fullName.split(","); //Last minute add
					firstName = name[1];
					lastName = name[0];
				}
				else if (headerToken[count].contains("FIRST NAME")){
					firstName = (data[count]);
				}
				else if (headerToken[count].contains("LAST NAME")){
					lastName = (data[count]);
				}
				else if (headerToken[count].contains("Id") || headerToken[count].contains("ID")){
					id = (data[count]);
				}
				else if (headerToken[count].contains("Assignment")){
					
					Assignment assignment = new Assignment(cutOff +"-" +headerToken[count], data[count], data[count +1]);
					
					assignments.add(assignment);
					count ++;
					
				}
				else if (headerToken[count].contains("project") || headerToken[count].contains("Project") ||headerToken[count].contains("ray")
						|| headerToken[count].contains("Sprint") || headerToken[count].contains("Paper")){
					
					Project project = new Project(headerToken[count], data[count], data[count +1]);
					
					projects.add(project);
					count ++;
				}
				else if (headerToken[count].contains("Exam")){
					
					exams.add(data[count]);
				}
				else if (headerToken[count].contains("Final")){
					finalExam = (data[count]);
				}
				else if (headerToken[count].contains("Total")){
					total = (data[count]);
				}
				if (headerToken[count].contains("grade") || headerToken[count].contains("Grade")){
					letterGrade = (data[count]);
					
					Student student = new Student(firstName, lastName, id, assignments, projects, exams,
							total, finalExam, letterGrade);
					
					
					students.add(student);
					count = -1;
					
					studentNum++;
					
					if (numOfStudents == studentNum){
						break;
					}


					studentLine = reader.nextLine();
					data = studentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					
					

				}
			
				
				count++;
				
				//System.out.println(students.size());
					
			}
			System.out.println("Successfully added file to repository");
			System.out.println("Number of students read: " + numOfStudents);
			
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
	
	public void addStudent(String id, String outFile)
	{
		FileWriter writer = null;
		try{
			writer = new FileWriter(outFile);
		}
		catch(IOException ex) {
                System.out.println(
                    "Error writing to file: " 
                    + outFile);                  
                System.exit(1);
            }
		
		Student student = new Student();
		
		for (int i = 0; i < students.size(); i++){
			if (id == students.get(i).getId()){
				student = students.get(i);
				break;
			}
		}
		
		String studentInfo = student.toString();
		
		//write to file outFile
	}
	
	public String[] gradeSearch(String criteria)
	{
		String[] searchParam = criteria.split(",");
		String[] gradeResults = new String[4];
		
		return gradeResults;
	}
	
}
	