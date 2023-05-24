package Cards;

import Enums.*;
import Classes.*;

public class RegistrationCard {
	private boolean canHandleTimeCredit;
	private int timeCreditBalance;
	private int firstHourMechanical;
	private int nextHourMechanical;
	private int firstHourElec;
	private int nextHourElec;
	
	
	public RegistrationCard(boolean canHandleTimeCredit, int firstHourMechanical,
			int nextHourMechanical, int firstHourElec, int nextHourElec) {
		super();
		this.canHandleTimeCredit = canHandleTimeCredit;
		this.timeCreditBalance = 0;
		this.firstHourMechanical = firstHourMechanical;
		this.nextHourMechanical = nextHourMechanical;
		this.firstHourElec = firstHourElec;
		this.nextHourElec = nextHourElec;
	}

	public double getFirstHourCost(BicycleType type) {
		if(type==BicycleType.ELECTRIC) {
			return getFirstHourElec();
		}
		return getFirstHourMechanical();
	}

	public double getNexttHourCost(double duration,BicycleType type) {
		if (duration<1) {
			return 0;
		}
		if(type==BicycleType.ELECTRIC) {
			return getNextHourElec()*(duration-1);
		}
		return getNextHourMechanical()*(duration -1);
	}

		

	public double computeRideCost(double duration,BicycleType type, User user) {
		if (duration == 0) {
			return 0;
		}
		int durationInHours= (int) (duration/60 +1);
		int totalCost= (int) (getFirstHourCost(type)+getNexttHourCost(durationInHours,type));
		return totalCost;
	}
	
	public boolean canHandleTimeCredit() {
		return canHandleTimeCredit;
	}

	public int getTimeCreditBalance() {
		return timeCreditBalance;
	}

	public int getFirstHourMechanical() {
		return firstHourMechanical;
	}

	public int getNextHourMechanical() {
		return nextHourMechanical;
	}

	public int getFirstHourElec() {
		return firstHourElec;
	}

	public int getNextHourElec() {
		return nextHourElec;
	}
	
	
}
