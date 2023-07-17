package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;

import static android.os.Build.VERSION_CODES.R;

public class FeaturedPlaceViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgFeat;
    public TextView txtFeatname, txtFeatlocation, txtlikecountFeat;
    public LinearLayout cardViewFeat;

    public FeaturedPlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        txtFeatname = (TextView) itemView.findViewById(com.example.bmenudemo.R.id.row_name_fetaured);
//        description = (TextView) itemView.findViewById(R.id.row_description);
        txtFeatlocation = (TextView) itemView.findViewById(com.example.bmenudemo.R.id.row_location_featured);
        imgFeat = (ImageView) itemView.findViewById(com.example.bmenudemo.R.id.imageholderFeaturedPlace);
        txtlikecountFeat = (TextView) itemView.findViewById(com.example.bmenudemo.R.id.likecountfeatured);
        cardViewFeat = (LinearLayout) itemView.findViewById(com.example.bmenudemo.R.id.cardViewFeaturedPlace);

    }
}
