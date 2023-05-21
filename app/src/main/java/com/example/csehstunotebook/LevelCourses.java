package com.example.csehstunotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelCourses extends AppCompatActivity {
    Button cse101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_courses);
        cse101=(Button) findViewById(R.id.cse101);
       String levelSemester= getIntent().getStringExtra("ls");
        cse101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LevelCourses.this,ViewNote.class);
                intent.putExtra("ls",levelSemester);
                intent.putExtra("course","cse101");
                startActivity(intent);
            }
        });
    }
}