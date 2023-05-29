package core.Classes;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import core.Cards.NoRegistrationCard;
import core.Cards.RegistrationCard;
import core.Enums.BicycleType;
import core.Enums.ParkingSlotStatus;
import front.MyVelibIndex;

/**
 * Create a User in a VelibNetwork
 */
public class User {
	protected String name;
	protected int id;
	protected GPSPosition position;
	protected String creditCardNumber;
	protected RegistrationCard registrationCard;
	protected double totalCharges;
	protected int numberOfRides;
	protected int timeSpent;
	protected double timeCredit;
	protected Bicycle bike;/* The bike of the user */
	protected LocalDateTime rentDateTime;
	protected int rentTotalTime;
	protected static HashMap<String, Integer> lastIdsByNetwork = new HashMap<>();
	double rideDurationInMinutes;
	BicycleType bicycleType;
	public static ArrayList<User> userList = new ArrayList<>();


	
	/**
	 * @param network
	 * @return
	 */
	private int generateUniqueId(String network) {
		int lastId = lastIdsByNetwork.getOrDefault(network, 0);
		int newId = lastId + 1;
		lastIdsByNetwork.put(network, newId);
		return newId;
	}

	@Override
	public String toString() {
		return "User id is : " + id + "\n User name is : "+ name;

	}
	/**
	 * @param name
	 * @param position
	 * @param creditCardNumber
	 * @param registrationCard
	 * @param network
	 */
	public User(String name, GPSPosition position, String creditCardNumber, RegistrationCard registrationCard,String network) {
		this.name = name;
		this.id = generateUniqueId(network);
		this.position = position;
		this.creditCardNumber = creditCardNumber;
		this.registrationCard = registrationCard;
		this.totalCharges = 0;
		this.numberOfRides = 0;
		this.timeSpent = 0;
		this.bike = null;
		this.rentTotalTime = 0;
		
	}

	/**
	 * @param name
	 * @param position
	 * @param creditCardNumber
	 * @param network
	 */
	public User(String name, GPSPosition position, String creditCardNumber,String network) {
		this.name = name;
		this.id = generateUniqueId(network);
		this.position = position;
		this.creditCardNumber = creditCardNumber;
		this.registrationCard = new NoRegistrationCard();
		this.totalCharges = 0;
		this.numberOfRides = 0;
		this.timeSpent = 0;
		this.bike = null;
		this.rentTotalTime = 0;
	}


	/**
	 * @param charge
	 */
	public void addCharge(double charge) {
		this.setTotalCharges(this.getTotalCharges() + charge);
	}


	/**
	 * @return
	 */
	public boolean hasABike() {
		return this.bike != null;
	}

	//Getters and setters;
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
	 * Give the cost of a ride
	 *
	 * @param bicycleType    the type of the bicycle rented
	 * @param rentDateTime   the rent datetime
	 * @param returnDateTime the return datetime
	 * @return the ride cost
	 */
	public double giveCost(BicycleType bicycleType, LocalDateTime rentDateTime, LocalDateTime returnDateTime,Bicycle bike) {
		int rideDurationInMinutes = (int) rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);
		this.rentTotalTime+= rideDurationInMinutes;
		double rideCost = this.getRegistrationCard().giveRideCost(rideDurationInMinutes, bicycleType);
		

		//Check for bonus discount
		if (bike.IsFromStreet()){
			rideCost = 0.9*rideCost;
			bike.setIsFromStreet(false);
		}

		this.addCharge(rideCost);

		return rideCost;
	}
/**
	 * Give the cost of a ride for a malus.
	 *
	 * @param bicycleType    the type of the bicycle rented
	 * @param rentDateTime   the rent datetime
	 * @param returnDateTime the return datetime
	 * @return the ride cost
	 */
	public double giveCostMalus(BicycleType bicycleType, LocalDateTime rentDateTime, LocalDateTime returnDateTime) {
		int rideDurationInMinutes = (int) rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);
		this.rentTotalTime+= rideDurationInMinutes;
		double rideCost = this.getRegistrationCard().giveRideCost(rideDurationInMinutes, bicycleType);
		
		//Malus
		
		rideCost = 1.1*rideCost;

		this.addCharge(rideCost);

		return rideCost;
	}



	/**
	 * @param parkingSlot
	 * @param rentDateTime
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
			

				System.out.println("User "+ this.id + " rented a bicyle");

			}else{
				System.out.println("The parkingslot doesn't have a bike");
			}

		}else{
			System.out.println("The user already has a bike");
		}
	}


	/**
	 * @param bike
	 * @param rentDateTime
	 */
	public void rentBikeOutOfStation(Bicycle bike, LocalDateTime rentDateTime) {
		// Check if the user has already a bike or not
		if (this.bike == null) {
		
			//Give the bike to user
			this.bike = bike;
			this.rentDateTime = rentDateTime;
			bike.setFree(false);
			bike.setInStation(false, null);
			bike.setIsFromStreet(true);


	

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

				giveCost(bike.getType(), this.rentDateTime, returnDateTime,bike);
				System.out.println("Bicycle successfully parked.");
				
				
				this.numberOfRides++;
				this.rentDateTime.until(returnDateTime, ChronoUnit.MINUTES);

				// Reset the data of the user
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
			giveCostMalus(bike.getType(), this.rentDateTime, returnDateTime);
			System.out.println("Bicycle successfully parked out of the station.");
			this.numberOfRides++;

			//Reset the data of the user
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
