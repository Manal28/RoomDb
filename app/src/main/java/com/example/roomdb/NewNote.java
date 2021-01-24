package com.example.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.roomdb.mydataBase.EntityClass;
import com.example.roomdb.mydataBase.NoteRoomDb;

import java.util.Date;

public class NewNote extends AppCompatActivity {

    EditText myNote ;
     public String note ,date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        myNote= (EditText)findViewById(R.id.your_note);
        note=myNote.getText().toString().trim();
        date=getDate().trim();

        try {
            myNote.setText(getIntent().getExtras().getString("myNote"));

        }

        catch (Exception ex){

            Log.w("Error",ex);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case (R.id.save):{
                saveData();
                Intent intent= new Intent(NewNote.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this,"Note saved",Toast.LENGTH_SHORT).show();
            }

                default:
                    return super.onOptionsItemSelected(item);

        }

    }

    public String  getDate (){

      Date mdate = new Date();
      date= mdate.toString();

      return date;

    }

    public void saveData(){

        NoteRoomDb.getInstance(this).noteDao().insert(new EntityClass(note,date));

    }
}
