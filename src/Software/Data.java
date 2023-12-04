package Software;

import java.util.ArrayList;
import java.io.Serializable;
import Hardware.*;
import People.*;

public class Data implements Serializable{
	public ArrayList<Train> trains;
	public ArrayList<Route> routes;
	
	Data() {
		trains = new ArrayList<Train>();
		routes = new ArrayList<Route>();
	}
	
	public void add_train(Train t) {
		trains.add(t);
	}
	
	public ArrayList<Train> get_trains() {
		return trains;
	}
	
	public void add_route(Route r) {
		routes.add(r);
	}
	
	public ArrayList<Route> get_routes() {
		return routes;
	}
}
