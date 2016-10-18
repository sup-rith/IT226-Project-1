import java.util.Scanner;

public class Menu 
{//start Menu
	public Scanner console = new Scanner(System.in);
	
	
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
					return;
		case "S":	saveData();
					return;
		case "G":	gradeSearch();
					return;
		case "E":	System.out.print("Have a nice day!");
					System.exit(0);
					return;
		default:
					System.out.println("Invalid entry. Please try again.");
					printMenu();
		}//end switch:case
		
	}//end printMenu()
	
	public String addData()
	{//start addData()
		boolean valid = false;
		String filename = "";
		
		while(!valid)
		{
			System.out.print("Enter file name: ");
			filename = console.nextLine();
			if(filename.compareTo("437-fall-2002.csv") == 0 || filename.compareTo("437-fall-2003.csv") == 0 || filename.compareTo("380-fall-2002.csv") == 0)
				valid = true;
			else
				System.out.println("You entered an invalid file name. Please try again.");
		}
		
		valid = false;
		while(!valid)
		{
			System.out.print("Enter Semester: ");
			String semester = console.nextLine();
			semester = semester.toUpperCase();
			
			if(semester.equals("FALL"))
				valid = true;
			else
			{
				System.out.println(semester +" semester cannot be added to repository. Enter a different value.");
				
			}
		}
		
		valid = false;
		while(!valid)
		{
			System.out.print("Enter Course Number: ");
			String course = console.nextLine();
			
			if(course.compareTo("437") == 0 || course.compareTo("380") == 0)
				valid = true;
			else
				System.out.println("Course number " +course +" cannot be added to repository. Enter a different course number.");
		}
		
		// Tell Driver class to add filename to repository
		// Then print number of students in file. And number of students where already in file.
		return filename;
		
	}//end addData()
	
	public void saveData()
	{//start saveData()
		System.out.print("Enter Student ID: ");
		String studentID = console.nextLine();
		System.out.print("\nEnter name of file to export: ");
		String exportFileName = console.nextLine();
		
		// Tell Driver class to find all data for studentID 
		// and save it in a file named exportFileName
		
	}//end saveData
	
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
			
			if(course.compareTo("437") == 0 || course.compareTo("437") == 0 || course.compareTo("380") == 0)
				valid = true;
			else
			{
				course = course.toUpperCase();
				if(course.compareTo("NONE") == 0)
					valid = true;
				else
					System.out.println("Course number " +course +" was not found within repository. Enter a course number.");
			}
		}

		valid = false;
		while(!valid)
		{
			System.out.print("Enter Semester (to skip enter none): ");
			semester = console.nextLine();
			semester = semester.toUpperCase();
			
			if(semester.compareTo("FALL")==0 || semester.compareTo("NONE") == 0)
				valid = true;
			else
				System.out.println(semester +" semester was not found within repository. Enter a different value.");
		}

		valid = false;
		while(!valid)
		{
			System.out.print("Enter School Year (to skip enter none): ");
			year = console.nextLine();
			if(year.compareTo("2002") == 0 || year.compareTo("2003") == 0)
				valid = true;
			else
			{
				year = year.toUpperCase();
				if(year.compareTo("NONE") == 0)
					valid = true;
				else
					System.out.println("School year was not found within repository. Enter a different value.");
			}
		}
		
		if(year.compareTo("NONE") == 0 && semester.compareTo("NONE") == 0 && course.compareTo("NONE") == 0)
			printMenu();
		else{
			//Tell Driver to create an array amount of each grade letter for the parameters sent.
		}

	}//end gradeSearch()
}//end Menu