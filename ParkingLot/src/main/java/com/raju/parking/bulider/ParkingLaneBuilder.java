package com.raju.parking.bulider;

import com.raju.enums.SLOT_TYPE;
import com.raju.models.ParkingRow;

public interface ParkingLaneBuilder {

	public ParkingRow getParkingRow(SLOT_TYPE laneCategory,int floorNumber,int laneNumber,int numberOfSlots);
	
}
