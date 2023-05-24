package RidePlanning;

import java.util.ArrayList;

import core.myVelib.Enums.DockingStationStatus;
import core.myVelib.coreClass.DockingStation;
import core.myVelib.coreClass.GPSPosition;
import core.myVelib.Enums.*;

public class RideAvoidingPlusStations extends NormalRide{

	public RideAvoidingPlusStations(GPSPosition startPosition, GPSPosition endPosition) {
		super(startPosition, endPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DockingStation findEndStation(ArrayList<DockingStation> stations) {
		DockingStation endstation = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (DockingStation station : stations) {
			if(station.getStationStatus() == DockingStationStatus.ONLINE){
				if (station.HasFreeParkingSlot() & station.getStationType()!=DockingStationType.PLUS) {
					if(getDistance(super.endPosition, station.getPosition())<distance) {
						endstation=station;
						distance=getDistance(super.endPosition, station.getPosition());
					}
				}
			}
		}
		return endstation;
	}
}
