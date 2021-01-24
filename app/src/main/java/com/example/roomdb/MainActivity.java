package com.example.roomdb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import com.example.roomdb.mydataBase.EntityClass;
import com.example.roomdb.mydataBase.MyAdapter;
import com.example.roomdb.mydataBase.NoteRoomDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    RecyclerView list;
   List<EntityClass> arr;
    RecyclerView.LayoutManager manager;
   MyAdapter adapter;



    public MainActivity() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setFab();
        setRecycler();



    }



    public void setFab() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent myIntent = new Intent(MainActivity.this,NewNote.class);
             startActivity(myIntent);
            }
        });
    }

    public  void initViews () {
        arr = new ArrayList<EntityClass>();
        arr = NoteRoomDb.getInstance(this).noteDao().getAllNotes();
        adapter= new MyAdapter(this,arr);
        add = (FloatingActionButton) (findViewById(R.id.add_btn));
        list = (RecyclerView) (findViewById(R.id.my_list));
    }

    public void setRecycler(){
        manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        list.setHasFixedSize(true);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()){

           case (R.id.Delete):
               NoteRoomDb.getInstance(this).noteDao().deleteAllNOtes(arr);
                arr.clear();
                adapter.notifyDataSetChanged();
       }
        return super.onOptionsItemSelected(item);


    }


}
