package Events;

import java.time.LocalDateTime;

import Classes.DockingStation;
import Classes.User;
import Enums.BicycleType;

public class Events {
    private User user;
    private LocalDateTime eventTime;
    private DockingStation station;
    private BicycleType type;

    public Events(LocalDateTime eventTime, DockingStation station, User user, BicycleType type) {
        this.user = user;
        this.eventTime = eventTime;
        this.station = station;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public DockingStation getStation() {
        return station;
    }

    public BicycleType getType() {
        return type;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public void setStation(DockingStation station) {
        this.station = station;
    }

    public void setType(BicycleType type) {
        this.type = type;
    }

    
    
}
