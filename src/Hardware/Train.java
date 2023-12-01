package Hardware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import People.*;
import Software.*;

public class Train {

	private int trainCode;
	private int[] seatTiers;
	private String status;
	private ArrayList<Route> routeList;
	private ArrayList<Integer> schedule;
	private ArrayList<Passenger> passengerList;

	public Train() {
		trainCode = 0;
		status = "in the station";
		seatTiers = new int[3];
		routeList = new ArrayList<Route>();
		schedule = new ArrayList<Integer>();
		passengerList = new ArrayList<Passenger>();
	}

	public int getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(int c) {
		trainCode = c;
	}

	public int getSeatTier(int i) {
		return seatTiers[i];
	}

	public void setSeat(int seat, int i) {
		seatTiers[i] = seat;
	}

	public void setSeatTiers(int[] seat) {
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

	public void printRouteList() {
		for (int i = 0; i < routeList.size(); i++) {
			System.out.println(routeList.get(i).getStartLocation() + " -> " + routeList.get(i).getEndLocation());
		}
	}

	public void printSchedule() {
		routeList.sort((o1, o2) -> Integer.compare(o1.getDepatureTime(),o2.getDepatureTime()));
		for (Route route : routeList) {
			System.out.println("Route " + route.getStartLocation() + "-" + route.getEndLocation() + " Departure: " + route.getDepatureTime() + " - Arrival: " + route.getArrivalTime());
		}
	}

	public void printPassengers() {
		for (int i = 0; i < passengerList.size(); i++) {
			System.out.println(passengerList.get(i).getName());
		}
	}

	public void addPassenger(Passenger p) {
		passengerList.add(p);
	}

	public void removePassenger(Passenger p) {
		passengerList.remove(p);
	}

	public void addRoute(Route r) {
		ArrayList<Route> temp = new ArrayList<>(routeList);
		temp.add(r);
		if (!routeValid(temp)) {
			System.out.println("This newly added Route " + r.getStartLocation() + "-" + r.getEndLocation()
					+ " is not compatible with current routes. Please add a different route that will form either a complete cycle"
					+ " (Strongly connected) or a directed single path (Unilateral connected)");
		}
		else {
			routeList.add(r);
			System.out.println("Newly added Route " + r.getStartLocation() + "-" + r.getEndLocation() + " is compatible. Added");
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
		}
		else {
			routeList.remove(r);
			System.out.println("Newly requested removal Route " + r.getStartLocation() + "-" + r.getEndLocation() + " is safe to remove. Removed");
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

}