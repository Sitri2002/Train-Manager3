package People;

import java.util.ArrayList;
import Software.*;
import Hardware.*;

public class Manager extends Person {
	private ArrayList<Train> trainsManaged;
	private ArrayList<Route> routeList;

	public Manager() {
		trainsManaged = new ArrayList<Train>();
		routeList = new ArrayList<Route>();
	}

	public ArrayList<Train> getTrainsManaged() {
		return trainsManaged;
	}

	public void setTrainsManaged(ArrayList<Train> trainsManaged) {
		this.trainsManaged = trainsManaged;
	}
	
	public ArrayList<Route> getRouteList() {
		return routeList;
	}

	public void addRoute(Route route) {
		this.routeList.add(route);
	}

	public Route createRoute(String start_loc, String end_loc, int depart_time, int arrive_time, int setprice) {
		Route newroute = new Route();

		newroute.setStartLocation(start_loc);
		newroute.setEndLocation(end_loc);
		newroute.setDepartureTime(depart_time);
		newroute.setArrivalTime(arrive_time);
		newroute.setPrice(setprice);
		routeList.add(newroute);
		return newroute;
	}

	public void updateRoute(Route route, String start_loc, String end_loc, int depart_time, int arrive_time,
			int setprice) {
		if (!routeList.contains(route)) {
			System.out.println("This route does not exist in the database.");
			return;
		}
		route.setStartLocation(start_loc);
		route.setEndLocation(end_loc);
		route.setDepartureTime(depart_time);
		route.setArrivalTime(arrive_time);
		route.setPrice(setprice);
	}

	public void updateRouteStartLocation(Route route, String start_loc) {
		if (!routeList.contains(route)) {
			System.out.println("This route does not exist in the database.");
			return;
		}
		route.setStartLocation(start_loc);
	}

	public void updateRouteEndLocation(Route route, String end_loc) {
		if (!routeList.contains(route)) {
			System.out.println("This route does not exist in the database.");
			return;
		}
		route.setEndLocation(end_loc);
	}

	public void updateRouteDepartureTime(Route route, int depart_time) {
		if (!routeList.contains(route)) {
			System.out.println("This route does not exist in the database.");
			return;
		}
		route.setDepartureTime(depart_time);
	}

	public void updateRouteArrivalTime(Route route, int arrive_time) {
		if (!routeList.contains(route)) {
			System.out.println("This route does not exist in the database.");
			return;
		}
		route.setArrivalTime(arrive_time);
	}

	public void updateRoutePrice(Route route, int setprice) {
		if (!routeList.contains(route)) {
			System.out.println("This route does not exist in the database.");
			return;
		}
		route.setPrice(setprice);
	}

	public Train createTrain(int train_code) {
		Train newtrain = new Train();

		newtrain.setTrainCode(train_code);

		if (!trainsManaged.contains(newtrain)) {
			trainsManaged.add(newtrain);
		}

		return newtrain;
	}

	public void updateTrainCode(Train train, int traincode) {
		if (!trainsManaged.contains(train)) {
			System.out.println("This train does not exist in the database.");
			return;
		}
		train.setTrainCode(traincode);
	}

	public void addRouteToTrain(Train train, Route route) {
		if (!trainsManaged.contains(train)) {
			System.out.println("This train does not exist in the database.");
			return;
		}
		if (train.getRouteList().contains(route)) {
			System.out.println("This train already has this route");
			return;
		}
		train.addRoute(route);
	}

	public void removeRouteFromTrain(Train train, Route route) {
		if (!trainsManaged.contains(train)) {
			System.out.println("This train does not exist in the database.");
			return;
		}
		if (!train.getRouteList().contains(route)) {
			System.out.println("This train does not have this route");
			return;
		}
		train.removeRoute(route);
	}

	public void cancelTrain(Train train) {
		if (trainsManaged.contains(train)) {
			trainsManaged.remove(train);
		}

		train = null;
	}

	public void removePassenger(Train train, Passenger p) {
		if (train.getPassengers().contains(p)) {
			train.getPassengers().remove(p);
		}
	}

	public void addPassenger(Train train, Passenger p) {
		if (!train.getPassengers().contains(p)) {
			train.getPassengers().add(p);
		}
	}

	public void viewTrainStatus(Train train) {
		if (trainsManaged.contains(train)) {
			System.out.println("Train " + train.getTrainCode() + " is currently " + train.getStatus());
		}

		else {
			System.out.println("Train could not be found. Please select a different train to view.");
		}
	}

	public void viewTrains() {
		for (Train train : trainsManaged) {
			System.out.println("Train " + train.getTrainCode() + " is currently " + train.getStatus());
			System.out.println("Train routes available for train "+ train.getTrainCode() + ": ");
			int index = 0;
			for (Route route : train.getRouteList()) {
				System.out.println("Route " + (index + 1) + ": ");
				System.out.println("Departing from: " + route.getStartLocation() + " at " + route.getDepatureTime());
				System.out.println("Arriving at: " + route.getEndLocation() + " at " + route.getArrivalTime());
				System.out.println("");
				index++;

			}
			index = 0;
		}
	}
	
	public void viewRoutes() {
		System.out.println("All current routes passengers can book: ");
		routeList.sort((o1, o2) -> Integer.compare(o1.getDepatureTime(),o2.getDepatureTime()));
		for (Route route : routeList) {
			System.out.println("Route " + route.getStartLocation() + "-" + route.getEndLocation() + " Departure: " + route.getDepatureTime() + " - Arrival: " + route.getArrivalTime());
		}
	}

}