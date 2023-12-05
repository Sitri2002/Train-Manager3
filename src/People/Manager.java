package People;

import java.io.Serializable;
import java.util.ArrayList;
import People.*;
import Hardware.*;

public class Manager extends Person implements Serializable {
	private ArrayList<Train> trainsManaged;
	
	public Manager() {
		trainsManaged = new ArrayList<Train>();
	}
	
	public ArrayList<Train> getTrainsManaged() {
		return trainsManaged;
	}

	public void setTrainsManaged(ArrayList<Train> trainsManaged) {
		this.trainsManaged = trainsManaged;
	}

	public Route createRoute(String start_loc, String end_loc, int depart_time, int arrive_time, int setprice) {
		Route newroute = new Route();
		
		newroute.setStartLocation(start_loc);
		newroute.setEndLocation(end_loc);
		newroute.setDepartureTime(depart_time);
		newroute.setArrivalTime(arrive_time);
		newroute.setPrice(setprice);
		
		return newroute;
	}
	
	public void updateRoute(Route route, String start_loc, String end_loc, int depart_time, int arrive_time, int setprice) {	
		route.setStartLocation(start_loc);
		route.setEndLocation(end_loc);
		route.setDepartureTime(depart_time);
		route.setArrivalTime(arrive_time);
		route.setPrice(setprice);

	}
	
	public void updateRouteStartLocation(Route route, String start_loc) {
		route.setStartLocation(start_loc);
	}
	
	public void updateRouteEndLocation(Route route, String end_loc) {
		route.setEndLocation(end_loc);
	}
	
	public void updateRouteDepartureTime(Route route, int depart_time) {
		route.setDepartureTime(depart_time);
	}
	
	public void updateRouteArrivalTime(Route route, int arrive_time) {
		route.setArrivalTime(arrive_time);
	}
	
	public void updateRoutePrice(Route route, int setprice) {
		route.setPrice(setprice);
	}
	
	public Train createTrain(int train_code) {
		Train newtrain = new Train();
		
		newtrain.setTrainCode(train_code);
		
		if(!trainsManaged.contains(newtrain)) {
		trainsManaged.add(newtrain);
		}
		
		return newtrain;
	}
	
	public void updateTrainCode(Train train, int traincode) {
		train.setTrainCode(traincode);
	}
	
	public void addRouteToTrain(Train train, Route route) {
		train.addRoute(route);
	}
	
	public void removeRouteFromTrain(Train train, Route route) {
		train.removeRoute(route);
	}
	
	public void cancelTrain(Train train) {
		if(trainsManaged.contains(train)){
		trainsManaged.remove(train);
		}
		
		train = null;
	}
	
	public void removePassenger(Train train, Passenger p) {
		if(train.getPassengers().contains(p)){
			train.getPassengers().remove(p);
		}
	}
	
	public void addPassenger(Train train, Passenger p) {
		if(!train.getPassengers().contains(p)){
			train.getPassengers().add(p);
		}
	}
	
	public void viewTrainStatus(Train train) {
		if(trainsManaged.contains(train)) {
			System.out.println("Train " + train.getTrainCode() + " is currently " + train.getStatus());
		}
		
		else {
			System.out.println("Train could not be found. Please select a different train to view.");
		}
	}
	
	public void viewTrains() {
		for(Train train : trainsManaged) {
			for(Route route : train.getRouteList()) {
				if(trainsManaged.contains(train)) {
					System.out.println("Train " + train.getTrainCode() + " is currently " + train.getStatus());
					System.out.println("Train routes available: ");
					System.out.println("Departing from: " + route.getStartLocation() + " at " + route.getDepartureTime());
					System.out.println("Arriving at: " + route.getEndLocation() + " at " + route.getArrivalTime() + "\n");
				}
				else {
					System.out.println("Train could not be found. Please select a different train to view.");
				}
			}
		}
	}


}