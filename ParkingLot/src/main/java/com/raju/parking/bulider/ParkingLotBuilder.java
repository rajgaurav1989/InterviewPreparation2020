package com.raju.parking.bulider;

import java.util.List;

import com.raju.exception.ParkingLotException;
import com.raju.models.Floor;
import com.raju.models.ParkingLot;

public interface ParkingLotBuilder {

	public ParkingLot getParkingLot(int numOfFloors, List<Floor> floors)  throws ParkingLotException;
	
}
