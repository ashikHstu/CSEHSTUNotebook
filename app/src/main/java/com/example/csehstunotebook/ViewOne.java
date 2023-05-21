package com.example.csehstunotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewOne extends AppCompatActivity {

    int idd=0;
    EditText etTitle,etDate,etNote;
    Button updateBtn;
    NoteDatabaseSource noteDatabaseSource;
    NoteModel noteModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one);
        noteModel= (NoteModel)getIntent().getSerializableExtra("NOTE");

        String levelSemester=noteModel.getLevelSemester();
        String Course=noteModel.getCourseCode();
        noteDatabaseSource=new NoteDatabaseSource(this,levelSemester,Course);

        etTitle=findViewById(R.id.etTitle);
        etNote=findViewById(R.id.etNote);
        etDate=findViewById(R.id.etDate);
        updateBtn=findViewById(R.id.updateNote);



        if(noteModel!=null)
        {
            updateBtn.setText("Update Note");
            etTitle.setText(noteModel.getTitle());
            etDate.setText(noteModel.getDate()+"");
            etNote.setText(noteModel.getNote());
        }


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update
                if(noteModel!=null)
                {
                    String updatedTitle=etTitle.getText().toString();
                    String updateDate=etDate.getText().toString();
                    String updatedNote=etNote.getText().toString();

                    int id=noteModel.getId();

                    // Toast.makeText(MainActivity.this, String.valueOf(id)+" "+updatedTitle, Toast.LENGTH_SHORT).show();

                    NoteModel updatedNoteModel=new NoteModel(id,updatedTitle,updateDate,updatedNote,levelSemester,Course);
                    Boolean updatedStatus=noteDatabaseSource.updateNote(updatedNoteModel);

                    if(updatedStatus)
                    {
                        Toast.makeText(ViewOne.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        // Toast.makeText(MainActivity.this,"not Updated.",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }
}