package core.Cards;

public class NoRegistrationCard extends RegistrationCard{

    public NoRegistrationCard() {
		super(false,1,1,2,2);
		
	}

	
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "No registration card";
    }
}