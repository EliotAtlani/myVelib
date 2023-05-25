
import Enums.*;
import core.Classes.GPSPosition;
import Classes.*;
import java.time.LocalDateTime;

public class ReturnStreetBike {
    private User user;
    private LocalDateTime eventTime;
    private BicycleType type;
    private GPSPosition position;
    
    public ReturnStreetBike(LocalDateTime eventTime, User user, BicycleType type) {
        this.user = user;
        this.eventTime = eventTime;
        this.type = type;
        this.position = user.getPosition();
    }


    public void ReturnBicycle() {
        Bicycle bicycle = this.user.getBicycle();
        bicycle.setInStation(false);
        bicycle.setFree(true);
        user.setBicycle(null);
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

    public void setPosition(BicycleType type) {
        this.position = type;
    }
}
