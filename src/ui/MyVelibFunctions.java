package ui;

import java.util.Scanner;

import Cards.NoRegistrationCard;
import Cards.RegistrationCard;
import Cards.VLibreCard;
import Cards.VMaxCard;
import Classes.*;
import Enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ui.MyVelibFunctions;

public class MyVelibFunctions {
    /**
     * Get random type of bicycle.
     *
     * @return the random type of bicycle
     */
    public static BicycleType getRandomBicycleType() {
        if (Math.random() < 0.5) {
            return BicycleType.MECHANICAL;
        } else {
            return BicycleType.ELECTRIC;
        }
    }

    /**
     * Setup a myVelib network.
     *
     *
     * @param nameStation the name of station
     * @param nbOfStation the number of station
     * @param nbOfSlots   the number of slot per station
     * @param sideLength  the side length
     * @param nbOfBikes   the number of bike
     */

    public MyVelibFunctions() {

    }

    public static void setup(String nameStation, Integer nbOfStation, Integer nbOfSlots, Double sideLength,
            Integer nbOfBikes) {
        ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();
        // Place stations uniformly on a square grid whose the side is of length;

        // On vérifie que le nom du network n'existe pas déjà.
        boolean existed =  MyVelibIndex.myVelibDatabase.VelibNetworksNamePossible(nameStation);
        if (!existed){
            VelibNetwork velibNetwork = new VelibNetwork(nameStation);

            for (int i = 0; i < nbOfStation; i++) {
                // We generate randomly the position of the docking station

                GPSPosition position = new GPSPosition(Math.random() * sideLength, Math.random() * sideLength);
                // Create the station
                DockingStation station = new DockingStation(position, DockingStationStatus.ONLINE,
                        DockingStationType.STANDARD);
                // stations.add(station);

                // Store the station in memory
                MyVelibIndex.myVelibDatabase.addStation(station);
                VelibNetwork.setStationToMyVelibNetwork(station);

                // Create nbOfSlots in this station
                for (int j = 0; j < nbOfSlots; j++) {
                    ParkingSlot parkingSlot = new ParkingSlot(ParkingSlotStatus.Free, null, station);
                    station.addParkingSlot(parkingSlot);
                    parkingSlots.add(parkingSlot);
                }
            }

            // Generate numberOfBike randomly distributed index between 0 and
            // numberOfStation*numberOfSlotPerStation
            List<Integer> randomParkingSlot = new ArrayList<>();
            for (int i = 0; i < nbOfSlots * nbOfStation; i++) {
                randomParkingSlot.add(i);
            }
            Collections.shuffle(randomParkingSlot);
            for (int i = 0; i < nbOfBikes; i++) {
                parkingSlots.get(randomParkingSlot.get(i)).setParkingSlotStatus(ParkingSlotStatus.Occupied);
                parkingSlots.get(randomParkingSlot.get(i)).setBike(new Bicycle(getRandomBicycleType(),
                        parkingSlots.get(randomParkingSlot.get(i)).getStationId().getPosition()));
            }
            //Store the VelibNetwork
            MyVelibIndex.myVelibDatabase.addVelibNetwork(velibNetwork);
        }else{
            // Print
            System.out.println("Nom de velibNetwork déjà prit");
        }

      

    }

    /**
     * Set up user
     * 
     * @param userName
     * @param registration
     * @param nameStation
     */
    public static void addUser(String userName,String registration, String nameStation){
        User userAdd;

        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetwork(nameStation);

        if (velibNetwork == null){
            System.out.println("The name of the velibNetwork doesn't exist");
        } else{

            if (registration.toLowerCase().equals("vlibre")) {
                VLibreCard card = new VLibreCard();
                userAdd = new User(userName, null, null, card);
             
                userAdd.addUserToList(userAdd);
                System.out.println(
                        "Added user " + userAdd.getName() + " with id " + userAdd.getId() + " and card Vlibre");
            } else if (registration.toLowerCase().equals("vmax")) {
                VMaxCard card = new VMaxCard();
                userAdd = new User(userName, null, null, card);
                userAdd.addUserToList(userAdd);

                System.out.println("Added user " + userAdd.getName() + " with id " + userAdd.getId() + " and card Max");

            } else {
                userAdd = new User(userName, null, null);
                userAdd.addUserToList(userAdd);

                System.out
                        .println("Added user " + userAdd.getName() + " with id " + userAdd.getId() + " and card None");

            }
            VelibNetwork.setUserToMyVelibNetwork(userAdd);

            MyVelibIndex.myVelibDatabase.addUser(userAdd);
        }



        
        
    }

    public static void offline (String nameStation,int stationId){
        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetwork(nameStation);

        if (velibNetwork == null){
            System.out.println("The name of the velibNetwork doesn't exist");

        }else{
            //We check if the station exist
            if ( VelibNetwork.getStations().get(stationId) == null ) {
                System.out.println("The station doesn't exist");
            }else{
                DockingStation station = VelibNetwork.getStation(stationId);
                
                // Check if the station status
                 if (station.getStationStatus() == DockingStationStatus.ONLINE){
                     station.setStationStatus(DockingStationStatus.OFFLINE);

                     System.out.println("The station n°" + stationId + " from the velibNetwork " + nameStation + " is now offline");
                 }
                 else{
                    System.out.println("The station was already offline");
                 }

            }
        }
    }

    public static void online(String nameStation, int stationId) {
        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetwork(nameStation);

        if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            // We check if the station exist
            if (VelibNetwork.getStations().get(stationId) == null) {
                System.out.println("The station doesn't exist");
            } else {
                DockingStation station = VelibNetwork.getStation(stationId);

                // Check if the station status
                if (station.getStationStatus() == DockingStationStatus.OFFLINE) {
                    station.setStationStatus(DockingStationStatus.ONLINE);

                    System.out.println(
                            "The station n°" + stationId + " from the velibNetwork " + nameStation + " is now online");
                } else {
                    System.out.println("The station was already online");
                }

            }
        }
    }
    
    
    public static void displayUser(String stationName, int userId)  {
       
        User user = MyVelibIndex.myVelibDatabase.getUsers().get(userId);

        System.out.println(user.toString());
      
       

    }

    public static void displayStation(String nameStation, Integer stationId){
        DockingStation dockingStation = new DockingStation(null, null, null);
    
    }
}
