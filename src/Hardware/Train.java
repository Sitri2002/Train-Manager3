package Hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import People.*;
import Software.*;

public class Train implements Serializable {

	private int trainCode;
	private double[] seatTiers = { 1, 1.5, 2 };
	private int[] seatAmount = { 10, 10, 10 };
	private String status;
	private ArrayList<Route> routeList;
	private ArrayList<Integer> schedule;
	private ArrayList<Passenger> passengerList;

	public Train() {
		trainCode = 0;
		status = "in the station";
		routeList = new ArrayList<Route>();
		schedule = new ArrayList<Integer>();
		passengerList = new ArrayList<Passenger>();
	}

	// getters and setters
	public int getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(int c) {
		trainCode = c;
	}

	public double getSeatTier(int i) {
		return seatTiers[i];
	}

	public void setSeat(double seat, int i) {
		seatTiers[i] = seat;
	}

	public void setSeatTiers(double[] seat) {
		seatTiers = seat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Route> getRouteList() {
		return routeList;
	}

	public ArrayList<Integer> getSchedule() {
		return schedule;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengerList;
	}

	// printing functions
	public void printRouteList() {
		for (int i = 0; i < routeList.size(); i++) {
			System.out.println(routeList.get(i).getStartLocation() + " -> " + routeList.get(i).getEndLocation());
		}
	}

	public void printSchedule() {
		routeList.sort((o1, o2) -> Integer.compare(o1.getDepartureTime(), o2.getDepartureTime()));
		for (Route route : routeList) {
			System.out.println("Route " + route.getStartLocation() + "-" + route.getEndLocation() + " Departure: "
					+ route.getDepartureTime() + " - Arrival: " + route.getArrivalTime());
		}
	}

	public void printPassengers() {
		for (int i = 0; i < passengerList.size(); i++) {
			System.out.println(passengerList.get(i).getName());
		}
	}
	
	// edits the list of passengers on this train
	public void addPassenger(Passenger p) {
		passengerList.add(p);
	}

	public void removePassenger(Passenger p) {
		int index = -1;
		for (int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getUsername().equals(p.getUsername())) {
				index = i;
			}
		}
		if (index >= 0) {
			passengerList.remove(index);
		}
	}

	// used for calculating seat tier price
	public int[] getSeatAmount() {
		return seatAmount;
	}

	public void setSeatAmount(int amount, int i) {
		seatAmount[i] = amount;
	}

	// uses routeValid() to check if a route is possible first, then adds it
	public void addRoute(Route r) {
		ArrayList<Route> temp = new ArrayList<>(routeList);
		temp.add(r);
		if (!routeValid(temp)) {
			System.out.println("This newly added Route " + r.getStartLocation() + "-" + r.getEndLocation()
					+ " is not compatible with current routes. Please add a different route that will form either a complete cycle"
					+ " (Strongly connected) or a directed single path (Unilateral connected)");
		} else {
			routeList.add(r);
			System.out.println(
					"Newly added Route " + r.getStartLocation() + "-" + r.getEndLocation() + " is compatible. Added");
		}
		temp.clear();
	}

	public void removeRoute(Route r) {
		if (!routeList.contains(r)) {
			System.out.println("This train does not currently have this route. Removal will not occur.");
			return;
		}
		ArrayList<Route> temp = new ArrayList<>(routeList);
		temp.remove(r);
		if (!routeValid(temp)) {
			System.out.println("This newly requested removal Route " + r.getStartLocation() + "-" + r.getEndLocation()
					+ " is not compatible with current routes for removal. Please remove a different route that will form either a complete cycle"
					+ " (Strongly connected) or a directed single path (Unilateral connected)");
		} else {
			routeList.remove(r);
			System.out.println("Newly requested removal Route " + r.getStartLocation() + "-" + r.getEndLocation()
					+ " is safe to remove. Removed");
		}
		temp.clear();

	}

	public void addTimeToSchedule(int time) {
		schedule.add(time);
	}

	public void removeTimeFromSchedule(int time) {
		schedule.remove(time);
	}

