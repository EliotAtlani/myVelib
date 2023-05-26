package Classes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import Cards.NoRegistrationCard;
import Cards.RegistrationCard;
import Enums.BicycleType;
import Enums.ParkingSlotStatus;
import front.MyVelibIndex;

public class User {
	private String name;
	private int id;
	private GPSPosition position;
	private String creditCardNumber;
	private RegistrationCard registrationCard;
	private double totalCharges;
	private int numberOfRides;
	private int timeSpent;
	private double timeCredit;
	private Bicycle bike;/* The bike of the user */
	private LocalDateTime rentDateTime;
	private int rentTotalTime;
	private static ArrayList<Integer> takenIds = new ArrayList<>();
	double rideDurationInMinutes;
	BicycleType bicycleType;
	public static ArrayList<User> userList = new ArrayList<>();

	private static int getValidId() {
		int tempId = 1;
		while (takenIds.contains(tempId)) {
			tempId++;
		}
		takenIds.add(tempId);
		return tempId;
	}

	@Override
	public String toString() {
		return "User id is : " + id + "\n User name is : "+ name;

	}
	public User(String name, GPSPosition position, String creditCardNumber, RegistrationCard registrationCard) {
		this.name = name;
		this.id = getValidId();
		this.position = position;
		this.creditCardNumber = creditCardNumber;
		this.registrationCard = registrationCard;
		this.totalCharges = 0;
		this.numberOfRides = 0;
		this.timeSpent = 0;
		this.bike = null;
		this.rentTotalTime = 0;
	}

	public User(String name, GPSPosition position, String creditCardNumber) {
		this.name = name;
		this.id = getValidId();
		this.position = position;
		this.creditCardNumber = creditCardNumber;
		this.registrationCard = new NoRegistrationCard();
		this.totalCharges = 0;
		this.numberOfRides = 0;
		this.timeSpent = 0;
		this.bike = null;
		this.rentTotalTime = 0;
	}

	public void addCharge(double charge) {
		this.setTotalCharges(this.getTotalCharges() + charge);
	}

	public double rideCost(BicycleType type, LocalDateTime rentDateTime, LocalDateTime returnDateTime) {
        int rideDuration = (int) ChronoUnit.MINUTES.between(rentDateTime,returnDateTime);
        double rideCost = this.getRegistrationCard().computeRideCost(rideDuration, type, this);
        this.addCharge(rideCost);
        return rideCost;
    }

