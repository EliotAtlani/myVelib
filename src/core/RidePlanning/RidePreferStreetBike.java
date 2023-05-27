package core.RidePlanning;

import java.util.ArrayList;

import core.Classes.Bicycle;
import core.Classes.GPSPosition;

public class RidePreferStreetBike extends NormalRide {

	public RidePreferStreetBike(GPSPosition startPosition, GPSPosition endPosition) {
		super(startPosition, endPosition);
	}

	public Bicycle findStartBike(ArrayList<Bicycle> bikes) {
		Bicycle startingbike = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (Bicycle bike : bikes) {
			if (bike.isInStation() == false && bike.isFree()) {
				System.out.println("Bike " + bike.getId() + " is not in station");
				if (super.startPosition.getDistance(bike.getPosition()) < distance) {
					startingbike = bike;
					distance = super.startPosition.getDistance(bike.getPosition());
				}
			}
		}
		return startingbike;
	}
}
