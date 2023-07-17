package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;

public class SubStatePlaceViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgSubState;
    public TextView txtSubStatename;
    public CardView cardViewSubStatePlace;

    public SubStatePlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        txtSubStatename= (TextView) itemView.findViewById(R.id.substateplace);
        imgSubState = (ImageView) itemView.findViewById(R.id.img_sub_item_state);
        cardViewSubStatePlace = (CardView) itemView.findViewById(R.id.subsateCardlayout);

    }
}
