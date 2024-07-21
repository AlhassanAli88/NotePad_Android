package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Note extends AppCompatActivity {

    TextView noteTitel;

    EditText noteAnteckning;

    Button cancelBtn;
    Button saveBtn;

    Button removeBtn;




    DataManager dataManager = new DataManager();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        //String string = Integer.toString(position);


        DataManager.DatabaseHelper dbHelper = dataManager.new DatabaseHelper(this);
       // SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();


        noteTitel = findViewById(R.id.note_titel);
        noteAnteckning = findViewById(R.id.note_anteckning);
        cancelBtn = findViewById(R.id.note_cancel);
        saveBtn = findViewById(R.id.note_save);
        removeBtn = findViewById(R.id.note_remove);
        //noteAnteckning.setText(string);

        Anteckningar anteckningar = dataManager.getItemByPosition(position);
        noteTitel.setText(anteckningar.getTitle());
        noteAnteckning.setText(anteckningar.getNote());
        Anteckningar oldAnteckningar = new Anteckningar(noteTitel.getText().toString(), noteAnteckning.getText().toString());



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Note.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                anteckningar.setNote(noteAnteckning.getText().toString());

                String JsonNew = dataManager.convertObjectToJson(anteckningar);

                String JsonOld = dataManager.convertObjectToJson(oldAnteckningar);




                updatedValues.put("object_data", JsonNew);
                db.update("objects",updatedValues, "object_data = ?", new String[]{JsonOld});




                Toast.makeText(Note.this, "Anteckningen har sparats", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Note.this, MainActivity.class);
                startActivity(intent);

            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataManager.anteckningars.remove(position);
                Toast.makeText(Note.this, "Anteckningen har raderats", Toast.LENGTH_SHORT).show();

                String searchJson = dataManager.convertObjectToJson(anteckningar);
                db.delete("objects", "object_data = ?", new String[]{searchJson});

                Intent intent = new Intent(Note.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}