import java.util.ArrayList;
import java.util.Scanner;

public class Menu 
{//start Menu
	public Scanner console = new Scanner(System.in);
	FileInput fileIn = new FileInput();
	int numOfFiles = 0;
	String[] nameOfFiles = new String[3];
	ArrayList<String> filesAdded = new ArrayList<String>();
	boolean added = false;
	boolean repeatedFile = false;
	
	public void printMenu()
	{//start printMenu()
		
		System.out.println("...Please use a command option from the list below...");
		System.out.println(":Add data: A");
		System.out.println(":Save data for a student: S");
		System.out.println(":Get Student list with specified grade from specified course/semester: G");
		System.out.println(":Exit: E");
		System.out.println("--------------------");
		String userAnswer = console.nextLine();
		
		userAnswer = userAnswer.toUpperCase();
		
		switch (userAnswer)
		{//start switch:case
		case "A":	addData();
					break;
		case "S":	studentData();
					break;
		case "G":	gradeSearch();
					break;
		case "E":	System.out.print("Have a nice day!");
					System.exit(1);
					break;
		default:
					System.out.println("Invalid entry. Please try again.");
					printMenu();
		}//end switch:case
		
	}//end printMenu()
	
	public void addData() //TODO: give user option to search by year, course and semster
	{//start addData()
		String filename = "";
		String file = "";
		String courseNumber = "";
		String semester = "";
		String year = "";
		int cutOff = 0;
		boolean isTrue = true;
		
		while(isTrue){
			
			System.out.println("Do you want to search by file name? (enter \"file\") or by course number, semester and year? (enter \"course\")?: ");
			String search = console.nextLine();
			
			search = search.toUpperCase();
			
			if (search.contains("COURSE")){
				System.out.print("Enter course number: ");
				String courseNum = console.nextLine();
				System.out.print("Enter semester: ");
				String sem = console.nextLine();
				System.out.print("Enter year: ");
				String yearOfClass = console.nextLine();
				
				file = courseNum + "-" + sem + "-" + yearOfClass;
				filename = file + ".csv";
				if(filename.equals("437-fall-2002.csv")|| filename.equals("437-fall-2003.csv")|| filename.equals("380-fall-2002.csv")){
					System.out.println("\tFile Found");
					isTrue = false;
				}
				else{
					System.out.println("You entered an invalid file name. Please try again.");
				}
			}
			
			else if (search.contains("FILE")){
				System.out.print("Enter file name: ");
				filename = console.nextLine();
				
				if(filename.equals("437-fall-2002.csv")|| filename.equals("437-fall-2003.csv")|| filename.equals("380-fall-2002.csv")){
						System.out.println("\tFile Found");
					for(int i =0; i < filename.length(); i++){
							cutOff = filename.indexOf('.');
						}
					
					file = filename.substring(0, cutOff);
					
					
					String[] fileNameString = filename.split("-");
					courseNumber = fileNameString[0];
					semester = fileNameString[1];
					year = fileNameString[2];
					isTrue = false;
				}
				else{
					System.out.println("You entered an invalid file name. Please try again.");
				}
				
			}
			
			else{
				System.out.println("You entered an invalid file name. Please try again.");
			}
		}
		
		for(int i = 0; i < filesAdded.size(); i++)
		{
			if(filesAdded.equals(filename))
				repeatedFile = true;
		}
		if(repeatedFile)
		{
			System.out.println("That file has already been added to the repository");
			printMenu();
		}
		else
		{
			boolean result = fileIn.readFile(filename,file);
			if(result)
			{
				added = true;
				filesAdded.add(filename);
			}
			nameOfFiles[numOfFiles] = filename;
			numOfFiles++;
			System.out.println("\n\n\n__________\n\n");
			printMenu();
		}
		
	}//end addData()
	
	
	public void studentData()
	{//start studentData()
		if(!added)
		{
			System.out.println("You have not added anything to the repository yet.");
			printMenu();
		}
		else
		{
			System.out.print("Enter Student ID: ");
			String studentID = console.nextLine();
			boolean valid = false;
			System.out.print("\nEnter name of file to export: ");
			String exportFileName = console.nextLine();
			
			fileIn.addStudent(studentID,exportFileName);
			
			System.out.println("______________");
			printMenu();
		}
		
	}//end studentData()
	
	public void gradeSearch()
	{//start gradeSearch()
		if(!added)
		{
			System.out.println("You have not added anything to the repository yet.");
			printMenu();
		}
		else
		{
		
			String course = "";
			String year = "";
			String semester = "";
			
			boolean valid = false;
			while(!valid)
			{
				System.out.print("Enter the course number to search (to skip enter none): ");
				course = console.nextLine();
				
				if(course.equals("437") || course.equals("380"))
					valid = true;
				else
				{
					course = course.toUpperCase();
					if(course.equals("NONE"))
						valid = true;
					else
						System.out.println("Course number " +course +" was not found within repository. Please try again.");
				}
			}
	
			valid = false;
			while(!valid)
			{
				System.out.print("Enter Semester (to skip enter none): ");
				semester = console.nextLine();
				semester = semester.toUpperCase();
				
				if(semester.equals("FALL")|| semester.equals("NONE"))
					valid = true;
				else
					System.out.println(semester +" semester was not found within repository. Please try again.");
			}
	
			valid = false;
			while(!valid)
			{
				System.out.print("Enter School Year (to skip enter none): ");
				year = console.nextLine();
				if(year.equals("2002") || year.equals("2003"))
					valid = true;
				else
				{
					year = year.toUpperCase();
					if(year.equals("NONE"))
						valid = true;
					else
						System.out.println("School year was not found within repository. Please try again.");
				}
			}
			
			if(year.equals("NONE") && semester.equals("NONE") && course.equals("NONE"))
				printMenu();
			else
			{
				String searchCriteria = course +"," +semester +"," +year;
				int[] grades = new int[5]; 
				grades = fileIn.gradeSearch(searchCriteria);
				
				System.out.println("A  B  C  D  F");
				for (int i = 0; i < grades.length; i++)
				{
					System.out.print(grades[i] +"  ");
				}
				System.out.println("\n\n");
				printMenu();
			}
		}

	}//end gradeSearch()
}//end Menu