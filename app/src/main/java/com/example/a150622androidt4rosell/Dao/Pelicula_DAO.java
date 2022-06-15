package com.example.a150622androidt4rosell.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.a150622androidt4rosell.Entities.Pelicula;

import java.util.List;

@Dao
public interface Pelicula_DAO {
    @Query("SELECT * FROM peliculas")
    List<Pelicula> getAll();

    @Query("SELECT * FROM peliculas WHERE id = :id")
    Pelicula find(int id);

    @Insert
    void create(Pelicula contact);

    @Update
    void update(Pelicula contact);
}
