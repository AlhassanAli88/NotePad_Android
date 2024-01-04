package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Add extends AppCompatActivity {

    EditText newTitle;
    EditText newNote;

    Button addBtn;

    Button cancelBtn;
    DataManager dataManager = new DataManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        newTitle = findViewById(R.id.add_titel);
        newNote = findViewById(R.id.add_anteckning);
        addBtn = findViewById(R.id.add_save);
        cancelBtn = findViewById(R.id.add_cancel);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Add.this,MainActivity.class);
                startActivity(intent);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = newTitle.getText().toString();
                String note = newNote.getText().toString();

                if (title.isEmpty()|| note.isEmpty()){


                    Toast.makeText(Add.this, "Titel eller anteckning saknas", Toast.LENGTH_SHORT).show();

                }

                else {

                    Context context = getApplicationContext();

                    Anteckningar anteckningar = dataManager.createNote(title,note, context);
                    Intent intent = new Intent(Add.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}