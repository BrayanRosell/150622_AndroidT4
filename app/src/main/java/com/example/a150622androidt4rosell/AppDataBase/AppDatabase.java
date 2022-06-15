package com.example.a150622androidt4rosell.AppDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a150622androidt4rosell.Dao.Pelicula_DAO;
import com.example.a150622androidt4rosell.Entities.Pelicula;

@Database(entities = {Pelicula.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Pelicula_DAO animeDAO();

    public static AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "com.example.androidvj20221.database.contacts.db")
                .allowMainThreadQueries()
                .build();
    }
}
