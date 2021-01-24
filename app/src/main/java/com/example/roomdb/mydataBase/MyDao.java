package com.example.roomdb.mydataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface MyDao {

    @Insert
    void insert(EntityClass note);
    @Delete
    void deleteAllNOtes ( List<EntityClass> notes);
    @Update
    void update(EntityClass note);

    @Query("SELECT * From NotesTable")
   List<EntityClass> getAllNotes();

@Query("DELETE FROM NotesTable WHERE _ID =:id")

    void deleteById (int id);


}
