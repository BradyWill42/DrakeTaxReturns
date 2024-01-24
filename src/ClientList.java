import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClientList {
    
    private static ArrayList<Client> importedClients = new ArrayList<Client>();
    private static ArrayList<Client> approvedClients = new ArrayList<Client>();
    private static ArrayList<Client> brokenClients = new ArrayList<Client>();
    
	private static File inputFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "InputDrakePrint.txt");
    

    public ClientList(){}

    /**
     * Reads input file for clients and adds names and codes to an imported client arraylist of all clients listed in the InputDrakePrint.txt file.
     * @return void
     */
    public static void readInputFile() throws IOException{
        FileReader fr = new FileReader(inputFile);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
        new StringBuffer();
        String clientFull;  
        int i = 0;
        while((clientFull = br.readLine()) != null){
            importedClients.add(new Client(clientFull.trim()));
            System.out.println(importedClients.get(i) + " added.");
            i++;
        }
        br.close();
    }

    /**
     * 
     * @return importedClients arraylist
     */
    public static ArrayList<Client> getClients(){
        return importedClients;
    }

    /**
     * 
     * @return approvedClients arraylist
     */
    public static ArrayList<Client> getApprovedClients(){
        return approvedClients;
    }

    /**
     * 
     * @return brokenClients arraylist
     */
    public static ArrayList<Client> getBrokenClients(){
        return brokenClients;
    }
    
    
    /**
     * Creates broken and approved client lists by sorting through each client in the importedClients Arraylist and checking if the client is broken or not.
     * @return void
     */
    public static void sort(){
        for(int i = 0; i < importedClients.size(); i++){
            if(!importedClients.get(i).isBroken()){
                approvedClients.add(importedClients.get(i));
                System.out.println(importedClients.get(i) + " added to good list.");
            } else {
                brokenClients.add(importedClients.get(i));
                System.out.println(importedClients.get(i) + " added to broken list.");
            }
        }
    }






}
