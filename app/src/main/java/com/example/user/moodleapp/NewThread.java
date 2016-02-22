package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class NewThread extends AppCompatActivity {
    ImageButton send;
    EditText title, desc;
    String cont, cond;
    private static String JSON_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thread);
        send = (ImageButton) findViewById(R.id.imageButton);

        title = (EditText) findViewById(R.id.editText);
        desc = (EditText) findViewById(R.id.editText4);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cont = title.getText().toString();
                cond = desc.getText().toString();
                JSON_URL = LoginChoice.ip + "threads/new.json?title=" + cont + "&description=" + cond + "&course_code=" + Courses.Csel;
                sendRequest();
            }
        });
    }

    private void sendRequest() {
        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET,
                JSON_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),
                        "Sucessfully Created a Thread",
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Couldnt Create a Thread.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(jreq);
    }
}