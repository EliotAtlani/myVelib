package ui;

import java.util.Scanner;

import Enums.BicycleType;
import Enums.DockingStationStatus;
import Enums.DockingStationType;
import Enums.ParkingSlotStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ui.MyVelibFunctions;

import Classes.*;

public class MyVelibCLUI {

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
    private void handleSetupCommand(Object[] arguments) {
        if (arguments.length == 1) {
            String nameStation = (String) arguments[0];

            MyVelibFunctions.setup(nameStation, 10, 10, 4000.0, 75);
        } else if (arguments.length == 5) {
            String nameStation = (String) arguments[0];
            Integer nbStations = Integer.parseInt(arguments[1].toString());
            Integer nbSlots = Integer.parseInt(arguments[2].toString());
            Double s = Double.parseDouble(arguments[3].toString());
            Integer nbBikes = Integer.parseInt(arguments[4].toString());
            MyVelibFunctions.setup(nameStation, nbStations, nbSlots, s, nbBikes);
        } else {
            System.out.println("You must enter the correct number of arguments. Type 'help' for more information");
        }

    }

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

                        + "rentBike <userID, stationID> :" +
                        "\n \tto let the user userID renting a bike from station stationID (if no bikes are available should behave accordingly)\n\n"

                        + "rentBike <userID, GPS_Position> :" +
                        "\n \t to let the user userID renting a bike parked at a given GPS_Position\n\n"

                        + "returnBike <userID, stationID, duration> : :" +
                        "\n \t to let the user userID returning a bike to station stationID for a given duration (if no parking bay is available should \n\n"
                        +
                        "\t behave accordingly). This command should display the cost of the rent\n\n"

                        + "returnBike <userID, GPS_Position, duration> :" +
                        "\n \t to let the user userID returning a bike in a given GPS_position for a given duration (if no parking bay is available should \n\n"
                        +
                        "\t behave accordingly). This command should display the cost of the rent\n\n"

                        + "displayStation <velibnetworkName, stationID> :" +
                        "\n \t to display the statistics of station stationID of a myVelib network velibnetwork.\n\n"

                        + "displayUser <velibnetworkName, userID>:" +
                        "\n \t to display the statistics of user userID of a myVelib network velibnetwork.\n \n"

                        + "sortStation <velibnetworkName, sortpolicy> :" +
                        "\n \t to display the stations in increasing order w.r.t. to the sorting policy of user sortpolicy. \n \n"

                        + "display  <velibnetworkName>:" +
                        "\n \t  to display the entire status (stations, parking bays, users) of an a myVelib network velibnetworkName\n\n"

                        + "exit :" +
                        "\n \t to exit the system. \n \n"

                        + "runTest <testScenarioFile.txt> :" +
                        "\n \t to run the test scenario described in testScenarioFile.txt\n \n");

    }

    private void handleAddUserCommand(String[] arguments) {
        String userName = arguments[0];
        String registration = arguments[1];
        String stationName = arguments[2];

        MyVelibFunctions.addUser(userName, registration, stationName);
    }

    private void handleOfflineCommand(String[] parts) {
        String nameStation = parts[0];
        int stationId = Integer.parseInt(parts[1].toString());

        MyVelibFunctions.offline(nameStation, stationId);

    }

    private void handleOnlineCommand(String[] parts) {
        String nameStation = parts[0];
        int stationId = Integer.parseInt(parts[1].toString());

        MyVelibFunctions.online(nameStation, stationId);

    }

    private void handleRentBikeCommand(String[] parts) {

    }

    private void handleReturnBikeCommand(String[] parts) {

    }

    private void handleDisplayStationCommand(String[] parts) {

    }

    private void handleDisplayUserCommand(String[] parts) {
        Integer userId = Integer.parseInt(parts[1].toString());
     
        MyVelibFunctions.displayUser(null, userId);
    }

    private void handleSortStationCommand(String[] parts) {

    }

    private void handleDisplayCommand(String[] parts) {

    }

    public static void main(String[] args) {
       
    }
}
