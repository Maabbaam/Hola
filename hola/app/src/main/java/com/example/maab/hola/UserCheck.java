package com.example.maab.hola;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.Date;
import java.util.ArrayList;




public class UserCheck extends AppCompatActivity {
    public final static String  key = "PROFILE";

    public EditText user;
    public ArrayList<String> userNames = new ArrayList<String>();


    User maab;
    User josh;
    User matt;
    User mable;
    @Override


    /*public User(String email, String school,
				String employment,
				String favPartyMoment,
				String name,
				String description,
				Date dateOfBirth,
				ArrayList<String> eventEmails,
				String location)*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check);



         maab = new User("maab.ahmed@gmail.com",
                           "Carleton",
                            "OGC",
                            "I did freestanding upside down yager bombs",
                            "Maab",
                            "Im a ...",
                            new Date(),
                            new ArrayList<String>(),
                            "Ottawa");

        josh = new User("maab.ahmed@gmail.com",
                "Carleton",
                "OGC",
                "I did freestanding upside down yager bombs",
                "Josh",
                "Im a ...",
                new Date(),
                new ArrayList<String>(),
                "Ottawa");

        matt = new User("maab.ahmed@gmail.com",
                "Carleton",
                "OGC",
                "I did freestanding upside down yager bombs",
                "Matt",
                "Im a ...",
                new Date(),
                new ArrayList<String>(),
                "Ottawa");

        mable = new User("maab.ahmed@gmail.com",
                "Carleton",
                "OGC",
                "I did freestanding upside down yager bombs",
                "Mabel",
                "Im a ...",
                new Date(),
                new ArrayList<String>(),
                "Ottawa");



        userNames.add(maab.getName());
        userNames.add(matt.getName());
        userNames.add(josh.getName());
        userNames.add(mable.getName());

        System.out.println(userNames.get(2));

        user = (EditText) findViewById(R.id.editText);

    }

    public void checkUser(View view) {

        String name = user.getText().toString();
/*
        DatabaseInterface db = new DatabaseInterface("172.17.154.39");
       ArrayList<User> u =  db.getUser("asdf@asdf.com");
       System.out.println(u.get(0).getDescription());*/

        /*

		this.email 			= email;
		this.nameOfEvent 	= nameOfEvent;
		this.time 			= time;
		this.description 	= description;
		this.location		= location;
		this.area 			= area;

		this.privacy 		= privacy;
		this.maxGuests		= maxGuests;

		this.date			= date;

		this.guestEmails	= guestEmails;*/


        Event e = new Event(
                "maab.ahmed@gmail.com",
                "nameOfEvent",
                new Date(2015,4,5),
                "11:11:11", //xx:yy:zz
                "i do upside down yager bombs",
                "ont",
                "ottawa",
                2,3,
                new ArrayList<String>()


        );

      //        db.saveEvent(e);

        //ArrayList<Event> p = db.getEvent("maab.ahmed@gmail.com");







        if (userNames.contains(name)) {


            Intent homeAct = (new Intent(this, homeActivity.class));
            homeAct.putExtra(key,name);
            startActivity(homeAct);


        } else {
            System.out.println("REREER");

        }
    }
}