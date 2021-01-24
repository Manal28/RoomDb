package com.example.roomdb.mydataBase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="NotesTable")
 public class EntityClass {

    @PrimaryKey(autoGenerate = true)
    private  int _ID;
    private  String _NOTE;
    private  String _DATE ;

    public EntityClass(String _NOTE, String _DATE) {
        this._NOTE = _NOTE;
        this._DATE = _DATE;
    }

    public int get_ID()
    {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String get_NOTE() {
        return _NOTE;
    }

    public void set_NOTE(String _NOTE) {
        this._NOTE = _NOTE;
    }

    public String get_DATE() {
        return _DATE;
    }

    public void set_DATE(String _DATE) {
        this._DATE = _DATE;
    }
}
