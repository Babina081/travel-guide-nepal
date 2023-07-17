package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;

public class SubCatPlaceViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgSubCat;
    public TextView txtSubCatname;
    public CardView cardViewSubCatPlace;

    public SubCatPlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        txtSubCatname= (TextView) itemView.findViewById(R.id.subcatplace);
        imgSubCat = (ImageView) itemView.findViewById(R.id.img_sub_item);
        cardViewSubCatPlace = (CardView) itemView.findViewById(R.id.subcatCardlayout);

    }
}
