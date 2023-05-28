package core.Classes;

import core.Enums.*;

public class Bicycle {
	private static Integer uniqueId = 1;
	protected Integer id;
	private BicycleType type;
	protected boolean inStation;
	protected GPSPosition position;
	protected boolean isFree;
	protected boolean fromStreet;

	/**
	 * @param type
	 * @param position
	 */
	public Bicycle(BicycleType type, GPSPosition position) {
		this.id = uniqueId;
		getNextUniqueId();
		this.type = type;
		this.position = position;
		this.inStation = true;
		this.isFree = true;
		this.fromStreet=false;
	}


	private static synchronized Integer getNextUniqueId() {
		return uniqueId++;
	}

	
	public Integer getId() {
		return id;
	}

	public GPSPosition getPosition() {
		return position;
	}

	/**
	 * @param position
	 */
	public void setPosition(GPSPosition position) {
		this.position = position;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public BicycleType getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(BicycleType type) {
		this.type = type;
	}

	/**
	 * @param inStation
	 * @param station
	 */
	public void setInStation(boolean inStation, DockingStation station) {
		this.inStation = inStation;
		if (station != null){
			this.position = station.getPosition();

		}
	
	}

	public boolean isInStation() {
		return inStation;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	// toString
	@Override
	public String toString() {
		return "Bicycle d'id : " + id + " and Type " + this.type.name();
	}


	/**
	 * @return
	 */
	public boolean IsFromStreet() {
		return this.fromStreet;
	}

	/**
	 * @param state
	 */
	public void setIsFromStreet(boolean state) {
		this.fromStreet = state;
	}
}
