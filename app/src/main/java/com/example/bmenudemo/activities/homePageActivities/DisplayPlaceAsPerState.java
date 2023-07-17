package com.example.bmenudemo.activities.homePageActivities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;

import com.example.bmenudemo.adapter.SubStatePlaceAdapter;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.PlaceListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayPlaceAsPerState extends AppCompatActivity {


    SubStatePlaceAdapter placeAdapter;

    RecyclerView rvSubStatePlace;

    private static ArrayList<PlaceListModel> placeListModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_place_as_per_state);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvSubStatePlace = findViewById(R.id.rvPlaceAsPerState);
        rvSubStatePlace.setHasFixedSize(true);
        rvSubStatePlace.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        extractPlace();
    }

    private void extractPlace() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_RETRIEVE_SUB_STATE_PLACE, null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        placeListModels.add(new PlaceListModel(

                                jsonObject.getString("placename"),

                                jsonObject.getString("description"),

                                jsonObject.getString("placelocation"),

                                jsonObject.getString("photo"),

                                jsonObject.getString("likes")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }

                placeAdapter = new SubStatePlaceAdapter(placeListModels, getApplicationContext());
                rvSubStatePlace.setAdapter(placeAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonArrayRequest);
    }
}