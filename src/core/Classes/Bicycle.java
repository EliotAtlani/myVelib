package Classes;

import Enums.*;

public class Bicycle {
    private static Integer uniqueId = 0;
    protected Integer id;
    private BicycleType type;
    protected boolean inStation;
    protected GPSPosition position;
    protected boolean isFree;

    public Bicycle(BicycleType type,GPSPosition position) {
        this.id = uniqueId;
        getNextUniqueId();
        this.type = type;
        this.position=position;
        this.inStation=false;
        this.isFree=true;
    }

    private static synchronized Integer getNextUniqueId() {
        return uniqueId++;
    }
    
    public Integer getId() {
    	return id;
    }
    
    public GPSPosition getPosition() {
		return position;
	}

	public void setPosition(GPSPosition position) {
		this.position = position;
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
	
	public void setInStation(DockingStation station) {
		this.inStation= true;
		this.position=station.getPosition();
		this.isFree=true;
	}
	
	public boolean isInStation() {
		return inStation;
	}
	
	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	//toString
    @Override
    public String toString() {
        return "Bicycle d'id : " + id + " and Type " + this.type.name();
    }
}


