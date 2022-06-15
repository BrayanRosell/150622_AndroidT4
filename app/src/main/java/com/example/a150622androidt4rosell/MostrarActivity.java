package com.example.a150622androidt4rosell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a150622androidt4rosell.Adapter.PeliculaAdapter;
import com.example.a150622androidt4rosell.AppDataBase.AppDatabase;
import com.example.a150622androidt4rosell.Entities.Pelicula;
import com.example.a150622androidt4rosell.Factories.RetrofitFactory;
import com.example.a150622androidt4rosell.Services.AnimeService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MostrarActivity extends AppCompatActivity {
    List<Pelicula> contacts = new ArrayList<>();
    SharedPreferences mSharedPref;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);


        db = AppDatabase.getDatabase(getApplicationContext());

        mSharedPref = getSharedPreferences("com.example.vargasrosell_t4.SHARED_PREFERENCES", Context.MODE_PRIVATE);

        String token = mSharedPref.getString("com.example.vargasrosell_t4.TOKEN", "");

        Log.i("APP_VJ20202", "El token es:" + token);
        FloatingActionButton btnRegister = findViewById(R.id.fab);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarActivity.this,RegistrarActivity.class);
                startActivity(intent);

            }
        });

        Retrofit retrofit = RetrofitFactory.build(this);
        AnimeService service = retrofit.create(AnimeService.class);
        Call<List<Pelicula>> call = service.getContacts();
        call.enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                if(!response.isSuccessful()) {
                    Log.e("APP_VJ20202", "Error de aplicación");
                } else {
                    Log.i("APP_VJ20202", "Respuesta Correcta");
                    contacts = response.body();
                    PeliculaAdapter adapter = new PeliculaAdapter(contacts);

                    RecyclerView rv = findViewById(R.id.rvContacts);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable t) {
                Log.e("APP_VJ20202", "No hubo conectividad con el servicio web");
            }
        });


    }
}