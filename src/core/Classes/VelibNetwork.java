package Classes;

import java.util.ArrayList;

/**
 * This class contains the VelibNetwork
 */
public class VelibNetwork {
    protected Integer id;
    private String name;
    public static ArrayList<DockingStation> stationList = new ArrayList<>();

    @Override
    public String toString() {
        int numberStation = ((CharSequence) stationList).length();
     
        return "VelibNetwork id is : " + id + "\n \t Velibnetwork name is : " + name + "\n \t Number of stations in the network" + numberStation;

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

    public void setStationToMyVelibNetwork(DockingStation station){
        stationList.add(station);
    }

    public ArrayList<DockingStation> getStations(){
        return stationList;
    }


}
