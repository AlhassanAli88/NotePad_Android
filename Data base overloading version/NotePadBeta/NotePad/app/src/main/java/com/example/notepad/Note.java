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


    /* MainActivity mainActivity = new MainActivity();
    int position = mainActivity.positionOfItem;
    String string = Integer.toString(position);



    */

    DataManager dataManager = new DataManager();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        //String string = Integer.toString(position);


        noteTitel = findViewById(R.id.note_titel);
        noteAnteckning = findViewById(R.id.note_anteckning);
        cancelBtn = findViewById(R.id.note_cancel);
        saveBtn = findViewById(R.id.note_save);
        //noteAnteckning.setText(string);

        Anteckningar anteckningar = dataManager.getItemByPosition(position);
        noteTitel.setText(anteckningar.getTitle());
        noteAnteckning.setText(anteckningar.getNote());

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
                Toast.makeText(Note.this, "Anteckningen har sparats", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Note.this, MainActivity.class);
                startActivity(intent);

            }
        });



    }
}