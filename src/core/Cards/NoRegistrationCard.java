package Cards;

public class NoRegistrationCard extends RegistrationCard{

    public NoRegistrationCard() {
		super(false,1,1,2,2);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "No registration card";
    }
}