package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginChoice extends AppCompatActivity {
    Button b2;
public static String ip="http://10.208.23.100:8000/";

    //public static String user,pass;
    private static String REGISTER_URL;
    private EditText username, password;
    public static String usernamestr, passwordstr;
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
        password = (EditText) findViewById(R.id.editText2);

        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                usernamestr = username.getText().toString();
                passwordstr = password.getText().toString();
                REGISTER_URL = ip+"default/login.json?userid=" + usernamestr + "&password=" + passwordstr;
                registerUser();

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void registerUser() {


        //send a request if data is valid
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginChoice.this, response, Toast.LENGTH_SHORT).show();

                        //CookieHandler.setDefault(new java.net.CookieManager());

                        Intent myIntent = new Intent(
                                LoginChoice.this, Profile.class);



                        startActivity(myIntent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginChoice.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Get a RequestQueue
        //RequestQueue queue = MyApp.getInstance(this.getApplicationContext()).
          //      getRequestQueue();

// ...

// Add a request (in this example, called stringRequest) to your RequestQueue.
        //MyApp.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(stringRequest);



    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LoginChoice Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.user.moodleapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LoginChoice Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.user.moodleapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
