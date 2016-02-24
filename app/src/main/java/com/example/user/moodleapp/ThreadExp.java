package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ThreadExp extends AppCompatActivity {
    TextView title;
    ListView l;
    Button reply;
    private static String jsonResponse="";
    public static ArrayList<String> comlist=new ArrayList<>();
    private static String JSON_URL;
    @Override
    //activity to expanding the thread view
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_exp);
        title=(TextView)findViewById(R.id.textView11);
        l=(ListView)findViewById(R.id.listView);
        reply=(Button)findViewById(R.id.button5);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(
                        ThreadExp.this, AddComment.class);
                startActivity(myIntent);
            }
        });
        title=(TextView)findViewById(R.id.textView12);
        JSON_URL= LoginChoice.ip + "threads/thread.json/"+Threadc.Tsel;
        sendRequest();
    }
    private void sendRequest() {

        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    comlist.clear();
                    // Parsing json object response
                    //json response for commenting on the expanded thread
                    JSONObject tdesc=response.getJSONObject("thread");
                    title.setText(tdesc.getString("description"));
                    JSONArray clist = response.getJSONArray("comments");
                    for (int i = 0; i < clist.length(); i++) {
                        JSONObject coursee = (JSONObject) clist.get(i);
                        String com = coursee.getString("description");
                        jsonResponse=com+jsonResponse;
                        comlist.add(com);
                    }
                    String[] array1 = comlist.toArray(new String[comlist.size()]);
                    Toast.makeText(ThreadExp.this,
                            jsonResponse,
                            Toast.LENGTH_LONG).show();
                    ArrayAdapter<String> t =new ArrayAdapter<String>(ThreadExp.this,R.layout.list_view_layout,R.id.code,array1);
                    l.setAdapter(t);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Thread comment fetching NOT WORKING " + e.getMessage(),
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

}
