package core.RidePlanning;

import java.util.ArrayList;
import core.Classes.DockingStation;
import core.Classes.GPSPosition;
import core.Enums.DockingStationStatus;

public class NormalRide {
	protected GPSPosition startPosition;
	protected GPSPosition endPosition;

	public NormalRide(GPSPosition startPosition, GPSPosition endPosition) {
		super();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	public DockingStation findStartStation(ArrayList<DockingStation> stations) {
		DockingStation startingstation = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (DockingStation station : stations) {
			if (station.getStationStatus() == DockingStationStatus.ONLINE) {
				if (station.HasFreeParkingSlot()) {
					Double dist=this.startPosition.getDistance(station.getPosition());
					System.out.println(dist);
					if (dist < distance) {
						startingstation = station;
						distance = dist;
					}
				}
			}
		}
		return startingstation;
	}

	public DockingStation findEndStation(ArrayList<DockingStation> stations) {
		DockingStation endstation = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (DockingStation station : stations) {
			if (station.getStationStatus() == DockingStationStatus.ONLINE) {
				if (station.HasFreeParkingSlot()) {
					if (this.endPosition.getDistance(station.getPosition()) < distance) {
						endstation = station;
						distance = this.endPosition.getDistance(station.getPosition());
					}
				}
			}
		}
		return endstation;
	}

	public GPSPosition getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(GPSPosition startPosition) {
		this.startPosition = startPosition;
	}

	public GPSPosition getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(GPSPosition endPosition) {
		this.endPosition = endPosition;
	}

}
