package com.raju.parking.bulider.impl;

import java.util.ArrayList;
import java.util.List;

import com.raju.enums.SLOT_TYPE;
import com.raju.models.Location;
import com.raju.models.ParkingRow;
import com.raju.models.Slot;
import com.raju.parking.bulider.ParkingLaneBuilder;

public class ParkingLaneBuilderImpl implements ParkingLaneBuilder{

	@Override
	public ParkingRow getParkingRow(SLOT_TYPE laneCategory,int floorNumber, int laneNumber, int numberOfSlots) {
		ParkingRow parkingRow = new ParkingRow();
		parkingRow.setCapacity(numberOfSlots);
		parkingRow.setLaneId(floorNumber+"_"+laneNumber);
		parkingRow.setFloorNumber(floorNumber);
		parkingRow.setLaneType(laneCategory);
		parkingRow.setParkingSlots(createSlots(floorNumber,numberOfSlots, laneNumber, laneCategory));
		return parkingRow;
	}
	
	private List<Slot> createSlots(int floorNumber,int numberOfSlots,int laneNumber,SLOT_TYPE slotType) {
		List<Slot> slotList = new ArrayList<>();
		for (int slotCounter = 0 ; slotCounter < numberOfSlots ; slotCounter++) {
			Slot newSlot = new Slot();
			newSlot.setSlotId(floorNumber+"_"+laneNumber+"_"+slotCounter);
			newSlot.setFree(true);
			newSlot.setSlotLocation(new Location(laneNumber, slotCounter));
			newSlot.setFloorNumber(floorNumber);
			newSlot.setSlotType(slotType);
			slotList.add(newSlot);
		}
		return slotList ;
	}

}
