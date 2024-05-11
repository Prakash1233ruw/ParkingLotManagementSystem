package com.pp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
public static void main(String[] args) {
 
	
int nFloors=4;
int nSlotsPerFloor=6; 
ParkingLotService parkingLot = new ParkingLotService("12347L", nFloors, nSlotsPerFloor);


LOGGER.info(parkingLot.getNoOfOpenSlots("car"));
String ticket1 = parkingLot.parkVehicle("car", "MH-03", "red");
String ticket2 = parkingLot.parkVehicle("car", "MH-04", "purple");
LOGGER.info(ticket2);


LOGGER.info(parkingLot.displayOccupiedSlots("car"));

LOGGER.info(parkingLot.unparkVehicle(ticket2));

LOGGER.info(parkingLot.displayOccupiedSlots("car"));

LOGGER.info(parkingLot.displayOpenSlots("truck"));

LOGGER.info(parkingLot.parkVehicle("truck", "MH-01", "black"));

LOGGER.info(parkingLot.displayOccupiedSlots("truck"));

LOGGER.info(parkingLot.displayOccupiedSlots("car"));
}
}
