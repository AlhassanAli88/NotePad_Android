package com.example.notepad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView mainListView;
    Button addBtn;





    DataManager dataManager = new DataManager();

    Boolean firstRun;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.main_add_btn);

        mainListView = findViewById(R.id.main_list_view);


        // added code starts here


        IsItFirstRun isItFirstRun = IsItFirstRun.getInstance();
        firstRun = isItFirstRun.getFirstRun();

        if (firstRun) {
            Context context = getApplicationContext();

            DataManager.DatabaseHelper dbHelper = dataManager.new DatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            db.execSQL("CREATE TABLE IF NOT EXISTS objects (id INTEGER PRIMARY KEY AUTOINCREMENT, object_data TEXT)");

            Cursor cursor = db.rawQuery("SELECT object_data FROM objects ORDER BY id ASC", null);


            while (cursor.moveToNext()) {
                @SuppressLint("Range") String json = cursor.getString(cursor.getColumnIndex("object_data"));
                Anteckningar anteckningar = dataManager.convertJsonToObject(json);
                dataManager.addAnteckning(anteckningar);
            }
            cursor.close();
            isItFirstRun.setFirstRun(false);



        }




        /*
        Cursor cursor = db.rawQuery("SELECT object_data FROM objects ORDER BY id ASC", null);
        List<MyObject> objectList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String json = cursor.getString(cursor.getColumnIndex("object_data"));
            MyObject myObject = convertJsonToObject(json);
            objectList.add(myObject);
        }
        cursor.close();



         */





        // Added code ends here
        MemberAdapter adapter = new MemberAdapter(this, DataManager.anteckningars );

        mainListView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this, "you pressed the button", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Add.class);
                //ska bort
               // Intent intent2 = new Intent(Intent.ACTION_DIAL);
                // ska bort
                startActivity(intent);
            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





                Intent intent1 = new Intent(MainActivity.this, Note.class);

                intent1.putExtra("position", position);

                startActivity(intent1);



                /*View temporaryView;
                temporaryView = LayoutInflater.from(adapter.getContext()).inflate(R.layout.activity_note, parent, false);

               Anteckningar anteckningar = adapter.getItem(position);
                String anteckningstitel = anteckningar.getTitle();
                String anteckningensText = anteckningar.getNote();



                TextView noteTitel = temporaryView.findViewById(R.id.note_titel);
                EditText noteAnteckning = temporaryView.findViewById(R.id.note_anteckning);

                noteTitel.setText(anteckningstitel);
                noteAnteckning.setText(anteckningensText);

                Toast.makeText(MainActivity.this, "clicked " + position, Toast.LENGTH_SHORT).show();

                 */





            }
        });







    }
}