package ridePlanning;

import coreClass.DockingStation;
import core.*;
import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;
import Enums.*;

import java.utils.ArrayList;


public class NormalRide {
	private GPSPosition startPosition;
	private GPSPosition endPosition;
	
	public NormalRide(GPSPosition startPosition, GPSPosition endPosition) {
		super();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	
	
	public DockingStation findStartStation(ArrayList<Station> stations) {
		DockingStation startingstation = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (DockingStation station : stations) {
			if(station.getStationState() == StationState.ON) {
				if (station.getDistance(startPosition) < distance) {
					startingstation = station;
					distance = station.getDistance(startPosition);
				}
			}
		}
	}
	
}

