package core.Test;

import core.Enums.*;
import core.Classes.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BicycleTest {
    @Test
    @DisplayName("Test of the type of bicycles")
    public void testTypeBicycle() {

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, new GPSPosition(0.0, 0.0));

        assertAll(" All types are correct",
                () -> assertTrue(mechaBicycle.getType() == BicycleType.MECHANICAL),
                () -> assertTrue(elecBicycle.getType() == BicycleType.ELECTRIC)
        );
    }

    @Test
    @DisplayName("Test unique ID for bicycles")
    public void testUniqueID() {

        // Two electrical bicycles and one electrical bicycle
        Bicycle mechaBicycle1 = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        Bicycle mechaBicycle2 = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, new GPSPosition(0.0, 0.0));

    

        assertAll("Assert all ID are unique",
                () -> assertTrue(mechaBicycle1.getId() != mechaBicycle2.getId()),
                () -> assertTrue(mechaBicycle2.getId() != elecBicycle.getId())
        );
    }

    @Test
    @DisplayName("Test of the position of bicycles")
    public void testPositionBicycle() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, position);
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, position);

        assertAll(" All positions are correct",
                () -> assertTrue(mechaBicycle.getPosition() == position),
                () -> assertTrue(elecBicycle.getPosition() == position)
        );
    }

    @Test
    @DisplayName("Test of the station of bicycles")
    public void testStationBicycle() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        GPSPosition position1 = new GPSPosition(1.0, 0.0);

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, position);
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, position);
        DockingStation station = new DockingStation(position1,DockingStationStatus.ONLINE,DockingStationType.STANDARD);
        elecBicycle.setInStation(true,station);

        assertAll(" All positions are correct",
                () -> assertTrue(mechaBicycle.getPosition() == position),
                () -> assertTrue(elecBicycle.getPosition() == position1),
                () -> assertTrue(elecBicycle.isFree() == true),
                () -> assertTrue(elecBicycle.isInStation() == true),
                () -> assertTrue(mechaBicycle.isFree() == true),
                () -> assertTrue(mechaBicycle.isInStation() == false)
        );

    }
    
}
