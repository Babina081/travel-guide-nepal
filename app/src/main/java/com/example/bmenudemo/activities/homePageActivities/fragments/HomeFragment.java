package com.example.bmenudemo.activities.homePageActivities.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.map.MapActivity;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.adapter.PlaceAdapter;
import com.example.bmenudemo.models.PlaceListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    SearchView search;
    ImageView image;
    TextView imageMyLocation;

    PlaceAdapter placeAdapter;
    RecyclerView recyclerView;

    private static ArrayList<PlaceListModel> placeListModels = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        image = view.findViewById(R.id.imageView);
        search = (SearchView) view.findViewById(R.id.etSearch);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                placeAdapter.getFilter().filter(s);
                return false;
            }
        });

        imageMyLocation=view.findViewById(R.id.imageMyLocation);
        imageMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapActivity.class));
            }
        });

        recyclerView = view.findViewById(R.id.recent_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        extractPlace();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void extractPlace() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_RETRIEVE, null
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

                placeAdapter = new PlaceAdapter(placeListModels, getContext());
                recyclerView.setAdapter(placeAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
