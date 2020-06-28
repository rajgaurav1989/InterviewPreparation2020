package com.raju.models;

import com.raju.enums.GATE_TYPE;

public class Gate {
	private String id ;
	private int floorNumber ;
	private Location gateLocation ;
	private GATE_TYPE gateType ;

	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Location getGateLocation() {
		return gateLocation;
	}
	public void setGateLocation(Location gateLocation) {
		this.gateLocation = gateLocation;
	}
	
	public GATE_TYPE getGateType() {
		return gateType;
	}
	public void setGateType(GATE_TYPE gateType) {
		this.gateType = gateType;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	@Override
	public String toString() {
		return "Gate [id=" + id + ", floorNumber=" + floorNumber + ", gateLocation=" + gateLocation + ", gateType="
				+ gateType + "]";
	}
	
	
	
	
}
