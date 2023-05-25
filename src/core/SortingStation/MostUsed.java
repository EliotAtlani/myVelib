package SortingStation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Classes.DockingStation;
import Events.Events;
import Events.RentStationBike;
import Events.ReturnStationBike;

public class MostUsed extends SortingStation {

    public Map<Integer, Double> getStationsRentReturn(ArrayList<Events> events, HashMap<Integer, DockingStation> stations) {
        Map<Integer, Double> stationValues = new HashMap<>();
        for (Map.Entry<Integer, DockingStation> entry : stations.entrySet()) {
            stationValues.put(entry.getKey(), 0.0);
        }
        for (Events event : events) {
            if (event instanceof RentStationBike || event instanceof ReturnStationBike) {
                    stationValues.put(event.getStation().getId(), stationValues.get(event.getStation().getId()) + 1);
                }
            }
        return stationValues;
    }

    public Map<Integer, Double> sortStations(ArrayList<Events> events, HashMap<Integer, DockingStation> stations) {
        Map<Integer, Double> stationsScore = getStationsRentReturn(events, stations);
        return this.sortHashMap(stationsScore, true);
    };

}
