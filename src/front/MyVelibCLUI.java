package front;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This script create the CLUI in the terminal by launching start and handle all the commands.
 */
public class MyVelibCLUI {

    /** 
     * @return 
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to myVelib! Made by Eliot and Titouan");
        System.out.println("For any help, type 'help'");

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            String[] parts = command.split("\\s+"); // Split the command into parts
            String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

            if (parts.length > 0) {
                String commandName = parts[0];

                // Handle different commands
                switch (commandName) {
                    case "setup":
                        handleSetupCommand(arguments);
                        break;
                    case "addUser":
                        handleAddUserCommand(arguments);
                        break;
                    case "offline":
                        handleOfflineCommand(arguments);
                        break;
                    case "online":
                        handleOnlineCommand(arguments);
                        break;
                    case "rentBike":
                        handleRentBikeCommand(arguments);
                        break;
                    case "returnBike":
                        handleReturnBikeCommand(arguments);
                        break;
                    case "displayStation":
                        handleDisplayStationCommand(arguments);
                        break;
                    case "displayUser":
                        handleDisplayUserCommand(arguments);
                        break;
                    case "sortStation":
                        handleSortStationCommand(arguments);
                        break;
                    case "display":
                        handleDisplayCommand(arguments);
                        break;
                    case "displayVelibnetworks":
                        handleNetworkCommand(arguments);
                        break;
                    case "displayAllBikes":
                        handleAllBikesCommand(arguments);
                        break;
                    case "runTest":
                        handleRunTestCommand(arguments);
                        break;
                    case "exit":
                        running = false;
                        break;
                    case "help":
                        handleDisplayHelp(parts);
                        break;

                    default:
                        System.out.println("Invalid command. Type 'help' for a list of available commands.");
                }
            }
        }

        scanner.close();
        System.out.println("Exiting myVelib. Goodbye!");
    }

    // Implement the logic to handle each command
    /**
     * @param parts
     */
    private void handleSetupCommand(Object[] parts) {
        if (parts.length == 1) {
            String nameStation = (String) parts[0];

            MyVelibFunctions.setup(nameStation, 10, 10, 4000.0, 75);
        } else if (parts.length == 5) {
            String nameStation = (String) parts[0];
            Integer nbStations = Integer.parseInt(parts[1].toString());
            Integer nbSlots = Integer.parseInt(parts[2].toString());
            Double s = Double.parseDouble(parts[3].toString());
            Integer nbBikes = Integer.parseInt(parts[4].toString());
            MyVelibFunctions.setup(nameStation, nbStations, nbSlots, s, nbBikes);
        } else {
            System.out.println("You must enter the correct number of arguments. Type 'help' for more information");
        }

    }

