package Events;

import java.time.LocalDateTime;

import Classes.Bicycle;
import Classes.GPSPosition;
import Classes.User;
import Enums.BicycleType;

public class RentStreetBike {
    private User user;
    private LocalDateTime eventTime;
    private GPSPosition position;
    private BicycleType type;

    public RentStreetBike(LocalDateTime eventTime, GPSPosition position, User user, BicycleType type) {
        this.user = user;
        this.eventTime = eventTime;
        this.position = user.getPosition();
        this.type = type;
    }


    public boolean RentingPossible() {
        if (this.position==this.user.getPosition() && this.user.hasABike()==false) {
            return true;
        }
        return false;
    }


    public void RentBicycle() {
        if (RentingPossible() && checkOneBicycle(this.user)) {
            Bicycle bicycle = this.station.getBicycle(this.type);
            this.user.setBicycle(bicycle);
            this.station.removeBicycle(bicycle);
            bicycle.setInStation(false);
            bicycle.setFree(false);
        } else if (RentingOtherTypesPossible() && checkOneBicycle(this.user)) {
            Bicycle bicycle = this.station.getAnyBicycle();
            this.user.setBicycle(bicycle);
            this.station.removeBicycle(bicycle);
            bicycle.setInStation(false);
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

    public BicycleType getType() {
        return type;
    }

    public void setType(BicycleType type) {
        this.type = type;
    }

    public GPSPosition getPosition() {
        return position;
    }

    public void setPosition(BicycleType type) {
        this.position = type;
    }

    
}
