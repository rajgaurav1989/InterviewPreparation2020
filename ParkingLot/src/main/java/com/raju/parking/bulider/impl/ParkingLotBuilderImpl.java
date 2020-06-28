package com.raju.parking.bulider.impl;

import java.util.List;

import com.raju.exception.ParkingLotException;
import com.raju.models.Floor;
import com.raju.models.ParkingLot;
import com.raju.parking.bulider.ParkingLotBuilder;

public class ParkingLotBuilderImpl implements ParkingLotBuilder{

	@Override
	public ParkingLot getParkingLot(int numOfFloors, List<Floor> floors) throws ParkingLotException {
		if (numOfFloors  != floors.size()) {
			throw new ParkingLotException("Number of floors "+numOfFloors+" don't match actual number of floors ");
		}
		
		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.setNumOfFloors(numOfFloors);
		parkingLot.setFloorList(floors);
		
		return parkingLot;
	}

}
