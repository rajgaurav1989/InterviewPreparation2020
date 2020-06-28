package com.raju.models;

import java.util.Date;

import com.raju.enums.SLOT_TYPE;


public class Slot {
	private String slotId;
	private int floorNumber ;
	private boolean isFree = true ;
	private Date startTime ;
	private Location slotLocation ;
	private String vehicleRegNumber ;
	private SLOT_TYPE slotType ;
	
	
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Location getSlotLocation() {
		return slotLocation;
	}
	public void setSlotLocation(Location slotLocation) {
		this.slotLocation = slotLocation;
	}
	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}
	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}
	
	public SLOT_TYPE getSlotType() {
		return slotType;
	}
	public void setSlotType(SLOT_TYPE slotType) {
		this.slotType = slotType;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", floorNumber=" + floorNumber + ", isFree=" + isFree + ", startTime="
				+ startTime + ", slotLocation=" + slotLocation + ", vehicleRegNumber=" + vehicleRegNumber
				+ ", slotType=" + slotType + "]";
	}
	
	
	
	
}
