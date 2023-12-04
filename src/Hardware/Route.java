package Hardware;

import java.io.Serializable;
import java.util.ArrayList;

import People.Passenger;
import People.*;

public class Route implements Serializable {
	private String startingLocation;
	private String endLocation;
	private int departureTime;
	private int arrivalTime;
	private int price;
	private ArrayList<Passenger> passengerList;
	

	public Route() {
		startingLocation = null;
		endLocation = null;
		departureTime = 0;
		arrivalTime = 0;
		price = 0;
		setPassengerList(new ArrayList<Passenger>());
	}
	
	public void setStartLocation(String location) {
		startingLocation = location;
	}
	
	public String getStartLocation() {
		return startingLocation;
	}
	
	public void setEndLocation(String location) {
		endLocation = location;
	}
	
	public String getEndLocation() {
		return endLocation;
	}
	
	public void setDepartureTime(int time) {
		departureTime = time;
	}
	
	public int getDepartureTime() {
		return departureTime;
	}
	
	public void setArrivalTime(int time) {
		arrivalTime = time;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public ArrayList<Passenger> getPassengerList() {
		return passengerList;
	}
	
	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
	public double getDuration() {
		double duration = 0;
		
		// convert to military time somehow to calculate time in between trains ? 
		duration = (arrivalTime - departureTime);
		// ex: depart time = 1600, arrival time = 1800
		// duration would be 200 or 2 hours
		
		System.out.println("The duration of this route is " + (duration/100) + " hours");
		
		return duration;
	}
	
	public String printRoute() {
		return(this.getStartLocation() + " -> " + this.getEndLocation() + " \t\tDeparting at: " + this.getDepartureTime() + " \t\tArriving at: " + this.getArrivalTime());
	}
	
	public void routeDisplay() {
		System.out.println("Route " + startingLocation + "-" + endLocation + ":");
		System.out.println("Departs at: " + timeDisplay(departureTime));
		System.out.println("Arrives at: " + timeDisplay(arrivalTime));
		System.out.println("Base price (not including seat tier upcharge): $" + price);
		System.out.println("" );
		System.out.println("Passenger list includes:" );
		for (Passenger p: passengerList) {
			System.out.println(p.getName() + "- Train " + p.getbookedTrain().getTrainCode());
		}
	}
	
	public static String timeDisplay(int time) {
		String r = "";
		int hour = time/100;
		int minute = time % 100;
		String hourString = "";
		String minuteString = "";
		if (hour < 10) {
			hourString = "0" + String.valueOf(hour);
		}
		else {
			hourString = String.valueOf(hour);
		}
		if (minute < 10) {
			minuteString = "0" + String.valueOf(minute);
		}
		else {
			minuteString = String.valueOf(minute);
		}
		r = hourString + ":" + minuteString;
		return r;
	}

}