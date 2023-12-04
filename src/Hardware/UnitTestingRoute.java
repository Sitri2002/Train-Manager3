package Hardware;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import People.Passenger;

class UnitTestingRoute {
	@Test
	void testRoute() {
		Route r1 = new Route();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		
		// testing all getters
		assertEquals(null, r1.getStartLocation());
		assertEquals(null, r1.getEndLocation());
		assertEquals(0, r1.getDepartureTime());
		assertEquals(0, r1.getArrivalTime());
		assertEquals(0, r1.getPrice());
		assertEquals(passengers, r1.getPassengerList());
	}

	@Test
	void testSetStartLocation() {
		Route r1 = new Route();
		String loc = "Bend";
		
		r1.setStartLocation(loc);
		
		assertEquals("Bend", r1.getStartLocation());
	}

	@Test
	void testSetEndLocation() {
		Route r1 = new Route();
		String loc = "Portland";
		
		r1.setEndLocation(loc);
		
		assertEquals("Portland", r1.getEndLocation());
	}

	@Test
	void testSetDepartureTime() {
		Route r1 = new Route();
		int t = 1500;
		
		r1.setDepartureTime(t);
		
		assertEquals(1500, r1.getDepartureTime());
	}

	@Test
	void testSetArrivalTime() {
		Route r1 = new Route();
		int t = 1700;
		
		r1.setArrivalTime(t);
		
		assertEquals(1700, r1.getArrivalTime());
	}

	@Test
	void testSetPrice() {
		Route r1 = new Route();
		int p = 100;
		
		r1.setPrice(p);
		
		assertEquals(100, r1.getPrice());
	}

	@Test
	void testSetPassengerList() {
		Route r1 = new Route();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		ArrayList<Passenger> test = new ArrayList<Passenger>();
		Passenger p1 = new Passenger();
		Passenger p2 = new Passenger();
		Passenger p3 = new Passenger();
		
		passengers.add(p1);
		passengers.add(p2);
		passengers.add(p3);
		
		test.add(p1);
		test.add(p2);
		test.add(p3);
		
		r1.setPassengerList(passengers);
		
		assertEquals(test, r1.getPassengerList());
	}

	@Test
	void testGetDuration() {
		Route r1 = new Route();
		
		int t2 = 1700;
		r1.setArrivalTime(t2);
		int t = 1500;
		r1.setDepartureTime(t);
		
		assertEquals(200, r1.getDuration());
	}
	
	// The rest of the functionality is tested in Driver.java in software folder (Use-case testing)
}
