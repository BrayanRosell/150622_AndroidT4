package com.example.a150622androidt4rosell.Services;

import com.example.a150622androidt4rosell.Entities.Pelicula;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface AnimeService {
    @GET("contacts")
    retrofit2.Call<List<Pelicula>> getContacts();

    // contacts/:id
    @GET("contacts/{id}")
    retrofit2.Call<Pelicula> findContact(@Path("id") int id);

    @POST("contacts")
    retrofit2.Call<Pelicula> create(@Body Pelicula contact);

    @DELETE("contacts/{id}")
    retrofit2.Call<Pelicula> delete(@Path("id") int Id);
    @PUT("contacts/{id}")
    retrofit2.Call<Pelicula> edit(@Path("id") int Id, @Body Pelicula contact);
}
