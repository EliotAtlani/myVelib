package core.Test;

import core.Enums.*;
import core.Classes.*;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;


public class DockingStationTest {

    @Test
    @DisplayName("Test of the position of docking station")
    public void testPositionDockingStation() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        DockingStation station = new DockingStation(position,DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",3);

        assertAll(" All positions are correct",
                () -> assertTrue(station.getPosition() == position)
        );
    }

    @Test
    @DisplayName("Test of the type of docking station")
    public void testTypeDockingStation() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        DockingStation station1 = new DockingStation(position,DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",3);
        DockingStation station2 = new DockingStation(position,DockingStationStatus.OFFLINE,DockingStationType.PLUS,"Paris",3);


        assertAll(" All types are correct",
                () -> assertTrue(station1.getStationType() == DockingStationType.STANDARD),
                () -> assertTrue(station2.getStationType() == DockingStationType.PLUS)

        );
    }

    @Test
    @DisplayName("Test of the status of docking station")
    public void testStatusDockingStation() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        DockingStation station1 = new DockingStation(position,DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",3);
        DockingStation station2 = new DockingStation(position,DockingStationStatus.OFFLINE,DockingStationType.PLUS,"Paris",3);

        assertAll(" All status are correct",
                () -> assertTrue(station1.getStationStatus() == DockingStationStatus.ONLINE),
                () -> assertTrue(station2.getStationStatus() == DockingStationStatus.OFFLINE)
        );
    }

    @Test
    @DisplayName("Test of the number of bicycles in docking station")
    public void testNumberBicycleDockingStation() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        DockingStation station = new DockingStation(position,DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",3);

        Bicycle mechaBicycle1 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle mechaBicycle2 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC,position);

        ParkingSlot p1=new ParkingSlot(station,mechaBicycle1);
        ParkingSlot p2=new ParkingSlot(station,mechaBicycle2);
        ParkingSlot p3=new ParkingSlot(station,elecBicycle);

        station.addParkingSlot(p1);
        station.addParkingSlot(p2);
        station.addParkingSlot(p3);

        
        assertAll(" All assertions are correct",
                () -> assertTrue(station.HasFreeParkingSlot() == false),
                () -> assertTrue(station.getNumberOfParkingSlots() == 3),
                () -> assertTrue(station.getNumberOfFreeParkingSlot() == 0),
                () -> assertTrue(station.getNumberOfBike(BicycleType.ELECTRIC) == 1),
                () -> assertTrue(station.getNumberOfBike(BicycleType.MECHANICAL) == 2),
                () -> assertTrue(station.hasBike(BicycleType.ELECTRIC) == true),
                () -> assertTrue(station.hasBike(BicycleType.MECHANICAL) == true)

        );
    }

    @Test
    @DisplayName("Test to add bicycle in docking station")
    public void testAddBicycleDockingStation() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        DockingStation station = new DockingStation(position,DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",3);

        Bicycle mechaBicycle1 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle mechaBicycle2 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC,position);

        ParkingSlot p1=new ParkingSlot(station,mechaBicycle1);
        ParkingSlot p2=new ParkingSlot(station,mechaBicycle2);
        ParkingSlot p3=new ParkingSlot(station,elecBicycle);
        ParkingSlot p4=new ParkingSlot(station);

        station.addParkingSlot(p1);
        station.addParkingSlot(p2);
        station.addParkingSlot(p3);
        station.addParkingSlot(p4);


        assertAll(" All assertions are correct",
                () -> assertTrue(station.HasFreeParkingSlot() == true),
                () -> assertTrue(station.getNumberOfParkingSlots() == 4),
                () -> assertTrue(station.getNumberOfFreeParkingSlot() == 1),
                () -> assertTrue(station.getNumberOfBike(BicycleType.ELECTRIC) == 1),
                () -> assertTrue(station.getNumberOfBike(BicycleType.MECHANICAL) == 2),
                () -> assertTrue(station.hasBike(BicycleType.ELECTRIC) == true),
                () -> assertTrue(station.hasBike(BicycleType.MECHANICAL) == true)
        );
    }

    @Test
    @DisplayName("Test to remove bicycle in docking station")
    public void testRemoveBicycleDockingStation() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        DockingStation station = new DockingStation(position,DockingStationStatus.ONLINE,DockingStationType.STANDARD,"Paris",3);

        Bicycle mechaBicycle1 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle mechaBicycle2 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC,position);

        ParkingSlot p1=new ParkingSlot(station,mechaBicycle1);
        ParkingSlot p2=new ParkingSlot(station,mechaBicycle2);
        ParkingSlot p3=new ParkingSlot(station,elecBicycle);

        station.addParkingSlot(p1);
        station.addParkingSlot(p2);
        station.addParkingSlot(p3);

        Bicycle mechaBicycle3 = new Bicycle(BicycleType.MECHANICAL,position);
        Bicycle elecBicycle2 = new Bicycle(BicycleType.ELECTRIC,position);

        station.addBicycle(mechaBicycle3);
        station.addBicycle(elecBicycle2);

        station.removeBike(mechaBicycle3);
        station.removeBike(elecBicycle2);

        assertAll(" All assertions are correct",
                () -> assertTrue(station.HasFreeParkingSlot() == false),
                () -> assertTrue(station.getNumberOfParkingSlots() == 3),
                () -> assertTrue(station.getNumberOfFreeParkingSlot() == 0),
                () -> assertTrue(station.getNumberOfBike(BicycleType.ELECTRIC) == 1),
                () -> assertTrue(station.getNumberOfBike(BicycleType.MECHANICAL) == 2),
                () -> assertTrue(station.hasBike(BicycleType.ELECTRIC) == true),
                () -> assertTrue(station.hasBike(BicycleType.MECHANICAL) == true),
                () -> assertTrue(station.hasAnyBike())


        );
    }
    

    
}
