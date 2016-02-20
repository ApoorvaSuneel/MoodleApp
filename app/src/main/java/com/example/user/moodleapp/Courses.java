package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Method;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.user.moodleapp.ParseJSON.*;

public class Courses extends AppCompatActivity {
    private ListView l;
    ImageButton im;
    private static String JSON_URL ;
    private static String REGISTER_URL_LOGIN;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        im=(ImageButton)findViewById(R.id.imageView);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Courses.this, Profile.class);
                startActivity(myIntent);

            }
        });


        //sendRequest();
        l = (ListView) findViewById(R.id.lv);
        REGISTER_URL_LOGIN = "http://10.192.40.165:8000/default/login.json?userid=cs1110200&password=john";
         JSON_URL="http://10.192.40.165:8000/courses/list.json";
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void sendRequest() {
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET,REGISTER_URL_LOGIN,/*new HashMap<String,String>(),*/
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //CookieHandler.setDefault(new java.net.CookieManager());
                       // Toast.makeText(Courses.this, response, Toast.LENGTH_SHORT).show();
                        // showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(Courses.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        StringRequest stringRequest = new StringRequest(Request.Method.GET,JSON_URL,/*new HashMap<String,String>(),*/
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //CookieHandler.setDefault(new java.net.CookieManager());
                        Toast.makeText(Courses.this, response, Toast.LENGTH_SHORT).show();
                       //showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Courses.this, error.getMessage()+"NOT WORKING", Toast.LENGTH_LONG).show();
                    }
                });

        //RequestQueue requestQueue = Volley.newRequestQueue(this);
      //  RequestP.add(stringRequest);
        // Get a RequestQueue
       // RequestQueue queue = MyApp.getInstance(this.getApplicationContext()).
         //       getRequestQueue();

// ...

// Add a request (in this example, called stringRequest) to your RequestQueue.
       // MyApp.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(stringRequest);

    }

    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this,pj.codes, pj.names, pj.descriptions,pj.credits,pj.ids,pj.ltps);
        l.setAdapter(cl);
    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Courses Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.user.moodleapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Courses Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.user.moodleapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
