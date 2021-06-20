package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    public TextView placename, description, placelocation,likecount;
    public ImageView img;
    public CardView cardView;

    /*//added for swiping
    public LinearLayout regularLayout;
    public LinearLayout swipeLayout;
    public TextView undo;*/

    public PlaceViewHolder(@NonNull View itemView) {
        super(itemView);

        placename = (TextView) itemView.findViewById(R.id.row_place_name);
        description = (TextView) itemView.findViewById(R.id.row_description);
        placelocation = (TextView) itemView.findViewById(R.id.row_place_location);
        img = (ImageView) itemView.findViewById(R.id.imageholder);
        likecount=(TextView) itemView.findViewById(R.id.like);
        cardView = (CardView) itemView.findViewById(R.id.cardView);

       /* //added for swiping
        regularLayout = itemView.findViewById(R.id.regularLayout);
        swipeLayout = itemView.findViewById(R.id.swipeLayout);
        undo = itemView.findViewById(R.id.undo);*/
    }
}
