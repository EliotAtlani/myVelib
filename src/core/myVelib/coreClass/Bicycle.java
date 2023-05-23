package coreClass;

import Enums.*;

public class Bicycle {
    private static Integer uniqueId = 0;
    protected Integer id;
    private BicycleType type;

    public Bicycle(BicycleType type) {
        this.id = uniqueId;
        getNextUniqueId();
        this.type = type;
    }

    private static synchronized Integer getNextUniqueId() {
        return uniqueId++;
    }
    
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id) {
    	this.id=id;
    }

	public BicycleType getType() {
		return type;
	}

	public void setType(BicycleType type) {
		this.type = type;
	}
	
	
	//toString
    @Override
    public String toString() {
        return "Bicycle d'id : " + id + " and Type " + this.type.name();
    }
}


