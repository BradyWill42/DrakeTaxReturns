import java.lang.*;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;

import java.io.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

public class Main{
	public static void main(String[] args) throws IOException,
                           AWTException, InterruptedException,
						   UnsupportedFlavorException{
	


		DrakeClientReturns drake = new DrakeClientReturns();
		drake.yearPrompt();
		System.out.println("Current year set to " + drake.year);

		Scanner input = new Scanner(System.in);
		System.out.println("Please choose Locate Type to use: \n 1 - Verito isRunning() Find Method \n 2 - Alt Tab method");
		Integer choice = input.nextInt();

		//Checking for choice option
		while(choice != 1 && choice != 2){
			System.out.println("Please choose a valid input.");
			choice = input.nextInt();
		}
		input.close();

		if(choice == 1){
        	//This is the "Find Virtual Machine No Matter What" way to start the program!  (JJ)

			//Checks to make sure Verito is up and running before continuing
			boolean cont = IsRunning.running();
			if(cont){
				System.out.println("Virtual Machine in focus.");
				drake.downloadReturn();
				drake.createLogFile();
			}
			
			
			//This is the END of the "Find Virtual Machine No Matter What" way to start the program!  (JJ)
		} else {

			//This is the ALT TAB way to start the program! (JJ)

			Robot vm = new Robot();
		
			vm.keyPress(KeyEvent.VK_ALT);
			vm.keyPress(KeyEvent.VK_TAB);
			vm.keyRelease(KeyEvent.VK_TAB);
			vm.keyRelease(KeyEvent.VK_ALT);
			
			drake.downloadReturn();
			drake.createLogFile();
			//This is the END of the ALT TAB way to start the program! (JJ)
		}

		JOptionPane.showMessageDialog(null, "Check Z Drive for files", "DONE", 0);
		
	}
	
}