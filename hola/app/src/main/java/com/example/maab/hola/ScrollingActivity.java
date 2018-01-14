package com.example.maab.hola;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

public class ScrollingActivity extends AppCompatActivity {
    public final static String  key = "PROFILE";

    public ArrayList<Event> userNames = new ArrayList<Event>();



    User maab ;
    User josh ;
    User matt ;
    User mable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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





    }


}
