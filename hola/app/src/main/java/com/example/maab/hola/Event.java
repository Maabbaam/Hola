package com.example.maab.hola;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	
	//Attributes
	private String email; //Hosts email, Primary key
	private String nameOfEvent;
	private String time;
	private String description;
	private String area; //Used by the program
	private String location; //Address used by user

	private int privacy;
	private int maxGuests;
	
	private Date date;

	private ArrayList<String> guestEmails;
	
	//DefaultConstructor
	public Event(){
		
		this.email 			= "default@default.com";
		this.nameOfEvent 	= "default";
		this.time 			= "default";
		this.description 	= "default";
		
		this.privacy		= -1;
		this.maxGuests		= -1;
		
		this.date			= new Date();
		
		this.guestEmails	= new ArrayList<String>();
	}

	
	//Constructor
	public Event(String email, String nameOfEvent, Date date, String time, String description, String area, String location, int privacy, int maxGuests, ArrayList<String> guestEmails){
		
		this.email 			= email;
		this.nameOfEvent 	= nameOfEvent;
		this.time 			= time;
		this.description 	= description;
		this.location		= location;
		this.area 			= area;
		
		this.privacy 		= privacy;
		this.maxGuests		= maxGuests;
		
		this.date			= date;
		
		this.guestEmails	= guestEmails;
	}
	
	//Doers
	public boolean addGuest(String email){
		if(guestEmails.size() < maxGuests){
			guestEmails.add(email);
			return true;
		}
		return false;
	}
	
	//Getters Setters
	public String getNameOfEvent() {
		return nameOfEvent;
	}

	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumOfGuests() {
		return guestEmails.size();
	}

	public ArrayList<String> getGuestEmails() {
		return guestEmails;
	}

	public void setGuestEmail(ArrayList<String> guestEmail) {
		this.guestEmails = guestEmail;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	 
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public void setGuestEmails(ArrayList<String> guestEmails) {
		this.guestEmails = guestEmails;
	}

}
