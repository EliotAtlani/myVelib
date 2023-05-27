package Classes;

import java.util.ArrayList;

/**
 * This class contains the VelibNetwork
 */
public class VelibNetwork {
    private  String name;
    protected ArrayList<DockingStation> stationList;
    protected ArrayList<User> userList;

    @Override
    public String toString() {
        int numberStation = this.stationList.size();
        int numberUsers = this.userList.size();
     
        return "Velibnetwork name is : " + name + "\n \t Number of stations in the network : " + numberStation 
        +"\n\t Number of user in the network "+numberUsers;

    }

    public VelibNetwork(String name){
        this.name=name;
        this.stationList = new ArrayList<DockingStation>();
        this.userList= new ArrayList<User>();
    }
    
    //Getters and setters

  
    public void setNameVelibNetwork(String newName){
        this.name =newName;
    }

    public String getNameVelibNetwork(){
        return this.name;
    }

    public void setStationToMyVelibNetwork(DockingStation station){
        this.stationList.add(station);
    }

    public void setUserToMyVelibNetwork(User user){
        this.userList.add(user);
    }

    public ArrayList<DockingStation> getStations(){
        return this.stationList;
    }

    public ArrayList<User> getUsers() {
        return this.userList;
    }

    public  DockingStation getStation(int stationId){
        return this.stationList.get(stationId);
    }

}
