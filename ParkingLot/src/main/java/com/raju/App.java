package com.raju;

import java.io.File;
import java.util.Scanner;

import com.raju.enums.COMMAND;
import com.raju.enums.SLOT_TYPE;
import com.raju.exception.ParkingLotException;
import com.raju.service.ParkingSevice;


public class App {
	public static void main(String[] args) throws RuntimeException, ParkingLotException {
		ParkingSevice parkingSevice = ParkingSevice.getInstance();
		parkingSevice.initializeParkingLot();
		executeCommands(parkingSevice);
	}
	
	private static void executeCommands(ParkingSevice parkingSevice) {
		try {
			Scanner scanner = new Scanner(new File("src/main/resources/parkingLot.txt"));
			while (scanner.hasNextLine()) {
				String line[] = scanner.nextLine().trim().split(" ") ;
				COMMAND command = COMMAND.valueOf(line[0].toUpperCase());
				try {
					switch (command) {
					case PARK:
						parkingSevice.parkVehicle(Integer.parseInt(line[3].trim()),
								SLOT_TYPE.valueOf(line[2].trim().toUpperCase()), line[1].trim());
						break;
					case UNPARK :
						parkingSevice.unpark(line[1].trim());
						break;
					default :
						System.out.println("Invalid Command");
					}
				}
				catch (Exception e) {
					System.out.println("Error "+e.getMessage()+" in commmand "+command.name());
				}
			}
			scanner.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
