package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
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
    TextView t1,t2;
    public static String Csel="default";
    private static int udone=0;
    public static ArrayList<String> mycourses=new ArrayList<String>();
    public static ArrayList<String> id=new ArrayList<String>();
    public static ArrayList<String> CCodes=new ArrayList<String>();
    private String jsonResponse;
    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lv);
        t1=(TextView)findViewById(R.id.textView6);
        t2=(TextView)findViewById(R.id.textView);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Courses.this, Profile.class);
                startActivity(myIntent);

            }
        });
        JSON_URL= LoginChoice.ip + "courses/list.json";
        t1.setText(LoginChoice.res[0]);
        t2.setText(LoginChoice.res[1]);
        sendRequest();
    }

    private void sendRequest() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
<<<<<<< HEAD
                    mycourses.clear();
                    CCodes.clear();



=======
>>>>>>> 2a2a7f0c6aa1068e7887a82fad8db472e3df6f6a
                    JSONArray clist = response.getJSONArray("courses");
                    for (int i = 0; i < clist.length(); i++) {

                        JSONObject coursee = (JSONObject) clist.get(i);
                        id.add(coursee.getString("id"));
                        String name = coursee.getString("id")+"  "+coursee.getString("name");
                        jsonResponse=name+jsonResponse;
                        if(udone==0){
                            mycourses.add(name);
                            CCodes.add(coursee.getString("code"));
                        }


                    }
                    String[] array1 = mycourses.toArray(new String[mycourses.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Courses.this,R.layout.list_view_layout,R.id.code,array1);
                    l.setAdapter(t);
                    //udone set to 1 for non repetative json results
                    if (clist.length()>0){
                        udone=1;//for static put 1
                    }
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

    ///method for listitem click
    public void clickcourse(View view) {
        Intent myIntent = new Intent(Courses.this, CourseP.class);
        Button tempb=(Button)view;
        String Sel= tempb.getText().toString();
        int ind=mycourses.indexOf(Sel);
        Csel=CCodes.get(ind);
        Toast.makeText(getApplicationContext(),
                Csel,
                Toast.LENGTH_SHORT).show();

        startActivity(myIntent);

    }
    }

