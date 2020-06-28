package com.raju.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


import com.raju.enums.SLOT_TYPE;
import com.raju.exception.ParkingLotException;
import com.raju.factory.PriceStrategyFactory;
import com.raju.models.Floor;
import com.raju.models.Location;
import com.raju.models.ParkingLot;
import com.raju.models.Slot;
import com.raju.parking.bulider.ParkingFloorBuilder;
import com.raju.parking.bulider.ParkingLaneBuilder;
import com.raju.parking.bulider.ParkingLotBuilder;
import com.raju.parking.bulider.impl.ParkingFloorBuilderImpl;
import com.raju.parking.bulider.impl.ParkingLaneBuilderImpl;
import com.raju.parking.bulider.impl.ParkingLotBuilderImpl;
import com.raju.parking.bulider.impl.ParkingLotDirector;
import com.raju.strategy.PricingStrategy;

public class ParkingSevice {

	private ParkingLot parkingLot;
	private static ParkingSevice singletonParkingService;
	private PricingStrategy pricingStrategy ;

	private ParkingSevice() {
	}

	public static ParkingSevice getInstance() {
		if (singletonParkingService == null) {
			singletonParkingService = new ParkingSevice();
		}
		return singletonParkingService;
	}

	public void initializeParkingLot() throws RuntimeException, ParkingLotException {
		try {

			ParkingLotBuilder parkingLotBuilder = new ParkingLotBuilderImpl();
			ParkingFloorBuilder parkingFloorBuilder = new ParkingFloorBuilderImpl();
			ParkingLaneBuilder parkingLaneBuilder = new ParkingLaneBuilderImpl();

			ParkingLotDirector parkingLotDirector = new ParkingLotDirector(parkingLotBuilder, parkingFloorBuilder,
					parkingLaneBuilder);
			
			Scanner scanner = new Scanner(new File("src/main/resources/parkingLotBuild.txt"));
			int numFloor = Integer.parseInt(scanner.nextLine().trim());
			Map<Integer, TreeMap<Integer, ParkingLotDirector.ParkingRowInitializer>> floorMap = new TreeMap<>();
			for (int floorIndex = 0; floorIndex < numFloor; floorIndex++) {
				int numRows = Integer.parseInt(scanner.nextLine().trim());
				TreeMap<Integer, ParkingLotDirector.ParkingRowInitializer> parkingRowMap = new TreeMap<>();
				for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
					String rowInfo[] = scanner.nextLine().trim().split(" ");
					SLOT_TYPE slotType = SLOT_TYPE.valueOf(rowInfo[0].trim());
					int rowCapacity = Integer.parseInt(rowInfo[1].trim());
					ParkingLotDirector.ParkingRowInitializer tempRowObj = parkingLotDirector.new ParkingRowInitializer(
							slotType, rowCapacity);
					parkingRowMap.put(rowIndex, tempRowObj);
				}
				floorMap.put(new Integer(floorIndex), parkingRowMap);
			}
			ParkingLotDirector.FloorInitializer floorInitializer = parkingLotDirector.new FloorInitializer(floorMap,
					numFloor);
			parkingLotDirector.setParkingLotParams(numFloor, floorInitializer);

			parkingLotDirector.construct();

			this.parkingLot = ParkingLot.getInstance();
			Map<SLOT_TYPE,Integer> slotPricingMap = getHardCodedPricingMap();
			this.pricingStrategy = PriceStrategyFactory.getPricingStrategy("FLAT", slotPricingMap);
			
			printVerify();
			
			System.out.println("\n\n\nParking lot initialization done\n\n\n");
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Slot parkVehicle(int floorNumber, SLOT_TYPE slotType,String vehicleRegNum) {
		List<Floor> floorList = parkingLot.getFloorList();
		List<SLOT_TYPE> applicableSlots = getApplicableSlotTypes(slotType);
		List<Integer> searchIndexList = Arrays.asList(floorNumber);
		int counter = 0;
		for (SLOT_TYPE type : applicableSlots) {
			while (true) {
				Slot slot = getSlotFromSearchIndexList(searchIndexList, floorList, type, vehicleRegNum);
				if (slot != null) {
					parkingLot.getVehicleSlotMap().put(vehicleRegNum, slot);
					System.out.println(vehicleRegNum+"\t"+ slotType + " is parked at "+ slot.getSlotId()+ "\t" +slot.getSlotType());
					return slot ;
				}
				counter++;
				searchIndexList = new ArrayList<>();
				listIntAdder(searchIndexList, floorNumber - counter);
				listIntAdder(searchIndexList, floorNumber + counter);
				if (searchIndexList.isEmpty()) {
					break;
				}
			}
		}

		return null;
	}
	
	public void unpark(String vehicleRegNum) throws ParkingLotException {
		Map<String,Slot> vehicleSlotMap = parkingLot.getVehicleSlotMap() ;
		if (!vehicleSlotMap.containsKey(vehicleRegNum)) {
			throw new ParkingLotException("Vehicle "+vehicleRegNum+" is not parked in this parking lot");
		}
		Slot slot = vehicleSlotMap.get(vehicleRegNum);
		vehicleSlotMap.remove(vehicleRegNum);
		int floorNum = slot.getFloorNumber() ;
		Floor floor = parkingLot.getFloorList().get(floorNum);
		TreeMap<Integer, LinkedHashMap<String, Slot>> distSlotMap = getAptSlotMap(floor, slot.getSlotType());
		int gateDistance = getDistance(slot.getSlotLocation());
		if (distSlotMap.containsKey(gateDistance)) {
			distSlotMap.get(gateDistance).put(slot.getSlotId(),slot);
		}
		else {
			LinkedHashMap<String, Slot> intmMap = new LinkedHashMap<>();
			intmMap.put(slot.getSlotId(), slot);
			distSlotMap.put(gateDistance,intmMap);
		}
		
		System.out.println("Vehicle "+vehicleRegNum+" is unparked successfully and tariff is "+
		pricingStrategy.getPrice(slot.getSlotType(),slot.getStartTime(), new Date()));
		return ;
	}
	
	private Map<SLOT_TYPE,Integer> getHardCodedPricingMap() {
		Map<SLOT_TYPE,Integer> slotPricingMap = new HashMap<>();
		
		slotPricingMap.put(SLOT_TYPE.CAR, 40) ;
		slotPricingMap.put(SLOT_TYPE.BIKE,10) ;
		slotPricingMap.put(SLOT_TYPE.BUS, 70) ;
		
		return slotPricingMap ;
	}
	
	private int getDistance(Location location) {
		int xdis = location.getRowNumber() ;
		int ydis = location.getColumnNumber() ;
		return ((xdis * xdis) + (ydis *ydis));
	}

	private Slot getSlotFromSearchIndexList(List<Integer> searchIndexList,List<Floor>floorList,SLOT_TYPE type,String vehicleRegNum) {
		for (int searchIndex = 0; searchIndex < searchIndexList.size(); searchIndex++) {
			Floor floor = floorList.get(searchIndex);
			if (!floor.getSupportedSlotType().contains(type)) {
				continue ;
			}
			TreeMap<Integer, LinkedHashMap<String, Slot>> slotMap = getAptSlotMap(floor, type);
			if (slotMap.size() > 0) {
				int key = slotMap.firstKey();
				LinkedHashMap<String, Slot> intmSlotMap = slotMap.firstEntry().getValue();
				Slot slot = intmSlotMap.entrySet().stream().findFirst().get().getValue();
				slot.setStartTime(new Date());
				slot.setVehicleRegNumber(vehicleRegNum);
				slot.setFree(false);
				if (intmSlotMap.size() == 1) {
					intmSlotMap = null ;
					slotMap.remove(key);
				}
				else {
					intmSlotMap.remove(slot.getSlotId());
				}
				return slot;
			}
		}
		return null ;
	}
	
	private void listIntAdder(List<Integer> inputList, int item) {
		if (item < inputList.size() && item >= 0) {
			inputList.add(item);
		}
	}

	private TreeMap<Integer, LinkedHashMap<String, Slot>> getAptSlotMap(Floor floor, SLOT_TYPE type) {
		switch (type) {
		case BIKE:
			return floor.getSmallFreeSlotMap();
		case CAR:
			return floor.getMediumFreeSlotMap();
		default:
			return floor.getLargeFreeSlotMap();
		}
	}
	
	private void printVerify() {
		System.out.println(ParkingLot.getInstance());

		System.out.println("\n\n\n");

		ParkingLot parkingLot = ParkingLot.getInstance();

		List<Floor> floorList = parkingLot.getFloorList();

		for (int index = 0; index < floorList.size(); index++) {
			Floor floor = floorList.get(index);
			System.out.println("\n\nPrinting Start floor " + floor.getFloorId() + "\n\n");
			printMap(floor.getSmallFreeSlotMap(), SLOT_TYPE.BIKE);
			printMap(floor.getMediumFreeSlotMap(), SLOT_TYPE.CAR);
			printMap(floor.getLargeFreeSlotMap(), SLOT_TYPE.BUS);
			System.out.println("\n\nPrinting End floor " + floor.getFloorId() + "\n\n");
		}

		System.out.println("\n\n\n");

	}
	
	private void printMap(Map<Integer, LinkedHashMap<String, Slot>> inputMap, SLOT_TYPE type) {
		System.out.println("\nPrinting " + type.name() + " Size Map Start");

		inputMap.forEach((key, value) -> {
			System.out.println("key " + key);
			System.out.println("\tprinting internal map start");

			value.forEach((k, v) -> {
				System.out.println("key " + key + " value " + v.getSlotId());
			});

			System.out.println("\tprinting internal map end");
		});

		System.out.println("\nPrinting " + type.name() + " Size Map end");

	}

	private List<SLOT_TYPE> getApplicableSlotTypes(SLOT_TYPE type) {
		List<SLOT_TYPE> slotTypeList = new ArrayList<>();
		slotTypeList.add(type);
		switch (type) {
		case BIKE:
			slotTypeList.add(SLOT_TYPE.CAR);
			slotTypeList.add(SLOT_TYPE.BUS);
			return slotTypeList;
		case CAR:
			slotTypeList.add(SLOT_TYPE.BUS);
			return slotTypeList;
		default:
			return slotTypeList;
		}
	}

}
