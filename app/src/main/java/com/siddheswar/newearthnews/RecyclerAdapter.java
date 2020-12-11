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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecylerviewHolder> {

   private String[] data;
   private String[] data2;
   private String[] data3;
   private String[] data4;
   private String[] data5;

     Context context;


    public RecyclerAdapter(String[] data, String[] data2, String[] data3, Context context, String[] data4, String[] data5)
    {
        this.data= data;
        this.data2 = data2;
        this.data3 = data3;
        this.context = context;
        this.data4 = data4;
        this.data5 = data5;

    }
    @NonNull
    @Override
    public RecylerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_sample,parent,false);

        return new RecylerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecylerviewHolder holder, int position) {
        String title = data[position];
        String author = data2[position];
        String sources = data3[position];
        String link = data4[position];
        String image = data5[position];

        Picasso.with(context).load(image).into(holder.img);


        holder.txt1.setText(title);
        holder.txt2.setText(author);
        holder.txt3.setText(sources);
        holder.txt4.setText(link);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.txt4.getText().toString();
                Intent i = new Intent(context, WebNewsPortal.class);
                i.putExtra("urlvalue",s);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });


    }
    @Override
    public int getItemCount() {
        return data.length;
    }
    public class RecylerviewHolder extends RecyclerView.ViewHolder{
        TextView txt1,txt2,txt3,txt4;
        ImageView img;
        CardView card;
        public RecylerviewHolder(@NonNull View itemView) {
            super(itemView);
           txt1 = itemView.findViewById(R.id.txt1);
           img =  itemView.findViewById(R.id.imgViewSample);
           txt2 = itemView.findViewById(R.id.txt2);
           txt3 = itemView.findViewById(R.id.txt3);
           txt4 = itemView.findViewById(R.id.txt4);
           card = itemView.findViewById(R.id.cardNews);
        }
    }
}
