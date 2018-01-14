package com.example.maab.hola;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

public class DatabaseInterface{

    private  String SERVICE_URL;
    private ArrayList<User>  returnUsers;
    private ArrayList<Event> returnEvents;

    public DatabaseInterface(String webServerIP){
        returnUsers = new ArrayList<User>();
        returnEvents = new ArrayList<Event>();

        SERVICE_URL = "http://" + webServerIP + ":8080/home/rest/items";
    }




    //Given an email retrieves a user from the db
    public ArrayList<User> getUser(String email){

        returnUsers = new ArrayList<User>();
        String sampleURL = SERVICE_URL + "/user/" + email;

        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, "GETting data...");

        try {
            handleResponse(wst.execute(new String[]{sampleURL}).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        while(returnUsers.size() < 1){

        }

        return returnUsers;
    }

    //Given an email retrieves an event from the db
    public ArrayList<Event> getEvent(String email){

        returnEvents = new ArrayList<Event>();
        String sampleURL = SERVICE_URL + "/event/" + email;

        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, "GETting data...");

        try {
            handleResponse(wst.execute(new String[]{sampleURL}).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        while(returnEvents.size() < 1){

        }

        return returnEvents;
    }

    //Given an email retrieves an event from the db
    public ArrayList<Event> getEvents(String area){

        returnEvents = new ArrayList<Event>();
        String sampleURL = SERVICE_URL + "/event/area/" + area;

        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, "GETting data...");

        try {
            handleResponse(wst.execute(new String[]{sampleURL}).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        while(returnEvents.size() < 1){

        }

        return returnEvents;
    }

    //Saves a user to the database
    public void saveUser(User user){


        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, "Posting data...");

        wst.addNameValuePair("email", user.getEmail());
        wst.addNameValuePair("school", user.getSchool());
        wst.addNameValuePair("employment", user.getEmployment());
        wst.addNameValuePair("favPartyMoment", user.getFavPartyMoment());
        wst.addNameValuePair("name", user.getName());
        wst.addNameValuePair("description", user.getDescription());
        wst.addNameValuePair("dateOfBirth", SQLHelper.dateOfBirthToString(user.getDateOfBirth()));
        wst.addNameValuePair("events", SQLHelper.arrayListToString(user.getEventEmails()));
        wst.addNameValuePair("area", user.getArea());

        System.out.println("saving");
        // the passed String is the URL we will POST to
        wst.execute(new String[] { SERVICE_URL + "/save/user" });


    }

    //Saves an event
    public void saveEvent(Event event){


        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, "Posting data...");

        wst.addNameValuePair("email", event.getEmail());
        wst.addNameValuePair("nameOfEvent", event.getNameOfEvent());
        wst.addNameValuePair("dateAndTime", SQLHelper.dateOfBirthToString(event.getDate()) + " " + event.getTime());
        wst.addNameValuePair("description", event.getDescription());
        wst.addNameValuePair("area", event.getArea());
        wst.addNameValuePair("location", event.getLocation());
        wst.addNameValuePair("privacy", "" + event.getPrivacy());
        wst.addNameValuePair("maxGuests", "" + event.getMaxGuests());
        wst.addNameValuePair("guests", SQLHelper.arrayListToString(event.getGuestEmails()));

        System.out.println("saving");
        // the passed String is the URL we will POST to
        wst.execute(new String[] { SERVICE_URL + "/save/event" });


    }



    private void handleResponse(String response) {

        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Event> events = new ArrayList<Event>();

        try {

            JSONArray array = new JSONArray(response);
            JSONObject jso;

            if(((JSONObject)array.get(0)).getString("type").equals("user")) {

                for (int i = 0; i < array.length(); i++) {
                    jso = (JSONObject) array.get(i);
                    String email = jso.getString("email");
                    String school = jso.getString("school");
                    String employment = jso.getString("employment");
                    String favPartyMoment = jso.getString("favPartyMoment");
                    String name = jso.getString("name");
                    String description = jso.getString("description");
                    String area = jso.getString("area");
                    String dateOfBirth = jso.getString("dateOfBirth");
                    String eventEmails = jso.getString("eventEmails");

                    System.out.println("\n\n\n\n Round i: " + email + "\n" + school + "\n" +
                            employment + "\n" + favPartyMoment + "\n" + name + "\n" +
                            description + "\n" + area + "\n" + dateOfBirth +
                            eventEmails.length() + "\n\n\n\n\n\n");

                    User user = new User(email, school, employment, favPartyMoment,
                            name, description, SQLHelper.getDateOfBirth(dateOfBirth),
                            SQLHelper.emailsToArrayList(eventEmails), area);

                    users.add(user);
                }
                returnUsers = users;
            } else if (((JSONObject)array.get(0)).getString("type").equals("event")){
                for (int i = 0; i < array.length(); i++) {
                    jso = (JSONObject) array.get(i);
                    String email = jso.getString("email");
                    String nameOfEvent = jso.getString("nameOfEvent");
                    String time = jso.getString("time");
                    String description = jso.getString("description");
                    int privacy = jso.getInt("privacy");
                    int maxGuests = jso.getInt("maxGuests");
                    String date = jso.getString("date");
                    String guestEmails = jso.getString("guestEmails");
                    String area = jso.getString("area");
                    String location = jso.getString("location");

                    System.out.println("\n\n\n\n Round i: " + email + "\n" + nameOfEvent + "\n" +
                            time + "\n" + description + "\n" + privacy + "\n" +
                            maxGuests + "\n" + date + "\n" + guestEmails +
                            area + "\n" + location + "\n\n\n\n\n\n");

                    Event event = new Event(email, nameOfEvent, SQLHelper.getDateOfBirth(date), time,
                            description, area, location,
                            privacy, maxGuests, SQLHelper.emailsToArrayList(guestEmails));

                    events.add(event);
                }
                returnEvents = events;
            }

        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n YOU DELETED MEEEEEEE \n\n\n\n\n");
            e.printStackTrace();
        }



    }












    private class WebServiceTask extends AsyncTask<String, Integer, String> {

        public static final int POST_TASK = 1;
        public static final int GET_TASK = 2;

        private static final String TAG = "WebServiceTask";

        // connection timeout, in milliseconds (waiting to connect)
        private static final int CONN_TIMEOUT = 3000;

        // socket timeout, in milliseconds (waiting for data)
        private static final int SOCKET_TIMEOUT = 5000;

        private int taskType = GET_TASK;
        private String processMessage = "Processing...";

        private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();


        public WebServiceTask(int taskType, String processMessage) {

            this.taskType = taskType;
            this.processMessage = processMessage;
        }

        public void addNameValuePair(String name, String value) {

            params.add(new BasicNameValuePair(name, value));
        }

        @Override
        protected void onPreExecute() {

        }

        protected String doInBackground(String... urls) {

            String url = urls[0];
            String result = "";

            HttpResponse response = doResponse(url);

            if (response == null) {
                return result;
            } else {

                try {

                    result = inputStreamToString(response.getEntity().getContent());

                } catch (IllegalStateException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);

                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                }

            }

            return result;
        }

        @Override
        protected void onPostExecute(String response) {


        }

        // Establish connection and socket (data retrieval) timeouts
        private HttpParams getHttpParams() {

            HttpParams htpp = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
            HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);

            return htpp;
        }

        private HttpResponse doResponse(String url) {

            // Use our connection and data timeouts as parameters for our
            // DefaultHttpClient
            HttpClient httpclient = new DefaultHttpClient(getHttpParams());

            HttpResponse response = null;

            try {
                switch (taskType) {

                    case POST_TASK:
                        HttpPost httppost = new HttpPost(url);
                        // Add parameters
                        httppost.setEntity(new UrlEncodedFormEntity(params));

                        response = httpclient.execute(httppost);
                        break;
                    case GET_TASK:
                        HttpGet httpget = new HttpGet(url);
                        response = httpclient.execute(httpget);
                        break;
                }
            } catch (Exception e) {

                Log.e(TAG, e.getLocalizedMessage(), e);

            }

            return response;
        }

        private String inputStreamToString(InputStream is) {

            String line = "";
            StringBuilder total = new StringBuilder();

            // Wrap a BufferedReader around the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                // Read response until the end
                while ((line = rd.readLine()) != null) {
                    total.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }

            // Return full string
            return total.toString();
        }

    }
}