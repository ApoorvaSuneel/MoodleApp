package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Gradec extends AppCompatActivity {

        ImageButton im;
        TextView t1,t2;
        Button back;
        public static int udone = 0;
        public static ArrayList<String> gradata11 = new ArrayList<String>();
        public static ArrayList<String> gradata1 = new ArrayList<String>();//array collecting data
        private ListView l;
        private static String JSON_URL;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gradec);
            Toast.makeText(Gradec.this, " Grades of  : "+Courses.Csel, Toast.LENGTH_SHORT).show();
            im = (ImageButton) findViewById(R.id.imageView);
            t1=(TextView)findViewById(R.id.textView6);
            t2=(TextView)findViewById(R.id.textView);
            t1.setText(LoginChoice.res[0]);
            t2.setText(LoginChoice.res[1]);
            l = (ListView) findViewById(R.id.Lvgc);
            back=(Button)findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            //button intent to move back to profile page
            im.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent myIntent = new Intent(
                            Gradec.this, Profile.class);
                    startActivity(myIntent);
                }});
            JSON_URL = LoginChoice.ip + "courses/course.json/" + Courses.Csel + "/grades";
            sendRequest();
        }

        private void sendRequest() {
            JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray glist1 = response.getJSONArray("grades");
                        for (int i = 0; i < glist1.length(); i++)
                        {
                            JSONObject grades1 = (JSONObject) glist1.get(i);
                            if (udone==0)
                            {
                                gradata11.add("NAME   : " + "  " + grades1.getString("name"));
                                gradata1.add("YOUR SCORE   : " + "  " + grades1.getString("score"));
                                gradata1.add("TOTAL   : " + "  " + grades1.getString("out_of"));
                                gradata1.add("WEIGHTAGE   : " + "  " + grades1.getString("weightage"));

                            }

                        }
                        if(glist1.length()>0)
                        {
                            udone=1;
                        }

                       String[] arraygrade1 = gradata11.toArray(new String[gradata11.size()]);
                        ArrayAdapter<String> t1 = new ArrayAdapter<String>(Gradec.this, R.layout.list_view_layout, R.id.code, arraygrade1);
                        l.setAdapter(t1);  //set the adapter
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "  Error occurred fetching your grades  ",
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
