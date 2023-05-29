package core.Cards;

import core.Enums.*;

/**
 * Handle the creation on registration Card
*/

public class RegistrationCard {
	private boolean canHandleTimeCredit;
	private double timeCreditBalance;
	private int firstHourMechanical;
	private int nextHourMechanical;
	private int firstHourElec;
	private int nextHourElec;

	/**
	 * 
	 * @param canHandleTimeCredit
	 * @param firstHourMechanical
	 * @param nextHourMechanical
	 * @param firstHourElec
	 * @param nextHourElec
	 */
	
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

	
	/** 
	 * @param type
	 * @return double
	 */
	public double getFirstHourCost(BicycleType type) {
		if(type==BicycleType.ELECTRIC) {
			return getFirstHourElec();
		}
		return getFirstHourMechanical();
	}

	/**
	 * 
	 * @param duration
	 * @param type
	 * @return
	 */
	public double getNexttHourCost(double duration,BicycleType type) {
		if (duration<1) {
			return 0;
		}
		if(type==BicycleType.ELECTRIC) {
			return getNextHourElec()*(duration-1);
		}
		return getNextHourMechanical()*(duration -1);
	}

		

	/**
	 * 
	 * @param duration
	 * @param type
	 * @return
	 */
	public double giveRideCost(double duration,BicycleType type) {
		if (duration == 0) {
			return 0;
		}
		int durationInHours= (int) (duration/60 +1);
		int totalCost= (int) (getFirstHourCost(type)+getNexttHourCost(durationInHours,type));
		return totalCost;
	}
	
	
	/** 
	 * @return boolean
	 */
	public boolean canHandleTimeCredit() {
		return canHandleTimeCredit;
	}
	/**
	 * 
	 * @return int
	 */
	public double getTimeCreditBalance() {
		return timeCreditBalance;
	}
	/**
	 * 
	 * @return int
	 */
	public int getFirstHourMechanical() {
		return firstHourMechanical;
	}


	/**
	 * @return int
	 */
	public int getNextHourMechanical() {
		return nextHourMechanical;
	}

	/**
	 * @return int
	 */
	public int getFirstHourElec() {
		return firstHourElec;
	}

	/**
	 * @return int
	 */
	public int getNextHourElec() {
		return nextHourElec;
	}
	
	
}
