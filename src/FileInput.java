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
	int count = 0;
	
	public boolean readFile(String fileName, String cutOff){
			
		
		try{
			System.out.println("Number of students currently in directory: " +students.size());
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
			
			String studentLine = reader.nextLine();
			String[] data = studentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			
			while (true){
				
				if (headerToken[count].contains("Student Name")){
					fullName = (data[count]);
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
					count++;
					
				}
				else if (headerToken[count].contains("project") || headerToken[count].contains("Project") ||headerToken[count].contains("ray")
						|| headerToken[count].contains("Sprint") || headerToken[count].contains("Paper")){
					
					Project project = new Project(cutOff +"-" +headerToken[count], data[count], data[count +1]);
					
					projects.add(project);
					count++;
				}
				else if (headerToken[count].contains("Exam") && !headerToken[count].contains("Final")){
					
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
					
					if(firstName != null && lastName != null)
						fullName = firstName + " " + lastName;

					if (fullName.contains(",")){
						String[] fullNameArr = fullName.split(",");
											
						fullName = fullNameArr[1] + " " + fullNameArr[0];
						
					}
					
					Student student = new Student(fullName, id, assignments, projects, exams,
							total, finalExam, letterGrade);
					
					
					students.add(student);
					count = -1;
					
					assignments = new ArrayList<Assignment>();
					projects = new ArrayList<Project>();
					exams = new ArrayList<String>(); 
					
					studentNum++;
					
					if (numOfStudents == studentNum){
						break;
					}


					studentLine = reader.nextLine();
					data = studentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				}
				
				count++;
					
			}
			System.out.println("Successfully added file to repository");
			System.out.println("Number of students read: " + numOfStudents);
			
			reader.close();
			
			return true;
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
		return false;
	}
	
	public void addStudent(String id, String outFile) 
	{		
		int[] studentsFound = {-1,-1,-1};
		
		for (int i = 0; i < students.size(); i++){ 
			if (id.equals(students.get(i).getId())){
				if(studentsFound[0] == -1)
					studentsFound[0] = i;
				else if(studentsFound[1] == -1)
					studentsFound[1] = i;
				else
					studentsFound[2] = i;
			}
		}
		
		if(studentsFound[0] == -1 && studentsFound[1] == -1 && studentsFound[2] == -1)
			System.out.println("Student was not found in the repository.");
		else
		{	
			FileWriter writer = null;
			
			String studentInfo0 = students.get(studentsFound[0]).toString();
			String studentInfo1 = "";
			String studentInfo2 = "";
			
			if((studentsFound[1] != -1))
			{
				studentInfo1 = students.get(studentsFound[1]).toString();
				studentInfo0 += studentInfo1;
			}
			if((studentsFound[2] != -1))
			{
				studentInfo2 = students.get(studentsFound[2]).toString();
				studentInfo0 += studentInfo2;
			}
				
			try{
				writer = new FileWriter(outFile);
				writer.write(studentInfo0);
				System.out.println("\t File Written  \n\n");
				writer.close();
				
			}
			catch(IOException ex) {
	                System.out.println(
	                    "Error writing to file: " 
	                    + outFile);                  
	                System.exit(1);
	            }
		}
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
		int[] gradeResults = new int[5];

		for (int i = 0; i < students.size(); i++){
			String studentGrade = students.get(i).getLetterGrade();
			
			studentGrade = studentGrade.toUpperCase();
			
			String[] gradeWithFile = studentGrade.split(",");
			
			String[] fileGradeCameFrom = gradeWithFile[1].split("-");
			
			if (userParam[0].equals("NONE") && userParam[2].equals("NONE") && userParam[1].equals("FALL")){ //user only enters fall
				if (gradeWithFile[0].equals("A")){
					numOfAs++;
				}
				if (gradeWithFile[0].equals("B")){
					numOfBs++;
				}
				if (gradeWithFile[0].equals("C")){
					numOfCs++;
				}
				if (gradeWithFile[0].equals("D")){
					numOfDs++;
				}
				if (gradeWithFile[0].equals("F")){
					numOfFs++;
				}
			}
			
			else if (!userParam[0].equals("NONE") && !userParam[2].equals("NONE")){ // search by course and year
				if (userParam[0].equals(fileGradeCameFrom[0]) && userParam[2].equals(fileGradeCameFrom[2])){
					if (gradeWithFile[0].equals("A")){
						numOfAs++;
					}
					if (gradeWithFile[0].equals("B")){
						numOfBs++;
					}
					if (gradeWithFile[0].equals("C")){
						numOfCs++;
					}
					if (gradeWithFile[0].equals("D")){
						numOfDs++;
					}
					if (gradeWithFile[0].equals("F")){
						numOfFs++;
					}
				}
			}
			
			else if(!userParam[0].equals("NONE")) //search by course 
			{
				if(userParam[0].equals(fileGradeCameFrom[0])){
					if (gradeWithFile[0].equals("A")){
						numOfAs++;
					}
					if (gradeWithFile[0].equals("B")){
						numOfBs++;
					}
					if (gradeWithFile[0].equals("C")){
						numOfCs++;
					}
					if (gradeWithFile[0].equals("D")){
						numOfDs++;
					}
					if (gradeWithFile[0].equals("F")){
						numOfFs++;
					}	
				}
			}	
			else if(!userParam[2].equals("NONE")){ //search by year
				if(userParam[2].equals(fileGradeCameFrom[2])){
					if (gradeWithFile[0].equals("A")){
						numOfAs++;
					}
					if (gradeWithFile[0].equals("B")){
						numOfBs++;
					}
					if (gradeWithFile[0].equals("C")){
						numOfCs++;
					}
					if (gradeWithFile[0].equals("D")){
						numOfDs++;
					}
					if (gradeWithFile[0].equals("F")){
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
	