package Hardware;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import People.Passenger;

class UnitTestingTrain {

	@Test
	void testTrain() {
		Train train = new Train();
		ArrayList<Route> r = new ArrayList<Route>();
		ArrayList<Integer> s = new ArrayList<Integer>();
		ArrayList<Passenger> plist = new ArrayList<Passenger>();
		
		// testing all getters
		assertEquals(0, train.getTrainCode());
		assertEquals("in the station", train.getStatus());
		assertEquals(r, train.getRouteList());
		assertEquals(s, train.getSchedule());
		assertEquals(plist, train.getPassengers());
	}

	@Test
	void testSetTrainCode() {
		Train train = new Train();
		int code = 301;
		
		train.setTrainCode(code);
		
		assertEquals(code, train.getTrainCode());
	}

	@Test
	void testSetSeat() {
		Train train = new Train();
		int seat = 301;
		
		train.setSeat(301, 0);
		
		assertEquals(seat, train.getSeatTier(0));
	}

	@Test
	void testSetSeatTiers() {
		Train train = new Train();
		double seat1 = 37;

		train.setSeat(seat1, 1);
		
		assertEquals(37, train.getSeatTier(1));
	}

	@Test
	void testSetStatus() {
		Train train = new Train();
		String stat = "On time";
		
		train.setStatus(stat);
		
		assertEquals(stat, train.getStatus());
	}
	
	@Test
	void testAddPassenger() {
		Train train = new Train();
		ArrayList<Passenger> plist = new ArrayList<Passenger>();
		Passenger p = new Passenger();
		
		plist.add(p);
		train.addPassenger(p);
		
		assertEquals(plist, train.getPassengers());
	}

	@Test
	void testRemovePassenger() {
		Train train = new Train();
		ArrayList<Passenger> plist = new ArrayList<Passenger>();
		Passenger p1 = new Passenger();
		Passenger p2 = new Passenger();
		Passenger p3 = new Passenger();
		
		plist.add(p1);
		plist.add(p2);
		plist.add(p3);
		plist.remove(p3);
		
		train.addPassenger(p1);
		train.addPassenger(p2);
		train.addPassenger(p3);
		train.removePassenger(p3);
		
		assertEquals(plist, train.getPassengers());
	}

	@Test
	void testAddRoute() {
		Train train = new Train();
		ArrayList<Route> rlist = new ArrayList<Route>();
		Route r = new Route();
		
		rlist.add(r);
		train.addRoute(r);
		
		assertEquals(rlist, train.getRouteList());
	}

	@Test
	void testRemoveRoute() {
		Train train = new Train();
		ArrayList<Route> rlist = new ArrayList<Route>();
		Route r1 = new Route();
		Route r2 = new Route();
		Route r3 = new Route();
		
		rlist.add(r1);
		rlist.add(r2);
		rlist.add(r3);
		rlist.remove(r3);
		
		train.addRoute(r1);
		train.addRoute(r2);
		train.addRoute(r3);
		train.removeRoute(r3);
		
		assertEquals(rlist, train.getRouteList());
	}

	@Test
	void testAddTimeToSchedule() {
		Train train = new Train();
		ArrayList<Integer> ilist = new ArrayList<Integer>();
		int i = 3;
		
		ilist.add(i);
		train.addTimeToSchedule(i);
		
		assertEquals(ilist, train.getSchedule());
	}

	// The rest of functionality is tested in Driver.java in software folder (Use-case testing)
}
