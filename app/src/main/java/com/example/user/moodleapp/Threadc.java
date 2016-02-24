package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
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
    ImageButton im,nt;
    Button back;
    TextView t1,t2;
    public static String Tsel="Default";
    public static int udone=0;
    public static ArrayList<String> threaddata1=new ArrayList<String>();
    public static ArrayList<String> threadid=new ArrayList<String>();
    private String jsonResponse;
    public static String[] arraythread;
    private ListView l;
    private static String JSON_URL ;

     //creates the thread activity which takes care of the threads related to your course
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadc);
        Toast.makeText(Threadc.this, Courses.Csel, Toast.LENGTH_SHORT).show();

        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvt);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //this image view button activity goes back to your profile
        //serves as link to your profile
        //present in all the activities of the app
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Threadc.this, Profile.class);
                startActivity(myIntent);

            }
        });
        nt=(ImageButton)findViewById(R.id.imageView1);
        nt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Threadc.this, NewThread.class);
                startActivity(myIntent);

            }
        });
         t1=(TextView)findViewById(R.id.textView6);
         t2=(TextView)findViewById(R.id.textView);
         t1.setText(LoginChoice.res[0]);
         t2.setText(LoginChoice.res[1]);
         //Cse1 is the course code pblic and static in nature
        JSON_URL= LoginChoice.ip + "courses/course.json/"+Courses.Csel+"/threads";
        sendRequest();
    }

    private void sendRequest() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    threadid.clear();
                    threaddata1.clear();
                    JSONArray glist =response.getJSONArray("course_threads");
                    String name="";
                    for (int i = 0; i < glist.length(); i++)
                    {
                        JSONObject grades =(JSONObject) glist.get(i);
                        name = grades.getString("id")+"  "+grades.getString("title");
                        jsonResponse=name+jsonResponse;
                        if(udone==0)
                        {
                            threaddata1.add(grades.getString("title"));
                            threadid.add(grades.getString("id"));
                        }
                    }
                    if(glist.length()>0)
                    {
                        udone=0;//set 1 for static
                    }
                    //toasting the thread you make
                    Toast.makeText(Threadc.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    //setting the array adapter
                    arraythread=threaddata1.toArray(new String[threaddata1.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Threadc.this,R.layout.list_view_layout,R.id.code,arraythread);
                    l.setAdapter(t);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: NOT ABLE TO POST THE THREAD" + e.getMessage(),
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
    //clicking on any thread which is a list item leads you to the page where you can comment those
    public void clickcourse(View view) {
        Button tempb=(Button)view;
        String Sel= tempb.getText().toString();
        int ind=threaddata1.indexOf(Sel);
        Tsel=threadid.get(ind);
        Toast.makeText(getApplicationContext(),
                Tsel,
                Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(
                Threadc.this, ThreadExp.class);
        startActivity(myIntent);


    }
}
