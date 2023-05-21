package com.example.csehstunotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class VisitingSite extends AppCompatActivity {
    String current_ls,current_course;
    TextView backButton,copyLink,title;

    NoteDatabaseSource source;
    ListView listView;
    ArrayList<NoteModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_site);
        current_ls=getIntent().getStringExtra("ls");
        current_course=getIntent().getStringExtra("course");

        backButton=(TextView) findViewById(R.id.backButton);
         copyLink=(TextView)findViewById(R.id.copyLink);
          title=(TextView)findViewById(R.id.title);
          title.setText(current_course.toUpperCase());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // This is Add note button
                Intent intent=new Intent(VisitingSite.this,AddNote.class);
                intent.putExtra("ls",current_ls);
                intent.putExtra("course",current_course);
                startActivity(intent);
            }
        });




        listView=findViewById(R.id.ListView);

        // (Kaj hobe na)  ArrayAdapter<NoteModel>arrayAdapter=new ArrayAdapter<NoteModel>();

        source=new NoteDatabaseSource(this,current_ls,current_course);
        arrayList=source.getAllNote();

        NoteAdapter noteAdapter=new NoteAdapter(this,arrayList);

        listView.setAdapter(noteAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NoteModel noteModel= arrayList.get(position);
                // noteModel.setId(position);
                Intent intent=new Intent(VisitingSite.this,ViewOne.class);
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