package core.Cards;

/**
 * Create VMaxCard for a user
 */
public class VMaxCard extends RegistrationCard {
	
	public VMaxCard() {
		super(true,0,1,0,1);
		
	}

	
	/** 
	 * @return String
	 */
	@Override
    public String toString() {
        return "Vmax card";
    }

}
