package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ThreadExp extends AppCompatActivity {
    TextView title;
    ListView l;
    Button reply;
    @Override
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
        
    }
}
