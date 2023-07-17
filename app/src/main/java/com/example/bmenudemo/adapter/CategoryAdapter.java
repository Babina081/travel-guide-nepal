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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.activities.homePageActivities.DisplayPlaceAsPerCategory;
import com.example.bmenudemo.activities.homePageActivities.PlaceDetailActivity;
import com.example.bmenudemo.models.CategoryListModel;
import com.example.bmenudemo.R;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.CategoryListModel;
import com.example.bmenudemo.models.StateListModel;
import com.example.bmenudemo.viewHolder.CategoryViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> implements Filterable {

    // CategoryListModel[] data;

    List<CategoryListModel> data;
    List<CategoryListModel> dataFull;
    Context context;

//    private RecyclerView.RecycledViewPool viewPool=new RecyclerView.RecycledViewPool();

    public CategoryAdapter(List<CategoryListModel> data, Context context) {
        this.data = data;
        this.context = context;
        dataFull = new ArrayList<>(data);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

//        CategoryListModel example = data[position];

        final CategoryListModel example = data.get(position);

        final String catname = data.get(position).getCategoryname();

        holder.categoryname.setText(example.getCategoryname());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, example.getCategoryname() + " Clicked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, DisplayPlaceAsPerCategory.class);

                context.startActivity(intent);


//                intent.putExtra("photo", example.getPhoto());
//                intent.putExtra("placename", example.getPlacename());
//                intent.putExtra("description", example.getDescription());
//                intent.putExtra("placelocation", example.getPlacelocation());
//                intent.putExtra("likes", example.getLikes());
//                context.startActivity(intent);

               /* AlertDialog.Builder builder= new AlertDialog.Builder(context);
                ProgressDialog progressDialog= new ProgressDialog(context);

                CharSequence[] dialogItem={"Edit Data","Delete Data"};
                builder.setTitle(data.get(position).getCategoryname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                context.startActivity(new Intent(context,EditCategoryActivity.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                               break;
                        }
                    }
                });
                builder.create().show();*/
            }
        });

       /* // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
               holder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(example.getPlaceListModels().size());

        // Create sub item view adapter
        PlaceAdapter placeAdapter = new PlaceAdapter(example.getPlaceListModels());

        holder.rvSubItem.setLayoutManager(layoutManager);
        holder.rvSubItem.setAdapter(placeAdapter);
        holder.rvSubItem.setRecycledViewPool(viewPool);*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

  /* public Filter getCatFilter(){
        return myFilter;
   }*/

   Filter myFilter=new Filter() {
       @Override
       protected FilterResults performFiltering(CharSequence charSequence) {
           List<CategoryListModel> filteredList = new ArrayList<>();

           if (charSequence == null || charSequence.length() == 0) {
               filteredList.addAll(dataFull);
           } else {

               String filterPattern = charSequence.toString().toLowerCase().trim();

               for (CategoryListModel category : data) {
                   if (category.getCategoryname().toLowerCase().contains(filterPattern)) {
                       filteredList.add(category);
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

    @Override
    public Filter getFilter() {
        return myFilter;
    }
}
