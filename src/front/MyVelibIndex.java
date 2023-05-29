package front;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import core.Classes.DataStore;
/**
 * This class contains the main void and allows to launch the initialisation and the command system 
 * 
 * It's the script you have to launch to get the CLUI
 * */
public class MyVelibIndex {

    /**
     * We start by create a new DataStore to store all the data we create
     */
    public static DataStore myVelibDatabase = new DataStore();

    //Here's the main void
    public static void main(String[] args){
        //Initialisation of the DataStore
        InitialConfiguration();
        //Launch CLUI
        RunCommand();


    }

    /**
     * 
     */
    private static void RunCommand() {
//Launch command CLUI
        MyVelibCLUI myVelibCLUI = new MyVelibCLUI();
        myVelibCLUI.start();
    }

    /**
     * 
     */
    private static void InitialConfiguration(){
        try {
            File file = new File("src/front/eval/my_velib.ini");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // Process the file line by line
            while ((line = bufferedReader.readLine()) != null) {
                
                if (line.startsWith("{stations}")) {
                    
                    // Process station-related data

                    processStationData(bufferedReader);
                } else if (line.startsWith("{users}")) {

                    // Process user-related data
                    processUserData(bufferedReader);
                }

            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param bufferedReader
     * @throws IOException
     */
    private static void processStationData(BufferedReader bufferedReader) throws IOException {
        String line;
        String name ="";
        int nstations = 0;
        int nslots = 0;
        double s = 0;
        int nbikes = 0;

        // Read and process each line until reaching the next section
        while((line=bufferedReader.readLine())!=null&&!line.startsWith("-"))
        {
             if (line.startsWith("name=")) {
               name = line.substring(line.indexOf("=")+1);
             }
            else if (line.startsWith("nstations=")) {
                nstations = Integer.parseInt(line.substring(line.indexOf("=") + 1));
            } else if (line.startsWith("nslots=")) {
                nslots = Integer.parseInt(line.substring(line.indexOf("=") + 1));
            } else if (line.startsWith("s=")) {
                s = Double.parseDouble(line.substring(line.indexOf("=") + 1));
            } else if (line.startsWith("nbikes=")) {
                nbikes = Integer.parseInt(line.substring(line.indexOf("=") + 1));
            }
        }

        // Initialize the station data in the myVelibDatabase
        MyVelibFunctions.setup(name, nstations,nslots, s,nbikes );

}

    /**
     * @param bufferedReader
     * @throws IOException
     */
    private static void processUserData(BufferedReader bufferedReader) throws IOException {
    String line;
    List<String> names = new ArrayList<>(); 
    List<String> cardTypes = new ArrayList<>(); 

    // Read and process each line until reaching the next section
    while ((line = bufferedReader.readLine()) != null && !line.startsWith("-")) {
        if (line.startsWith("names=")) {
            String[] nameArray = line.substring(line.indexOf("=") + 1).split(",");
            names.addAll(Arrays.asList(nameArray)); 
        } else if (line.startsWith("cardtype=")) {
            String[] cardTypeArray = line.substring(line.indexOf("=") + 1).split(",");
            cardTypes.addAll(Arrays.asList(cardTypeArray)); 
        }
    }

    // Ensure both lists have the same size
    int size = Math.min(names.size(), cardTypes.size());

    for (int i = 0; i < size; i++) {
        String name = names.get(i);
        String cardType = cardTypes.get(i);
        MyVelibFunctions.addUser(name, cardType, "network1");
    }
}

    
}
