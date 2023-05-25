package Events;

import Enums.*;
import Classes.*;
import java.time.LocalDateTime;

public class BikeEvent extends StationEvent{
    private User user;

    public RentBike(LocalDateTime eventTime, TypeOfEvent eventType, Station station, User user) {
        super(eventTime, eventType, station);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    



    
}
