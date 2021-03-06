package com.example.a150622androidt4rosell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a150622androidt4rosell.AppDataBase.AppDatabase;
import com.example.a150622androidt4rosell.Dao.Pelicula_DAO;
import com.example.a150622androidt4rosell.Entities.Pelicula;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mSharedPref;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mSharedPref = getPreferences(Context.MODE_PRIVATE);
        String token = mSharedPref.getString("com.example.vargasrosell_t4.TOKEN","");

        //Button btnRegistrar = findViewById(R.id.btnNuevo);
        Button btnMostrar = findViewById(R.id.btnMostrar);
        Button btnSincronizar = findViewById(R.id.btnSincronizar);
        Button btnMostrarDB = findViewById(R.id.btnMostrarDB);

        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = AppDatabase.getDatabase(getApplicationContext());
                Pelicula_DAO dao = db.animeDAO();

//        Contact contact = new Contact();
//        contact.name = "brayan rosell";
//        contact.number = "90909838";
//
//        dao.create(contact);


                List<Pelicula> contacts = dao.getAll();
                Log.i("APP_VJ20202", "cadena:" + new Gson().toJson(contacts));
                Intent intent = new Intent(MainActivity.this,SincronizarActivity.class);
                startActivity(intent);

            }
        });
        /*btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrarActivity.class);
                startActivity(intent);
            }
        });*/
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MostrarActivity.class);
                startActivity(intent);
            }
        });
        btnMostrarDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MostrarDBActivity.class);
                startActivity(intent);
            }
        });
    }

}