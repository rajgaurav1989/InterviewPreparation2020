package com.raju.parking.bulider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.raju.enums.GATE_TYPE;
import com.raju.enums.SLOT_TYPE;
import com.raju.exception.ParkingLotException;
import com.raju.models.Floor;
import com.raju.models.Gate;
import com.raju.models.Location;
import com.raju.models.ParkingRow;
import com.raju.models.Slot;
import com.raju.parking.bulider.ParkingFloorBuilder;

public class ParkingFloorBuilderImpl implements ParkingFloorBuilder{

	@Override
	public Floor getFloor(int floorNumber,int numberOfRows, List<ParkingRow> parkingRows) throws ParkingLotException{
		if (numberOfRows < parkingRows.size()) {
			throw new ParkingLotException("Number of rows on floor "+floorNumber+" don't match actual number of rows ");
		}
		Floor floor = new Floor() ;
		floor.setFloorId(floorNumber);
		floor.setNumOfRows(numberOfRows);
		Set<SLOT_TYPE> supportedParkingTypes = parkingRows.stream().map(e -> e.getLaneType()).collect(Collectors.toSet());
		floor.setSupportedSlotType(supportedParkingTypes);
		floor.setParkingRowList(parkingRows);
		floor.setGateList(getFloorGates(floorNumber,parkingRows.size()));
		TreeMap<Integer,LinkedHashMap<String,Slot>> smallFreeSlotMap = new TreeMap<>() ;
		TreeMap<Integer,LinkedHashMap<String,Slot>> mediumFreeSlotMap = new TreeMap<>() ;
		TreeMap<Integer,LinkedHashMap<String,Slot>> largeFreeSlotMap = new TreeMap<>() ;
		
		populateFreeSlotMap(parkingRows, smallFreeSlotMap, mediumFreeSlotMap, largeFreeSlotMap);
		
		floor.setSmallFreeSlotMap(smallFreeSlotMap);
		floor.setMediumFreeSlotMap(mediumFreeSlotMap);
		floor.setLargeFreeSlotMap(largeFreeSlotMap);
		
		return floor;
	}

	private void populateFreeSlotMapHelper(List<Slot> parkingSlots , Map<Integer,LinkedHashMap<String,Slot>> inputMap,int rowIndex) {
		for (int slotIndex = 0 ; slotIndex < parkingSlots.size() ; slotIndex++) {
			Slot slot = parkingSlots.get(slotIndex);
			int distance = (rowIndex * rowIndex) + (slotIndex * slotIndex) ;
			if (!inputMap.containsKey(distance)) {
				LinkedHashMap<String,Slot> tempMap = new LinkedHashMap<>() ;
				inputMap.put(distance,tempMap);
			}
			Map<String,Slot> slotMap = inputMap.get(distance);
			slotMap.put(slot.getSlotId(),slot);
		}
	}
	
	private void populateFreeSlotMap(List<ParkingRow> parkingRows,Map<Integer,LinkedHashMap<String,Slot>> smallFreeSlotMap,
			Map<Integer,LinkedHashMap<String,Slot>> mediumFreeSlotMap,Map<Integer,LinkedHashMap<String,Slot>> largeFreeSlotMap){
		
		for (int rowIndex = 0 ; rowIndex < parkingRows.size() ; rowIndex++) {
			ParkingRow parkingRow = parkingRows.get(rowIndex) ;
			switch (parkingRow.getLaneType()) {
			case BIKE:
				populateFreeSlotMapHelper(parkingRow.getParkingSlots(),smallFreeSlotMap,rowIndex);
				break;
			case CAR :
				populateFreeSlotMapHelper(parkingRow.getParkingSlots(),mediumFreeSlotMap, rowIndex);
				break;
			default:
				populateFreeSlotMapHelper(parkingRow.getParkingSlots(),largeFreeSlotMap, rowIndex);
			}
		}
		
	}
	
	private List<Gate> getFloorGates(int floorNumber,int numRows){
		List<Gate> gateList = new ArrayList<>() ;
		gateList.add(getGate(floorNumber,0, numRows, GATE_TYPE.ENTRY));
		gateList.add(getGate(floorNumber,1, numRows, GATE_TYPE.EXIT));
		return gateList ;
	}
	
	private Gate getGate(int floorNumber,int id,int numRows,GATE_TYPE gateType) {
		Gate gate = new Gate();
		gate.setId(id+"_"+floorNumber);
		gate.setGateType(GATE_TYPE.ENTRY);
		gate.setGateLocation(new Location(0,numRows-1));
		gate.setFloorNumber(floorNumber);
		return gate;
	}
}
