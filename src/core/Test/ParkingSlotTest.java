package core.Test;


import core.Classes.*;
import core.Enums.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;



public class ParkingSlotTest {

    @Test
    @DisplayName("Test of the status of parking slots")
    public void testStatusParkingSlot() {

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        DockingStation station = new DockingStation(new GPSPosition(0.0, 0.0),DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris");
        ParkingSlot slot1 = new ParkingSlot(station,mechaBicycle);
        ParkingSlot slot2 = new ParkingSlot(station);

        assertAll(" All status are correct",
                () -> assertTrue(slot1.getParkingSlotStatus() == ParkingSlotStatus.Occupied),
                () -> assertTrue(slot2.getParkingSlotStatus() == ParkingSlotStatus.Free)
        );
    }

    @Test
    @DisplayName("Test unique ID for parking slots")
    public void testUniqueID() {

        // Two parking slots and one parking slot
        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        DockingStation station = new DockingStation(new GPSPosition(0.0, 0.0),DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris");
        ParkingSlot slot1 = new ParkingSlot(station,mechaBicycle);
        ParkingSlot slot2 = new ParkingSlot(station);

        assertAll("Assert all ID are unique",
                () -> assertTrue(slot1.getId() != slot2.getId())
        );
    }

    @Test 
    @DisplayName("Test of the bicycle of parking slots")
    public void testBicycleParkingSlot() {

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        DockingStation station = new DockingStation(new GPSPosition(0.0, 0.0),DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris");
        ParkingSlot slot1 = new ParkingSlot(station,mechaBicycle);
        ParkingSlot slot2 = new ParkingSlot(station);

        assertAll(" All bicycles are correct",
                () -> assertTrue(slot1.getBike() == mechaBicycle),
                () -> assertTrue(slot2.getBike() == null),
                () -> assertTrue(slot1.getParkingSlotStatus() == ParkingSlotStatus.Occupied),
                () -> assertTrue(slot2.getParkingSlotStatus() == ParkingSlotStatus.Free)
        );
    }


    
}
