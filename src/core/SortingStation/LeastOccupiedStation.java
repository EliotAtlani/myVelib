package SortingStation;

import Classes.*;
import Events.*;

public class LeastOccupiedStation extends SortingStation {
    
        public Map<Integer, Double> getStationsOccupation(ArrayList<Event> events, HashMap<Integer, Station> stations) {
            Map<Integer, Double> stationValues = new HashMap<>();
            for (Map.Entry<Integer, Station> entry : stations.entrySet()) {
                stationValues.put(entry.getKey(), 0.0);
            }
            for (Event event : events) {
                if (event instanceof RentStationBike) {
                    stationValues.put(event.getStation().getId(), stationValues.get(event.getStation().getId()) - 1);
                } else if (event instanceof ReturnStationBike) {
                    stationValues.put(event.getStation().getId(), stationValues.get(event.getStation().getId()) + 1);
                }
            }
            return stationValues;
        }
    
        public Map<Integer, Double> sortStations(ArrayList<Event> events, HashMap<Integer, Station> stations) {
            Map<Integer, Double> stationsScore = getStationsOccupation(events, stations);
            return this.sortHashMap(stationsScore, false);
        };

    

}