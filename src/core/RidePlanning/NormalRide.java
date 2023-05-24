package RidePlanning;

import java.util.ArrayList;

import core.*;
import core.myVelib.Enums.*;
import core.myVelib.coreClass.*;



public class NormalRide {
	protected GPSPosition startPosition;
	protected GPSPosition endPosition;
	
	public NormalRide(GPSPosition startPosition, GPSPosition endPosition) {
		super();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	
	public Double getDistance(GPSPosition startPoint, GPSPosition endPoint) {
		Double lat_a = startPoint.getLatitude();
        Double lng_a = startPoint.getLongitude();
        Double lat_b = endPoint.getLatitude();
        Double lng_b = endPoint.getLongitude();
        
        Double d_lng=lng_b-lng_a;
        
        Double t1 = Math.sin(lat_a)*Math.sin(lat_b);
        Double t2 = Math.cos(lat_a)*Math.cos(lat_b)*Math.cos(d_lng);
        Double t3 = Math.cos(t1+t2);
        Double S_start_end=Math.acos(t3);
        return S_start_end;
	}
	
	public DockingStation findStartStation(ArrayList<DockingStation> stations) {
		DockingStation startingstation = null;
		Double distance = Double.POSITIVE_INFINITY;
		for (DockingStation station : stations) {
			if(station.getStationStatus() == DockingStationStatus.ONLINE){
				if (station.HasFreeParkingSlot()) {
					if(getDistance(startPosition, station.getPosition())<distance) {
						startingstation=station;
						distance=getDistance(startPosition, station.getPosition());
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
			if(station.getStationStatus() == DockingStationStatus.ONLINE){
				if (station.HasFreeParkingSlot()) {
					if(getDistance(endPosition, station.getPosition())<distance) {
						endstation=station;
						distance=getDistance(endPosition, station.getPosition());
					}
				}
			}
		}
		return endstation;
	}
	
}

