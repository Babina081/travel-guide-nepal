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
import com.example.bmenudemo.viewHolder.FeaturedPlaceViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FeaturedPlaceAdapter extends RecyclerView.Adapter<FeaturedPlaceViewHolder> {

    List<PlaceListModel> data;
    List<PlaceListModel> dataFull;
    Context context;

    public FeaturedPlaceAdapter() {
    }

    public FeaturedPlaceAdapter(List<PlaceListModel> data, Context context) {
        this.data = data;
        this.context = context;
        dataFull = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public FeaturedPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_featured_places, parent, false);
        return new FeaturedPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedPlaceViewHolder holder, int position) {
        final PlaceListModel example = data.get(position);

        holder.txtFeatname.setText(example.getPlacename());
//        holder.description.setText(example.getDescription());
        holder.txtFeatlocation.setText(example.getPlacelocation());
        Glide.with(holder.imgFeat.getContext()).load(Constants.URL_GLIDE_IMAGE + example.getPhoto()).into(holder.imgFeat);
        holder.txtlikecountFeat.setText(example.getLikes());
        /*setsetOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, example.getPlacename() + " Liked", Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.cardViewFeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), example.getPlacename() + " Clicked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, PlaceDetailActivity.class);
                intent.putExtra("photo", example.getPhoto());
                intent.putExtra("placename", example.getPlacename());
                intent.putExtra("description", example.getDescription());
                intent.putExtra("placelocation", example.getPlacelocation());
                intent.putExtra("likes", example.getLikes());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
