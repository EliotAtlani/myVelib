package SortingStation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Classes.DockingStation;
import Events.Events;


public abstract class SortingStation {

    public abstract Map<Integer, Double> sortStations(ArrayList<Events> events, HashMap<Integer, DockingStation> stations);

    public Map<Integer, Double> sortHashMap(Map<Integer, Double> stationValues, Boolean reversed) {
        List<Map.Entry<Integer, Double>> stationValuesList = new ArrayList<>(stationValues.entrySet());
        stationValuesList.sort(Map.Entry.comparingByValue());
        if (reversed) {
            Collections.reverse(stationValuesList);
        }

        Map<Integer, Double> sortedStations = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : stationValuesList) {
            sortedStations.put(entry.getKey(), entry.getValue());
        }
        return sortedStations;
    }
    
}
