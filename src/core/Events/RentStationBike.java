package core.Events;

import core.Enums.*;
import core.Classes.*;
import java.time.LocalDateTime;


public class RentStationBike extends Events{
    private User user;
    private LocalDateTime eventTime;
    private DockingStation station;
    private BicycleType type;

    public RentStationBike(LocalDateTime eventTime, DockingStation station, User user, BicycleType type) {
        super(eventTime, station, user, type);
    }

    public boolean RentingTypePossible(BicycleType type) {
        if (station.hasBike(type)) {
            return true;
        }
        return false;
    }

    public boolean RentingOtherTypesPossible() {
        if (station.hasAnyBike()) {
            return true;
        }
        return false;
    }

    public boolean checkOneBicycle(User user){
        if (user.hasABike()){
            return false;
        }
        return true;
    }

    public void RentBicycle() {
        if (RentingTypePossible(this.type) && checkOneBicycle(this.user)) {
            Bicycle bicycle = this.station.getBicycle(this.type);
            this.user.setBike(bicycle);
            this.station.removeBike(bicycle);
            bicycle.setInStation(false, this.station);
            bicycle.setFree(false);
        } else if (RentingOtherTypesPossible() && checkOneBicycle(this.user)) {
            Bicycle bicycle = this.station.getAnyBicycle();
            this.user.setBike(bicycle);
            this.station.removeBike(bicycle);
            bicycle.setInStation(false, this.station);
            bicycle.setFree(false);
            System.out.println("Different type of bicycle rented");
        } else {
            System.out.println("No bicycle available");
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
