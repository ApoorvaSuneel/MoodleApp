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

public class Gradec extends AppCompatActivity {
    ImageButton im;
    public static int udone=0;
    public static  ArrayList<String> gradata1=new ArrayList<String>();
    private String jsonResponse;
    public static String[] arraygrade1;
    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradec);
        Toast.makeText(Gradec.this, Courses.Csel, Toast.LENGTH_SHORT).show();

        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvgc);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Gradec.this, Profile.class);
                startActivity(myIntent);

            }
        });

        JSON_URL= LoginChoice.ip + "courses/course.json/"+Courses.Csel+"/grades";
        sendRequest();

    }

    private void sendRequest() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONArray glist =response.getJSONArray("grades");
                    String name="";
                    for (int i = 0; i < glist.length(); i++) {
                        JSONObject grades =(JSONObject) glist.get(i);
                        name = grades.getString("id")+"  "+grades.getString("name");
                        jsonResponse=name+jsonResponse;
                        if(udone==0) {
                            gradata1.add("NAME   :" + "  " + grades.getString("name"));
                            gradata1.add("YOUR SCORE   :" + "  " + grades.getString("score"));
                            gradata1.add("TOTAL   :" + "  " + grades.getString("out_of"));
                            gradata1.add("WEIGHTAGE   :" + "  " + grades.getString("weightage"));
                        }
                    }
                    if(glist.length()>0){
                        udone=1;
                    }
                    Toast.makeText(Gradec.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    arraygrade1=gradata1.toArray(new String[gradata1.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Gradec.this,R.layout.list_view_layout,R.id.code,arraygrade1);
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
