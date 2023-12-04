package People;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Hardware.Route;
import Hardware.Train;

class UnitTestingPassenger {
	@Test
	void testPassenger() {
		Passenger p1 = new Passenger();
		
		// testing getters
		assertEquals("unknown", p1.getName());
		assertEquals("unknown", p1.getUsername());
		assertEquals("unknown", p1.getPassword());
		assertEquals("unknown", p1.getEmail());
		assertEquals(0, p1.getSeatTier());
	}

	@Test
	void testSetName() {
		Passenger m1 = new Passenger();
		
		m1.setName("Jack");
		
		assertEquals("Jack", m1.getName());
	}

	@Test
	void testSetUsername() {
		Passenger m1 = new Passenger();
		
		m1.setUsername("sitri2002");
		
		assertEquals("sitri2002", m1.getUsername());
	}

	@Test
	void testSetPassword() {
		Passenger m1 = new Passenger();
		
		m1.setPassword("Kota!2345");
		
		assertEquals("Kota!2345", m1.getPassword());
	}

	@Test
	void testSetEmail() {
		Passenger m1 = new Passenger();
		
		m1.setEmail("jacknguyen@arizona.edu");
		
		assertEquals("jacknguyen@arizona.edu", m1.getEmail());
	}

	@Test
	void testSetBookedRoute() {
		Route r = new Route();
		Passenger p = new Passenger();
		
		p.setBookedRoute(r);
		
		assertEquals(r, p.getBookedRoute());
	}

	@Test
	void testSetbookedTrain() {
		Train t = new Train();
		Passenger p = new Passenger();
		
		p.setbookedTrain(t);
		
		assertEquals(t, p.getbookedTrain());
	}

	@Test
	void testBookTrain() {
		Train testTrain = new Train();
		Route testRoute = new Route();
		Passenger p = new Passenger();
		
		testTrain.setTrainCode(333);
		
		testRoute.setArrivalTime(1000);
		testRoute.setDepartureTime(1200);
		
		testTrain.addRoute(testRoute);
		
		p.bookTrain(testTrain, testRoute);
		
		assertEquals(testTrain, p.getbookedTrain());
		assertEquals(testRoute, p.getBookedRoute());
	}

	@Test
	void testCancelBooking() {
		Train testTrain = new Train();
		Passenger p = new Passenger();
		
		p.cancelBooking(testTrain);
		
		assertEquals(null, p.getbookedTrain());
	}

	@Test
	void testChangeSeatTier() {
		Passenger p = new Passenger();
		int seat = 3;
		
		p.changeSeatTier(seat);
		
		assertEquals(seat, p.getSeatTier());
	}

	// The rest of the functionality is tested in Driver.java in software folder (Use-case testing)
}
