package com.raju.parking.bulider;

import java.util.List;

import com.raju.exception.ParkingLotException;
import com.raju.models.Floor;
import com.raju.models.ParkingRow;

public interface ParkingFloorBuilder {

	public Floor getFloor(int floorNumber,int numberOfRows,List<ParkingRow> parkingRows)  throws ParkingLotException;
	
}
