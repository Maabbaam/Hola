package com.example.maab.hola;

import java.util.ArrayList;
import java.util.Date;

public class User {

	private String email;
	private String school;
	private String employment;
	private String favPartyMoment;
	private String name;
	private String description;
	private String area; // city
	
	private Date   dateOfBirth;
	
	private ArrayList<String> eventEmails;
	
	public User(String email){
		this.email = email;
		
		//Defaults
		dateOfBirth 	= new Date();
		school 			= "default school";
		employment 		= "default employment";
		favPartyMoment 	= "default favPartyMoment";
		name	 		= "default nameOfUser";
		description 	= "default description";
		area			= "default area";
		
		eventEmails 		= new ArrayList<String>();
	}
	
	public User(String email, String school,
				String employment,
				String favPartyMoment,
				String name,
				String description,
				Date dateOfBirth,
				ArrayList<String> eventEmails,
				String location)

	{
		this.email = email;
		
		//Defaults
		this.dateOfBirth 	= dateOfBirth;
		this.school 		= school;
		this.employment 	= employment;
		this.favPartyMoment = favPartyMoment;
		this.name 			= name;
		this.description 	= description;
		this.area			= location;
		
		this.eventEmails 	= new ArrayList<String>();
	}
	
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	//Doers
	public void addToEvent(String eventEmail){
		eventEmails.add(eventEmail);
	}
	
	public void removeEvent(String eventEmail){
		for(int i = 0; i < eventEmails.size(); i++){
			if(eventEmails.get(i) == eventEmail){
				eventEmails.remove(i);
				break;
			}
		}
	}
	
	//Getters
	public String getEmail(){
		return email;
	}
	
	public Date getDateOfBirth(){
		return dateOfBirth;
	}
	
	public String getSchool(){
		return school;
	}
	
	public String getFavPartyMoment(){
		return favPartyMoment;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmployment(){
		return employment;
	}
	public String getDescription(){
		return description;
	}
	
	public ArrayList<String> getEventEmails(){
		return eventEmails;
	}
	
	
	//Setters
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public void setSchool(String school){
		this.school = school;
	}
	
	public void setFavPartyMoment(String favPartyMoment){
		this.favPartyMoment = favPartyMoment;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setEventEmails(ArrayList<String> eventEmails){
		this.eventEmails = eventEmails;
	}
	
	public void setEmployment(String employment){
		this.employment = employment;
	}
}
