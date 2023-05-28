package core.Test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import core.Classes.*;
import core.Enums.*;

public class DataStoreTest {

    @Test
    @DisplayName("Test if the DataStore is empty")
    public void testEmptyDataStore() {
        DataStore dataStore = new DataStore();
        assertTrue(dataStore.getUsers().isEmpty());
        assertTrue(dataStore.getStations().isEmpty());
        assertTrue(dataStore.getVelibNetworks().isEmpty());
    }

    @Test
    @DisplayName("Test to adda a User to the DataStore")
    public void testAddUser() {
        DataStore dataStore = new DataStore();
        User user1 = new User("Jean", new GPSPosition(0.0, 0.0), "123456789","Paris");

        dataStore.addUser(user1);

        assertAll("Assert all users are in the DataStore",
                () -> assertTrue(dataStore.getUsers().containsKey(user1.getId())),
                () -> assertTrue(dataStore.getUsers().containsValue(user1))
        );

    }

    @Test
    @DisplayName("Test to add a Station to the DataStore")
    public void testAddStation() {
        DataStore dataStore = new DataStore();
        DockingStation station = new DockingStation(new GPSPosition(0.0, 0.0),DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",10);

        dataStore.addStation(station);

        assertAll("Assert all stations are in the DataStore",
                () -> assertTrue(dataStore.getStations().containsKey(station.getId())),
                () -> assertTrue(dataStore.getStations().containsValue(station))
        );
    }


    @Test
    @DisplayName("Test to add a VelibNetwork to the DataStore")
    public void testAddVelibNetwork() {
        DataStore dataStore = new DataStore();
        VelibNetwork network = new VelibNetwork("Paris");

        dataStore.addVelibNetwork(network);

        assertAll("Assert all networks are in the DataStore",
                () -> assertTrue(dataStore.getVelibNetworks().containsKey(network.getNameVelibNetwork())),
                () -> assertTrue(dataStore.getVelibNetworks().containsValue(network))
        );
    }

    @Test
    @DisplayName("Test if VelibNetworks Name is Possible")
    public void testVelibNetworksNameIsPossible() {
        DataStore dataStore = new DataStore();
        DataStore dataStore1 = new DataStore();
        VelibNetwork network = new VelibNetwork("Paris");

        dataStore1.addVelibNetwork(network);

        assertAll("Assert all networks are in the DataStore",
                () -> assertTrue(dataStore.VelibNetworksNamePossible(network.getNameVelibNetwork())),
                () -> assertFalse(dataStore1.VelibNetworksNamePossible(network.getNameVelibNetwork()))
        );

    }

    @Test
    @DisplayName("Test if Bike GPS exists")
    public void testBikeGPSExists() {
        DataStore dataStore = new DataStore();
        Bicycle bicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));

        dataStore.setBikeOutOfStation(bicycle);

        assertAll("Assert all bikes are in the DataStore",
                () -> assertTrue(dataStore.isBikeGPSExist(bicycle.getPosition())),
                () -> assertFalse(dataStore.isBikeGPSExist(new GPSPosition(1.0, 1.0)))
        );
    }


    
    
    
    
}
