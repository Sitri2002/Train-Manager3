package People;
import Software.*;

import java.util.ArrayList;

import Hardware.*;

public class Passenger extends Person {
	private Train bookedTrain;
//	private int bookedTrainCode;		changed to train because its too hard to work with only train code and route.. unless you guys have a better way to implement
	private Route bookedRoute;
	private int seatTier;
	
	public Passenger() {
	//	setBookedTrainCode(0);
		setBookedRoute(new Route());
		seatTier = 0;
	}
	
/*	public int getBookedTrainCode() {
		return bookedTrainCode;
	}

	public void setBookedTrainCode(int bookedTrainCode) {
		this.bookedTrainCode = bookedTrainCode;
	} */

	public Route getBookedRoute() {
		return bookedRoute;
	}

	public void setBookedRoute(Route bookedRoute) {
		this.bookedRoute = bookedRoute;
	}

	public Train getbookedTrain() {
		return bookedTrain;
	}

	public void setbookedTrain(Train train) {
		this.bookedTrain = train;
	}

	public void viewRoutes(ArrayList<Route> routeList) {
		System.out.println("All current routes you can book: ");
		routeList.sort((o1, o2) -> Integer.compare(o1.getDepatureTime(),o2.getDepatureTime()));
		for (Route route : routeList) {
			System.out.println("Route " + route.getStartLocation() + "-" + route.getEndLocation() + " Departure: " + route.getDepatureTime() + " - Arrival: " + route.getArrivalTime());
		}
	}
	
	public void viewTrainStatus() {
		System.out.println("Booked train status: "); 
		bookedTrain.printSchedule();
	}
	
	public void bookTrain(Train train, Route route) {
		train.addPassenger(this);
		this.setbookedTrain(train);
		
		for(Route r : train.getRouteList()) {
			if(train.getRouteList().contains(route)) {
				if(r == route) {
					setBookedRoute(r); // change routelist to be only one route? : UPDATED... maybe - it is kind of hard to iterate trhough and match routes
				}
		 }
		}
	}
	
	public void viewBooking() {
		if (bookedTrain != null) {
			System.out.println("Your upcoming booked train number" + bookedTrain.getTrainCode() + " is currently " + bookedTrain.getStatus());// add train status
			System.out.println("Your route is ");
			System.out.println("Departing from: " + bookedRoute.getStartLocation() + " at " + bookedRoute.getDepatureTime());
			System.out.println("Arriving at: " + bookedRoute.getEndLocation() + " at " + bookedRoute.getArrivalTime());
		} else {
			System.out.println("There is no train booked currently");
		}
	}
	
	public void cancelBooking(Train train) {
		train.removePassenger(this);
		
		bookedTrain = null;
	}
	
	public void changeSeatTier(int new_tier) {
		seatTier = new_tier;
	}
	
	public int getSeatTier() {
		return seatTier;
	}


}