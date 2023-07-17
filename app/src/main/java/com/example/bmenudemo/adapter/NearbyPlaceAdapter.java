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
import com.example.bmenudemo.activities.homePageActivities.PlaceDetailActivity;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.PlaceListModel;
import com.example.bmenudemo.viewHolder.NearbyPlaceViewHolder;
import com.example.bmenudemo.viewHolder.PlaceViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NearbyPlaceAdapter extends RecyclerView.Adapter<NearbyPlaceViewHolder> implements Filterable {

    List<PlaceListModel> data;
    List<PlaceListModel> dataFull;
    Context context;


    public NearbyPlaceAdapter() {
    }

    public NearbyPlaceAdapter(List<PlaceListModel> data, Context context) {
        this.data = data;
        this.context = context;
        dataFull = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public NearbyPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_nearby_places, parent, false);
        return new NearbyPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyPlaceViewHolder holder, int position) {

        final PlaceListModel example = data.get(position);

        holder.placename.setText(example.getPlacename());
        holder.placelocation.setText(example.getPlacelocation());
        Glide.with(holder.img.getContext()).load(Constants.URL_GLIDE_IMAGE + example.getPhoto()).into(holder.img);
        holder.likecount.setText(example.getLikes());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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

        //favourite system
        holder.isliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<PlaceListModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dataFull);
            } else {

                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (PlaceListModel place : data) {
                    if (place.getPlacename().toLowerCase().contains(filterPattern)) {
                        filteredList.add(place);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }

    };

}
