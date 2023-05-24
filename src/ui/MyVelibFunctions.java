package ui;

import java.util.Scanner;

import Cards.RegistrationCard;
import Classes.*;
import Enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ui.MyVelibFunctions;

public class MyVelibFunctions {
    /**
     * Get random type of bicycle.
     *
     * @return the random type of bicycle
     */
    public static BicycleType getRandomBicycleType() {
        if (Math.random() < 0.5) {
            return BicycleType.MECHANICAL;
        } else {
            return BicycleType.ELECTRIC;
        }
    }

    /**
     * Setup a myVelib network.
     *
     *
     * @param nameStation the name of station
     * @param nbOfStation the number of station
     * @param nbOfSlots   the number of slot per station
     * @param sideLength  the side length
     * @param nbOfBikes   the number of bike
     */

    public MyVelibFunctions() {

    }

    public static void setup(String nameStation, Integer nbOfStation, Integer nbOfSlots, Double sideLength,
            Integer nbOfBikes) {
        ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();
        // Place stations uniformly on a square grid whose the side is of length
        // sideLength
        for (int i = 0; i < nbOfStation; i++) {
            // We generate randomly the position of the docking station
            GPSPosition position = new GPSPosition(Math.random()*sideLength, Math.random()*sideLength);
            //Create the station
            DockingStation station = new DockingStation(position, DockingStationStatus.ONLINE,
                    DockingStationType.STANDARD);
            // stations.add(station);
            // MyVelibSystem.myVelibRecord.addStationIfNotExists(station);

            // Create nbOfSlots in this station
            for (int j = 0; j < nbOfSlots; j++) {
                ParkingSlot parkingSlot = new ParkingSlot(ParkingSlotStatus.Free, null,station);
                station.addParkingSlot(parkingSlot);
                parkingSlots.add(parkingSlot);
            }
        }


        // Generate numberOfBike randomly distributed index between 0 and
        // numberOfStation*numberOfSlotPerStation
        List<Integer> randomParkingSlot = new ArrayList<>();
        for (int i = 0; i < nbOfSlots * nbOfStation; i++) {
            randomParkingSlot.add(i);
        }
        Collections.shuffle(randomParkingSlot);
        for (int i = 0; i < nbOfBikes; i++) {
            parkingSlots.get(randomParkingSlot.get(i)).setParkingSlotStatus(ParkingSlotStatus.Occupied);
            parkingSlots.get(randomParkingSlot.get(i)).setBike(new Bicycle(getRandomBicycleType(), parkingSlots.get(randomParkingSlot.get(i)).getStationId().getPosition()));
        }

    }

    /**
     * Set up user
     * 
     * @param userName
     * @param registration
     * @param nameStation
     */
    public static void addUser(String userName,String registration, String nameStation){
        
    }

    public static void displayStation(String nameStation, Integer stationId){
        DockingStation dockingStation = new DockingStation(null, null, null);
    
    }
}
