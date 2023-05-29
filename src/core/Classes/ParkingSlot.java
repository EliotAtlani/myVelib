package core.Classes;

import core.Enums.*;

/**
 * Create a parkingslot in a DockingStation
 */
public class ParkingSlot {
    private static Integer uniqueId = 1;
    protected Integer id;
    private Bicycle bike;
    private DockingStation station;
    private ParkingSlotStatus slot_status;

    /**
     * @return
     */
    private static synchronized Integer getNextUniqueId() {
        return uniqueId++;
    }

    // ToString
    @Override
    public String toString() {
        return "Parking slot number " + id +
                " is " + slot_status + " with " + bike +" in station" + station.getId();
    }

    /**
     * @param station
     * @param bike
     */
    public ParkingSlot(DockingStation station, Bicycle bike) {
        id = uniqueId;
        getNextUniqueId();
        this.station=station;
        this.slot_status = ParkingSlotStatus.Occupied;
        this.bike = bike;
    }

    /**
     * @param station
     */
    public ParkingSlot(DockingStation station) {
        id = uniqueId;
        getNextUniqueId();
        this.station=station;
        this.slot_status = ParkingSlotStatus.Free;
        this.bike = null;
        }
    

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public DockingStation getStationId(){
        return station;
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
