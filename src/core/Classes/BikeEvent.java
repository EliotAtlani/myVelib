package Classes;

public class BikeEvent extends Event{
    private User user;

    public RideEvent(LocalDateTime eventTime, EventType eventType, Station station, User user) {
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
