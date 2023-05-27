package front;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import Cards.VLibreCard;
import Cards.VMaxCard;
import Classes.Bicycle;
import Classes.DockingStation;
import Classes.GPSPosition;
import Classes.ParkingSlot;
import Classes.User;
import Classes.VelibNetwork;
import Enums.BicycleType;
import Enums.DockingStationStatus;
import Enums.DockingStationType;
import Enums.ParkingSlotStatus;

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


        // On vérifie que le nom du network n'existe pas déjà.
        boolean existed =  MyVelibIndex.myVelibDatabase.VelibNetworksNamePossible(nameStation);
        if (existed){
            VelibNetwork network = new VelibNetwork(nameStation);
            ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();
            for (int i = 0; i < nbOfStation; i++) {
                // We generate randomly the position of the docking station

                GPSPosition position = new GPSPosition(Math.random() * sideLength, Math.random() * sideLength);
                // Create the station
                DockingStation station = new DockingStation(position, DockingStationStatus.ONLINE,
                        DockingStationType.STANDARD,nameStation);
                // stations.add(station);

                // Store the station in memory
                MyVelibIndex.myVelibDatabase.addStation(station);
                network.setStationToMyVelibNetwork(station);

                // Create nbOfSlots in this station
                for (int j = 0; j < nbOfSlots; j++) {
                    ParkingSlot parkingSlot = new ParkingSlot(station);
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
            MyVelibIndex.myVelibDatabase.addVelibNetwork(network);
            System.out.println("velibNetwork "+ nameStation + " has been created");
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

        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

        if (velibNetwork == null){
            System.out.println("The name of the velibNetwork doesn't exist");
        } else{

            if (registration.toLowerCase().equals("vlibre")) {
                VLibreCard card = new VLibreCard();
                userAdd = new User(userName, null, null, card,nameStation);
             
                userAdd.addUserToList(userAdd);
                System.out.println(
                        "Added user " + userAdd.getName() + " with id " + userAdd.getId() + " and card Vlibre");
            } else if (registration.toLowerCase().equals("vmax")) {
                VMaxCard card = new VMaxCard();
                userAdd = new User(userName, null, null, card,nameStation);
                userAdd.addUserToList(userAdd);

                System.out.println("Added user " + userAdd.getName() + " with id " + userAdd.getId() + " and card Max");

            } else {
                userAdd = new User(userName, null, null,nameStation);
                userAdd.addUserToList(userAdd);

                System.out
                        .println("Added user " + userAdd.getName() + " with id " + userAdd.getId() + " and card None");

            }
            velibNetwork.setUserToMyVelibNetwork(userAdd);

            MyVelibIndex.myVelibDatabase.addUser(userAdd);
        }



        
        
    }

    public static void offline (String nameStation,int stationId){
        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);


        if (velibNetwork == null){
            System.out.println("The name of the velibNetwork doesn't exist");

        }else{
            //We check if the station exist
            if ( velibNetwork.getStations().get(stationId) == null ) {
                System.out.println("The station doesn't exist");
            }else{
                DockingStation station = velibNetwork.getStation(stationId);
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
        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

        if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            // We check if the station exist
            if (velibNetwork.getStations().get(stationId) == null) {
                System.out.println("The station doesn't exist");
            } else {
                DockingStation station = velibNetwork.getStation(stationId);

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

    public static void rentbike(int userId,int stationId,String type,String nameStation){

        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            if (velibNetwork.getUsers().get(userId) == null) {
                System.out.println("User doesn't exist");
            } else if (velibNetwork.getStations().get(stationId) == null) {
                System.out.println("Station doesn't exist");
            } else {
                User user = velibNetwork.getUsers().get(userId); // Different from null
                DockingStation station = velibNetwork.getStations().get(stationId); // Different from

                // Check if the station is online
                if (station.getStationStatus() == DockingStationStatus.ONLINE) {
                    // We check if there is a bike available
                    if (station.hasBike(BicycleType.MECHANICAL) && type.toLowerCase().equals("mechanical")) {

                        ParkingSlot parkingSlot = station.getParkingSlotWithBike(BicycleType.MECHANICAL);
                        user.rentBikeUser(parkingSlot, LocalDateTime.now());
                        // Add stats
                        station.addNumberOfRent();

                    } else if (station.hasBike(BicycleType.ELECTRIC) && type.toLowerCase().equals("electric")
                            && station.getStationStatus() == DockingStationStatus.ONLINE) {
                        ParkingSlot parkingSlot = station.getParkingSlotWithBike(BicycleType.ELECTRIC);
                        user.rentBikeUser(parkingSlot, LocalDateTime.now());
                        // Add stats
                        station.addNumberOfRent();
                    } else if (!station.hasBike(BicycleType.MECHANICAL) && type.toLowerCase().equals("mechanical")) {
                        System.out.println("There is no mechanical bike available ");
                    } else if (!station.hasBike(BicycleType.ELECTRIC) && type.toLowerCase().equals("electric")) {
                        System.out.println("There is no electric bike available ");
                    } else if (!type.toLowerCase().equals("mechanical") && !type.toLowerCase().equals("electric")) {
                        System.out.println("Wrong type marked");
                    }
                } else {
                    System.out.println("The station is offline");
                }

        }
        
           



        }
    }
    
    public static void rentbikeGPS(int userId, double latitude,double longitude , String type, String nameStation) {

          VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            GPSPosition position = new GPSPosition(longitude,latitude);
            if (velibNetwork.getUsers().get(userId) == null) {
                System.out.println("User doesn't exist");
            } else if (!MyVelibIndex.myVelibDatabase.isBikeGPSExist(position)) {
                System.out.println("Bike at this position doesn't exist");
            } else {
                User user = velibNetwork.getUsers().get(userId); // Different from null
                Bicycle bike = MyVelibIndex.myVelibDatabase.getBikeGPS(position,type); //Get the bike
            
                user.rentBikeOutOfStation(bike,LocalDateTime.now());
                

            }
        }
    }

    public static void returnbike(int userId, int stationId,int duration,String nameStation) {
        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            if (velibNetwork.getUsers().get(userId) == null) {
                System.out.println("User doesn't exist");
            } else if (velibNetwork.getStations().get(stationId) == null) {
                System.out.println("Station doesn't exist");
            } else {
                User user = velibNetwork.getUsers().get(userId); // Different from null

                DockingStation station = velibNetwork.getStations().get(stationId); // Different from null

                //Check if the station is ONLINE
                if (station.getStationStatus() == DockingStationStatus.ONLINE){
                    // We check if the user has a bike
                    if (user.getBike() == null) {
                        System.out.println("The user given has no bike rented");

                    } else if (station.HasFreeParkingSlot()
                            && station.getStationStatus() == DockingStationStatus.ONLINE) {
                        // Take a free parking slot
                        ParkingSlot parkingSlot = station.getFreeParkingSlot();

                        //
                        double beforeCharges = user.getTotalCharges();
                        user.returnBike(parkingSlot, LocalDateTime.now().plus(Duration.ofMinutes(duration)), station);
                        double afterCharges = user.getTotalCharges();

                        double finalCost = afterCharges - beforeCharges;

                        // Add stats
                        station.addNumberOfReturn();

                        System.out.println(velibNetwork.getUsers().get(userId).getName()
                                + " has dropped his bike in station " + stationId + " in parking slot n°"
                                + parkingSlot.getId() +
                                "\n\t Cost of the ride: " + finalCost);

                    } else {
                        System.out.println("No parking place available in the station" + stationId);
                    }
                }else{
                    System.out.println("The station is OFFLINE");
                }

            
            
            }
    }
    }

    public static void returnbikeGPS(int userId, double latitude, double longitude,int duration,String nameStation) {

          VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            GPSPosition position = new GPSPosition(longitude, latitude);
            if (velibNetwork.getUsers().get(userId) == null) {
                System.out.println("User doesn't exist");
            } else {

                User user = velibNetwork.getUsers().get(userId); // Different from null

                if (user.getBike() == null){
                    System.out.println("The user doesn't have a bike yet");
                }else{
                        
                    double beforeCharges = user.getTotalCharges();
                    user.returnBikeOutOfStation(position, LocalDateTime.now().plus(Duration.ofMinutes(duration)));
                    double afterCharges = user.getTotalCharges();

                    double finalCost = afterCharges - beforeCharges;

                    System.out.println(velibNetwork.getUsers().get(userId).getName()
                            + " has dropped his bike out of station " + position.toString() +
                            "\n\t Cost of the ride: " + finalCost);

                }



            }
    }
    }
    
    
    public static void displayUser(String nameStation, int userId)  {
         VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            if (velibNetwork.getUsers().get(userId) == null){
                System.out.println("The user doesn't exist");
            }else{
                User user = velibNetwork.getUsers().get(userId);
                MyVelibIndex.myVelibDatabase.userStatistics(user);

            }
        }

      
       

    }

    public static void displayStation(String nameStation, Integer stationId){
         VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            if( velibNetwork.getStations().get(stationId) == null){
                System.out.println("The station doesn't exist");
            }else{
                DockingStation station = velibNetwork.getStations().get(stationId);
                MyVelibIndex.myVelibDatabase.stationBalance(station);
            }
    }
       
    
    }

    public static void displayAllBikes(String nameStation){
         VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            //Get all the station
             ArrayList<DockingStation> stations = velibNetwork.getStations();
             for (int i =0;i<stations.size();i++){
                 DockingStation station = stations.get(i);
                 HashMap<Integer, ParkingSlot> parkingSlots = station.getAllParking();
                 for (int key2 : parkingSlots.keySet()) {
                     ParkingSlot parkingSlot = parkingSlots.get(key2);
                     System.out.println(parkingSlot.toString());
                 }

             }

           
    }
    }

    public static void displayNetworks(){
        HashMap<String,VelibNetwork> networks = MyVelibIndex.myVelibDatabase.getVelibNetworks();

        for (String key : networks.keySet()) {
            VelibNetwork network = networks.get(key);
            System.out.println("Network: " + key);
            System.out.println("Nombre de station: " + network.getStations().size());
            System.out.println("Nombre de user: " + network.getUsers().size());
            // Affichez d'autres propriétés spécifiques à VelibNetwork que vous souhaitez
            System.out.println("---------------------------------------");
        }

    }

    public static void sortStation(String nameStation,String sortPolicy){
          VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);

         if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            if (sortPolicy.toLowerCase().equals("mos")){
                ArrayList<DockingStation> stations = velibNetwork.getStations();
                List<DockingStation> sortedStations = new ArrayList<>(stations);
            
                Collections.sort(sortedStations, new Comparator<DockingStation>() {
                    @Override
                    public int compare(DockingStation station1, DockingStation station2) {
                        int sum1 = station1.getNumberOfReturn() + station1.getNumberOfRent();
                        int sum2 = station2.getNumberOfReturn() + station2.getNumberOfRent();
                        return Integer.compare(sum2, sum1); // Tri décroissant
                    }
                });
                for (DockingStation station : sortedStations) {
                    int sum = station.getNumberOfReturn() + station.getNumberOfRent();
                    System.out.println("Station: " + station.getId() + ", Score: " + sum);
                }

            }else if (sortPolicy.toLowerCase().equals("los")){
                ArrayList<DockingStation> stations = velibNetwork.getStations();
                List<DockingStation> sortedStations = new ArrayList<>(stations);

                Collections.sort(sortedStations, new Comparator<DockingStation>() {
                    @Override
                    public int compare(DockingStation station1, DockingStation station2) {
                        int sum1 = station1.getNumberOfRent() - station1.getNumberOfReturn();
                        int sum2 = station2.getNumberOfRent() - station2.getNumberOfReturn();
                        if (sum1 < sum2) {
                            return 1; // Tri décroissant pour les scores négatifs
                        } else if (sum1 > sum2) {
                            return -1; // Tri décroissant pour les scores positifs
                        } else {
                            return 0;
                        }
                    }
                });
                for (DockingStation station : sortedStations) {
                    int sum = station.getNumberOfRent() - station.getNumberOfReturn();
                    System.out.println("Station: " + station.getId() + ", Score: " + sum);
                }

            }else{
                System.out.println("The sortPolicy entered doesn't exist, type 'help' for more informations");
            }
        }
    }

    public static void displayVelib(String nameStation) {
        VelibNetwork velibNetwork = MyVelibIndex.myVelibDatabase.getVelibNetworks().get(nameStation);
        if (velibNetwork == null) {
            System.out.println("The name of the velibNetwork doesn't exist");

        } else {
            ArrayList<User> users = velibNetwork.getUsers();

            ArrayList<DockingStation> stations = velibNetwork.getStations();

            System.out.println("Statistics of the velibNetwork "+nameStation );
            System.out.println("--------------------------");
            System.out.println("Number of users: "+users.size());
            System.out.println("Number of stations: "+stations.size());
            System.out.println("--------------------------");
            System.out.println("id n°" + " | "+" name  "+" | "+" Card type ");

            for (int i =0;i<users.size();i++){
                User user = users.get(i);
                
                System.out.println(user.getId() + " | " + user.getName()+ " | "+ user.getRegistrationCard());
            }

            System.out.println("--------------------------");
            System.out.println("id n°" + " | " + " Status  " + " | " + " Number of Free Parking Slot ");
            for (int i = 0; i < stations.size(); i++) {
                DockingStation station = stations.get(i);
                

                System.out.println(station.getId()+" | "+ station.getStationStatus()+" | "+station.getNumberOfFreeParkingSlot());
            }

        }
    
    }





}
