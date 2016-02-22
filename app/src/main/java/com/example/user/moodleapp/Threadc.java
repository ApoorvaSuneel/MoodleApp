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

public class Threadc extends AppCompatActivity {
    ImageButton im;
    public static int udone=0;
    public static ArrayList<String> threaddata1=new ArrayList<String>();
    private String jsonResponse;
    public static String[] arraythread;
    private ListView l;
    private static String JSON_URL ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadc);
        Toast.makeText(Threadc.this, Courses.Csel, Toast.LENGTH_SHORT).show();

        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvt);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Threadc.this, Profile.class);
                startActivity(myIntent);

            }
        });

        JSON_URL= LoginChoice.ip + "courses/course.json/cop290/threads";
        sendRequest();
    }

    private void sendRequest() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONArray glist =response.getJSONArray("course_threads");
                    String name="";
                    for (int i = 0; i < glist.length(); i++) {
                        JSONObject grades =(JSONObject) glist.get(i);
                        name = grades.getString("id")+"  "+grades.getString("title");
                        jsonResponse=name+jsonResponse;
                       // threaddata1.add("NAME   :"+"  "+grades.getString("name"));
                       // threaddata1.add("YOUR SCORE   :" + "  " + grades.getString("score"));
                        threaddata1.add("TITLE   :" + "  " + grades.getString("description"));
                       // threaddata1.add("ID   :" + "  " + grades.getString("title"));

                    }
                    Toast.makeText(Threadc.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    arraythread=threaddata1.toArray(new String[threaddata1.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Threadc.this,R.layout.list_view_layout,R.id.code,arraythread);
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
    public void clickcourse(View view) {
        Toast.makeText(getApplicationContext(),
                "click listener working.",
                Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(
                Threadc.this, ThreadExp.class);
       // myIntent.putExtra("id",arraygrade);
        startActivity(myIntent);


    }
}
