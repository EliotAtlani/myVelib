package coreClass;

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

}
