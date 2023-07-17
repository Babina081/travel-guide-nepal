package com.example.bmenudemo.adapter;

import android.content.Context;
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
import com.example.bmenudemo.R;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.Message;
import com.example.bmenudemo.viewHolder.NotificationViewholder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifictaionAdapter extends RecyclerView.Adapter<NotificationViewholder> {

    Context context;
    List<Message> messages=new ArrayList<>();

    public NotifictaionAdapter() {
    }

    public NotifictaionAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public NotificationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_notification_item, parent, false);
        return new NotificationViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewholder holder, int position) {

        final Message example = messages.get(position);

        final String title = (String) messages.get(position).getText();
        final String message = (String) messages.get(position).getSender();

        holder.ntitle.setText(example.getText());
        holder.nmessage.setText(example.getSender());
        holder.ndate.setText(example.getTimestamp());


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
