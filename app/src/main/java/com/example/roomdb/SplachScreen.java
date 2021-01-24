package com.example.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplachScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        setSplachScreen();

    }

    public void setSplachScreen (){

        Thread backRound = new Thread() {


            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(SplachScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    Log.w("Error",e);

                }
            }
        };
        backRound.start();
    }
}
