package com.example.notepad;

public class Anteckningar {


    String title;
    String note;
public Anteckningar(String title , String note){


    this.title = title;
    this.note = note;
}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return title + "\n" + note ;
    }
}
