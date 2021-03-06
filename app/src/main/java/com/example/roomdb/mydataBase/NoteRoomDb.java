package com.example.roomdb.mydataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities={EntityClass.class},version =1,exportSchema = false)

public abstract class NoteRoomDb extends RoomDatabase {

    private static NoteRoomDb instance;

    public abstract MyDao noteDao();

    // Singlton(not to repeat more than one version from the same Db)
    public static synchronized NoteRoomDb getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDb.class, "NOTE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();

        }
        return instance;
    }


}



