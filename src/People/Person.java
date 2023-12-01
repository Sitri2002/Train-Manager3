package People;

import Software.*;
import Hardware.*;

public abstract class Person {
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
}
