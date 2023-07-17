package com.example.bmenudemo.activities.homePageActivities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.bmenudemo.activities.loginregistrationactivities.IntroActivity;
import com.example.bmenudemo.adapter.FeaturedPlaceAdapter;
import com.example.bmenudemo.adapter.SliderAdapter;
import com.example.bmenudemo.adapter.StateAdapter;
import com.example.bmenudemo.adapter.ViewPagerAdapter;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.adapter.PlaceAdapter;
import com.example.bmenudemo.models.PlaceListModel;
import com.example.bmenudemo.models.StateListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    SearchView search;

    PlaceAdapter placeAdapter;
    StateAdapter stateAdapter;
    FeaturedPlaceAdapter featuredPlaceAdapter;

    RecyclerView recyclerView, rvStates, rvFeatured;

    private static ArrayList<PlaceListModel> placeListModels = new ArrayList<>();
    public static List<StateListModel> stateListModels = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        search = (SearchView) view.findViewById(R.id.etSearch);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                placeAdapter.getFilter().filter(s);
                stateAdapter.getMyFilter().filter(s);
                return false;
            }
        });

        rvFeatured = view.findViewById(R.id.rv_featured_place);
        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        GetFeaturedPlaces();

        rvStates = view.findViewById(R.id.rvStates);
        rvStates.setHasFixedSize(true);
//        rvStates.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvStates.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        GetStates();

        recyclerView = view.findViewById(R.id.recent_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        extractPlace();


        return view;
    }

    private void GetFeaturedPlaces() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_RETRIEVE_FEATURED, null
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

                featuredPlaceAdapter = new FeaturedPlaceAdapter(placeListModels, getContext());
                rvFeatured.setAdapter(featuredPlaceAdapter);
                featuredPlaceAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jsonArrayRequest);
    }

    private void GetStates() {
        /*ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading.....");
        progressDialog.show();*/

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, /*"http://192.168.254.191/travel/retrieveState.php"*/ Constants.URL_RETRIEVE_STATES, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i <= response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                stateListModels.add(new StateListModel(

                                        jsonObject.getString("statename"),

                                        jsonObject.getString("stateimage")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
//                                progressDialog.dismiss();
                            }
                            stateAdapter = new StateAdapter(stateListModels, getContext());
                            rvStates.setAdapter(stateAdapter);
                            stateAdapter.notifyDataSetChanged();


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
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
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
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
