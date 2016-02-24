package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6;
    TextView t1,t2,t3,t4;
    String JSON_URL="";
    @Override
    //main profile of themoodleapp containing links to various other apps
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        b1=(Button)findViewById(R.id.grades);
        b2=(Button)findViewById(R.id.course);
        b4=(Button)findViewById(R.id.notify);
        b3=(Button)findViewById(R.id.files);
        b5=(Button)findViewById(R.id.logout);
        b6=(Button)findViewById(R.id.button7);
        t1=(TextView)findViewById(R.id.textView6);
        t3=(TextView)findViewById(R.id.textView1);
        t4=(TextView)findViewById(R.id.textView2);
        b1.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Profile.this,GradeView.class);
                startActivity(myIntent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Profile.this,Courses.class);
                startActivity(myIntent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        Profile.this,Notifications.class);
                startActivity(myIntent);
            }
        });
        //this button click leads to the file explorer of the phone to view your files
        b3.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
           {
               Toast.makeText(getApplicationContext(),
                       "View Your Files",
                       Toast.LENGTH_SHORT).show();

               Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.asus.filemanager");
               startActivity(launchIntent);

           }

        });
        //button present only in the profile for you to logout of the account
        b5.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                JSON_URL = LoginChoice.ip + "default/logout.json";
                registerUser();
            }

        });
        //leads to the calender of your phone to mark your deadlines and important dates
        b6.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),
                        "Set a Reminder for yourself",
                        Toast.LENGTH_SHORT).show();
                Intent launchIntent1 = getPackageManager().getLaunchIntentForPackage("com.google.android.calendar");
                startActivity(launchIntent1);
            }

        });
        //intent to get data from previous application
        Intent i=getIntent();
        String[] s=i.getStringArrayExtra("data");
        t1.setText(s[0]+" "+s[1]);
        t3.setText(s[2]);
        t4.setText(s[3]);
        }
        //executes for logging out of the profile
        private void registerUser() {
            JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //define intent
                        Toast.makeText(Profile.this,
                                " Successfully Logged Out ",
                                Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(
                                Profile.this, LoginChoice.class);
                        startActivity(myIntent);
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
