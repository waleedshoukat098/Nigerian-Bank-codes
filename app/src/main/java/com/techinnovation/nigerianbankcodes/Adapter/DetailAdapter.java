package com.techinnovation.nigerianbankcodes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.techinnovation.nigerianbankcodes.Activites.CodeDetailAct;
import com.techinnovation.nigerianbankcodes.Activites.DetailActivity;
import com.techinnovation.nigerianbankcodes.Model.DetailModel;
import com.techinnovation.nigerianbankcodes.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.Viewholder_detail> {
Context context;
ArrayList<DetailModel>list;

    public DetailAdapter(Context context, ArrayList<DetailModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder_detail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.card_design,parent,false);
        return new Viewholder_detail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder_detail holder, int position) {
        DetailModel model=list.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.text.setText(model.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(context, CodeDetailAct.class);
             intent.putExtra("key",model.getTitle());
             context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder_detail extends RecyclerView.ViewHolder {
ImageView imageView;
TextView text;
CardView cardView;

        public Viewholder_detail(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cardimage);
            text=itemView.findViewById(R.id.titleop);
            cardView=itemView.findViewById(R.id.carddesign);
        }
    }
}