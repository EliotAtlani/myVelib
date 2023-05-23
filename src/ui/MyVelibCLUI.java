package ui;

import java.util.Scanner;
import java.util.Arrays;

public class MyVelibCLUI {
    
    /**
     *  Setup a myVelib network.
     *
     *
     * @param nameStation    the name of station
     * @param nbOfStation        the number of station
     * @param nbOfSlots       the number of slot per station
     * @param sideLength             the side length
     * @param nbOfBikes           the number of bike
     */
    
    public void setup(String nameStation,Integer nbOfStation,Integer nbOfSlots,Double sideLength, Integer nbOfBikes) {
    	
    }

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
                        handleAddUserCommand(parts);
                        break;
                    case "offline":
                        handleOfflineCommand(parts);
                        break;
                    case "online":
                        handleOnlineCommand(parts);
                        break;
                    case "rentBike":
                        handleRentBikeCommand(parts);
                        break;
                    case "returnBike":
                        handleReturnBikeCommand(parts);
                        break;
                    case "displayStation":
                        handleDisplayStationCommand(parts);
                        break;
                    case "displayUser":
                        handleDisplayUserCommand(parts);
                        break;
                    case "sortStation":
                        handleSortStationCommand(parts);
                        break;
                    case "display":
                        handleDisplayCommand(parts);
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
    private void handleSetupCommand(String[] arguments) {
    	if (arguments.length == 1) {
    		String nameStation = arguments[0];
    		int nstations = 10;
            int nslots = 10;
    		System.out.println(nameStation);
    		
    		// Setup(nameStation, 10,10,0.75)
    	} else {
    		//Setup arguments
    	}
    	System.out.println(Arrays.toString(arguments));
     	System.out.println(arguments.length);
    	
   
    }
    private void handleDisplayHelp(String[] parts) {
    	System.out.println("Here's the notice for help");
   
    }


    private void handleAddUserCommand(String[] parts) {
    
    }

    private void handleOfflineCommand(String[] parts) {
      
    }

    private void handleOnlineCommand(String[] parts) {
      
    }

    private void handleRentBikeCommand(String[] parts) {
       
    }

    private void handleReturnBikeCommand(String[] parts) {
      
    }

    private void handleDisplayStationCommand(String[] parts) {
       
    }

    private void handleDisplayUserCommand(String[] parts) {
     
    }

    private void handleSortStationCommand(String[] parts) {
      
    }

    private void handleDisplayCommand(String[] parts) {
    
    }

    public static void main(String[] args) {
        MyVelibCLUI myVelibCLUI = new MyVelibCLUI();
        myVelibCLUI.start();
    }
}
