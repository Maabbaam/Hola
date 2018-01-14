package projectTomcat.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SQLHelper {

	public static Date getDateOfBirth(String dateIn){
		
		String[] date = dateIn.split(" ");
		String yearDate = date[0];
		String[] yearDateSplit = yearDate.split("-");
		
		Date returnDate = new Date(Integer.parseInt(yearDateSplit[0]), Integer.parseInt(yearDateSplit[1]), Integer.parseInt(yearDateSplit[2]));
		
		return returnDate;
	}
	
	public static ArrayList<String> emailsToArrayList(String emailsIn) {
		String[] emails = emailsIn.split(",");
		
		ArrayList<String> emailsList = new ArrayList<String>();
		
		for(int i = 0; i < emails.length; i++){
			emailsList.add(emails[i]);
		}
		
		return emailsList;
	}
	
	public static ArrayList<Event> constructEvent(ResultSet rs) throws SQLException{
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		String email;
		String nameOfEvent;
		String description;
		String area;
		String location;
		String time;
		
		String date;
		
		int privacy;
		int maxGuests;
		
		String guestsEmail;
		
		
		
		while(rs.next()){
			email 		= rs.getString("email");
			nameOfEvent = rs.getString("nameOfEvent");
			description = rs.getString("description");
			area 		= rs.getString("area");
			location 	= rs.getString("location");
			time 		= getTime(rs.getString("dateAndTime"));
			
			privacy 	= Integer.parseInt(rs.getString("privacy"));
			maxGuests 	= Integer.parseInt(rs.getString("maxGuests"));
			
			date 		= rs.getString("dateAndTime");
			guestsEmail = rs.getString("guests");
			
			events.add(new Event(email, nameOfEvent, date, time, description, area, location, privacy, maxGuests, guestsEmail));
		}
		
		return events;
	}
	
	public static String getTime(String timeIn){
		
		System.out.println(timeIn);
		return timeIn.split(" ")[1];
	}
	
	public static ArrayList<User> constructUser(ResultSet rs) throws SQLException{

		ArrayList<User> users = new ArrayList<User>();
		
		String email;
		String school;
		String employment;
		String favPartyMoment;
		String name;
		String description;
		String location;
		
		String dateOfBirth;
		
		String eventEmails;
		
		
		while(rs.next()){
			email 			= rs.getString("email");
			school 			= rs.getString("school");
			employment 		= rs.getString("employment");
			favPartyMoment 	= rs.getString("favPartyMoment");
			name			= rs.getString("name");
			description 	= rs.getString("description");
			location		= rs.getString("area");
			
			dateOfBirth 	= rs.getString("dateOfBirth");
			
			eventEmails		= rs.getString("events");
			
			User tempUser = new User(email, school, employment, favPartyMoment, name, description, dateOfBirth, eventEmails, location);
			users.add(tempUser);
		}
		
		return users;
	}
	
	public static String arrayListToString(ArrayList<String> eventsList){
		String returnString = "";
		
		for(int i = 0; i < eventsList.size(); i++){
			returnString = returnString + eventsList.get(i) + ",";
		}
		
		return returnString;
	}
	
	public static String dateOfBirthToString(Date inDate){
		String returnString = "";
		
		returnString = "" + inDate.getYear() 
		+ "-" + inDate.getMonth() 
		+ "-" + inDate.getDate();
		
		return returnString;
	}
}
