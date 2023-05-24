package coreClass;

import Enums.*;
import Cards.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class User {
	private String name;
	private int id;
	private GPSPosition position;
	private String creditCardNumber;
	private RegistrationCard registrationCard;
	private double totalCharges;
	private int numberOfRides;
	private int timeSpent;
	private Bicycle bike;
	private LocalDateTime rentDateTime;
	private int rentTotalTime;
	private static ArrayList<Integer> takenIds = new ArrayList<>();
	double rideDurationInMinutes;
	BicycleType bicycleType;

	private static int getValidId() {
		int tempId = 0;
		while (takenIds.contains(tempId)) {
			tempId++;
		}
		takenIds.add(tempId);
		return tempId;
	}

	public User(String name, int id, GPSPosition position, String creditCardNumber, RegistrationCard registrationCard) {
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

	public User(String name, int id, GPSPosition position, String creditCardNumber) {
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
		int rideDuration = (int) ChronoUnit.MINUTES.between(rentDateTime, returnDateTime);

		double rideCost = this.getRegistrationCard().computeRideCost(rideDurationInMinutes, bicycleType, this);
		this.addCharge(rideCost);
		return rideCost;
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

}
