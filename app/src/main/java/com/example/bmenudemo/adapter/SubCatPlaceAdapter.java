package com.example.bmenudemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.CallBackCheckLike;
import com.example.bmenudemo.activities.HandleLikeDislike;
import com.example.bmenudemo.activities.homePageActivities.PlaceDetailActivity;
import com.example.bmenudemo.activities.homePageActivities.SessionManager;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.PlaceListModel;
import com.example.bmenudemo.viewHolder.PlaceViewHolder;
import com.example.bmenudemo.viewHolder.SubCatPlaceViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SubCatPlaceAdapter extends RecyclerView.Adapter<SubCatPlaceViewHolder> {

    List<PlaceListModel> data;
    List<PlaceListModel> dataFull;
    Context context;


    public SubCatPlaceAdapter() {
    }

    public SubCatPlaceAdapter(List<PlaceListModel> data, Context context) {
        this.data = data;
        this.context = context;
        dataFull = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public SubCatPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_sub_category_place, parent, false);
        return new SubCatPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCatPlaceViewHolder holder, int position) {
        final PlaceListModel example = data.get(position);
        holder.txtSubCatname.setText(example.getPlacename());
        Glide.with(holder.imgSubCat.getContext()).load(Constants.URL_GLIDE_IMAGE + example.getPhoto()).into(holder.imgSubCat);
        holder.cardViewSubCatPlace.setOnClickListener(new View.OnClickListener() {
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

