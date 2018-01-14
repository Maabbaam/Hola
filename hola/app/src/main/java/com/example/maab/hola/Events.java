package com.example.maab.hola;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class Events extends AppCompatActivity {
    public final static String  key = "PROFILE";
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);

        userName = getIntent().getStringExtra(key);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       //setSupportActionBar(toolbar);


    }

    public void createEvent(View v){

        Intent proAct = (new Intent(this, EventCreation.class));
        proAct.putExtra(key, userName);
        startActivity(proAct);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.makeEvent) {
            Intent proAct = (new Intent(this, EventCreation.class));
            proAct.putExtra(key, userName);
            startActivity(proAct);
            return true;
        }
        return super.onOptionsItemSelected(item);
        }

}
