package core.Events;

import core.Enums.*;
import core.Classes.GPSPosition;
import core.Classes.*;
import java.time.LocalDateTime;

public class ReturnStreetBike extends Events {
    private User user;
    private LocalDateTime eventTime;
    private BicycleType type;
    private GPSPosition position;
    
    public ReturnStreetBike(LocalDateTime eventTime, User user, BicycleType type) {
        super(eventTime, null, user, type);

    }


    public void ReturnBicycle() {
        Bicycle bicycle = this.user.getBike();
        bicycle.setInStation(false,null);
        bicycle.setFree(true);
        user.setBike(null);
        bicycle.setPosition(this.position);
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

    public void setPosition(GPSPosition position) {
        this.position = position;
    }
}
