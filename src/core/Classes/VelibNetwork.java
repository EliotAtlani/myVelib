package Classes;

import java.util.ArrayList;

/**
 * This class contains the VelibNetwork
 */
public class VelibNetwork {
    private String name;
    public static ArrayList<DockingStation> stationList = new ArrayList<>();
    public static ArrayList<User> userList= new ArrayList<>();

    @Override
    public String toString() {
        int numberStation = stationList.size();
        int numberUsers = userList.size();
     
        return "Velibnetwork name is : " + name + "\n \t Number of stations in the network : " + numberStation 
        +"\n\t Number of user in the network "+numberUsers;

    }

    public VelibNetwork(String name){
        this.name=name;
    }
    
    //Getters and setters

  
    public void setNameVelibNetwork(String newName){
        this.name =newName;
    }

    public String getNameVelibNetwork(){
        return name;
    }

    public void setStationToMyVelibNetwork(DockingStation station){
        stationList.add(station);
    }

    public void setUserToMyVelibNetwork(User user){
        userList.add(user);
    }

    public ArrayList<DockingStation> getStations(){
        return stationList;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    public  DockingStation getStation(int stationId){
        return stationList.get(stationId);
    }

}
