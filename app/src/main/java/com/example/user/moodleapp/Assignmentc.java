package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Assignmentc extends AppCompatActivity {
    ImageButton im;
    public static int udone=0;
    public static ArrayList<String> assgndata=new ArrayList<String>();
    private String jsonResponse;
    public static String[] arrayassgn;
    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmentc);
        Toast.makeText(Assignmentc.this, Courses.Csel, Toast.LENGTH_SHORT).show();

        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvac);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Assignmentc.this, Profile.class);
                startActivity(myIntent);

            }
        });

        JSON_URL= LoginChoice.ip + "courses/course.json/"+Courses.Csel+"/assignments";
        sendRequest();
    }

    private void sendRequest() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONArray glist =response.getJSONArray("assignments");
                    String name="";
                    for (int i = 0; i < glist.length(); i++) {
                        JSONObject grades =(JSONObject) glist.get(i);
                        name = "  "+grades.getString("name");
                        jsonResponse=name+jsonResponse;
                        if(udone==0) {
                            assgndata.add("NAME   :" + "  " + grades.getString("name"));
                            // assgndata.add("CREATED AT   :" + "  " + grades.getString("created_at"));
                            //assgndata.add("DEADLINE   :" + "  " + grades.getString("deadline"));
                            //assgndata.add("   :" + "  " + grades.getString("weightage"));
                        }
                    }
                    if(glist.length()>0){
                        udone=1;
                    }
                    Toast.makeText(Assignmentc.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    arrayassgn=assgndata.toArray(new String[assgndata.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Assignmentc.this,R.layout.list_view_layout,R.id.code,arrayassgn);
                    l.setAdapter(t);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: NOT WORKING yehi " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
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
