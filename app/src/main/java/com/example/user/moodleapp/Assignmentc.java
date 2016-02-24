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

public class Assignmentc extends AppCompatActivity {
    ImageButton im;
    TextView t1,t2;
    Button back;
    public static int udone=0;
    public static ArrayList<String> assgndata=new ArrayList<String>();
    public static String[] arrayassgn;
    public static String[] arrayassgn1;
    private ListView l;
    private static String JSON_URL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmentc);
        Toast.makeText(Assignmentc.this, Courses.Csel, Toast.LENGTH_SHORT).show();//toasting the name of the course

        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvac);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //image to move back to home
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Assignmentc.this, Profile.class);
                startActivity(myIntent);

            }
        });
        t1=(TextView)findViewById(R.id.textView6);
        t2=(TextView)findViewById(R.id.textView);
        t1.setText(LoginChoice.res[0]);
        t2.setText(LoginChoice.res[1]);
        JSON_URL= LoginChoice.ip + "courses/course.json/"+Courses.Csel+"/assignments";
        sendRequest();
    }

    private void sendRequest() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<String> ne=new ArrayList<String>();
                    JSONArray glist =response.getJSONArray("assignments");
                    //loop to take data from json objects
                    for (int i = 0; i < glist.length(); i++) {
                        JSONObject grades =(JSONObject) glist.get(i);
                        if(udone==0) {
                            assgndata.add("NAME   :\n" + "  " + grades.getString("name"));
                            ne.add("CREATED AT   :" + "  " + grades.getString("created_at"));
                            ne.add("DEADLINE   :" + "  " + grades.getString("deadline"));
                            ne.add("DESCRIPTION   :" + "  " + grades.getString("description"));
                        }
                    }
                    if(glist.length()>0){
                        udone=1;
                    }
                    //setting adapter for the list view
                    arrayassgn=assgndata.toArray(new String[assgndata.size()]);
                    arrayassgn1=ne.toArray(new String[ne.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Assignmentc.this,R.layout.list_view_layout,R.id.code,arrayassgn);
                    l.setAdapter(t);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    //error toast
                    Toast.makeText(getApplicationContext(),
                            "Assignment fetch erroneous" + e.getMessage(),
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
    //the click of any listitem triggers this method
    public void clickcourse(View view) {
        Intent myIntent = new Intent(Assignmentc.this, AssignmentPreview.class);
        myIntent.putExtra("assgn",arrayassgn1);
        startActivity(myIntent);
    }
}
