package com.example.a150622androidt4rosell.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a150622androidt4rosell.PeliculaActivity;
import com.example.a150622androidt4rosell.Entities.Pelicula;
import com.example.a150622androidt4rosell.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ContactViewHolder> {
    List<Pelicula> anims;
    public PeliculaAdapter(List<Pelicula> anims) {
        this.anims = anims;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder vh, int position) {

        View itemView = vh.itemView;

        Pelicula pelicula = anims.get(position);

        TextView tvTitulo = itemView.findViewById(R.id.tvNombre);
        TextView tvResumen = itemView.findViewById(R.id.tvDescripcion);

        ImageView ivAvatar = itemView.findViewById(R.id.ivAvatar );


        tvTitulo.setText(pelicula.Nombre);
        tvResumen.setText(pelicula.Descripcion);
        //Integer.parseInt(tvContactRegion.setText(contact.Numero));
        //Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/"+contact.Numero+".svg").into(ivAvatar);
       Picasso.get().load(pelicula.Imagen).into(ivAvatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), PeliculaActivity.class);

                String contactJSON = new Gson().toJson(pelicula);
                intent.putExtra("CONTACT", contactJSON);
                //intent.putExtra("titulo", contact.Titulo);


                itemView.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return anims.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Pelicula pelicula = anims.get(i);
            Log.i("APP_VJ20202", "click en el elemento" + pelicula.Id);
        }
    }
}
