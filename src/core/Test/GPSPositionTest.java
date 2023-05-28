package core.Test;

import core.Classes.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GPSPositionTest {
    @Test
    @DisplayName("Test of the position ")
    public void testPosition() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        GPSPosition position1 = new GPSPosition(1.0, 0.0);

        assertAll(" All positions are correct",
                () -> assertTrue(position.getLongitude() == 0.0),
                () -> assertTrue(position.getLatitude() == 0.0),
                () -> assertTrue(position1.getLongitude() == 1.0),
                () -> assertTrue(position1.getLatitude() == 0.0)
        );
    }

    @Test
    @DisplayName("Test of getting distance")
    public void testGettingDistance(){
        
        double lat1 = 37.7749;
        double lon1 = -122.4194;
        double lat2 = 34.0522;
        double lon2 = -118.2437;
        GPSPosition position1 = new GPSPosition(lat1, lon1);
        GPSPosition position2 = new GPSPosition(lat2, lon2);

        double expectedDistance = 509; // Expected distance in kilometers

        double distance = position1.getDistance(position2);
        assertEquals(expectedDistance, distance, 0.01); // Specify a delta of 0.01 for floating-point comparisons
    }
   
}
