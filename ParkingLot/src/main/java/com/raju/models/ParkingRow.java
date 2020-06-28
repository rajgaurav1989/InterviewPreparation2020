package com.raju.models;

import java.util.List;

import com.raju.enums.SLOT_TYPE;

public class ParkingRow {
 	private String laneId ;
 	private int floorNumber;
	private SLOT_TYPE laneType ;
	private int capacity ;
	private List<Slot> parkingSlots ;
	
	
	public String getLaneId() {
		return laneId;
	}
	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
	public SLOT_TYPE getLaneType() {
		return laneType;
	}
	public void setLaneType(SLOT_TYPE laneType) {
		this.laneType = laneType;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<Slot> getParkingSlots() {
		return parkingSlots;
	}
	public void setParkingSlots(List<Slot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	@Override
	public String toString() {
		return "ParkingRow [laneId=" + laneId + ", floorNumber=" + floorNumber + ", laneType=" + laneType
				+ ", capacity=" + capacity + ", parkingSlots=" + parkingSlots + "]";
	}
	
	
	
}
