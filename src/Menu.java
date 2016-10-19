import java.util.Scanner;

public class Menu 
{//start Menu
	public Scanner console = new Scanner(System.in);
	FileInput fileIn = new FileInput();
	
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
			System.out.print("Enter file name: ");
			filename = console.next();
			console.nextLine();
			
			if(filename.equals("437-fall-2002.csv")|| filename.equals("437-fall-2003.csv")|| filename.equals("380-fall-2002.csv")){
					
				for(int i =0; i < filename.length(); i++){
						cutOff = filename.indexOf('.');
					}
				
				file = filename.substring(0, cutOff);
				System.out.println(file);
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
		
		fileIn.readFile(filename);
		System.out.println("\n\n\n__________\n\n");
		printMenu();
		
	}//end addData()
	
	
	public void studentData()
	{//start studentData()
		System.out.print("Enter Student ID: ");
		String studentID = console.nextLine();
		System.out.print("\nEnter name of file to export: ");
		String exportFileName = console.nextLine();
		
		fileIn.getStudent(studentID,exportFileName);
		// Tell FileInput.java class to find all data for studentID 
		// and save it in a file named exportFileName
		
	}//end studentData()
	
	public void gradeSearch()
	{//start gradeSearch()
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
			String[] grades = new String[4]; 
			grades = fileIn.gradeSearch(searchCriteria);
			//Tell Driver to create an array of each grade letter for the parameters sent.
			//The string sent to FileInput's gradeSearch method is split by commas. 
			//Three fields: course# then semester then school year
			//Value of those fields could be NONE. One field will always have a value.
		}

	}//end gradeSearch()
}//end Menu