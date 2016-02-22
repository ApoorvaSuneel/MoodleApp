package com.example.user.moodleapp;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Gradec extends AppCompatActivity {
        ImageButton im;
        public static int udone = 0;
        public static ArrayList<String> gradata1 = new ArrayList<String>();
        private String jsonResponse;
        public static String[] arraygrade1;
        private ListView l;
        private static String JSON_URL;
        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */
        private GoogleApiClient client;


        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gradec);

            Toast.makeText(Gradec.this, Courses.Csel, Toast.LENGTH_SHORT).show();

<<<<<<< HEAD
            im = (ImageButton) findViewById(R.id.imageView);
            l = (ListView) findViewById(R.id.Lvgc);
            im.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent myIntent = new Intent(
                            Gradec.this, Profile.class);
                    startActivity(myIntent);

=======
                try {
                    gradata1.clear();
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
                        udone=0;//set 1 for static
                    }
                    Toast.makeText(Gradec.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    arraygrade1=gradata1.toArray(new String[gradata1.size()]);
                    ArrayAdapter<String> t =new ArrayAdapter<String>(Gradec.this,R.layout.list_view_layout,R.id.code,arraygrade1);
                    l.setAdapter(t);
>>>>>>> ca1d240050543b5348f064264c14a67dad88a172
                }
            });

            JSON_URL = LoginChoice.ip + "courses/course.json/" + Courses.Csel + "/grades";
            sendRequest();

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        }

        private void sendRequest() {
            JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                    JSON_URL, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {


                    try {

                        JSONArray glist = response.getJSONArray("grades");
                        String name = "";
                        for (int i = 0; i < glist.length(); i++) {
                            JSONObject grades = (JSONObject) glist.get(i);
                            name = grades.getString("id") + "  " + grades.getString("name");
                            jsonResponse = name + jsonResponse;
                            gradata1.add("NAME   :" + "  " + grades.getString("name"));
                            gradata1.add("YOUR SCORE   :" + "  " + grades.getString("score"));
                            gradata1.add("TOTAL   :" + "  " + grades.getString("out_of"));
                            gradata1.add("WEIGHTAGE   :" + "  " + grades.getString("weightage"));

                        }
                        Toast.makeText(Gradec.this,
                                jsonResponse,
                                Toast.LENGTH_LONG).show();
                        arraygrade1 = gradata1.toArray(new String[gradata1.size()]);
                        ArrayAdapter<String> t = new ArrayAdapter<String>(Gradec.this, R.layout.list_view_layout, R.id.code, arraygrade1);
                        l.setAdapter(t);
                    } catch (JSONException e) {
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

        @Override
        public void onStart() {
            super.onStart();

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client.connect();
            Action viewAction = Action.newAction(
                    Action.TYPE_VIEW, // TODO: choose an action type.
                    "Gradec Page", // TODO: Define a title for the content shown.
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
                    "Gradec Page", // TODO: Define a title for the content shown.
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
