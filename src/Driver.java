import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	
	
	
	public static void main(String[] args){
		
		FileInput fileIn;
		
		fileIn = new FileInput();
			
		
		Menu menu = new Menu();
		
		String file = menu.addData();
		
		fileIn.readFile(file);
		
	}
}
