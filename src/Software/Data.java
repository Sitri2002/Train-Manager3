package Software;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import Hardware.*;
import People.*;

/* this file is used to save train and routes data in the system so we can easily access them in our
   main GUI file. Serialization of data occurs here for trains and routes.*/

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
	
	public static void saveData(Data d){
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream("root/data.ser");		//the Employee object makes its way to serial data in the file Employee.ser
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(d);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
 	}
	
	public static Data loadData()
	{	
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Data d=null;
			
		try
		{
			fileIn = new FileInputStream("root/data.ser");
			objIn = new ObjectInputStream(fileIn);
			d = (Data)objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return d;
	}	
}
