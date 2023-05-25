package Classes;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

import Enums.*;

public class DockingStation {
	protected Integer id;
	private static Integer uniqueId = 1;
	protected GPSPosition position;
	protected DockingStationStatus stationStatus;
	protected DockingStationType stationType;
	protected HashMap<Integer, ParkingSlot> SlotHashMap;
	private int initialNumberOfBikes;

	// ToString
	public String displayHashMap(HashMap<Integer, ParkingSlot> SlotHashMap) {
		String string = "";
		for (Integer keys : SlotHashMap.keySet()) {
			string += "\n" + SlotHashMap.get(keys);
		}
		return string;
	}

	@Override
	public String toString() {
		return "Station number " + id + " is located at " +
				position.getLatitude() + " latitude and " +
				position.getLongitude() + " longitude is " + stationStatus +
				". The station's type is " + stationType + "\n" +
				"Parking Slots : " + displayHashMap(SlotHashMap);

	}

	public DockingStation(GPSPosition position, DockingStationStatus stationStatus,
			DockingStationType stationType) {
		id = uniqueId;
		uniqueId++;

		this.position = position;
		this.stationStatus = stationStatus;
		this.stationType = stationType;
		this.SlotHashMap = new HashMap<Integer, ParkingSlot>();
	}

	public void addParkingSlot(ParkingSlot parkingSlot) {
		this.SlotHashMap.put(parkingSlot.getId(), parkingSlot);
		if (parkingSlot.getParkingSlotStatus() != ParkingSlotStatus.Free) {
			this.initialNumberOfBikes++;
		}
	}

	public Boolean hasBike(BicycleType typeOfBicycle) {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Occupied
					&& slot.getValue().getBike().getType() == typeOfBicycle) {
				return true;
			}
		}
		return false;
	}

	public Boolean HasFreeParkingSlot() {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Free) {
				return true;
			}
		}
		return false;
	}

	public int getNumberOfParkingSlots() {
		return this.SlotHashMap.size();
	}

	public Integer getnumberOfFreeParkingSlot() {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		Integer number = 0;
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Free) {
				number++;
			}
		}
		return number;
	}

	public Integer getnumberOfBike(BicycleType type) {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		Integer number = 0;
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Occupied
					&& slot.getValue().getBike().getType() == type) {
				number++;
			}
		}
		return number;
	}

	public ParkingSlot getParkingSlotWithBike(BicycleType type) {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Occupied
					&& slot.getValue().getBike().getType() == type) {
				return slot.getValue();
			}
		}
		return null;
	}

	/**
	 * Get a free parking slot.
	 *
	 * @return a free parking slot within the station
	 */
	public ParkingSlot getFreeParkingSlot() {
		Iterator it = SlotHashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, ParkingSlot> entry = (Map.Entry) it.next();
			if (entry.getValue().getParkingSlotStatus() == ParkingSlotStatus.Free) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public HashMap<Integer, ParkingSlot>  getAllParking() {
		return SlotHashMap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GPSPosition getPosition() {
		return position;
	}

	public void setPosition(GPSPosition position) {
		this.position = position;
	}

	public DockingStationStatus getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(DockingStationStatus stationStatus) {
		this.stationStatus = stationStatus;
	}

	public DockingStationType getStationType() {
		return stationType;
	}

	public void setStationType(DockingStationType stationType) {
		this.stationType = stationType;
	}

	public HashMap<Integer, ParkingSlot> getSlotHashMap() {
		return SlotHashMap;
	}

	public void setSlotHashMap(HashMap<Integer, ParkingSlot> slotHashMap) {
		this.SlotHashMap = slotHashMap;
	}

	public int getInitialNumberOfBikes() {
		return initialNumberOfBikes;
	}

}
