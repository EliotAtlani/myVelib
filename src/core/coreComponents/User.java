package core.coreComponents;
import java.util.ArrayList;
public class User {
	
	private String name;
	
	private Integer id;
	
	private double longitude;
	private double latitude;
	
	private String creditCardNumber;
	
	private String registrationCard;
	private double timeCreditBalance;
	
	private double totalCharges;
	
	  private static ArrayList<Integer> usedIds = new ArrayList<>(); 
	  
	private static int getValidId(){
        int tempId=0;
        // find the first id that is not used
        while (usedIds.contains(tempId)) {
            tempId ++;
        }
        // add this id to the list of used ones
        usedIds.add(tempId);
        return tempId;
    }
	 @Override
	    public String toString() {
	        String baseString = "User " + name + " [id:" + id + "]\n" +
	                "Location : (latitude : " + latitude + "\u00B0, longitude : " + longitude + "\u00B0) \n" +
	                "Credit card : " + creditCardNumber + ", total charges : " + totalCharges + "\n" + "rented " + "\n";
	        // if the user has no registration card
	       
	            return baseString + "No registration card.";
	      

	    }
	/**
     * Instantiates a new User with a registration card
     *
     * @param name             the name (first name and last name or username) of the user
     * @param latitude         the latitude coordinate of the user's location
     * @param longitude        the longitude coordinate of the user's location
     * @param creditCardNumber the credit card number of the user
     * @param registrationCard the registration that the user has
     * @throws Exception the exception
     */
    public User(String name, double latitude, double longitude, String creditCardNumber,String registrationCard) {
        this.name = name;
        // check the values of latitude and longitude
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        // check if the card number is correct
        this.setCreditCardNumber(creditCardNumber);
        this.registrationCard = registrationCard;
        this.timeCreditBalance = 0;
        this.totalCharges = 0;
    }
    
  //Getters
    
    public String getName() {
    	return name;
    }
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    
    public double getTotalCharges()
    {
    	return totalCharges;
    }    
    //Setters
    
    public void setName(String name) {
    	this.name=name;
    }
    
    public void setId(int id) throws Exception {
        // if this id isn't used
        if (!usedIds.contains(id)) {
            // mark the new id as used
            usedIds.add(id);
            // remove the old id from the list
            usedIds.remove(this.id);
            this.id = id;
        } else {
           // throw new IdAlreadyTakenException(id);
        }
    }
    
    public void setLatitude(double latitude) {
    	this.latitude=latitude;
    	
    }
    
    public void setLongitude(double longitude) {
    	this.longitude=longitude;
    	
    }
    
    public void setCreditCardNumber(String creditCardNumber) {
    	this.creditCardNumber=creditCardNumber;
    	
    }
    
    public void addCharges(double charges) {
    	this.totalCharges = this.getTotalCharges()+charges;
    	}
    
    
    
  
}
