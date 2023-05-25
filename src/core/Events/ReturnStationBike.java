package Events;

import java.time.LocalDateTime;

import Classes.Bicycle;
import Classes.DockingStation;
import Classes.User;
import Enums.BicycleType;
import Enums.DockingStationStatus;


public class ReturnStationBike extends Events{
    private User user;
    private LocalDateTime eventTime;
    private DockingStation station;
    private BicycleType type;
    
    public ReturnStationBike(LocalDateTime eventTime, DockingStation station, User user, BicycleType type) {
        super(eventTime, station, user, type);

    }

    public boolean ReturnPossible() {
        if (this.station.getNumberOfFreeParkingSlot()>0 && this.station.getStationStatus()==DockingStationStatus.ONLINE) {
            return true;
        }
        return false;
    }

    public void ReturnBicycle() {
        if (ReturnPossible()) {
            Bicycle bicycle = this.user.getBike();
            this.station.addBicycle(bicycle);
            bicycle.setInStation(true, this.station);
            bicycle.setFree(true);
            user.setBike(null);
        } else {
            System.out.println("No parking slot available");
        }
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public DockingStation getStation() {
        return station;
    }

    public void setStation(DockingStation station) {
        this.station = station;
    }

    public BicycleType getType() {
        return type;
    }

    public void setType(BicycleType type) {
        this.type = type;
    }
}
