package com.example.csehstunotebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="note.db";
    public static final int DATABASE_VERSION=1;

    public static final String NOTE_TABLE="note_table";

    public static final String COL_ID="_id";   /// primary key
    public static final String COL_TITLE="title";
    public static final String COL_DATE="date";
    public static final String COL_NOTE="note";
    public static final String COL_ls="ls";
    public static final String COL_course="course";

  //  public static final String CREATE_TABLE= "create table "+NOTE_TABLE+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_TITLE+" TEXT, "+COL_DATE+" TEXT, "+COL_NOTE+" TEXT "+")";
    public static final String CREATE_TABLE= "create table "+NOTE_TABLE+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_TITLE+" TEXT, "+COL_DATE+" TEXT, "+COL_NOTE+" TEXT, "+COL_ls+" TEXT ,"+COL_course+" TEXT "+")";

    public NoteDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+NOTE_TABLE);
        this.onCreate(sqLiteDatabase);
    }
}
