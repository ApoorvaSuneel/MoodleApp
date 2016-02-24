package com.example.user.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssignmentPreview extends AppCompatActivity {
     TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //function to preview the details of theassignmets
        setContentView(R.layout.activity_assignment_preview);//this class shows the preview page of assignments
        Intent i =getIntent();
        String[] product =i.getStringArrayExtra("assgn");
        t1=(TextView)findViewById(R.id.textView13);
        t2=(TextView)findViewById(R.id.textView14);
        t3=(TextView)findViewById(R.id.textView15);
        t1.setText(product[0]);
        t2.setText(product[1]);
        t3.setText(product[2]);


    }
}
