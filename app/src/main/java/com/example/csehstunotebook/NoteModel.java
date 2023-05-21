package com.example.csehstunotebook;


import java.io.Serializable;

public class NoteModel implements Serializable {
    int id;
    String title;
    String date;
    String note;
    String levelSemester;
    String courseCode;


    public NoteModel(int id, String title, String date, String note,String levelSemester,String courseCode) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.note = note;
        this.levelSemester=levelSemester;
        this.courseCode=courseCode;
    }

    public String getLevelSemester() {
        return levelSemester;
    }

    public void setLevelSemester(String levelSemester) {
        this.levelSemester = levelSemester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
