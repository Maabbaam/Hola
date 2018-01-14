package com.example.maab.hola;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;




public class homeActivity extends AppCompatActivity {

    public final static String  key = "PROFILE";
    public ArrayList<String> userNames = new ArrayList<String>();
    String userName;


    User thisUser;

    User maab ;
    User josh ;
    User matt ;
    User mable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        userName =  getIntent().getStringExtra(key);




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



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        else if (id == R.id.events){

           Intent eventAct = (new Intent(this, Events.class));
            eventAct.putExtra(key, userName);
            startActivity(eventAct);
            return true;
        }

        else if (id == R.id.profile){
            Intent proAct = (new Intent(this, ProfileActivity.class));
            proAct.putExtra(key, userName);
            startActivity(proAct);

            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
