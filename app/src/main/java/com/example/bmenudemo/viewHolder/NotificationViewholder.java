package com.example.bmenudemo.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;


public class NotificationViewholder extends RecyclerView.ViewHolder {

    public TextView ntitle, nmessage, ndate;


    public NotificationViewholder(@NonNull View itemView) {
        super(itemView);

        ntitle = itemView.findViewById(R.id.ntitle);
        nmessage = itemView.findViewById(R.id.nmessage);
        ndate = itemView.findViewById(R.id.ndate);


    }
}
