package com.example.maab.hola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EventCreation extends AppCompatActivity {
    public final static String  key = "PROFILE";
    Event thisEvent;
     String userName;

    public EditText eventNam;
    public EditText eventCity;
    public EditText eventAddress;
    public EditText eventDescription;
    public EditText eventMaxGuests;
    public EditText eventTime;


//DatabaseInterface g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);

        userName = getIntent().getStringExtra(key);


        eventNam = (EditText) findViewById(R.id.edEventName);
        eventCity = (EditText) findViewById(R.id.edCity);
        eventAddress = (EditText) findViewById(R.id.edAddress);
        eventDescription = (EditText) findViewById(R.id.edDes);
        eventMaxGuests = (EditText) findViewById(R.id.edNumGuests);
        eventTime = (EditText) findViewById(R.id.edTime);


    }

/*

public Event(String email, String nameOfEvent, Date date, String time, String description, String area, String location, int privacy, int maxGuests, ArrayList<String> guestEmails)
 */
    public void makeEvent(){


        thisEvent = new Event(
        );
        //g.saveEvent(e);




    }

}
