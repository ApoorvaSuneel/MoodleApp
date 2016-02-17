package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent myIntent = new Intent(
                            MainActivity0.this,MainActivity1.class);
                    startActivity(myIntent);
                }
            }
        };
        timer.start();
    }
}
