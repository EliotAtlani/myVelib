package Classes;

import java.util.ArrayList;

/**
 * This class contains the VelibNetwork
 */
public class VelibNetwork {
    protected Integer id;
    private String name;
    public static ArrayList<DockingStation> stationList = new ArrayList<>();
    public static ArrayList<User> userList= new ArrayList<>();

    @Override
    public String toString() {
        int numberStation = ((CharSequence) stationList).length();
        int numberUsers = ((CharSequence) userList).length();
     
        return "VelibNetwork id is : " + id + "\n \t Velibnetwork name is : " + name + "\n \t Number of stations in the network" + numberStation 
        +"\n\t Number of user in the network"+numberUsers;

    }

    public VelibNetwork(String name){
        this.name=name;
    }
    
    //Getters and setters

    public Integer getId(){
        return id;
    }



    public void setNameVelibNetwork(String newName){
        this.name =newName;
    }

    public String getNameVelibNetwork(){
        return name;
    }

    public static void setStationToMyVelibNetwork(DockingStation station){
        stationList.add(station);
    }

    public static void setUserToMyVelibNetwork(User user){
        userList.add(user);
    }

    public static ArrayList<DockingStation> getStations(){
        return stationList;
    }

    public static ArrayList<User> getUsers() {
        return userList;
    }

    public static DockingStation getStation(int stationId){
        return stationList.get(stationId);
    }



}
