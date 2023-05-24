package RidePlanning;

import java.util.ArrayList;

import Enums.*;
import Classes.*;

public class RidePreferStreetBike extends NormalRide {

	public RidePreferStreetBike(GPSPosition startPosition, GPSPosition endPosition) {
		super(startPosition, endPosition);
		// TODO Auto-generated constructor stub
	}

	public Bicycle findStartBike(ArrayList<Bicycle> bikes) {
		Bicycle startingbike = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (Bicycle bike : bikes) {
			if (bike.isInStation()==false & bike.isFree()) {
					if(getDistance(super.startPosition, bike.getPosition())<distance) {
						startingbike=bike;
						distance=getDistance(startPosition, bike.getPosition());
					}
				}
			}
		return startingbike;
	}
}
