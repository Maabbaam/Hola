package projectTomcat;

import javax.ws.rs.Path;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;


import projectTomcat.storage.DatabaseConnection;
import projectTomcat.storage.Event;
import projectTomcat.storage.SQLHelper;
import projectTomcat.storage.User;

import javax.ws.rs.core.Request;

import org.json.simple.*;

@Path("/items")

public class ItemResource {
	
	// The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information (no kidding).
    @Context
    UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    @Context
    Request request;
    
 // Basic "is the service running" test
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
        return "Server is running";
    }
    
    
    //Gets user based on email
    @GET
    @Path("/user/{userEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<JSONObject> getUser(@PathParam("userEmail") String userEmail) {
    	DatabaseConnection con = new DatabaseConnection();
        
    	ArrayList<User> userList = con.getUser(userEmail);
    	
    	ArrayList<JSONObject> objectList = new ArrayList<JSONObject>();
    	
    	for(int i = 0; i < userList.size(); i++){
    		JSONObject obj = new JSONObject();
    		obj.put("type", "user");
			obj.put("email", userList.get(i).getEmail());
			obj.put("area", userList.get(i).getArea());
			obj.put("dateOfBirth", userList.get(i).getDateOfBirth());
			obj.put("description", userList.get(i).getDescription());
			obj.put("employment", userList.get(i).getEmployment());
			obj.put("eventEmails", userList.get(i).getEventEmails());
			obj.put("favPartyMoment", userList.get(i).getFavPartyMoment());
			obj.put("name", userList.get(i).getName());
			obj.put("school", userList.get(i).getSchool());
		
    		
    		objectList.add(obj);
    	}
    	
    	System.out.println(objectList.get(0).toString());
    	return objectList;
    }
    
    //Gets event based on email
    @GET
    @Path("/event/{eventEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<JSONObject> getEvent(@PathParam("eventEmail") String eventEmail) {
    	DatabaseConnection con = new DatabaseConnection();
        
    	ArrayList<Event> eventList = con.getEvent(eventEmail);
    	
    	
    	
    	ArrayList<JSONObject> objectList = new ArrayList<JSONObject>();
    	
    	for(int i = 0; i < eventList.size(); i++){
    		JSONObject obj = new JSONObject();
    		obj.put("type", "event");
			obj.put("email", eventList.get(i).getEmail());
			obj.put("nameOfEvent", eventList.get(i).getNameOfEvent());
			obj.put("time", eventList.get(i).getTime());
			obj.put("description", eventList.get(i).getDescription());
			obj.put("privacy", eventList.get(i).getPrivacy());
			obj.put("maxGuests", eventList.get(i).getMaxGuests());
			obj.put("date", eventList.get(i).getDate());
			obj.put("guestEmails", eventList.get(i).getGuestEmails());
			obj.put("area", eventList.get(i).getArea());
			obj.put("location", eventList.get(i).getLocation());
    		
    		objectList.add(obj);
    	}
    	
    	System.out.println(objectList.get(0).toString());
    	return objectList;
    }
    
    @GET
    @Path("/event/area/{area}")
    @Produces(MediaType.APPLICATION_JSON)
    public  ArrayList<JSONObject> getEvents(@PathParam("area") String area){
    	DatabaseConnection con = new DatabaseConnection();
        
    	ArrayList<Event> eventList = con.getEvents(area);
    	System.out.println("EVENT LIST SIZE" + eventList.size());
    	
    	
    	
    	ArrayList<JSONObject> objectList = new ArrayList<JSONObject>();
    	
    	for(int i = 0; i < eventList.size(); i++){
    		JSONObject obj = new JSONObject();
    		obj.put("type", "event");
			obj.put("email", eventList.get(i).getEmail());
			obj.put("nameOfEvent", eventList.get(i).getNameOfEvent());
			obj.put("time", eventList.get(i).getTime());
			obj.put("description", eventList.get(i).getDescription());
			obj.put("privacy", eventList.get(i).getPrivacy());
			obj.put("maxGuests", eventList.get(i).getMaxGuests());
			obj.put("date", eventList.get(i).getDate());
			obj.put("guestEmails", eventList.get(i).getGuestEmails());
			obj.put("area", eventList.get(i).getArea());
			obj.put("location", eventList.get(i).getLocation());
    		
    		objectList.add(obj);
    	}
    	
    	
    	return objectList;
    }
    
    //Save a user to the database
    @POST
    @Path("save/user")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> postUser(
            MultivaluedMap<String, String> userParams
            ) {
         
        String email 			= userParams.getFirst("email");
        String school 			= userParams.getFirst("school");
        String employment 		= userParams.getFirst("employment");
        String favPartyMoment 	= userParams.getFirst("favPartyMoment");
        String name 			= userParams.getFirst("name");
        String description 		= userParams.getFirst("description");
        String dateOfBirth 		= userParams.getFirst("dateOfBirth");
        String events 			= userParams.getFirst("events");
        String area 			= userParams.getFirst("area");
        
        User newUser = new User(email, 
        		school, 
        		employment, 
        		favPartyMoment, 
        		name, 
        		description, 
        		dateOfBirth,
        		events, 
        		area);
        		
        
        System.out.println("Storing posted " + email + " " + school + "  " + employment + " " + 
        favPartyMoment + " " + name + " " + description + " " + dateOfBirth + " " + 
        		events + " " + area);
           
        DatabaseConnection con = new DatabaseConnection();
        con.saveUser(newUser);
        
        return new ArrayList<User>();
        
    }
    
    //Save an event to the database
    @POST
    @Path("save/event")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Event> postEvent(
            MultivaluedMap<String, String> eventParams
            ) {
         
        String email 			= eventParams.getFirst("email");
        String nameOfEvent 		= eventParams.getFirst("nameOfEvent");
        String dateAndTime 		= eventParams.getFirst("dateAndTime");
        String description	 	= eventParams.getFirst("description");
        String area 			= eventParams.getFirst("area");
        String location 		= eventParams.getFirst("location");
        String privacy	 		= eventParams.getFirst("privacy");
        String maxGuests 		= eventParams.getFirst("maxGuests");
        String guests 			= eventParams.getFirst("guests");
        
        Event newEvent = new Event(email, 
        		nameOfEvent, 
        		dateAndTime.split(" ")[0], 
        		SQLHelper.getTime(dateAndTime), 
        		description, 
        		area,
        		location, 
        		Integer.parseInt(privacy),
        		Integer.parseInt(maxGuests),
        		guests);
        		
        
        System.out.println("Storing posted " + email + " " + nameOfEvent + "  " + dateAndTime + " " + 
        description + " " + area + " " + location + " " + privacy + " " + 
        		maxGuests + " " + guests);
           
        DatabaseConnection con = new DatabaseConnection();
        con.saveEvent(newEvent);
        
        return new ArrayList<Event>();     
    }
    
    
}