    /**
     * @param parts
     */
    private void handleDisplayHelp(String[] parts) {
        System.out.println(
                " Here's the list of all the command possible. Your command shoud start like this : 'Command-name <arg1> <arg2> ... <argN>'' \n \n "
                        + "Commands available : \n \n"

                        + "setup <velibnetworkName>: " +
                        "\n \t to create a myVelib network with given name and consisting of 10 stations each of which has 10 parking slots \n"
                        +
                        "\t and such that stations are arranged on a square grid whose of side 4km \n" +
                        "\t and initially populated with a 75% bikes randomly distributed over the 10 stations\n \n"

                        + "setup  <name> <nstations> <nslots> <s> <nbikes> :" +
                        "\n  \t to create a myVelib network with given name and consisting of nstations stations each of which has nslots parking slots and such that stations are arranged in as uniform as possible\n"
                        +
                        "\t manner over an area you may assume either being circular of radium s or squared of side s \n"
                        +
                        "\t Furthermore the network should be initially populated with a nbikes bikes randomly distributed over the nstations stations\n\n"

                        + "addUser <userName,cardType, velibnetworkName>  :" +
                        "\n \t to add a user with name userName and card cardType (i.e. ``none'' if the user has no card) to a myVelib network velibnetworkName\n\n"

                        + "offline <velibnetworkName, stationID>  :" +
                        "\n \t to put offline the station stationID of the myVelib network velibnetworkName\n\n"

                        + "online <velibnetworkName, stationID>:" +
                        "\n \t to put online the station stationID of the myVelib network velibnetworkName\n\n"

                        + "rentBike <userID, stationID,velibnetworkName > :" +
                        "\n \tto let the user userID renting a bike from station stationID (if no bikes are available should behave accordingly)\n\n"

                        + "rentBike <userID, latitude,longitude, velibnetworkName> :" +
                        "\n \t to let the user userID renting a bike parked at a given GPS_Position\n\n"

                        + "returnBike <userID, stationID, duration,velibnetworkName> : :" +
                        "\n \t to let the user userID returning a bike to station stationID for a given duration (if no parking bay is available should \n\n"
                        +
                        "\t behave accordingly). This command should display the cost of the rent\n\n"

                        + "returnBike <userID, latitude,longitude, duration,velibnetworkName> :" +
                        "\n \t to let the user userID returning a bike in a given GPS_position for a given duration (if no parking bay is available should \n\n"
                        +
                        "\t behave accordingly). This command should display the cost of the rent\n\n"

                        + "displayStation <velibnetworkName, stationID> :" +
                        "\n \t to display the statistics of station stationID of a myVelib network velibnetwork.\n\n"

                        + "displayUser <velibnetworkName, userID>:" +
                        "\n \t to display the statistics of user userID of a myVelib network velibnetwork.\n \n"

                        + "sortStation <velibnetworkName, sortpolicy> :" +
                        "\n \t to display the stations in increasing order w.r.t. to the sorting policy of user sortpolicy. Use 'mos' or 'los' for policy \n \n"

                        + "display  <velibnetworkName>:" +
                        "\n \t  to display the entire status (stations, parking bays, users) of an a myVelib network velibnetworkName\n\n"

                        + "displayAllBikes <velibnetworkName>:"+
                        "\n\t to display all the bikes in station from a velibNetWork"

                          + "runTest <testScenarioFile.txt> :" +
                        "\n \t to run the test scenario described in testScenarioFile.txt\n \n"
                        + "exit :" +
                        "\n \t to exit the system. \n \n");


    }

    /**
     * @param parts
     */
    private void handleAddUserCommand(String[] parts) {
        String userName = parts[0];
        String registration = parts[1];
        String stationName = parts[2];

        MyVelibFunctions.addUser(userName, registration, stationName);
    }

    /**
     * @param parts
     */
    private void handleOfflineCommand(String[] parts) {
        String nameStation = parts[0];
        int stationId = Integer.parseInt(parts[1].toString());
        // -1 to retrieve to correct DockingStation
        MyVelibFunctions.offline(nameStation, stationId-1);

    }

    /**
     * @param parts
     */
    private void handleOnlineCommand(String[] parts) {
        String nameStation = parts[0];
        int stationId = Integer.parseInt(parts[1].toString());

        MyVelibFunctions.online(nameStation, stationId-1);

    }

    /**
     * @param parts
     */
    private void handleNetworkCommand(String[] parts) {
        MyVelibFunctions.displayNetworks();
    }

    /**
     * @param parts
     */
    private void handleRentBikeCommand(String[] parts) {
        if (parts.length == 4){
            int userId = Integer.parseInt(parts[0].toString());
            int stationId = Integer.parseInt(parts[1].toString());
            String type = parts[2];
            String nameStation = parts[3];
            //-1 because it's an array
            MyVelibFunctions.rentbike(userId-1, stationId-1, type,nameStation);
        } else if (parts.length==5){
            int userId = Integer.parseInt(parts[0].toString());
            double latitude = Double.parseDouble(parts[1].toString());
            double longitude = Double.parseDouble(parts[2].toString());
            String type = parts[3];
            String nameStation = parts[4];

            MyVelibFunctions.rentbikeGPS(userId-1,latitude,longitude, type,nameStation);
        }else{
            System.out.println("-> Wrong arugments given");
        }
     

    }

