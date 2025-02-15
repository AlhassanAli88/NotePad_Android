package com.example.notepad;

import com.google.gson.Gson;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;


public class DataManager {

    public static ArrayList<Anteckningar> anteckningars = new ArrayList<>();


    public Anteckningar createNote(String title, String note, Context context) {

        Anteckningar anteckning = new Anteckningar(title, note);
        anteckningars.add(anteckning);


        DatabaseHelper dbHelper  = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("object_data", convertObjectToJson(anteckning));
        db.insert("objects", null, values);
        db.close();



        return anteckning;
    }

    public void addAnteckning(Anteckningar anteckningen){

        anteckningars.add(anteckningen);

    }



    public Anteckningar getItemByPosition(int position) {
        return anteckningars.get(position);
    }


    public String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public Anteckningar convertJsonToObject(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Anteckningar.class);
    }







    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "YourDatabaseName";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_NAME = "objects";
        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, object_data TEXT);";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Handle database schema upgrades here
        }
    }
}


