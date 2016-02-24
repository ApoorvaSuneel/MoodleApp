package com.example.user.moodleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AddComment extends AppCompatActivity {
    EditText desc;
    String cond;
    Button send;
    private static String JSON_URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        send = (Button) findViewById(R.id.button6);

        desc = (EditText) findViewById(R.id.editText5);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cond = desc.getText().toString();
                JSON_URL = LoginChoice.ip +"threads/post_comment.json?thread_id="+Threadc.Tsel+"&description="+cond;
                Toast.makeText(getApplicationContext(),
                        "yahoo",
                        Toast.LENGTH_SHORT).show();
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
                        "Sucessfully added comment",
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Couldnt add a comment.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(jreq);
    }

}
