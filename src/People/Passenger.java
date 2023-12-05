package People;

import java.io.Serializable;

import Hardware.Route;
import Hardware.Train;

public class Passenger extends Person implements Serializable{
	private Train bookedTrain;
	private Route bookedRoute = null;
	private int seatTier;
	
	public Passenger() {
		seatTier = 0;
	}
	
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

	
	public void bookTrain(Train train, Route route, int tier) {
		train.addPassenger(this);
		this.setbookedTrain(train);
		
		for(Route r : train.getRouteList()) {
			if(train.getRouteList().contains(route)) {
				if(r.equals(route)) {
					bookedRoute = r;
					seatTier = tier;
					train.setSeatAmount(train.getSeatAmount()[tier]-1, tier);
				}
		 }
		}
		
	}
	
	public void viewBooking() {
		if (bookedTrain != null) {
			System.out.println("Your upcoming booked train number" + bookedTrain.getTrainCode() + " is currently " + bookedTrain.getStatus());// add train status
			System.out.println("Your route is ");
			System.out.println("Departing from: " + bookedRoute.getStartLocation() + " at " + bookedRoute.getDepartureTime());
			System.out.println("Arriving at: " + bookedRoute.getEndLocation() + " at " + bookedRoute.getArrivalTime());
		} else {
			System.out.println("There is no train booked currently");
		}
	}
	
	public void cancelBooking(Train train) {
		train.removePassenger(this);
		System.out.println(train.getPassengers().size());
		train.setSeatAmount(train.getSeatAmount()[seatTier]+1, seatTier);
		bookedTrain = null;
		bookedRoute = null;
	}
	
	public void changeSeatTier(int new_tier) {
		seatTier = new_tier;
	}
	
	public int getSeatTier() {
		return seatTier;
	}


}