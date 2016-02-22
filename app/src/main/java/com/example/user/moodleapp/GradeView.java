package com.example.user.moodleapp;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GradeView extends AppCompatActivity {
    ImageButton im;

    private ListView l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_view);
        im=(ImageButton)findViewById(R.id.imageView);
        l=(ListView)findViewById(R.id.lvgv);
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        GradeView.this, Profile.class);
                startActivity(myIntent);

            }
        });
        //TextView txtProduct = (TextView) findViewById(R.id.product_label);

        Intent i = getIntent();
        // getting attached intent data
        String[] product = i.getStringArrayExtra("id");
        // displaying selected product name
        ArrayAdapter<String> t =new ArrayAdapter<String>(GradeView.this,R.layout.list_view_layout,R.id.code,product);
        l.setAdapter(t);




    }

}
