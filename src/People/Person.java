package People;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable {
	private String name;
	private String username;
	private String password;
	private String email;
	
	public Person() {
		name = "unknown";
		username = "unknown";
		password = "unknown";
		email = "unknown";
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String pswd) {
		this.password = pswd;
	}
	
	public String getPassword() {
		return password;
	}
		
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public static void saveData(ArrayList<Person> p){
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream("root/accounts.ser");		//the Employee object makes its way to serial data in the file Employee.ser
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(p);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
 	}
	
	public static ArrayList<Person> loadData()
	{	
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		ArrayList<Person> p=null;
			
		try
		{
			fileIn = new FileInputStream("root/accounts.ser");
			objIn = new ObjectInputStream(fileIn);
			p = (ArrayList)objIn.readObject();
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
		return p;
	}	
}
