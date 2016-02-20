package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Courses extends AppCompatActivity {
    ImageButton im;
    private static ArrayList<String> mycourses=new ArrayList<>();
    private static ArrayList<String> id=new ArrayList<String>();
    private String jsonResponse;
    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lv);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Courses.this, Profile.class);
                startActivity(myIntent);

            }
        });
        JSON_URL= LoginChoice.ip + "courses/list.json";
        sendRequest();

    }

    private void sendRequest() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    JSONArray clist = response.getJSONArray("courses");
                    for (int i = 0; i < clist.length(); i++) {

                        JSONObject coursee = (JSONObject) clist.get(i);
                        id.add(coursee.getString("id"));
                        String name = coursee.getString("id")+"  "+coursee.getString("name");
                        jsonResponse=name+jsonResponse;
                        mycourses.add(name);


                    }
                    String[] array1 = mycourses.toArray(new String[mycourses.size()]);
                    Toast.makeText(Courses.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Courses.this,R.layout.list_view_layout,R.id.code,array1);
                    l.setAdapter(t);

                   /* CustomList cl = new CustomList(Courses.this,array1);//, pj.descriptions,pj.credits,pj.ids,pj.ltps);
                    l.setAdapter(cl);*/

                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: NOT WORKING " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(jreq);


    }







}
