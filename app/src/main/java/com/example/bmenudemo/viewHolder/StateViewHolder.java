package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;


public class StateViewHolder extends RecyclerView.ViewHolder{
    public TextView vStatename;
    public ImageView vImg;
    public CardView cardViewState;

    public StateViewHolder(@NonNull View itemView) {
        super(itemView);

        this.vStatename = (TextView) itemView.findViewById(R.id.row_state_name);
        this.vImg = (ImageView) itemView.findViewById(R.id.imageholderState);
        this.cardViewState = (CardView) itemView.findViewById(R.id.cardViewState);


    }
}
