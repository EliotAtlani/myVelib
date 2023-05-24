package coreClass;
 import Enums.*;

public class ParkingSlot {
	private static Integer uniqueId = 0;
	protected Integer id;
	private Bicycle bike;
	private DockingStation station;
	private ParkingSlotStatus slot_status;
	private DockingStationStatus station_status;
	private DockingStationType type;
	
	
	private static synchronized Integer getNextUniqueId() {
        return uniqueId++;
    }
	
	//ToString
    @Override
    public String toString() {
        return "Parking slot number " + id +
                " is " + slot_status + " with " + bike;
    }

    public ParkingSlot(ParkingSlotStatus slot_status, Bicycle bike) {
        id = uniqueId;
        getNextUniqueId();
        this.slot_status = slot_status;
        if (slot_status == ParkingSlotStatus.Occupied){ // One can only instantiates a bicycle to a parking slot if the parking slot is set Occupied
            this.bike = bike;
        }
        else { // Otherwise the bicycle is set to null
            this.bike = null;
        }
    }
    
    //Getters and Setters
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer Id) {
        id = Id;
    }
	
    public ParkingSlotStatus getParkingSlotStatus() {
		return slot_status;
	}
    
    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.slot_status = parkingSlotStatus;
    }


	public Bicycle getBike() {
		return bike;
	}


	public void setBike(Bicycle bike) {
		this.bike = bike;
	}

	
	
}



