package core.Test;


import core.Enums.*;
import core.Classes.*;
import core.RidePlanning.*;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RideAvoindingPlusStationTest {

    @Test
    @DisplayName("Test findEndStation")
    public void testFindEndStation(){
        ArrayList<DockingStation> stations = new ArrayList<DockingStation>();
        GPSPosition position1 = new GPSPosition(0.0, 0.0);
        GPSPosition position2 = new GPSPosition(0.0, 1.0);
        GPSPosition position3 = new GPSPosition(0.0, 2.0);
        GPSPosition position4 = new GPSPosition(0.0, 3.0);
        GPSPosition position5 = new GPSPosition(0.0, 4.0);
        GPSPosition position6 = new GPSPosition(0.0, 5.0);

        DockingStation station1 = new DockingStation(position1, DockingStationStatus.ONLINE, DockingStationType.PLUS,"Paris",10);
        station1.addParkingSlot(new ParkingSlot(station1));
        DockingStation station2 = new DockingStation(position2, DockingStationStatus.ONLINE, DockingStationType.PLUS,"Paris",10);
        station2.addParkingSlot(new ParkingSlot(station2));
        DockingStation station3 = new DockingStation(position3, DockingStationStatus.ONLINE, DockingStationType.PLUS,"Paris",10);
        station3.addParkingSlot(new ParkingSlot(station3));
        DockingStation station4 = new DockingStation(position4, DockingStationStatus.ONLINE, DockingStationType.PLUS,"Paris",10);
        station4.addParkingSlot(new ParkingSlot(station4));
        DockingStation station5 = new DockingStation(position5, DockingStationStatus.ONLINE, DockingStationType.STANDARD,"Paris",10);
        station5.addParkingSlot(new ParkingSlot(station5));
        DockingStation station6 = new DockingStation(position6, DockingStationStatus.ONLINE, DockingStationType.PLUS,"Paris",10);
        station6.addParkingSlot(new ParkingSlot(station6));

        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);

        NormalRide ride = new RideAvoidingPlusStations(station1.getPosition(), station2.getPosition());

        assertAll("Assert start station is correct",
                () -> assertTrue(ride.findStartStation(stations) == station1),
                () -> assertTrue(ride.findEndStation(stations) == station5)
        );

    }



    
}
