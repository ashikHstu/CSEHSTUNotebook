package com.example.csehstunotebook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<NoteModel> {
    Context context;

    ArrayList<NoteModel> arrayList;

    public NoteAdapter(@NonNull Context context, ArrayList<NoteModel> arrayList) {
        super(context,R.layout.single_note_row, arrayList);
        this.context=context;
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);


        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.single_note_row,parent,false);
        TextView nameTV= v.findViewById(R.id.title);
        TextView ageTV=v.findViewById(R.id.date);
        TextView addressTV= v.findViewById(R.id.note);


        nameTV.setText(arrayList.get(position).getTitle());
        //ageTV.setText(String.valueOf(arrayList.get(position).getDate()));
        ageTV.setText(arrayList.get(position).getDate());
        addressTV.setText(arrayList.get(position).getNote());


        return v;
    }
}