	// An algorithm to check if the routes form strongly connected and unilaterally
	// strongly connected
	// graph. If they are, the routeList is valid, otherwise, its not;
	public boolean routeValid(ArrayList<Route> list) {
		int pm[][] = pathMatrix(list);
		boolean strong = true;
		boolean lowertri = true;
		boolean uppertri = true;
		boolean uni;
		// Check strongly connected
		for (int i = 0; i < pm.length; i++) {
			for (int j = 0; j < pm[0].length; j++) {
				if (pm[i][j] == 0) {
					strong = false;
				}
			}
		}
		// Check diagonal 1s upper
		for (int i = 0; i < pm.length; i++) {
			for (int j = 0; j < pm[0].length; j++) {
				if (i > j && pm[i][j] == 0) {
					uppertri = false;
				}
			}
		}
		// Check diagonal 1s lower
		for (int i = 0; i < pm.length; i++) {
			for (int j = 0; j < pm[0].length; j++) {
				if (i < j && pm[i][j] == 0) {
					lowertri = false;
				}
			}
		}

		if (lowertri ^ uppertri) {
			uni = true;
		} else {
			uni = false;
		}

		if (!strong && !uni) {
			return false;
		} else {
			return true;
		}
	}

	// helper method when debugging
	public static String printMatrix(int[][] m) {
		String result = "";
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				result += m[i][j] + " ";
			}
			result += "\n";
		}
		return result;
	}

	// Helper function for routeValid create a path matrix from the routeList using
	// Warshall's algorithm
	public int[][] pathMatrix(ArrayList<Route> list) {
		// Integer mapping for location names
		Map<String, Integer> cityIndex = new HashMap<>();
		int index = 0;
		for (Route route : list) {
			if (!cityIndex.containsKey(route.getStartLocation())) {
				cityIndex.put(route.getStartLocation(), index++);
			}
			if (!cityIndex.containsKey(route.getEndLocation())) {
				cityIndex.put(route.getEndLocation(), index++);
			}
		}
		// Create an adjacency matrix
		int[][] am = new int[cityIndex.size()][cityIndex.size()];
		// Create a path matrix
		int[][] pm = new int[cityIndex.size()][cityIndex.size()];
		for (int i = 0; i < cityIndex.size(); i++) {
			for (int j = 0; j < cityIndex.size(); j++) {
				am[i][j] = 0;
			}
		}

		for (Route route : list) {
			int startIndex = cityIndex.get(route.getStartLocation());
			int endIndex = cityIndex.get(route.getEndLocation());
			am[startIndex][endIndex] = 1;
		}

		for (int i = 0; i < cityIndex.size(); i++) {
			for (int j = 0; j < cityIndex.size(); j++) {
				pm[i][j] = am[i][j];
			}
		}

		for (int k = 0; k < cityIndex.size(); k++) {
			for (int i = 0; i < cityIndex.size(); i++) {
				for (int j = 0; j < cityIndex.size(); j++) {
					pm[i][j] = pm[i][j] | (pm[i][k] & pm[k][j]);
				}
			}
		}
		return pm;
	}

	private String tierString(int tier) {
		String r = "";
		if (tier == 0) {
			r = "Economy";
		} else if (tier == 1) {
			r = "Business";
		} else {
			r = "First Class";
		}
		return r;
	}

	public void trainDisplay() {
		System.out.println("Train " + trainCode + " information:");
		System.out.println("Status: " + status);
		System.out.println("Economy tier seat price: " + seatTiers[0] * 100 + "% of route price.");
		System.out.println("Economy seats remaining: " + seatAmount[0]);
		System.out.println("Business tier seat price: " + seatTiers[1] * 100 + "% of route price.");
		System.out.println("Business seats remaining: " + seatAmount[1]);
		System.out.println("First Class tier seat price: " + seatTiers[2] * 100 + "% of route price.");
		System.out.println("First Class seats remaining: " + seatAmount[2]);
		System.out.println("");
		System.out.println("All the current routes for train " + trainCode + ":");
		for (Route r : routeList) {
			System.out.println("Route " + r.getStartLocation() + " - " + r.getEndLocation());
			System.out.println("Schedule: " + Route.timeDisplay(r.getDepartureTime()) + " - "
					+ Route.timeDisplay(r.getArrivalTime()));
			System.out.println("Price: " + r.getPrice());
			System.out.println("");
		}
		System.out.println("Passengers who booked this train:");
		for (Passenger p : passengerList) {
			System.out.println(p.getName() + " - Seat tier purchased: " + tierString(p.getSeatTier()));
		}
	}
}