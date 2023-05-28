package core.Test;

import core.Classes.*;
import core.Enums.*;


import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;


public class VelibNetworkTest {

    @Test
    @DisplayName("Test of add station")
    public void testAddStation() {

        VelibNetwork velibNetwork = new VelibNetwork("Paris");

        DockingStation station1 = new DockingStation(new GPSPosition(0.0, 0.0), DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");
        DockingStation station2 = new DockingStation(new GPSPosition(1.0, 0.0), DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");
        DockingStation station3 = new DockingStation(new GPSPosition(2.0, 0.0), DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");
        DockingStation station4 = new DockingStation(new GPSPosition(3.0, 0.0), DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");

        velibNetwork.setStationToMyVelibNetwork(station1);
        velibNetwork.setStationToMyVelibNetwork(station2);
        velibNetwork.setStationToMyVelibNetwork(station3);
        velibNetwork.setStationToMyVelibNetwork(station4);

        assertAll("Assert all stations are added",
                () -> assertTrue(velibNetwork.getStations().contains(station1)),
                () -> assertTrue(velibNetwork.getStations().contains(station2)),
                () -> assertTrue(velibNetwork.getStations().contains(station3)),
                () -> assertTrue(velibNetwork.getStations().contains(station4))
        );
    }
    
    @Test   
    @DisplayName("Test of add user")
    public void tesAddUser(){

        VelibNetwork velibNetwork = new VelibNetwork("Paris");

        User user1=new User("Jean", new GPSPosition(0.0, 0.0), "123456789","Paris");
        User user2=new User("Paul", new GPSPosition(1.0, 0.0), "987654321","Paris");
        User user3=new User("Jacques", new GPSPosition(2.0, 0.0), "123456789","Paris");

        velibNetwork.setUserToMyVelibNetwork(user1);
        velibNetwork.setUserToMyVelibNetwork(user2);
        velibNetwork.setUserToMyVelibNetwork(user3);

        assertAll("Assert all users are added",
                () -> assertTrue(velibNetwork.getUsers().contains(user1)),
                () -> assertTrue(velibNetwork.getUsers().contains(user2)),
                () -> assertTrue(velibNetwork.getUsers().contains(user3))
        );


    }
}
