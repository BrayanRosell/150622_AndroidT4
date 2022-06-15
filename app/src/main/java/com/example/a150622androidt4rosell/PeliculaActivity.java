package com.example.a150622androidt4rosell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a150622androidt4rosell.Entities.Pelicula;
import com.example.a150622androidt4rosell.Factories.RetrofitFactory;
import com.example.a150622androidt4rosell.Services.AnimeService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        String contactJson = getIntent().getStringExtra("CONTACT");
        Pelicula peli = new Gson().fromJson(contactJson, Pelicula.class);
        Log.i("APP_VJ20202", "llega hasat a qui");

        TextView Nombre = findViewById(R.id.editNombre);
        TextView Descripcion = findViewById(R.id.editDescripcion);
        TextView Imagen = findViewById(R.id.editUrlImage);

        Nombre.setText(peli.Nombre);
        Descripcion.setText(peli.Descripcion);
        Imagen.setText(peli.Imagen);



        Button edit = findViewById(R.id.btnEditar);
        Button btnDelete = findViewById(R.id.btnEliminar);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = Nombre.getText().toString();
                String descripcion = Descripcion.getText().toString();
                String imagen = Imagen.getText().toString();
                peli.Nombre = nombre;
                peli.Descripcion = descripcion;
                peli.Imagen = imagen;
                Retrofit retrofit = RetrofitFactory.build(PeliculaActivity.this);
                AnimeService service = retrofit.create(AnimeService.class);
                Call<Pelicula> call = service.edit(peli.Id,peli);
                call.enqueue(new Callback<Pelicula>() {
                    @Override
                    public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {

                    }

                    @Override
                    public void onFailure(Call<Pelicula> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(PeliculaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build(PeliculaActivity.this);
                AnimeService service = retrofit.create(AnimeService.class);
                Call<Pelicula> call = service.delete(peli.Id);
                call.enqueue(new Callback<Pelicula>() {
                    @Override
                    public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {

                    }

                    @Override
                    public void onFailure(Call<Pelicula> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(PeliculaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}