package SortingStation;

import Classes.*;
import Events.*;
import java.util.Collections;

public abstract class SortingStation {

    public abstract Map<Integer, Double> sortStations(ArrayList<Event> events, HashMap<Integer, Station> stations);

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
