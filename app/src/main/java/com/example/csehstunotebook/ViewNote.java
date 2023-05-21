package com.example.csehstunotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewNote extends AppCompatActivity {
     Button addNote;

    NoteDatabaseSource source;
    ListView listView;
    ArrayList<NoteModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        addNote=findViewById(R.id.addnote);


        String ls=getIntent().getStringExtra("ls");
        String course=getIntent().getStringExtra("course");


        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewNote.this,AddNote.class);
                intent.putExtra("ls",ls);
                intent.putExtra("course",course);
                startActivity(intent);
            }
        });


        listView=findViewById(R.id.ListView);

        // (Kaj hobe na)  ArrayAdapter<NoteModel>arrayAdapter=new ArrayAdapter<NoteModel>();

        source=new NoteDatabaseSource(this,ls,course);
        arrayList=source.getAllNote();

        NoteAdapter noteAdapter=new NoteAdapter(this,arrayList);

        listView.setAdapter(noteAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NoteModel noteModel= arrayList.get(position);
                // noteModel.setId(position);
                Intent intent=new Intent(ViewNote.this,ViewOne.class);
                intent.putExtra("NOTE",noteModel);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        menu.setHeaderTitle("Delete Note");


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getItemId()==R.id.delete)
        {
            boolean status=source.deleteNote(arrayList.get(adapterContextMenuInfo.position));

            if(status)
            {
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Failed to Delete", Toast.LENGTH_SHORT).show();
            }

        }

        return super.onContextItemSelected(item);
    }
}

