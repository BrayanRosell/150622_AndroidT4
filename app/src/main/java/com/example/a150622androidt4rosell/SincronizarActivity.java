package com.example.a150622androidt4rosell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.a150622androidt4rosell.Adapter.PeliculaAdapter;
import com.example.a150622androidt4rosell.AppDataBase.AppDatabase;
import com.example.a150622androidt4rosell.Dao.Pelicula_DAO;
import com.example.a150622androidt4rosell.Entities.Pelicula;
import com.example.a150622androidt4rosell.Factories.RetrofitFactory;
import com.example.a150622androidt4rosell.Services.AnimeService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SincronizarActivity extends AppCompatActivity {
    List<Pelicula> peliculas = new ArrayList<>();
    SharedPreferences mSharedPref;
    AppDatabase db;
    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);

        db = AppDatabase.getDatabase(getApplicationContext());

        mSharedPref = getSharedPreferences("com.example.vargasrosell_t4.SHARED_PREFERENCES", Context.MODE_PRIVATE);

        String token = mSharedPref.getString("com.example.vargasrosell_t4.TOKEN", "");
        //Anime_DAO dao = db.animeDAO();
        //List<Anime> contacts = dao.getAll();
        Log.i("APP_VJ20202", "El token es:" + token);
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
                    peliculas = response.body();
                    Log.i("APP_VJ20202", "muestra:" + new Gson().toJson(peliculas));
                    saveInDatabase(peliculas);
                    Log.i("APP_VJ20202", "cadena:" + new Gson().toJson(peliculas));
                    PeliculaAdapter adapter = new PeliculaAdapter(peliculas);

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
    private void saveInDatabase(List<Pelicula> peliculas) {
        Log.i("APP_VJ20202", "llega:");
        Pelicula_DAO dao = db.animeDAO();

            for (Pelicula pelicula : peliculas) {
                /*if(cont == 0) {
                    dao.create(pelicula);
                    Log.i("APP_VJ20202", "cero:");
                }
                if (cont>0) {
                    dao.update(pelicula);
                    Log.i("APP_VJ20202", "uno:");
                Log.i("APP_VJ20202", "guardando:" + new Gson().toJson(pelicula));*/
                try {
                    dao.create(pelicula);
                    Log.i("APP_VJ20202", "cero:");
                }catch (Exception e){
                    dao.update(pelicula);
                    Log.i("APP_VJ20202", "uno:");

                }
            }
       // cont++;
    }
}