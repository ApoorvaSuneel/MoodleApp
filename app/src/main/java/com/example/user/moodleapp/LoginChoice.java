package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginChoice extends AppCompatActivity {
Button b2;
    //public static String user,pass;
    private static String REGISTER_URL;
     private EditText username,password;
    public static String usernamestr,passwordstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // public static String user,pass;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);
        b2=(Button)findViewById(R.id.button2);
        username  = (EditText )findViewById(R.id.editText3);
        password=(EditText)findViewById(R.id.editText2);

        b2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v) {
                usernamestr = username.getText().toString();
                passwordstr = password.getText().toString();
                REGISTER_URL="http://10.192.40.165:8000/default/login.json?userid="+usernamestr+"&password="+passwordstr;
                                  registerUser();

                          }
        });


    }
    private void registerUser() {


        //send a request if data is valid
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginChoice.this, response, Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(
                                LoginChoice.this,Profile.class);
                        startActivity(myIntent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginChoice.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue RequestP = Volley.newRequestQueue(this);
        RequestP.add(stringRequest);


    }

}
