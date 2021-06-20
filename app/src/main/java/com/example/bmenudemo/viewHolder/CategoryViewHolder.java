package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView categoryname;
    public CardView cardView;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        categoryname = (TextView) itemView.findViewById(R.id.row_category_name);
        cardView = (CardView) itemView.findViewById(R.id.cardviewExplore);

    }
}
