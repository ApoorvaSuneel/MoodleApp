package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class Grades extends AppCompatActivity {
    ImageButton im;
    public static int udone=0;
    private static ArrayList<String> mycourses=new ArrayList<>();
    public  static ArrayList<String> id=new ArrayList<String>();
    public static  ArrayList<String> gradata=new ArrayList<String>();
    private String jsonResponse;
    public static String[] arraygrade;

    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvg);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Grades.this, Profile.class);
                startActivity(myIntent);

            }
        });

        JSON_URL= LoginChoice.ip + "/default/grades.json";
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
                    JSONArray glist =response.getJSONArray("grades");
                    String name="";
                    for (int i = 0; i < clist.length(); i++) {

                        JSONObject coursee = (JSONObject) clist.get(i);
                        JSONObject grades =(JSONObject) glist.get(i);
                        gradata.add("COURSE   :" + "  " + coursee.getString("name"));
                        gradata.add("CREDITS   :" + "  " + coursee.getString("credits"));
                        gradata.add("L-T-P   :"+  "  "+coursee.getString("l_t_p"));
                        gradata.add("NAME   :"+"  "+grades.getString("name"));
                        gradata.add("YOUR SCORE   :"+"  "+grades.getString("score"));
                        gradata.add("TOTAL   :"+"  "+grades.getString("out_of"));
                        gradata.add("WEIGHTAGE   :"+"  "+grades.getString("weightage"));
                        if(id.contains(coursee.getString("id")))
                        {

                        }
                        else
                        {
                            id.add(coursee.getString("id"));
                            name = coursee.getString("id")+"  "+coursee.getString("name");
                            jsonResponse=name+jsonResponse;
                            if(udone==0){
                                mycourses.add(name);
                            }





                        }
                    }
                    String[] array1 = mycourses.toArray(new String[mycourses.size()]);
                    arraygrade=gradata.toArray(new String[gradata.size()]);
                    Toast.makeText(Grades.this,
                            jsonResponse,
                            Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Grades.this,R.layout.list_view_layout,R.id.code,array1);
                    l.setAdapter(t);


                    if (clist.length()>0){
                        udone=1;
                    }
                /*    l.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                            //Intent n = new Intent(getApplicationContext(), GradeView.class);
                            //sending data to new activity
                            // n.putExtra("position", position);
                            // startActivity(n);
                            Intent myIntent = new Intent(
                                    Grades.this, GradeView.class);
                            startActivity(myIntent);

                        }
                    });*/

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
    public void clickcourse(View view) {
        Intent myIntent = new Intent(
                Grades.this, GradeView.class);
        myIntent.putExtra("id",arraygrade);
        startActivity(myIntent);

    }
}

