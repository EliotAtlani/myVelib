package Classes;
import java.time.LocalDateTime;
import Enums.*;

public class Event {
  
    private LocalDateTime eventTime;
    private TypeOfEvent eventType;
    private Station station;

    public Event(LocalDateTime eventTime, EventType eventType, Station station) {
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.station = station;
    }

    @Override
    public String toString() {
        return "Event of type " + eventType +
                "  took place " + eventTime + " at station #" + station.getId() ;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }
    
    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
