package com.example.maab.hola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class ProfileActivity extends AppCompatActivity {
    public final static String  key = "PROFILE";
       public ArrayList<String> userNames = new ArrayList<String>();
    public HashMap<String,User> userPro = new HashMap<String,User> ();

    User thisUser;

    User maab ;
    User josh ;
    User matt ;
    User mable ;

    public TextView nameAge;
    public TextView favPartyMom;
    public TextView area;
    public TextView followings;
    public TextView followers;

    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);\


        userName = getIntent().getStringExtra(key);

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

        userPro.put(maab.getName(), maab);
        userPro.put(josh.getName(), josh);
        userPro.put(matt.getName(), matt);
        userPro.put(mable.getName(), mable);


      thisUser = userPro.get(userName);



        nameAge = (TextView)  findViewById(R.id.nameAndAge);
        favPartyMom = (TextView)  findViewById(R.id.favPartyMoment);
        area = (TextView)  findViewById(R.id.area);
        followings = (TextView)  findViewById(R.id.followings);
        followers = (TextView)  findViewById(R.id.followers);

        nameAge.setText(thisUser.getName());
        favPartyMom.setText(thisUser.getFavPartyMoment());
        area .setText(thisUser.getArea());
        followings.setText("Following 0");
        followers.setText("Followers 0");






    }



}
