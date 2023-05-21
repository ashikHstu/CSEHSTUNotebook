package com.example.csehstunotebook;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NoteDatabaseSource {



    NoteDatabaseHelper noteDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    String ls;
    String course;
    //StudentModel studentModel;
    public NoteDatabaseSource(Context context,String ls,String course)
    {
        noteDatabaseHelper=new NoteDatabaseHelper(context);
        this.ls=ls;
        this.course= course;
    }


    public void open()
    {
        sqLiteDatabase =noteDatabaseHelper.getWritableDatabase();
    }

    public void close()
    {
        noteDatabaseHelper.close();
    }
    public boolean addNote(NoteModel noteModel)
    {
        this.open();
        ContentValues contentValues=new ContentValues();

        contentValues.put(NoteDatabaseHelper.COL_TITLE,noteModel.getTitle());
        contentValues.put(NoteDatabaseHelper.COL_DATE,noteModel.getDate());
        contentValues.put(NoteDatabaseHelper.COL_NOTE,noteModel.getNote());
        contentValues.put(NoteDatabaseHelper.COL_ls,noteModel.getLevelSemester());
        contentValues.put(NoteDatabaseHelper.COL_course,noteModel.getCourseCode());
        //  contentValues.put(StudentDatabaseHelper.COL_ID,studentModel.getId());

        Long insertedRow=sqLiteDatabase.insert(NoteDatabaseHelper.NOTE_TABLE,null,contentValues);


        this.close();
        if(insertedRow>0)
        {
            return true;
        }
        else return false;
    }

    public boolean updateNote(NoteModel noteModel)
    {
        this.open();
        ContentValues contentValues=new ContentValues();

        contentValues.put(NoteDatabaseHelper.COL_TITLE,noteModel.getTitle());
        contentValues.put(NoteDatabaseHelper.COL_DATE,noteModel.getDate());
        contentValues.put(NoteDatabaseHelper.COL_NOTE,noteModel.getNote());

        int updatedRow= sqLiteDatabase.update(NoteDatabaseHelper.NOTE_TABLE,contentValues,NoteDatabaseHelper.COL_ID+" =?",new String[]{String.valueOf(noteModel.getId())});

        this.close();
        if(updatedRow>0)return true;
        else return false;
    }

    public ArrayList<NoteModel> getAllNote()
    {
        this.open();
        ArrayList<NoteModel>arrayList=new ArrayList<>();

        /** To sort in a specific order :
         *
         * String sortOrder =
         *     NoteDatabaseHelper.COL_ID + " DESC";
         */
        Cursor cursor=sqLiteDatabase.query(NoteDatabaseHelper.NOTE_TABLE,null,null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do {

                String name= cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COL_TITLE));
                String age=cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COL_DATE));
                String address=cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COL_NOTE));
                int id= cursor.getInt(cursor.getColumnIndex(NoteDatabaseHelper.COL_ID));
                  String sls=cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COL_ls));
                  String scourse=cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COL_course));

                NoteModel noteModel=new NoteModel(id,name,age,address,sls,scourse);
                if(ls.equals(sls) && course.equals(scourse) )
                {

                    arrayList.add(noteModel);
                }



            }while (cursor.moveToNext());
        }

        this.close();
        cursor.close();
        return arrayList;
    }


    public boolean deleteNote(NoteModel model)
    {
        this.open();
        int deletedRow=sqLiteDatabase.delete(NoteDatabaseHelper.NOTE_TABLE,NoteDatabaseHelper.COL_ID+" =?",new String[]{String.valueOf(model.getId())});
        this.close();

        if(deletedRow>0)return true;
        else return false;

    }


}