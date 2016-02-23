package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class LoginChoice extends AppCompatActivity {

    Button b2;

    public static String ip="http://10.192.40.165:8000/";


    //public static String user,pass;
    private static String JSON_URL;
    public static ArrayList<String> logchoice=new ArrayList<String>();
    public static String[] res;
    private EditText username, password;
    public static String usernamestr, passwordstr,jsonResponse;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // public static String user,pass;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);
        b2 = (Button) findViewById(R.id.button2);
        username = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText);

        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                usernamestr = username.getText().toString();
                passwordstr = password.getText().toString();
                JSON_URL = ip + "default/login.json?userid=" + usernamestr + "&password=" + passwordstr;
                registerUser();

            }
        });



    }

    private void registerUser() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONObject u=response.getJSONObject("user");
                    String s=response.getString("success");

                    String name = "";

                        name = u.getString("first_name") + "  " + u.getString("last_name");
                        jsonResponse = "Hello "+ name ;
                        logchoice.add(u.getString("first_name"));
                        logchoice.add(u.getString("last_name"));
                        logchoice.add(u.getString("entry_no"));
                        logchoice.add(u.getString("email"));
                        logchoice.add(s);
                    res = logchoice.toArray(new String[logchoice.size()]);
                     if (s.equals("true"))
                     {
                         //define intent
                         Toast.makeText(LoginChoice.this,
                                 jsonResponse,
                                 Toast.LENGTH_LONG).show();
                         Intent myIntent = new Intent(
                                 LoginChoice.this, Profile.class);
                         myIntent.putExtra("data",res);
                         startActivity(myIntent);


                     }




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "INVALID LOGIN"+e.getMessage(),
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
