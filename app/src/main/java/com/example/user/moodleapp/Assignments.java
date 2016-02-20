package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class Assignments extends AppCompatActivity {
    ImageButton im;
    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        im=(ImageButton)findViewById(R.id.imageView);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Assignments.this, Profile.class);
                startActivity(myIntent);

            }
        });

        sendRequest();
        l = (ListView) findViewById(R.id.lv);
        JSON_URL="http://10.192.40.165:8000/courses/course.json/cop290/assignments";

    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,JSON_URL,/*new HashMap<String,String>(),*/
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //CookieHandler.setDefault(new java.net.CookieManager());
                        Toast.makeText(Assignments.this, response, Toast.LENGTH_SHORT).show();
                        // showJSON(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Assignments.this, error.getMessage()+" NOT WORKING", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(stringRequest);

    }




}
