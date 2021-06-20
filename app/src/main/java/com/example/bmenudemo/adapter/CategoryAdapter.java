package com.example.bmenudemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.bmenudemo.models.CategoryListModel;
import com.example.bmenudemo.R;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.CategoryListModel;
import com.example.bmenudemo.viewHolder.CategoryViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    // CategoryListModel[] data;

    List<CategoryListModel> data;
    List<CategoryListModel> dataFull;
    Context context;

    public CategoryAdapter(List<CategoryListModel> data, Context context) {
        this.data = data;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
