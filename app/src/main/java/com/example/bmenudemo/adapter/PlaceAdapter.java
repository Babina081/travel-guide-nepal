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
import com.example.bmenudemo.activities.CallBackCheckLike;
import com.example.bmenudemo.activities.HandleLikeDislike;
import com.example.bmenudemo.activities.homePageActivities.SessionManager;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.activities.homePageActivities.PlaceDetailActivity;
import com.example.bmenudemo.viewHolder.PlaceViewHolder;
import com.example.bmenudemo.models.PlaceListModel;
import com.example.bmenudemo.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> implements Filterable {

    List<PlaceListModel> data;
    List<PlaceListModel> dataFull;
    Context context;



    public PlaceAdapter() {
    }

//    public PlaceAdapter(List<PlaceListModel> data) {
//        this.data = data;
//    }

    public PlaceAdapter(List<PlaceListModel> data, Context context) {
        this.data = data;
        this.context = context;
        dataFull = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_places, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

        final PlaceListModel example = data.get(position);

        holder.placename.setText(example.getPlacename());
//        holder.description.setText(example.getDescription());
        holder.placelocation.setText(example.getPlacelocation());
        Glide.with(holder.img.getContext()).load(Constants.URL_GLIDE_IMAGE + example.getPhoto()).into(holder.img);
        holder.likecount.setText(example.getLikes());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
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

        // getting like button status
//       holder.getLikeButtonStatus(context, example.getPlaceid(), new SessionManager(context).getUserId());

        //favourite system
        holder.isliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               holder.testclick = true;
//
//                if(holder.testclick==true){
//                    new HandleLikeDislike().checkLikeStatus(context, new SessionManager(context).getUserId(), example.getPlaceid(), new CallBackCheckLike() {
//                        @Override
//                        public void onSuccessResponse(boolean checklike) {
//                            if(checklike==true){
//                                new HandleLikeDislike().deleteLike(context, new SessionManager(context).getUserId(), example.getPlaceid(), new CallBackCheckLike() {
//                                    @Override
//                                    public void onSuccessResponse(boolean checklike) {
//                                        if (checklike==true){
//                                           holder.isliked.setImageResource(R.drawable.favorite);
//                                            holder.testclick=false;
//                                        }
//                                    }
//                                });
//                            }
//                            else{
//                                new HandleLikeDislike().insertLike(context, new SessionManager(context).getUserId(), example.getPlaceid(), new CallBackCheckLike() {
//                                    @Override
//                                    public void onSuccessResponse(boolean checklike) {
//                                        if(checklike==true){
//                                            holder.isliked.setImageResource(R.drawable.like);
//                                            holder.testclick=false;
//
//                                        }
//                                    }
//                                });
//                            }
//                        }
//                    });
//                }
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

    /*ArrayList<PlaceListModel> placeListModels;
    LayoutInflater inflater;

    public PlaceAdapter() {
    }

    public PlaceAdapter(ArrayList<PlaceListModel> placeListModels,Context context) {
        this.inflater=LayoutInflater.from(context);
        this.placeListModels = placeListModels;
         }

           @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.recents_row_item,parent,false);
        return new PlaceViewHolder(view);
    }

     @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

        final PlaceListModel data = placeListModels.get(position);

        holder.placename.setText(placeListModels.get(position).getPlacename());
        holder.description.setText(placeListModels.get(position).getDescription());
        holder.placelocation.setText(placeListModels.get(position).getPlacelocation());
        Picasso.get().load(placeListModels.get(position).getPhoto()).into(holder.img);


    }

        @Override
    public int getItemCount() {
        return placeListModels.size();
    }*/



}
