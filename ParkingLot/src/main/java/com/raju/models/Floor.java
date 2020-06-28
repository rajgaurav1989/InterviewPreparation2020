package com.raju.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.raju.enums.SLOT_TYPE;

public class Floor {

	private int floorId ;
	private int numOfRows;
	private List<ParkingRow> parkingRowList ;
	private List<Gate> gateList ;
	private boolean isFull ;
	private Set<SLOT_TYPE> supportedSlotType;
	private TreeMap<Integer,LinkedHashMap<String,Slot>> smallFreeSlotMap ;
	private TreeMap<Integer,LinkedHashMap<String,Slot>> mediumFreeSlotMap ;
	private TreeMap<Integer, LinkedHashMap<String,Slot>> largeFreeSlotMap ;
	
	public int getFloorId() {
		return floorId;
	}
	
	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}
	
	public List<ParkingRow> getParkingRowList() {
		return parkingRowList;
	}
	
	public void setParkingRowList(List<ParkingRow> parkingRowList) {
		this.parkingRowList = parkingRowList;
	}
	
	public List<Gate> getGateList() {
		return gateList;
	}

	public void setGateList(List<Gate> gateList) {
		this.gateList = gateList;
	}

	public boolean isFull() {
		return isFull;
	}
	
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	
	public Set<SLOT_TYPE> getSupportedSlotType() {
		return supportedSlotType;
	}

	public void setSupportedSlotType(Set<SLOT_TYPE> supportedSlotType) {
		this.supportedSlotType = supportedSlotType;
	}


	public TreeMap<Integer, LinkedHashMap<String, Slot>> getSmallFreeSlotMap() {
		return smallFreeSlotMap;
	}

	public void setSmallFreeSlotMap(TreeMap<Integer, LinkedHashMap<String, Slot>> smallFreeSlotMap) {
		this.smallFreeSlotMap = smallFreeSlotMap;
	}

	public TreeMap<Integer, LinkedHashMap<String, Slot>> getMediumFreeSlotMap() {
		return mediumFreeSlotMap;
	}

	public void setMediumFreeSlotMap(TreeMap<Integer, LinkedHashMap<String, Slot>> mediumFreeSlotMap) {
		this.mediumFreeSlotMap = mediumFreeSlotMap;
	}

	public TreeMap<Integer, LinkedHashMap<String, Slot>> getLargeFreeSlotMap() {
		return largeFreeSlotMap;
	}

	public void setLargeFreeSlotMap(TreeMap<Integer, LinkedHashMap<String, Slot>> largeFreeSlotMap) {
		this.largeFreeSlotMap = largeFreeSlotMap;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	@Override
	public String toString() {
		return "Floor [floorId=" + floorId + ", numOfRows=" + numOfRows + ", parkingRowList=" + parkingRowList
				+ ", gateList=" + gateList + ", isFull=" + isFull + ", supportedSlotType=" + supportedSlotType
				+ ", smallFreeSlotMap=" + smallFreeSlotMap + ", mediumFreeSlotMap=" + mediumFreeSlotMap
				+ ", largeFreeSlotMap=" + largeFreeSlotMap + "]";
	}

	
	
	
}
