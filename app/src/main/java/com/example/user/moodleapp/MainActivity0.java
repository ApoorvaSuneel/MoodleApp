package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity0 extends AppCompatActivity {
      int t=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(t==0)
                    {
                        Intent myIntent = new Intent(
                                MainActivity0.this,MainActivity1.class);
                        t=1;
                        startActivity(myIntent);
                    }
                    else
                    {
                        Intent myIntent1 = new Intent(
                                MainActivity0.this,LoginChoice.class);
                        startActivity(myIntent1);

                    }

                }
            }
        };
        timer.start();
    }
}
