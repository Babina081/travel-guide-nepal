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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.DisplayPlaceAsPerCategory;
import com.example.bmenudemo.activities.homePageActivities.DisplayPlaceAsPerState;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.StateListModel;
import com.example.bmenudemo.viewHolder.StateViewHolder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateAdapter extends RecyclerView.Adapter<StateViewHolder> implements Filterable {

    List<StateListModel> data;
    List<StateListModel> dataFull;
    Context context;

    public StateAdapter() {

    }

    public StateAdapter(List<StateListModel> data, Context context) {
        this.data = data;
        this.context = context;
        dataFull = new ArrayList<>(data);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull

    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_states, parent, false);
        return new StateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {

        final StateListModel example = data.get(position);

        holder.vStatename.setText(example.getStatename());

        Glide.with(holder.vImg.getContext()).load(Constants.URL_GLIDE_IMAGE + example.getStateimage()).into(holder.vImg);

        //holder for card view with on click listener
        holder.cardViewState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), example.getStatename() + " Clicked", Toast.LENGTH_LONG).show();
/*

                Intent intent = new Intent(context, GalleryActivity.class);
                intent.putExtra("statename", example.getStatename());
                intent.putExtra("stateimage", example.getStateimage());
                context.startActivity(intent);
*/

                Intent intent = new Intent(context, DisplayPlaceAsPerState.class);

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Filter getMyFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<StateListModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dataFull);
            } else {

                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (StateListModel state : data) {
                    if (state.getStatename().toLowerCase().contains(filterPattern)) {
                        filteredList.add(state);
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