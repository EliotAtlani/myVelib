package core.RidePlanning;

import java.util.ArrayList;

import core.Classes.DockingStation;
import core.Classes.GPSPosition;
import core.Enums.DockingStationStatus;
import core.Enums.DockingStationType;

public class RidePreferPlusStation extends NormalRide {

	public RidePreferPlusStation(GPSPosition startPosition, GPSPosition endPosition) {
		super(startPosition, endPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DockingStation findEndStation(ArrayList<DockingStation> stations) {
		DockingStation endstation = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (DockingStation station : stations) {
			if (station.getStationStatus() == DockingStationStatus.ONLINE) {
				if (station.HasFreeParkingSlot() & station.getStationType() == DockingStationType.PLUS) {
					if (super.endPosition.getDistance(station.getPosition()) < distance) {
						endstation = station;
						distance = super.endPosition.getDistance(station.getPosition());
					}
				}
			}
		}
		if (endstation == null) {
			for (DockingStation station : stations) {
				if (station.getStationStatus() == DockingStationStatus.ONLINE) {
					if (station.HasFreeParkingSlot()) {
						if (super.endPosition.getDistance(station.getPosition()) < distance) {
							endstation = station;
							distance = super.endPosition.getDistance(station.getPosition());
						}
					}
				}
			}
			return endstation;
		} else {
			return endstation;
		}
	}

}
