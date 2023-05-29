package core.Test;

import core.Classes.*;
import core.Enums.*;
import core.Cards.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CardsTest {

    @Test
    @DisplayName("Test of the riding cost depending on cards")
    public void testCards() {

        RegistrationCard card1 = new NoRegistrationCard();
        RegistrationCard card2 = new VLibreCard();
        RegistrationCard card3 = new VMaxCard();

        Double duration = 340.0;

        User user1= new User("user",new GPSPosition(0., 0.),"123456","Paris");
        User user2= new User("user",new GPSPosition(0., 0.),"123456","Paris");
        User user3= new User("user",new GPSPosition(0., 0.),"123456","Paris");

        Double ride1=card1.giveRideCost(duration,BicycleType.MECHANICAL);
        Double ride2=card1.giveRideCost(duration,BicycleType.ELECTRIC);
        Double ride3=card2.giveRideCost(duration,BicycleType.MECHANICAL);
        Double ride4=card2.giveRideCost(duration,BicycleType.ELECTRIC);
        Double ride5=card3.giveRideCost(duration,BicycleType.MECHANICAL);
        Double ride6=card3.giveRideCost(duration,BicycleType.ELECTRIC);


        assertAll(
                () -> assertTrue(ride1 == 6.0),
                () -> assertTrue(ride2 == 12.0),
                () -> assertTrue(ride3 == 5.0),
                () -> assertTrue(ride4 == 11.0),
                () -> assertTrue(ride5 == 5.0),
                () -> assertTrue(ride6 == 5.0)

            );   
    }
    
}
