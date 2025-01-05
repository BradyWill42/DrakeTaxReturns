import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.IntegerSyntax;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class DrakeClientReturns {

    public Integer year;
    
    // public void drakeReturnSetup() throws IOException {
    //     //creates DrakeReturns Folder on main machine desktop if it does not already exist
    //     System.out.println("Checking for DrakeReturns directory on C: Drive and creating it if it does not exist...");
    //     new File(System.getProperty("user.home") + "\\Desktop\\DrakeReturns").mkdirs();
    //     System.out.println("Successfully Created DrakeReturns Directory.");
         
    //     System.out.println("Reading Input file...");

    //     ClientList.readInputFile();

    //     //loops through names listed in the input file that have been transfered into an arraylist
    //     for(int i = 0; i < ClientList.getClients().size(); i++){

    //         //assigns client variable
    //         Client client = ClientList.getClients().get(i);

    //         //creates client directory
    //         System.out.println("Creating " + client.getName() + " Directory in DrakeReturns folder...");
    //         new File(System.getProperty("user.home") + "\\Desktop\\DrakeReturns\\" + client.getName() + "\\2022").mkdirs();
    //         File acctGruntFile = new File(System.getProperty("user.home") + "\\Desktop\\DrakeReturns\\" + client.getName() + "\\2022");
    //         System.out.println(client.getName() + " Directory Successfully Created.");

    //         //creates account directory of the clients private 2022 folder
    //         File account = new File("Z:\\" + client.getName() + "\\Private\\2022");
    //         System.out.println("Copying " + client.getName() + " Directory over from Z: Drive...");
            
    //         //tries to copy directory over
            
    //         FileUtils.copyDirectory(account, acctGruntFile, true);
    //         System.out.println(client.getName() + " Directory Successfully Copied.");
        
    //         System.out.println(client.getName() + " Client does not exist.");
    //         System.out.println("Deleting non-existent Client Folder...");
    //         FileUtils.deleteDirectory(new File(System.getProperty("user.home") + "\\Desktop\\Gruntworx\\" + client.getName()));
    //         client.setBroken(true);
    //         System.out.println("Folder Deleted.");
            
    //     }
    // }

    public void yearPrompt() throws NumberFormatException, IOException{
        File inputFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "DrakePrintCounter.txt"); 
        //adds to counter
        FileReader fr = new FileReader(inputFile);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        new StringBuffer();
        String number;  
        Integer runs = 0;
        while((number = br.readLine()) != null){
            runs = Integer.parseInt(number);
        }
        System.out.println("You have completed " + runs + " successful Drake Prints");

        Scanner input = new Scanner(System.in);
        System.out.println("Please click after the colon, type the usable year, and press enter:");
        year = input.nextInt();
    }

    public void downloadReturn() throws InterruptedException, AWTException{
        StringSelection str;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();;
        Robot vm = new Robot();

        // Thread.sleep(3000);

        // vm.keyPress(KeyEvent.VK_WINDOWS);
        // vm.keyPress(KeyEvent.VK_UP);
        // vm.keyRelease(KeyEvent.VK_UP);
        // vm.keyRelease(KeyEvent.VK_WINDOWS);

        // Thread.sleep(3000);

        //reads input file
        System.out.println("Reading Input file...");
        try {
            ClientList.readInputFile();
        } catch (IOException e) {
            System.out.println("Could not read Input file.");
            e.printStackTrace();
        }

        //creates clipboard and robot variables
        
        Integer counter = 0;

        for(int i = 0; i < ClientList.getClients().size(); i++){
            
            System.out.println("Checking for ADrakePrint directory on C: Drive, creating it if it does not exist, deleting and re-creating it if it does...");
            

            File aDrakeFile = new File("C:\\ADrakePrint");
            ClearDir clear = new ClearDir();

            if(!new File("C:\\ADrakePrint").mkdirs()){
                try {
                    clear.deleteDirectoryRecursion(aDrakeFile.toPath());
                } catch (IOException e) {
                    System.out.println(aDrakeFile.toPath().toString() + " does not exist. Moving on to next client");
                    e.printStackTrace(); 
                    continue;
                }
                new File("C:\\ADrakePrint").mkdirs();
            }

            System.out.println("Successfully Created ADrakePrint Directory.");

            //creates client variable from ClientList
            Client client = ClientList.getClients().get(i);
            
            File checkClientFile = new File("Z:\\" + client.getName());

            if(checkClientFile.exists() && !client.isBroken()){
                //delay once you get into Drake program itself
                Thread.sleep(3000);

                vm.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_O);
                vm.keyRelease(KeyEvent.VK_O);
                vm.keyRelease(KeyEvent.VK_CONTROL);

                //delay once you actually open the client to let the client file load in Drake after hitting Control-O to open it
                Thread.sleep(5000);

                //Character limit in Gruntworx Input is 48; don't need full name to make Gruntworx work
                String fullString = client.getName();
                    
                //check length of name, if longer than 48 chars, take only the first 48 chars
                if(fullString.length() > 48){
                    String adjustedString = fullString.substring(0, 47);
                    str = new StringSelection(adjustedString);
                    clipboard.setContents(str, null);
                } 
                //otherwise continue as normal with full string
                else {
                    str = new StringSelection(fullString);
                    clipboard.setContents(str, null);
                }


                //Thread.sleep(3000);  JJ - Feb 13th comment out 

                //paste name into returnSearch
                vm.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_V);
                vm.keyRelease(KeyEvent.VK_V);
                vm.keyRelease(KeyEvent.VK_CONTROL);

                //just waiting after pasting client name to open in Drake
                Thread.sleep(5000);

                //enter name
                vm.keyPress(KeyEvent.VK_ENTER);
                vm.keyRelease(KeyEvent.VK_ENTER);

                //waiting to open client file in Drake
                Thread.sleep(5000);

                //bypass potential popup notes, bypasses 2
                vm.keyPress(KeyEvent.VK_ALT);
                //waiting to bypass the initial "NOTE" POPUP that comes up in Drake if the client has a NOTE written in their file
                Thread.sleep(5000);
                vm.keyPress(KeyEvent.VK_C);
                vm.keyRelease(KeyEvent.VK_C);
                vm.keyPress(KeyEvent.VK_C);
                vm.keyRelease(KeyEvent.VK_C);
                vm.keyRelease(KeyEvent.VK_ALT);

                //waiting after passing the NOTE screen and then the client opens in data entry mode in Drake
                Thread.sleep(5000);

                //Open View/Print Tab
                vm.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_SHIFT);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_V);
                vm.keyRelease(KeyEvent.VK_V);
                vm.keyRelease(KeyEvent.VK_SHIFT);
                vm.keyRelease(KeyEvent.VK_CONTROL);

                //wait 10 seconds for return to load in View mode
                Thread.sleep(10000);

                //tab over to Sets
                vm.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_TAB);
                vm.keyRelease(KeyEvent.VK_TAB);
                vm.keyRelease(KeyEvent.VK_CONTROL);

                //wait for the SETS tab to open in View mode
                Thread.sleep(5000);

                //close and select first 3 folders
                vm.keyPress(KeyEvent.VK_UP);
                vm.keyRelease(KeyEvent.VK_UP);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_LEFT);
                vm.keyRelease(KeyEvent.VK_LEFT);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_SPACE);
                vm.keyRelease(KeyEvent.VK_SPACE);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_DOWN);
                vm.keyRelease(KeyEvent.VK_DOWN);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_LEFT);
                vm.keyRelease(KeyEvent.VK_LEFT);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_SPACE);
                vm.keyRelease(KeyEvent.VK_SPACE);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_DOWN);
                vm.keyRelease(KeyEvent.VK_DOWN);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_LEFT);
                vm.keyRelease(KeyEvent.VK_LEFT);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_SPACE);
                vm.keyRelease(KeyEvent.VK_SPACE);

                //waits after selecting everything (the 3 returns) to print 
                Thread.sleep(5000);

                //Print return setup
                vm.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_P);
                vm.keyRelease(KeyEvent.VK_P);
                vm.keyRelease(KeyEvent.VK_CONTROL);

                //waits after printing
                Thread.sleep(18000);

                //JJ - Bypass potential popup that asks you to print form that needs social security number showing (like payment vouchers in Client set)
                // vm.keyPress(KeyEvent.VK_ALT);
                // Thread.sleep(100);
                // vm.keyPress(KeyEvent.VK_Y);
                // vm.keyRelease(KeyEvent.VK_Y);
                // vm.keyRelease(KeyEvent.VK_ALT);

                // Thread.sleep(3000);
                //JJ - doing this makes ALT-F4 not work!  Can get around this by hitting ESC then ESC then ESC one more time
                //JJ- if have to have social sec numbers masked, then try this AFTER tax season!
                //JJ- enabled social security numbers to be seen in Drake instead of implementing this code

                //print
                vm.keyPress(KeyEvent.VK_ALT);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_P);
                vm.keyRelease(KeyEvent.VK_P);
                vm.keyRelease(KeyEvent.VK_ALT);

                //After hitting to print and before opening File Explorer - gives File Explorer time to open
                Thread.sleep(15000);

                //Loops saving  the 3 files that we automatically print in Drake Print explorer (will print Preparer copy as well if no Estimated payments)
                for(int k = 0; k < 3; k++){
                    //naviagates file explorer popup
                    for(int j = 0; j < 3; j++){
                        vm.keyPress(KeyEvent.VK_SHIFT);
                        vm.keyPress(KeyEvent.VK_TAB);
                        vm.keyRelease(KeyEvent.VK_TAB);
                        vm.keyRelease(KeyEvent.VK_SHIFT);
                        Thread.sleep(150);
                    }

                    //This is short - just alt Tabs into Drake Directory file explorer (doesn't need to be long - it's a direct action)
                    //Thread.sleep(5000);

                    //types in "C" and selects it
                    String desktop = "C";
                    char[] deskArray = desktop.toCharArray();
                    for (int j = 0; j < deskArray.length; j++){
                        vm.keyPress(deskArray[j]);
                        vm.keyRelease(deskArray[j]);
                        //Thread.sleep(3000);  //JJ - increased due to need time to get to C drive - used to be 150
                    }

                    //After typing in C to get to the C drive to save the file that will be printed, then WAIT!
                    //Thread.sleep(10000);  //JJ - increased due to need time to get to C drive - used to be 5000

                    vm.keyPress(KeyEvent.VK_ENTER);
                    vm.keyRelease(KeyEvent.VK_ENTER);

                    //Actually loading the C drive after selecting it
                    Thread.sleep(15000);  //JJ - increased due to need time to get to C drive - used to be 8000
                    
                    vm.keyPress(KeyEvent.VK_CONTROL);
                    Thread.sleep(250);
                    vm.keyPress(KeyEvent.VK_L);
                    vm.keyRelease(KeyEvent.VK_L);
                    vm.keyRelease(KeyEvent.VK_CONTROL);

                    Thread.sleep(8000);

                    for(int j = 0; j < 5; j++){
                        vm.keyPress(KeyEvent.VK_TAB);
                        vm.keyRelease(KeyEvent.VK_TAB);
                        Thread.sleep(250);
                    }

                    
                    //Changed from Drake 2023, numerous options added, need to reset and make more reliable, added code above.
                    //vm.keyPress(KeyEvent.VK_TAB);
                    //vm.keyRelease(KeyEvent.VK_TAB);

                    //Once in C drive, once you tab in it gives it extra time before getting to "ADRAKEPRINT" directory
                    //Thread.sleep(8000);  //JJ - increased due to need time to get to C drive - used to be 5000

                    //types in "GruntWorx" and selects it
                    String drakePrint = "ADRAKEPRINT";
                    char[] drakeArray = drakePrint.toCharArray();
                    for (int j = 0; j < drakeArray.length; j++){
                        vm.keyPress(drakeArray[j]);
                        vm.keyRelease(drakeArray[j]);
                        Thread.sleep(75);
                    }
        
                    //This is short - just alt Tabs into Drake Directory file explorer (doesn't need to be long - it's a direct action)
                    Thread.sleep(150);

                    vm.keyPress(KeyEvent.VK_ENTER);
                    vm.keyRelease(KeyEvent.VK_ENTER);

                    //After typing in "ADRAKEPRINT" to get to that directory to save the file that will be printed, then WAIT to let load!
                    Thread.sleep(8000);  //JJ - increased due to need time to get to C drive - used to be 5000

                    //adds files to ADRAKEPRINT
                    vm.keyPress(KeyEvent.VK_ALT);
                    Thread.sleep(100);
                    vm.keyPress(KeyEvent.VK_S);
                    vm.keyRelease(KeyEvent.VK_S);
                    vm.keyRelease(KeyEvent.VK_ALT);

                    //After saving in "ADRAKEPRINT", then WAIT to let save!
                    Thread.sleep(15000);  //JJ - increased due to need time to get to C drive - used to be 150

                }
                
                //After all 3 files have been printed and saved, give another 6 seconds just to let the client finish and make sure all is done
                Thread.sleep(9000);  //JJ - increased due to need time to get to C drive - used to be 6000

                vm.keyPress(KeyEvent.VK_ALT);
                Thread.sleep(100);
                vm.keyPress(KeyEvent.VK_F4);
                vm.keyRelease(KeyEvent.VK_F4);
                vm.keyRelease(KeyEvent.VK_ALT);
                
                Thread.sleep(3000);

                vm.keyPress(KeyEvent.VK_ALT);
                vm.keyRelease(KeyEvent.VK_ALT);

                Thread.sleep(3000);
                
                File clientsZDrive = new File("Z:\\" + client.getName() + "\\Firm docs shared with client\\" + year);

                File [] files = aDrakeFile.listFiles();
                for (int j = 0; j < files.length; j++){
                    if (files[j].isFile() && !files[j].toString().contains("Preparer")){ //this line weeds out other directories/folders and makes sure it isn't the preparer file
                        System.out.println(files[j].toPath());
                        System.out.println(Paths.get(clientsZDrive.toString() + files[j].getName()));
                        Path temp = null;
                        try {
                            temp = Files.copy(files[j].toPath(), Paths.get(clientsZDrive.toString() + File.separator + files[j].getName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            System.out.println("File could not be copied, check location and client are correct.");
                            ClientList.getClients().get(i).setBroken(true);
                            e.printStackTrace();
                            continue;
                        }              
                        if(temp != null)
                        {
                            System.out.println(files[j].getName() + " moved successfully");
                            
                        }
                        else
                        {
                            System.out.println("Failed to move the file");
                        }
                    }
                }
                if (!isEmpty(clientsZDrive.toPath())){
                    counter++;
                    System.out.println("Added one to total Drake Print counter");
                } else {
                    ClientList.getClients().get(i).setBroken(true);
                    continue;
                }
                try {
                    System.out.println("Deleting aDrakeFile...");
                    clear.deleteDirectoryRecursion(aDrakeFile.toPath());
                    System.out.println("aDrakePrint directory deleted.");
                } catch (IOException e)  {
                    System.out.println ("No such directory exists. Continuing to next client.");
                    e.printStackTrace();
                    continue;
                }
            } else {
                ClientList.getClients().get(i).setBroken(true);
                System.out.println("Please check spelling of client " + ClientList.getClients().get(i).getName()  + " - the directory for this client cannot be found in Taxdome");
                continue;
            }
        }

        try {
            addToCounter(counter);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Completed");
    }

    public boolean isEmpty(Path path) {
        return path.toFile().listFiles().length == 0;
    }

    public void createLogFile(){
        //Create .txt with info. 
        ClientList.sort();
        //gets current date and time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd.yyy_HH.mm.ss"); 
        LocalDateTime now = LocalDateTime.now();  

        String currentTime = dtf.format(now);  

        //creates log file with date and time in name
        File logFile = new File(System.getProperty("user.dir") + File.separator + currentTime + "_Log_File.txt");


        try {
            logFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            //writes the date and time in the log file
            FileWriter write = new FileWriter(logFile.getCanonicalFile(), false);
            PrintWriter printLine = new PrintWriter(write);
            printLine.printf("%s" + "%n", "Log created on " + currentTime + "\n" + "Using InputDrakePrint.txt as input file." + "\n");
            printLine.printf("%s" + "%n", "Successful Drake Prints: ");
            
            //writes the successful client uploads to gruntworx
            for(int i = 0; i < ClientList.getApprovedClients().size(); i++){
                printLine.printf("%s" + "%n", ClientList.getApprovedClients().get(i));
            }

            printLine.printf("%s" + "%n", "");
            printLine.printf("%s" + "%n", "Broken Client List: ");
            
            //writes the broken clients that did not upload to gruntworx
            for(int i = 0; i < ClientList.getBrokenClients().size(); i++){
                printLine.printf("%s" + "%n", ClientList.getBrokenClients().get(i));
            }

            printLine.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addToCounter(Integer count) throws IOException{
        File inputFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "DrakePrintCounter.txt"); 
        //adds to counter
        FileReader fr = new FileReader(inputFile);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        new StringBuffer();
        String number;  
        Integer runs = 0;
        while((number = br.readLine()) != null){
            System.out.println(number);
            runs = Integer.parseInt(number);
        }
        
        System.out.println(runs);
        runs = runs + count;
        br.close();
        fr.close();

        inputFile.delete();

        File newInputFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "DrakePrintCounter.txt"); 
        newInputFile.createNewFile();
        FileWriter write = new FileWriter(newInputFile.getCanonicalFile(), false);
        PrintWriter printLine = new PrintWriter(write);

        System.out.println(runs.toString());
        printLine.printf(runs.toString());

        printLine.close();
        write.close();  
        
    }        

}
