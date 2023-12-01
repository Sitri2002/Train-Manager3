package Software;

import People.*;
import Hardware.*;

import java.util.ArrayList;
import java.util.Scanner;

// A small command line demo of the engine
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean run = true;
		boolean manager = false;
		boolean passenger = false;
		while (run) {
			Passenger p = new Passenger();
			Manager m = new Manager();
			System.out.println("Welcome to the console commands for the train management system homepage.");
			System.out.println("Press associated keys to access commands.");
			System.out.println("");
			System.out.print("Are you a manager or passenger? press m for manager and p for passenger rights: ");
			String rights = scan.nextLine();
			if (rights.equals("m")) {
				manager = true;
			}
			if (rights.equals("p")) {
				passenger = true;
			}
			while (manager) {
				System.out.println("");
				System.out.println(
						"Welcome to manager commands. Here are associated commands you can take, press q to go back to homepage");
				System.out.println(
						"create-route START END ARRIVAL DEPARTURE PRICE: add a new route with following params");
				System.out.println("create-train CODE: add a new train with following code");
				System.out.println("add-route TRAINCODE ROUTESTART ROUTEEND ARRIVAL DEPARTURE: add route to train");
				System.out.println(
						"remove-route TRAINCODE ROUTESTART ROUTEEND ARRIVAL DEPARTURE: remove route from train");
				System.out.println("view-trains: view all trains");
				System.out.println("view-routes: view all routes");
				System.out.println("view-train CODE: view a single train status");
				String commands = scan.nextLine();
				String[] command = commands.split(" ");
				if (command[0].equals("q") ) {
					manager = false;
				}
				if (command[0].equals("view-routes")) {
					System.out.println("All available routes:");
					m.viewRoutes();
				}
				if (command[0].equals("view-trains")) {
					System.out.println("All available trains:");
					m.viewTrains();
				}
				if (command[0].equals("view-train")) {
					System.out.println("View selected train with code");
					ArrayList<Train> trainList = m.getTrainsManaged();
					for (Train train : trainList) {
						if (train.getTrainCode() == (Integer.valueOf(command[1]))) {
							m.viewTrainStatus(train);
							break;
						}
					}
				}
				if (command[0].equals("create-route")) {
					m.createRoute(command[1], command[2], Integer.valueOf(command[3]), Integer.valueOf(command[4]),
							Integer.valueOf(command[5]));
					System.out.println("Route created");
				}
				if (command[0].equals("create-train")) {
					System.out.println("Creating train");
					m.createTrain(Integer.valueOf(command[1]));
				}
				if (command[0].equals("add-route")) {
					System.out.println("Adding route to train");
					ArrayList<Train> trainList = m.getTrainsManaged();
					ArrayList<Route> routeList = m.getRouteList();
					for (Train train : trainList) {
						if (train.getTrainCode() == (Integer.valueOf(command[1]))) {
							for (Route route : routeList) {
								if (route.getStartLocation().equals(command[2])
										&& route.getEndLocation().equals(command[3])
										&& route.getArrivalTime() == Integer.valueOf(command[4])
										&& route.getDepatureTime() == Integer.valueOf(command[5])) {
									m.addRouteToTrain(train, route);
								}
							}
						}
					}
				}
				if (command[0].equals("remove-route")) {
					System.out.println("Adding route to train");
					ArrayList<Train> trainList = m.getTrainsManaged();
					ArrayList<Route> routeList = m.getRouteList();
					for (Train train : trainList) {
						if (train.getTrainCode() == (Integer.valueOf(command[1]))) {
							for (Route route : routeList) {
								if (route.getStartLocation().equals(command[2])
										&& route.getEndLocation().equals(command[3])
										&& route.getArrivalTime() == Integer.valueOf(command[4])
										&& route.getDepatureTime() == Integer.valueOf(command[5])) {
									m.removeRouteFromTrain(train, route);
								}
							}
						}
					}
				}
			}
			while (passenger) {
				System.out.println("");
				System.out.println(
						"Welcome to passenger commands. Here are associated commands you can take, press q to go back to homepage");
				String commands = scan.nextLine();
				String[] command = commands.split(" ");
				if (command[0].equals("q") ) {
					passenger = false;
				}
				
			}
		}
	}

}
