package core.Test;

import core.Enums.*;
import core.Classes.*;
import core.RidePlanning.*;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RidePreferStreetStationTest {

    @Test
    @DisplayName("Test find Start Bike and end Station")
    public void testFindEndStation(){
        ArrayList<DockingStation> stations = new ArrayList<DockingStation>();
        ArrayList<Bicycle> bikes = new ArrayList<Bicycle>();

        GPSPosition position1 = new GPSPosition(0.0, 0.0);
        GPSPosition position2 = new GPSPosition(0.0, 1.0);
        GPSPosition position3 = new GPSPosition(0.0, 2.0);
        GPSPosition position4 = new GPSPosition(0.0, 3.0);
        GPSPosition position5 = new GPSPosition(0.0, 4.0);
        GPSPosition position6 = new GPSPosition(0.0, 5.0);

        DockingStation station1 = new DockingStation(position1, DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");
        station1.addParkingSlot(new ParkingSlot(station1));
        DockingStation station2 = new DockingStation(position2, DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");
        station2.addParkingSlot(new ParkingSlot(station2));
        DockingStation station3 = new DockingStation(position3, DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris");
        station3.addParkingSlot(new ParkingSlot(station3));
    

        stations.add(station1);
        stations.add(station2);
        stations.add(station3);

        Bicycle bike1 = new Bicycle(BicycleType.MECHANICAL,position4);
        bike1.setInStation(false, null);
        Bicycle bike2 = new Bicycle(BicycleType.MECHANICAL,position5);
        bike2.setInStation(false, null);
        Bicycle bike3 = new Bicycle(BicycleType.MECHANICAL,position6);
        bike3.setInStation(false, null);


        bikes.add(bike1);
        bikes.add(bike2);
        bikes.add(bike3);

        RidePreferStreetBike ride = new RidePreferStreetBike(station1.getPosition(), station2.getPosition());

        assertAll("Assert start station is correct",
                () -> assertTrue(ride.findStartBike(bikes) == bike1),
                () -> assertTrue(ride.findEndStation(stations) == station2)
        );
    }
    

    
}