	public boolean hasABike() {
		return this.bike != null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GPSPosition getPosition() {
		return position;
	}

	public void setPosition(GPSPosition position) {
		this.position = position;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public RegistrationCard getRegistrationCard() {
		return registrationCard;
	}

	public void setRegistrationCard(RegistrationCard registrationCard) {
		this.registrationCard = registrationCard;
	}

	public double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public int getNumberOfRides() {
		return numberOfRides;
	}

	public void setNumberOfRides(int numberOfRides) {
		this.numberOfRides = numberOfRides;
	}

	public int getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Bicycle getBike() {
		return bike;
	}

	public void setBike(Bicycle bike) {
		this.bike = bike;
	}

	public LocalDateTime getRentDateTime() {
		return rentDateTime;
	}

	public void setRentDateTime(LocalDateTime rentDateTime) {
		this.rentDateTime = rentDateTime;
	}

	public int getRentTotalTime() {
		return rentTotalTime;
	}

	public void setRentTotalTime(int rentTotalTime) {
		this.rentTotalTime = rentTotalTime;
	}

	public void addUserToList(User user){
		userList.add(user);
	}

	/**
	 * Computes the cost of a ride.
	 *
	 * @param bicycleType    the type of the bicycle rented
	 * @param rentDateTime   the rent datetime
	 * @param returnDateTime the return datetime
	 * @return the ride cost
	 */
	public double computeCost(BicycleType bicycleType, LocalDateTime rentDateTime, LocalDateTime returnDateTime) {
		int rideDurationInMinutes = (int) rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);
		this.rentTotalTime+= rideDurationInMinutes;
		double rideCost = this.getRegistrationCard().computeRideCost(rideDurationInMinutes, bicycleType, this);
		this.addCharge(rideCost);
		return rideCost;
	}

	/**
	 * Function rentBikeUser which allow a user to rent a bike at a date
	 * We check if the user doesn't have a bike yet and if the parking slot is occupied.
	 */
	public void rentBikeUser(ParkingSlot parkingSlot, LocalDateTime rentDateTime){
		//Check if the user has already a bike or not
		if (this.bike == null){
			//Check if the slot is occupied
			if (parkingSlot.getParkingSlotStatus() == ParkingSlotStatus.Occupied){

				//Give the bike to user
				this.bike = parkingSlot.getBike();
				// Set the bike occupied;
				this.bike.setFree(false);
				this.bike.setInStation(false, null);
				// Set the slot free
				parkingSlot.setParkingSlotStatus(ParkingSlotStatus.Free);
				//Set the bicycle of the slot null
				parkingSlot.setBike(null);
				this.rentDateTime = rentDateTime;
				//Print out the log

				System.out.println("User "+ this.id + " rented a bicyle");

			}else{
				System.out.println("The parkingslot doesn't have a bike");
			}

		}else{
			System.out.println("The user already has a bike");
		}
	}

	/**
	 * Function rentBikeOutOfStation which allow a user to rent a bike at a date which is not in a station.
	 * We check if the user doesn't have a bike yet and if the parking slot is
	 * occupied.
	 */
	public void rentBikeOutOfStation(Bicycle bike, LocalDateTime rentDateTime) {
		// Check if the user has already a bike or not
		if (this.bike == null) {
		
			//Give the bike to user
			this.bike = bike;
			this.rentDateTime = rentDateTime;
			bike.setFree(false);
			bike.setInStation(false, null);

			// Print out the log

			System.out.println("User " + this.id + " rented a bicyle out of the station");

			

		} else {
			System.out.println("The user already has a bike");
		}
	}

	/**
	 * return a bike
	 * We check if the user has a bike yet and if the parking slot is
	 * free.
	 *
	 * @param parkingSlot    the parking slot
	 * @param returnDateTime the return date time
	 */
	public void returnBike(ParkingSlot parkingSlot, LocalDateTime returnDateTime,DockingStation station) {
		if (this.bike == null) {
			System.out.println("The user doesn't have a bike");
		} else {
			//Check if the slot is free
			if (parkingSlot.getParkingSlotStatus() == ParkingSlotStatus.Free) {
				//Set the parkingslot as occupied
				parkingSlot.setParkingSlotStatus(ParkingSlotStatus.Occupied);
				//Give the parking slot the bike
				parkingSlot.setBike(bike);
				this.bike.setFree(true);
				this.bike.setInStation(true,station);

				double computeCost = computeCost(bike.getType(), this.rentDateTime, returnDateTime);
				System.out.println("Bicycle successfully parked.");
				// update user statistics
				// time credit and charges are updated when computing cost
				
				this.numberOfRides++;
				this.rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);

				// Reset user's data;
				this.bike = null;
				this.rentDateTime = null;
			} else {
				System.out.println("The parking slot is occupied");
			}

		}
	}

	/**
	 * return a bike out of a station
	 * We check if the user has a bike yet and if the parking slot is
	 * free.
	 *
	 * @param parkingSlot    the parking slot
	 * @param returnDateTime the return date time
	 */
	public void returnBikeOutOfStation(GPSPosition position, LocalDateTime returnDateTime) {
		if (this.bike == null) {
			System.out.println("The user doesn't have a bike");
		} else {
			// Check if the slot is free
			bike.setFree(true);
			bike.setInStation(false, null);
			bike.setPosition(position);

			MyVelibIndex.myVelibDatabase.setBikeOutOfStation(bike);



			double computeCost = computeCost(bike.getType(), this.rentDateTime, returnDateTime);
			System.out.println("Bicycle successfully parked out of the station.");
				// update user statistics
				// time credit and charges are updated when computing cost
			this.numberOfRides++;
				// this.+= this.rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);

			// Reset user's data;
			this.bike = null;
			this.rentDateTime = null;
		

		}
	}

	public double getTimeCredit() {
		return timeCredit;
	}

	public void setTimeCreditBalance(double timeCredit) {
		this.timeCredit = timeCredit;
	}
}
