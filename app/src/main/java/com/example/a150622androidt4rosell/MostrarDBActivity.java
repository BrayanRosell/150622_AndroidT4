package com.example.a150622androidt4rosell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.a150622androidt4rosell.Adapter.PeliculaAdapter;
import com.example.a150622androidt4rosell.AppDataBase.AppDatabase;
import com.example.a150622androidt4rosell.Dao.Pelicula_DAO;
import com.example.a150622androidt4rosell.Entities.Pelicula;
import com.google.gson.Gson;

import java.util.List;

public class MostrarDBActivity extends AppCompatActivity {
  //  List<Anime> contacts = new ArrayList<>();
    SharedPreferences mSharedPref;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_dbactivity);

        db = AppDatabase.getDatabase(getApplicationContext());
        Pelicula_DAO dao = db.animeDAO();
        List<Pelicula> contacts = dao.getAll();
        Log.i("APP_VJ20202", "cadena:" + new Gson().toJson(contacts));
        PeliculaAdapter adapter = new PeliculaAdapter(contacts);
        RecyclerView rv = findViewById(R.id.rvContacts2);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }
}