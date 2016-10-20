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
					letterGrade = (data[count] +"," +cutOff);
					
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
		
		int studentNumber = 0;
		
		for (int i = 0; i < students.size(); i++){ //TODO: FML is not looping
			if (id == students.get(i).getId()){
				studentNumber = i;
				i = students.size() + 1;
			}
		}
		
		
		FileWriter writer = null;
		String studentInfo = students.get(studentNumber).toString();
		
		
		
		try{
			writer = new FileWriter(outFile);
			writer.write(studentInfo);
			writer.close();
			
		}
		catch(IOException ex) {
                System.out.println(
                    "Error writing to file: " 
                    + outFile);                  
                System.exit(1);
            }
		
		
		
		//write to file outFile
	}
	
	public int[] gradeSearch(String criteria)
	{
		
		int numOfAs = 0;
		int numOfBs = 0;
		int numOfCs = 0;
		int numOfDs = 0;
		int numOfFs = 0;
		
		criteria = criteria.toUpperCase();
		String[] userParam = criteria.split(",");
		int[] gradeResults = new int[4];

		for (int i = 0; i < students.size(); i++){
			String studentGrade = students.get(i).getLetterGrade();
			
			studentGrade = studentGrade.toUpperCase();
			
			String[] params = studentGrade.split(",");
			
			String[] fileGradeCameFrom = params[1].split("-");
			
			if(userParam[0] != "NONE")
			{
				if(userParam[0].equals(fileGradeCameFrom[0])){
					if (students.get(i).getLetterGrade().equals("A")){
						numOfAs++;
					}
					if (students.get(i).getLetterGrade().equals("B")){
						numOfBs++;
					}
					if (students.get(i).getLetterGrade().equals("C")){
						numOfCs++;
					}
					if (students.get(i).getLetterGrade().equals("D")){
						numOfDs++;
					}
					if (students.get(i).getLetterGrade().equals("F")){
						numOfFs++;
					}	
				}
				if(userParam[2] != "NONE")
				{
					if(userParam[2].equals(fileGradeCameFrom[2])){
						if (students.get(i).getLetterGrade().equals("A")){
							numOfAs++;
						}
						if (students.get(i).getLetterGrade().equals("B")){
							numOfBs++;
						}
						if (students.get(i).getLetterGrade().equals("C")){
							numOfCs++;
						}
						if (students.get(i).getLetterGrade().equals("D")){
							numOfDs++;
						}
						if (students.get(i).getLetterGrade().equals("F")){
							numOfFs++;
						}	
					}
			}
			
		}
		
		gradeResults[0] = numOfAs;
		gradeResults[1] = numOfBs;
		gradeResults[2] = numOfCs;
		gradeResults[3] = numOfDs;
		gradeResults[4] = numOfFs;
		

		
		return gradeResults;
	}
	
}
	