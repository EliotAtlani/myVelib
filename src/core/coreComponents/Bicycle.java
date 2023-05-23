package core.coreComponents;

import core.types.TypeOfBicycle;

public class Bicycle {
	
	protected Integer Id;
	
	protected TypeOfBicycle type;
	

	private static Integer uniqueId = 0;
	 //toString
    @Override
    public String toString() {
        return "Bicycle :" + "\n" +
                "Id : " + Id + "\n" +
                "Type : " + this.type.name();
    }

	public Bicycle(TypeOfBicycle type) {
	        Id = uniqueId;
	        uniqueId++;
	        this.type = type;
	    }
	
	//Setters
	
	public void setType(TypeOfBicycle type) {
		this.type=type;
	}
	
	public void setId(Integer Id) {
		this.Id = Id;
	}
	
	// Getters
	
	public TypeOfBicycle getType() {
        return type;
    }
	
	 public Integer getId() {
	        return Id;
	    }
}
