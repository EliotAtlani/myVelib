
import Enums.*;
import core.Classes.DockingStation;
import Classes.*;
import java.time.LocalDateTime;

public class ReturnStationBike {
    private User user;
    private LocalDateTime eventTime;
    private DockingStation station;
    private BicycleType type;
    
    public ReturnStationBike(LocalDateTime eventTime, Station station, User user, BicycleType type) {
        this.user = user;
        this.eventTime = eventTime;
        this.station = station;
        this.type = type;
    }

    public boolean ReturnPossible() {
        if (this.station.getnumberOfFreeParkingSlot()>0 && this.station.getStationStatus()==DockingStationStatus.ONLINE) {
            return true;
        }
        return false;
    }

    public void ReturnBicycle() {
        if (ReturnPossible()) {
            Bicycle bicycle = this.user.getBicycle();
            this.station.addBicycle(bicycle);
            bicycle.setInStation(true);
            bicycle.setFree(true);
            user.setBicycle(null);
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

    public Station getStation() {
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
