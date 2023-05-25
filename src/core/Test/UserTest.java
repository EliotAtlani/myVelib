package core.Test;


import core.Classes.*;
import core.Cards.*;
import core.Enums.*;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;



public class UserTest {

    @Test 
    @DisplayName("Test unique ID for users")
    public void testUniqueID() {

        // Two users and one user
        User user1 = new User("Jean", new GPSPosition(0.0, 0.0), "123456789");
        User user2 = new User("Paul", new GPSPosition(1.0, 0.0), "987654321");
        User user3 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789");

        assertAll("Assert all ID are unique",
                () -> assertTrue(user1.getId() != user2.getId()),
                () -> assertTrue(user2.getId() != user3.getId()),
                () -> assertTrue(user3.getId() != user1.getId())

        );
    }

    @Test
    @DisplayName("Test of ride cost")
    public void testRideCost() {

        User user1 = new User("Jean", new GPSPosition(0.0, 0.0), "123456789");
        User user2 = new User("Paul", new GPSPosition(1.0, 0.0), "987654321");
        User user3 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789");
        User user4 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789");

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, new GPSPosition(0.0, 0.0));

        Double r1=user1.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 59, 0));
        Double r2=user2.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 59, 0));
        Double r3=user3.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 39, 0));
        Double r4=user4.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 59, 0));

        assertAll("Assert all ride cost are correct",
                () -> assertTrue(r1==1.0),
                () -> assertTrue(r2==2.0),
                () -> assertTrue(r3==3.0),
                () -> assertTrue(r4==6.0)
        );
    }

    @Test
    @DisplayName("Test of the position of users")
    public void testPositionUser() {

        GPSPosition position = new GPSPosition(0.0, 0.0);
        User user = new User("Jean", position, "123456789");

        assertAll(" All positions are correct",
                () -> assertTrue(user.getPosition() == position)
        );
    }

    @Test
    @DisplayName("Test of Card for ride cost for 1 hour")
    public void testRideCostWithCards() {

        VLibreCard vLibreCard = new VLibreCard();
        VMaxCard vMaxCard = new VMaxCard();

        User user1 = new User("Jean", new GPSPosition(0.0, 0.0), "123456789");
        User user2 = new User("Paul", new GPSPosition(1.0, 0.0), "987654321", vLibreCard);
        User user3 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789", vLibreCard);
        User user4 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789", vMaxCard);
        User user5 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789", vMaxCard);

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, new GPSPosition(0.0, 0.0));

        Double r1=user1.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 59, 0));
        Double r2=user2.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 59, 0));
        Double r3=user3.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 39, 0));
        Double r4=user4.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 59, 0));
        Double r5=user5.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 1, 59, 0));

        assertAll("Assert all ride cost are correct",
                () -> assertTrue(r1==1.0),
                () -> assertTrue(r2==1.0),
                () -> assertTrue(r3==0.0),
                () -> assertTrue(r4==0.0),
                () -> assertTrue(r5==0.0)
        );
    }

    @Test
    @DisplayName("Test of Card for ride cost for 3 hours")
    public void testRideCostLong() {

        VLibreCard vLibreCard = new VLibreCard();
        VMaxCard vMaxCard = new VMaxCard();

        User user1 = new User("Jean", new GPSPosition(0.0, 0.0), "123456789");
        User user2 = new User("Paul", new GPSPosition(1.0, 0.0), "987654321", vLibreCard);
        User user3 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789", vLibreCard);
        User user4 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789", vMaxCard);
        User user5 = new User("Jacques", new GPSPosition(2.0, 0.0), "123456789", vMaxCard);

        Bicycle mechaBicycle = new Bicycle(BicycleType.MECHANICAL, new GPSPosition(0.0, 0.0));
        Bicycle elecBicycle = new Bicycle(BicycleType.ELECTRIC, new GPSPosition(0.0, 0.0));

        Double r1=user1.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 59, 0));
        Double r2=user2.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 59, 0));
        Double r3=user3.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 59, 0));
        Double r4=user4.rideCost(elecBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 59, 0));
        Double r5=user5.rideCost(mechaBicycle.getType(), LocalDateTime .of(2019, 1, 1, 1, 0, 0), LocalDateTime .of(2019, 1, 1, 3, 59, 0));

        assertAll("Assert all ride cost are correct",
                () -> assertTrue(r1==3.0),
                () -> assertTrue(r2==5.0),
                () -> assertTrue(r3==2.0),
                () -> assertTrue(r4==2.0),
                () -> assertTrue(r5==2.0)
        );
    }

    @Test
    @DisplayName("test of adding charges to users")
    public void tesAddingCharges(){
        User user1 = new User("Jean", new GPSPosition(0.0, 0.0), "123456789");
        User user2 = new User("Paul", new GPSPosition(1.0, 0.0), "987654321");

        user1.addCharge(10.0);

        assertAll("Assert all charges are correct",
                () -> assertTrue(user1.getTotalCharges()==10.0),
                () -> assertTrue(user2.getTotalCharges()==0.0)
        );
    }
    




    
    
}