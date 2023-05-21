package com.example.csehstunotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    int idd=0;
    EditText etTitle,etDate,etNote;
    Button addBtn;
    NoteDatabaseSource noteDatabaseSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        String ls=getIntent().getStringExtra("ls");
        String course=getIntent().getStringExtra("course");
        noteDatabaseSource=new NoteDatabaseSource(this,ls,course);

        etTitle=findViewById(R.id.etTitle);
        etNote=findViewById(R.id.etNote);
        etDate=findViewById(R.id.etDate);
        addBtn=findViewById(R.id.addNote);




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //insert


                NoteModel noteModel=new NoteModel(idd,etTitle.getText().toString(),etDate.getText().toString(),etNote.getText().toString(),ls,course);

                Boolean status=noteDatabaseSource.addNote(noteModel);
                if(status)
                {
                    etTitle.setText("");
                    etDate.setText("");
                    etNote.setText("");
                    Toast.makeText(AddNote.this,"saved",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddNote.this,"not Saved",Toast.LENGTH_SHORT).show();
                }




            }
        });



    }
}