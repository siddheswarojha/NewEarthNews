package com.siddheswar.newearthnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class RecyclerAdapterGenre extends RecyclerView.Adapter<RecyclerAdapterGenre.GenreViewHolder> {

    String[] title;
    String[] author;
    String[] links;
    String[] imageurl;
    String[] source;
    Context contextGenre;


    public RecyclerAdapterGenre(String[] title, String[] author, String[] source, Context contextGenre, String[] links, String[] imageurl) {
        this.title = title;
        this.author = author;
        this.links = links;
        this.imageurl = imageurl;
        this.contextGenre = contextGenre;
        this.source = source;

    }


    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_sample, parent, false);

        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GenreViewHolder holder, int position) {

        String info1 = title[position];
        String info2 = author[position];
        String info4 = links[position];
        String info3 = source[position];
        String info5 = imageurl[position];

        holder.txt1.setText(info1);
        holder.txt2.setText(info2);
        holder.txt3.setText(info3);
        holder.txt4.setText(info4);

        Picasso.with(contextGenre).load(info5).into(holder.img);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.txt4.getText().toString();
                Intent i = new Intent(contextGenre, WebNewsPortal.class);
                i.putExtra("urlvalue", s);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                contextGenre.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3, txt4;
        ImageView img;
        CardView card;


        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            img = itemView.findViewById(R.id.imgViewSample);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);
            txt4 = itemView.findViewById(R.id.txt4);
            card = itemView.findViewById(R.id.cardNews);
        }
    }
}