    /**
     * @param parts
     */
    private void handleReturnBikeCommand(String[] parts) {
         if (parts.length == 4){
            int userId = Integer.parseInt(parts[0].toString());
            int stationId = Integer.parseInt(parts[1].toString());
            int duration = Integer.parseInt(parts[2].toString());
            String nameStation = parts[3];
            MyVelibFunctions.returnbike(userId-1, stationId-1, duration,nameStation);
        } else if (parts.length==5){
            //5 arguments = GPSPosition arguments
            int userId = Integer.parseInt(parts[0].toString());
            double latitude = Double.parseDouble(parts[1].toString());
            double longitude = Double.parseDouble(parts[2].toString());
            int duration = Integer.parseInt(parts[3].toString());
            String nameStation = parts[4];

            MyVelibFunctions.returnbikeGPS(userId-1, latitude, longitude, duration,nameStation);
        }
       
    }

    /**
     * @param parts
     */
    private void handleDisplayStationCommand(String[] parts) {
        if (parts.length == 2) {
            String velibNetwork = parts[0];
            Integer stationId = Integer.parseInt(parts[1].toString());

            MyVelibFunctions.displayStation(velibNetwork, stationId-1);
        } else {
            System.out.println("Wrong number of arguments");
        }
    }

    /**
     * @param parts
     */
    private void handleDisplayUserCommand(String[] parts) {
        if (parts.length == 2) {
            String velibNetwork = parts[0];
            Integer userId = Integer.parseInt(parts[1].toString());

            MyVelibFunctions.displayUser(velibNetwork, userId-1);
        } else {
            System.out.println("Wrong number of arguments");
        }
        
     
    }

    /**
     * @param parts
     */
    private void handleSortStationCommand(String[] parts) {
        if (parts.length == 2) {
            String velibNetwork = parts[0];
            String sortPolicy = parts[1];

            MyVelibFunctions.sortStation(velibNetwork, sortPolicy);

        } else {
            System.out.println("Wrong number of arguments");
        }
      
    }

    /**
     * @param parts
     */
    private void handleDisplayCommand(String[] parts) {
        if (parts.length == 1) {
            String velibNetwork = parts[0];

            MyVelibFunctions.displayVelib(velibNetwork);
        } else {
            System.out.println("Wrong number of arguments");
        }
    }
    
    /**
     * @param parts
     */
    private void handleAllBikesCommand(String[] parts) {
        if (parts.length == 1) {
            String velibNetwork = parts[0];
            MyVelibFunctions.displayAllBikes(velibNetwork);
        } else {
            System.out.println("Wrong number of arguments");
        }
        
    }

    /**
     * @param parts
     */
    private void handleRunTestCommand(String[] parts) {
        if (parts.length == 1) {
            String scenario = parts[0];
            MyVelibFunctions.runtTest(scenario);
        } else {
            System.out.println("Wrong number of arguments");
        }

    }


    //To execute file.txt

    /**
     * @param filename
     */
    public void executeCommandsFromFile(String filename) {
        try {
            File file = new File("./eval/"+filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                String[] parts = command.split("\\s+");
                if(parts[0].equals("-")){
                executeCommand(command);

                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    /**
     * @param command
     */
    private void executeCommand(String command) {
        String[] parts = command.split("\\s+"); // Split the command into parts
            String[] arguments = Arrays.copyOfRange(parts, 2, parts.length);

            if (parts.length > 0) {
                String commandName = parts[1];

                // Handle different commands
                switch (commandName) {
                    case "setup":
                        handleSetupCommand(arguments);
                        break;
                    case "addUser":
                        handleAddUserCommand(arguments);
                        break;
                    case "offline":
                        handleOfflineCommand(arguments);
                        break;
                    case "online":
                        handleOnlineCommand(arguments);
                        break;
                    case "rentBike":
                        handleRentBikeCommand(arguments);
                        break;
                    case "returnBike":
                        handleReturnBikeCommand(arguments);
                        break;
                    case "displayStation":
                        handleDisplayStationCommand(arguments);
                        break;
                    case "displayUser":
                        handleDisplayUserCommand(arguments);
                        break;
                    case "sortStation":
                        handleSortStationCommand(arguments);
                        break;
                    case "display":
                        handleDisplayCommand(arguments);
                        break;
                    case "displayVelibnetworks":
                        handleNetworkCommand(arguments);
                        break;
                    case "displayAllBikes":
                        handleAllBikesCommand(arguments);
                        break;
                    case "exit":
                        break;
                    case "help":
                        handleDisplayHelp(parts);
                        break;

                    default:
                        System.out.println("Invalid command. Type 'help' for a list of available commands.");
                }
    }
    }

  
}
