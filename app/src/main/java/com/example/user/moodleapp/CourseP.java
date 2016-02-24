package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class CourseP extends AppCompatActivity {
    ImageButton im;
    TextView t1,t2;
    Button back;
    Button asnb,threadb,gradeb;
    TextView codec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_p);
        //bind Image button
        im=(ImageButton)findViewById(R.id.imageView);
        t1=(TextView)findViewById(R.id.textView6);
        t2=(TextView)findViewById(R.id.textView);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        im.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(
                        CourseP.this, Profile.class);
                startActivity(myIntent);

            }
        });
        t1.setText(LoginChoice.res[0]);
        t2.setText(LoginChoice.res[1]);
        //bind the text view
        codec=(TextView)findViewById(R.id.textView2);
        String ccode =Courses.Csel;
        codec.setText(ccode);
        //bind the content buttons
        asnb = (Button) findViewById(R.id.button);
        asnb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(
                        CourseP.this, Assignmentc.class);
                startActivity(myIntent);
            }
        });


        //following activity shows the threads in the course
        threadb=(Button) findViewById(R.id.button4);
        threadb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(
                        CourseP.this, Threadc.class);
                startActivity(myIntent);
            }
        });

        //to show the grades in the course
        gradeb=(Button) findViewById(R.id.button3);
        gradeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(
                        CourseP.this, Gradec.class);
                startActivity(myIntent);
            }
        });

    }
}
