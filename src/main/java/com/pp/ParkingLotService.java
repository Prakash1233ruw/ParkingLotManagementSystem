package com.pp;

import java.util.ArrayList;
import java.util.List;




public class ParkingLotService {

	
    private String parkingLotID;
    private List<List<Slot>> slots;

    @Override
	public String toString() {
    	
    	for(List<Slot> s:slots) {
    		for(Slot p:s) {
    			return "slot "+ p;
    		}
    	}
		return "ParkingLotService [parkingLotID=" + parkingLotID + ", slots=" + slots + "]";
	}

	public ParkingLotService(String parkingLotId, Integer nfloors, Integer noOfSlotsPerFlr) {
        this.parkingLotID = parkingLotId;
        slots = new ArrayList<>();
        for (int i = 0; i < nfloors; i++) {
            slots.add(new ArrayList<>());
            List<Slot> floorSlots = slots.get(i);
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));

            for (int j = 3; j < noOfSlotsPerFlr; j++) {
                slots.get(i).add(new Slot("car"));
            }
        }
    }

    public ParkingLotService() {

    }

    public String parkVehicle(String type, String regNo, String color) {
        Vehicle vehicle = new Vehicle(type, regNo, color);
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getType().equals(type) && slot.getVehicle() == null) {
                    slot.setVehicle(vehicle);
                    slot.setTicketId(generateTicketId(i + 1, j + 1));
                    return slot.getTicketId();
                }
            }
        }
        return "No slot available for given type";
    }

    private String generateTicketId(int flr, int slno) {
        return parkingLotID + "_" + flr + "_" + slno;
    }

    public String unparkVehicle(String ticketId) {
        String message = null;
        String[] extract = ticketId.split("_");
        int flrIdx = Integer.parseInt(extract[1]) - 1;
        int slotIdx = Integer.parseInt(extract[2]) - 1;
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                if (i == flrIdx && j == slotIdx) {
                    Slot slot = slots.get(i).get(j);
                    slot.setVehicle(null);
                    slot.setTicketId(null);
                    message = "Vehicle unparked successfully";
                }
            }
        }
        return message;
    }

    public int getNoOfOpenSlots(String type) {
        int count = 0;
        for (List<Slot> floor : slots) {
            for (Slot slot : floor) {
                if (slot.getVehicle() == null && slot.getType().equals(type)) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<String> displayOpenSlots(String type) {
        List<String> openSlots = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getVehicle() == null && slot.getType().equals(type)) {
                    openSlots.add("Floor " + (i + 1) + ", Slot " + (j + 1));
                }
            }
        }
        return openSlots;
    }

    public List<String> displayOccupiedSlots(String type) {
        List<String> occupiedSlots = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getVehicle() != null && slot.getType().equals(type)) {
                    occupiedSlots.add("Floor " + (i + 1) + ", Slot " + (j + 1));
                }
            }
        }
        return occupiedSlots;
    }
}

