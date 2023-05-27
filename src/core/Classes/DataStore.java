package Classes;

import java.util.ArrayList;
import java.util.HashMap;

import Enums.*;

/**
 * This class allows to store the list of users, stations and velibNetWork
 */
public class DataStore {
    /**
     * hash map of { user id : user object }
     */
    private HashMap<Integer, User> users;
    /**
     * hash map of { station id : station object }
     */
    private HashMap<Integer, DockingStation> stations;
    /**
     * Array list of Bikes not in DockingStation
     */
    private HashMap<Integer, Bicycle> bikeOutOfStation;
     /**
      * Hash map of { velibNetwork id : velibNetwork object}
      */
     private HashMap<String,VelibNetwork> velibNetworks;




    public DataStore() {
        this.users = new HashMap<>();
        this.stations = new HashMap<>();
        this.velibNetworks = new HashMap<>();
        this.bikeOutOfStation = new HashMap<>();       
    }

    /**
     * Instantiates a new Record with users, stations and events.
     *
     * @param users    the users
     * @param stations the stations
     * @param events   the events
     */
    public DataStore(HashMap<Integer, User> users, HashMap<Integer, DockingStation> stations, HashMap<String, VelibNetwork> velibNetwork) {
        this.users = users;
        this.stations = stations;
        this.velibNetworks = velibNetwork;
       
    }

    // custom methods
    public void addUser(User user) {
        if (!users.containsValue(user)) {
            users.put(user.getId(), user);
        }
    }
    
    /**
     * Adds the station to the stations HashMap if it isn't already added.
     *
     * @param station the station
     */
    public void addStation(DockingStation station) {
        if (!stations.containsValue(station)) {
            stations.put(station.getId(), station);
        }
    }

    /**
     * Adds the velibNetwork to the velibNetwork HashMap if it isn't already added.
     *
     * @param station the station
     */
    public void addVelibNetwork(VelibNetwork velibNetwork) {
        if (!velibNetworks.containsValue(velibNetwork)) {
        velibNetworks.put(velibNetwork.getNameVelibNetwork(), velibNetwork);
        }
    }
    /**
     * Prints statistics about a user.
     *
     * @param user the user
     */
    public String computeUserStatistics(User user) {
        int numberOfRides = user.getNumberOfRides();
        int totalRentTimeInMinutes = user.getRentTotalTime();
        double totalCharges = user.getTotalCharges();
        double timeCredit = user.getRegistrationCard().getTimeCreditBalance();

        // change the way it is displayed
        return user.getName() + " statistics : \n\t" +
                "- number of rides : " + numberOfRides + ",\n\t" +
                "- time spent on a bike : " + totalRentTimeInMinutes / 60 + " hour(s) and "
                + totalRentTimeInMinutes % 60 + " minute(s),\n\t" +
                "- total charges : " + totalCharges + " \u20AC,\n\t" +
                "- time-credit balance : " + timeCredit + " minute(s).";
    }

    @Override
    public String toString() {
        return "Record containing :\n" +
                "\t " + users.size() + " users,\n" +
                "\t " + stations.size() + " stations,\n" ;
    }

    // getters & setters

    /**
     * Gets the users hashmap.
     *
     * @return the users hashmap
     */
    public HashMap<Integer, User> getUsers() {
        return users;
    }

    /**
     * Gets the station hashmap.
     *
     * @return the station hashmap
     */
    public HashMap<Integer, DockingStation> getStations() {
        return stations;
    }

    
    /**
     * Gets the users hashmap.
     *
     * @return the users hashmap
     */
    public HashMap<String,VelibNetwork> getVelibNetworks() {
        return velibNetworks;
        
    }

   

    public boolean VelibNetworksNamePossible(String name){
        for (VelibNetwork network : velibNetworks.values()) {
            if (network.getNameVelibNetwork().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean isBikeGPSExist(GPSPosition position){
        for (Bicycle bike : bikeOutOfStation.values()){
         
            if (bike.getPosition().toString().equals(position.toString())){
                return true;
            }
        }
        return false;
    }

    public Bicycle getBikeGPS(GPSPosition position,String type){
        for (Bicycle bike : bikeOutOfStation.values()) {
            if (bike.getPosition().toString().equals(position.toString()) && bike.getType().toString().toLowerCase().equals(type.toLowerCase())){
                return bike;
            } 
        }
        return null;
    }

    public void setBikeOutOfStation(Bicycle bike){
        if (!bikeOutOfStation.containsValue(bike)) {
            bikeOutOfStation.put(bike.getId(), bike);
        }
    }

    /**
     * stationBalance
     * Show the statistiques of a station
     * @param station
     */
    public void stationBalance(DockingStation station){
        int nbOfRent = station.getNumberOfRent();
        int nbOfReturn = station.getNumberOfReturn();
        int balance = nbOfReturn - nbOfRent;
        System.out.println(
        "Statistics of station n°"+station.getId()+    
        "\n\t Station balance: "+ balance +
        "\n\t number of return: " + nbOfReturn+
        "\n\t number of rent: "+nbOfRent+
        "\n \t status: "+ station.getStationStatus().toString()) ;
        System.out.println("---------------------");
        System.out.println("Number slot OCCUPIED: "+(station.getNbOfSlots()- station.getNumberOfFreeParkingSlot()));
        System.out.println("Number slot FREE: "+station.getNumberOfFreeParkingSlot());
        float averageOccupation = (float) (station.getNbOfSlots() - station.getNumberOfFreeParkingSlot())
                / station.getNbOfSlots();
        System.out.println("Average occupation: " + averageOccupation+"%");
        System.out.println("---------------------");
        HashMap<Integer, ParkingSlot> parkingSlots= station.getAllParking();
        for (int key: parkingSlots.keySet() ){
            ParkingSlot parkingSlot = parkingSlots.get(key);
            System.out.println(parkingSlot.toString());

        }

    }


    public void userStatistics(User user){
        int nbRides = user.getNumberOfRides();
        int totalRentTime = user.getRentTotalTime();
        double totalCharges = user.getTotalCharges();
        double timeCredit = user.getTimeCredit();
        int userId = user.getId();

        System.out.println(
            "Statistics of the user" + userId + ":"+
            "\n\t--------------------------------"+
            "\n\tNumber of rides: "+nbRides +
               "\n\t--------------------------------"+
               "\n\tTotal Rent Time: "+ totalRentTime+ "min"+
                  "\n\t--------------------------------"+
                  "\n\tTotal charges: "+totalCharges+
                     "\n\t--------------------------------"+
                     "\n\tTime credit: "+timeCredit
        );
    }

 
    

}
