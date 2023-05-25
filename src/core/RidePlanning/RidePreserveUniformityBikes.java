package core.RidePlanning;

import core.Enums.*;
import core.Classes.*;

import java.util.ArrayList;


public class RidePreserveUniformityBikes extends NormalRide {

    public RidePreserveUniformityBikes(GPSPosition startPosition, GPSPosition endPosition) {
        super(startPosition, endPosition);
        // TODO Auto-generated constructor stub
    }

    public DockingStation findStartStation(ArrayList<DockingStation> stations, BicycleType typeOfBicycle) {
        DockingStation startStation = null;
        Double minDistance = Double.POSITIVE_INFINITY;
        DockingStation startStationWithMoreBicycle = stations.get(0);
        Double minDistanceWithMoreBicycle = getDistance(super.startPosition, stations.get(0).getPosition());
        for (DockingStation station : stations) {
            if (station.getStationStatus() == DockingStationStatus.ONLINE) {
                if (station.hasBike(typeOfBicycle)) {
                    Double distance = getDistance(super.startPosition, station.getPosition());
                    if (distance < minDistance) {
                        startStation = station;
                        minDistance = distance;
                    } else if (distance <= minDistance * 1.05) { // To find the closest station with more bicycle
                        startStationWithMoreBicycle = station;
                        minDistanceWithMoreBicycle = distance;
                    }
                }
            }
        }
        if (startStationWithMoreBicycle.getNumberOfBike(typeOfBicycle) > startStation.getNumberOfBike(typeOfBicycle)) {
            return startStationWithMoreBicycle;
        } else {
            return startStation;
        }
    }

    @Override
    public DockingStation findEndStation(ArrayList<DockingStation> stations) {
        DockingStation destinationStation = null;
        Double minDistance = Double.POSITIVE_INFINITY;
        DockingStation destinationStationWithMoreBicycle = stations.get(0);
        Double minDistanceWithMoreBicycle = getDistance(super.endPosition, stations.get(0).getPosition());
        for (DockingStation station : stations) { // Parsing through the stations
            if (station.getStationStatus() == DockingStationStatus.ONLINE) { // If the station is on service
                if (station.HasFreeParkingSlot()) { // If the station has at least one free parking slot
                    Double distance = getDistance(super.endPosition, station.getPosition());
                    if (distance <= minDistance) { // To find the closest station
                        destinationStation = station;
                        minDistance = distance;
                    } else if (distance <= minDistance * 1.05) { // To find the closest station with more bicycle
                        destinationStationWithMoreBicycle = station;
                        minDistanceWithMoreBicycle = distance;
                    }
                }
            }
        }
        if (destinationStationWithMoreBicycle.getNumberOfFreeParkingSlot() > destinationStation
                .getNumberOfFreeParkingSlot()) {
            return destinationStationWithMoreBicycle;
        } else {
            return destinationStation;
        }
    }

}
