package core.Classes;

import java.util.ArrayList;
import java.util.HashMap;

import core.Enums.*;

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
     * Array list of events (eg. rent a bike, return a bike, ...)
     */

     /**
      * Hash map of { velibNetwork id : velibNetwork object}
      */
     private HashMap<String,VelibNetwork> velibNetworks;

    /**
     * Instantiates a new Record with empty lists.
     */
    public DataStore() {
        this.users = new HashMap<>();
        this.stations = new HashMap<>();
        this.velibNetworks = new HashMap<>();
       
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
                return true;
            }
        }
        return false;
    }

    

}
