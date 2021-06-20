package com.example.bmenudemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.adapter.PlaceAdapter;
import com.example.bmenudemo.models.PlaceListModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    EditText search;
    ImageView image;

    //the recyclerview
    RecyclerView recyclerView;
    private static ArrayList<PlaceListModel> placeListModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = findViewById(R.id.imageView);
        search = findViewById(R.id.etSearch);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recent_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_RETRIEVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                PlaceListModel[] data = gson.fromJson(response, PlaceListModel[].class);
                PlaceAdapter placeAdapter= new PlaceAdapter(data, getApplicationContext());
                recyclerView.setAdapter(placeAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/
    }
}