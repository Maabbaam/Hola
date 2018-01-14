package projectTomcat.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseConnection {
	
	//Data Lists
	private ArrayList<User> users;
	private ArrayList<Event> events;
	
	//Connection stuff
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	
	public DatabaseConnection(){
		//Initialize lists
		users = new ArrayList<User>();
		events = new ArrayList<Event>();
		
		//Initialize connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/hola", "Application", "kennedy");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * returns a user given when given an email. 
	 * DO NOT INJECT SQL THINGS... or do, im a 
	 * comment not a cop.
	 * @param String email
	 * @return List of Users, if email not found 
	 * returns empty arrayList
	 */
	public ArrayList<User> getUser(String emailIn){
		return retrieveUserFromServer("select * from Users where email = '" + emailIn + "'");
	}
	
	/**
	 * returns events when given an area
	 * @param area
	 * @return
	 */
	public ArrayList<Event> getEvents(String area){
		return retrieveEventFromServer("select * from Events where area = '" + area + "'");
	}
	
	/**
	 * returns event when given an email blah blah 
	 * blah don't inject things
	 * @param String email
	 * @return List of Events, if email is not found 
	 * returns emptyArrayList
	 */
	public ArrayList<Event> getEvent(String emailIn){
		return retrieveEventFromServer("select * from Events where email = '" + emailIn +"'");
	}
	
	/**
	 * Saves a user to the database, returns 
	 * various error codes, or not... i dunno
	 * ill figure it out later.
	 * @param User
	 * @return Error code, currently always returns 1
	 */
	public int saveUser(User user){
		
		String email 			= "'" + user.getEmail() 			+ "'";
		String school 			= "'" + user.getSchool() 			+ "'";
		String employment 		= "'" + user.getEmployment() 		+ "'";
		String favPartyMoment 	= "'" + user.getFavPartyMoment() 	+ "'";
		String name 			= "'" + user.getName() 				+ "'";
		String description 		= "'" + user.getDescription() 		+ "'";
		String area 			= "'" + user.getArea() 				+ "'";
		String events			= "'" + user.getEventEmails() 		+ "'";
		String dateOfBirth 		= "'" + user.getDateOfBirth() 		+ "'";
		
		
		try {
			
			deleteUser(user.getEmail());
			
			stmt = con.createStatement();
			stmt.executeUpdate("Insert into Users values(" 
			+ email + ", "
			+ school + ", "
			+ employment + ", "
			+ favPartyMoment + ", "
			+ name + ", "
			+ description + ", "
			+ dateOfBirth + ", "
			+ events + ", "
			+ area + ");"
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	
	/**
	 * Saves a user to the database, deletes duplicate entries, returns 
	 * various error codes, or not... i dunno
	 * ill figure it out later.
	 * @param Event
	 * @return Error code, currently always returns 1
	 */
	public int saveEvent(Event event){
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		String email 		= "'" + event.getEmail() 		+ "'";
		String nameOfEvent 	= "'" + event.getNameOfEvent() 	+ "'";
		String description 	= "'" + event.getDescription() 	+ "'";
		String area 		= "'" + event.getArea() 		+ "'";
		String location 	= "'" + event.getLocation() 	+ "'";
		
		String dateAndTime 	= "'" + event.getDate() + " " + event.getTime() + "'";
		
		int privacy 		= event.getPrivacy();
		int maxGuests 		= event.getMaxGuests();
		
		String guestsEmail 	= "'" + event.getGuestEmails() + "'";
		
		try {
			
			deleteEvent(event.getEmail());
			
			stmt = con.createStatement();
			stmt.executeUpdate("Insert into Events values(" 
					+ email + ", "
					+ nameOfEvent + ", "
					+ dateAndTime + ", "
					+ description + ", "
					+ area + ", "
					+ location + ", "
					+ privacy + ", "
					+ maxGuests + ", "
					+ guestsEmail + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	
	/**
	 * Executes SQL statement and returns an event
	 * @param statement
	 * @return List of users.
	 */
	private ArrayList<Event> retrieveEventFromServer(String statement){
		ArrayList<Event> returnEvents = null;
		
		try {
			//Return User data
			stmt = con.createStatement();
			rs = stmt.executeQuery(statement);
			
			returnEvents = SQLHelper.constructEvent(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = null;
		
		return returnEvents;
	}
	
	/**
	 * Executes SQL statement and returns a user
	 * @param statement
	 * @return List of users.
	 */
	private ArrayList<User> retrieveUserFromServer(String statement){
		ArrayList<User> returnUsers = null;
		
		try {
			//Return User data
			stmt = con.createStatement();
			rs = stmt.executeQuery(statement);
			
			returnUsers = SQLHelper.constructUser(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = null;
		
		return returnUsers;
	}
	
	/**
	 * Given an email, all entries matching that email 
	 * in the db are deleted.
	 * 
	 * @param emailIn
	 * @return error code, currently only returns 1
	 */
	public int deleteUser(String emailIn){
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("Delete from Users where email = '" + emailIn + "';");
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * Given an email, all entries matching that email 
	 * in the db are deleted.
	 * 
	 * @param emailIn
	 * @return error code, currently only returns 1
	 */
	public int deleteEvent(String emailIn){
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("Delete from Events where email = '" + emailIn + "';");
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
