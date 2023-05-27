package Classes;

public class GPSPosition {
	protected Double longitude;
	protected Double latitude;

	public GPSPosition(Double longitude, Double latitue) {
		super();
		this.longitude = longitude;
		this.latitude = latitue;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitue) {
		this.latitude = latitue;
	}

	@Override
	public String toString() {
		return "longitude is " + longitude + " and latitude is" + latitude;
	}

	public Double getDistance(GPSPosition endPoint) {
		Double lat_a = this.latitude;
		Double lng_a = this.longitude;
		Double lat_b = endPoint.getLatitude();
		Double lng_b = endPoint.getLongitude();

		double earthRadius = 6371;

		// Convert degrees to radians
		double phi1 = Math.toRadians(lat_a);
		double phi2 = Math.toRadians(lat_b);
		double deltaPhi = Math.toRadians(lat_b - lat_a);
		double deltaLambda = Math.toRadians(lng_b - lng_a);

		// Haversine formula
		double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
				Math.cos(phi1) * Math.cos(phi2) *
						Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		// Calculate the distance
		double distance = earthRadius * c;

		return distance;
	}

}
