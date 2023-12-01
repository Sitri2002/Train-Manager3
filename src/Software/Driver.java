package Software;

import People.*;


import Hardware.*;

public class Driver {
	
	public static void main(String args[]) {
		// manager login
		String manager_name="Matthew";
		String manager_username="mwasko";
		String manager_password="admin";
		String manager_email="mwasko@arizona.edu";
		
		// create manager
		Manager m1 = new Manager();
		m1.setName(manager_name);
		m1.setUsername(manager_username);
		m1.setPassword(manager_password);
		m1.setEmail(manager_email);
		
		// should be empty train managed list
		m1.getTrainsManaged();
		
		// create a new route
		Route route1 = m1.createRoute("TUS", "PHX", 1500, 1700, 10);
		Route route2 = m1.createRoute("PHX", "NY", 1800, 2300, 10);
		Route route3 = m1.createRoute("NY", "CHI", 2400, 500, 10);
		Route route4 = m1.createRoute("CHI", "TUS", 500, 1800, 10);
		Train t1 = m1.createTrain(1830);
		System.out.println("Testing route add/removal validity:");
		m1.addRouteToTrain(t1, route1);
		m1.addRouteToTrain(t1, route2);
		m1.addRouteToTrain(t1, route3);
		m1.addRouteToTrain(t1, route4);
		t1.removeRoute(route4);
		t1.removeRoute(route2);
		System.out.println("");
		System.out.println("View available trains in the station:");
		m1.viewTrains();
		System.out.println("");
		System.out.println("Train 1 schedule:");
		t1.printSchedule();
		String passenger1_name="Passenger1";
		String passenger1_password="passeng";
		String passenger1_username="user1";
		String passenger1_email="passenger1@arizona.edu";
		
		// create passenger1
		Passenger p1 = new Passenger();
		p1.setName(passenger1_name);
		p1.setPassword(passenger1_password);
		p1.setUsername(passenger1_username);
		p1.setEmail(passenger1_email);
		
		p1.bookTrain(t1, t1.getRouteList().get(0));
		System.out.println("");
		System.out.println("Printing the booking details for p1:");
		
		p1.viewBooking();
		
		String passenger2_name="Passenger2";
		String passenger2_password="passeng";
		String passenger2_username="user2";
		String passenger2_email="passenger2@arizona.edu";
		
		// create passenger2
		Passenger p2 = new Passenger();
		p2.setName(passenger2_name);
		p2.setPassword(passenger2_password);
		p2.setUsername(passenger2_username);
		p2.setEmail(passenger2_email);
		
		p2.bookTrain(t1, t1.getRouteList().get(0));
		System.out.println("");
		System.out.println("Printing passengers:");
		t1.printPassengers();
		
		p1.cancelBooking(t1);
		System.out.println("");
		System.out.println("Checking if the booking for p1 has been cancelled:");
		p1.viewBooking();
		System.out.println("");
		System.out.println("Printing passengers after p1 cancelled:");
		t1.printPassengers();
		System.out.println("");
		System.out.println("Printing the duration for the t1 route: ");
		t1.getRouteList().get(0).getDuration();
		System.out.println("");
		System.out.println("Route available to passengers: ");
		p1.viewRoutes(m1.getRouteList());
		
	}
}
