package com.raju.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParkingLot {
	private int numOfFloors ;
	private List<Floor> floorList ;
	private boolean isFull = false ;
	private static ParkingLot singletonParkingLot ;
	private Map<String,Slot> vehicleSlotMap ;
	
	private ParkingLot() {
	}
	
	public static ParkingLot getInstance() {
		if (singletonParkingLot == null) {
			singletonParkingLot = new ParkingLot();
			singletonParkingLot.vehicleSlotMap = new HashMap<>();
		}
		return singletonParkingLot ;
	}
	
	public int getNumOfFloors() {
		return numOfFloors;
	}
	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}
	public List<Floor> getFloorList() {
		return floorList;
	}
	public void setFloorList(List<Floor> floorList) {
		this.floorList = floorList;
	}
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	public Map<String, Slot> getVehicleSlotMap() {
		return vehicleSlotMap;
	}


	@Override
	public String toString() {
		return "ParkingLot [numOfFloors=" + numOfFloors + ", floorList=" + floorList + ", isFull=" + isFull + "]";
	}
	
	
}
