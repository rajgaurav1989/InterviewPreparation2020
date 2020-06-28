package com.raju.parking.bulider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.RowFilter;

import com.raju.enums.SLOT_TYPE;
import com.raju.exception.ParkingLotException;
import com.raju.models.Floor;
import com.raju.models.ParkingRow;
import com.raju.parking.bulider.ParkingFloorBuilder;
import com.raju.parking.bulider.ParkingLaneBuilder;
import com.raju.parking.bulider.ParkingLotBuilder;

public class ParkingLotDirector {
	private ParkingLotBuilder parkingLotBuilder ;
	private ParkingFloorBuilder parkingFloorBuilder ;
	private ParkingLaneBuilder parkingLaneBuilder ;
	private FloorInitializer floorInitializer ;
	private int numberOfFloors = 5 ;
	
	public ParkingLotDirector(ParkingLotBuilder parkingLotBuilder,ParkingFloorBuilder parkingFloorBuilder ,ParkingLaneBuilder parkingLaneBuilder) {
		this.parkingLotBuilder = parkingLotBuilder ;
		this.parkingFloorBuilder = parkingFloorBuilder ;
		this.parkingLaneBuilder = parkingLaneBuilder ;
	}

	public void setParkingLotParams(int numberOfFloors,FloorInitializer floorInitializer) {
		this.numberOfFloors = numberOfFloors ;
		this.floorInitializer = floorInitializer;
	}
	
	public void construct() throws RuntimeException, ParkingLotException {
		List<Floor> floorList = new ArrayList<>() ;
		floorInitializer.floorRowSlotTypeMap.forEach((floorNum,floorRowTypeMap) -> {
			Set<SLOT_TYPE> supportedVehicleType = new HashSet<>();
			List<ParkingRow> parkingLanes = new ArrayList<>();
			floorRowTypeMap.forEach((rowNum,parkingRowInitializer) -> {
				supportedVehicleType.add(parkingRowInitializer.slotType);
				ParkingRow parkingRow = parkingLaneBuilder.getParkingRow(parkingRowInitializer.slotType,floorNum, rowNum, parkingRowInitializer.size);
				parkingLanes.add(parkingRow);
			});
			try {
				Floor floor = parkingFloorBuilder.getFloor(floorNum, floorInitializer.floorCapacity,parkingLanes);
				floorList.add(floor);
			} catch (ParkingLotException e) {
				throw new RuntimeException("Error in floor creation");
			}
			
		});
		parkingLotBuilder.getParkingLot(numberOfFloors, floorList);
	}
	
	public class ParkingRowInitializer{
		private SLOT_TYPE slotType ;
		private int size ;
		
		public ParkingRowInitializer(SLOT_TYPE slotType,int size) {
			this.slotType = slotType ;
			this.size = size ;
		}
	}
	
	public class FloorInitializer {
		private Map<Integer,TreeMap<Integer,ParkingRowInitializer>> floorRowSlotTypeMap ;
		private int floorCapacity ;
		
		public FloorInitializer(Map<Integer,TreeMap<Integer,ParkingRowInitializer>> floorRowSlotTypeMap , int floorCapacity) {
			this.floorRowSlotTypeMap = floorRowSlotTypeMap ;
			this.floorCapacity = floorCapacity ;
		}
		
	}
	
	
}
