package com.example.bmenudemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.PlaceDetailActivity;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.PlaceListModel;
import com.example.bmenudemo.viewHolder.PlaceViewHolder;
import com.example.bmenudemo.viewHolder.RecentPlaceViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecentPlaceAdapter extends RecyclerView.Adapter<RecentPlaceViewHolder>  {

    List<PlaceListModel> rdata;
//    List<PlaceListModel> dataFull;
    Context context;

    public RecentPlaceAdapter() {
    }

    public RecentPlaceAdapter(List<PlaceListModel> rdata, Context context) {
        this.rdata = rdata;
        this.context = context;
//        dataFull = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public RecentPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_recent, parent, false);
        return new RecentPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecentPlaceViewHolder holder, int position) {
        final PlaceListModel example = rdata.get(position);

        holder.recentPlacename.setText(example.getPlacename());
        Glide.with(holder.recentPlaceimage.getContext()).load(Constants.URL_GLIDE_IMAGE + example.getPhoto()).into(holder.recentPlaceimage);
        holder.likecountRecent.setText(example.getLikes());

        holder.cardViewRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),example.getPlacename()+" Clicked",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, PlaceDetailActivity.class);
                intent.putExtra("photo", example.getPhoto());
                intent.putExtra("placename", example.getPlacename());
                intent.putExtra("description", example.getDescription());
                intent.putExtra("placelocation", example.getPlacelocation());
                intent.putExtra("likes",example.getLikes());
                context.startActivity(intent);

            }
        });

        //favourite system
        holder.islikedRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
