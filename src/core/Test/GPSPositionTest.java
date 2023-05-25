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
   
}
