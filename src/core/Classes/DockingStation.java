package Classes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Enums.BicycleType;
import Enums.DockingStationStatus;
import Enums.DockingStationType;
import Enums.ParkingSlotStatus;

public class DockingStation {
	protected Integer id;
	protected static Integer uniqueId = 1;
	protected GPSPosition position;
	protected DockingStationStatus stationStatus;
	protected DockingStationType stationType;
	protected HashMap<Integer, ParkingSlot> SlotHashMap;
	protected static HashMap<String, Integer> lastIdsByNetwork = new HashMap<>();
	protected int initialNumberOfBikes;
	protected int numberOfRent;
	protected int numberOfReturn;

	// ToString
	public String displayHashMap(HashMap<Integer, ParkingSlot> SlotHashMap) {
		String string = "";
		for (Integer keys : SlotHashMap.keySet()) {
			string += "\n" + SlotHashMap.get(keys);
		}
		return string;
	}

	private int generateUniqueId(String network) {
		int lastId = lastIdsByNetwork.getOrDefault(network, 0);
		int newId = lastId + 1;
		lastIdsByNetwork.put(network, newId);
		return newId;
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
			DockingStationType stationType,String network) {
		this.id = generateUniqueId(network);

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

	public void addBicycle(Bicycle bicycle) {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Free) {
				slot.getValue().setParkingSlotStatus(ParkingSlotStatus.Occupied);
				slot.getValue().setBike(bicycle);
			}
		}
	}

	public void removeBike(Bicycle bicycle) {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Occupied
					&& slot.getValue().getBike().getId() == bicycle.getId()) {
				slot.getValue().setParkingSlotStatus(ParkingSlotStatus.Free);
				slot.getValue().setBike(null);
			}
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

	public Boolean hasAnyBike() {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Occupied) {
				return true;
			}
		}
		return false;
	}

	public Bicycle getBicycle(BicycleType type) {
		ParkingSlot slot=getParkingSlotWithBike(type);
		if (slot!=null) {
			return slot.getBike();
		}else{
			return null;
		}
	}

	public Bicycle getAnyBicycle() {
		ParkingSlot slot=getParkingSlotWitAnyBike();
		if (slot!=null) {
			return slot.getBike();
		}else{
			return null;
		}
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

	public Integer getNumberOfFreeParkingSlot() {
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

	public Integer getNumberOfBike(BicycleType type) {
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
	
	public ParkingSlot getParkingSlotWitAnyBike() {
		Iterator iterator = SlotHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingSlot> slot = (Map.Entry<Integer, ParkingSlot>) iterator.next();
			if (slot.getValue().getParkingSlotStatus() == ParkingSlotStatus.Free) {
				return slot.getValue();
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

	public void addNumberOfRent(){
		this.numberOfRent++;
	}
	
	public void addNumberOfReturn() {
		this.numberOfReturn++;
	}

	public int getNumberOfRent(){
		return this.numberOfRent;
	}
	
	public int getNumberOfReturn() {
		return this.numberOfReturn;
	}

}
