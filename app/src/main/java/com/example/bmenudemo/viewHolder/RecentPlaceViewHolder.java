package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;

public class RecentPlaceViewHolder extends RecyclerView.ViewHolder{

    public TextView recentPlacename,likecountRecent;
    public ImageView recentPlaceimage;
    public CardView cardViewRecent;
    public Button islikedRecent;

    public RecentPlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        recentPlacename = (TextView) itemView.findViewById(R.id.row_recent_place_name);
        recentPlaceimage = (ImageView) itemView.findViewById(R.id.imageholderrecent);
        likecountRecent = (TextView) itemView.findViewById(R.id.likerecent);
        cardViewRecent = (CardView) itemView.findViewById(R.id.cardViewrecent);
        islikedRecent = (Button) itemView.findViewById(R.id.btnIsLikedrecent);

    }
}
