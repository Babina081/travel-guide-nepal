package com.example.bmenudemo.activities.homePageActivities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.adapter.CategoryAdapter;
import com.example.bmenudemo.adapter.PlaceAdapter;
import com.example.bmenudemo.adapter.RecentPlaceAdapter;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.CategoryListModel;
import com.example.bmenudemo.models.PlaceListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SearchView search;

    CategoryAdapter categoryAdapter;
    PlaceAdapter placeAdapter;
    RecentPlaceAdapter recentPlaceAdapter;

    RecyclerView rvExplore, rvRecent, rvCategory;

    private static ArrayList<CategoryListModel> categoryListModels = new ArrayList<>();
    private static ArrayList<PlaceListModel> placeListModels = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = view.findViewById(R.id.etSearch2);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                placeAdapter.getFilter().filter(s);
                categoryAdapter.getFilter().filter(s);
                return false;
            }
        });


        rvRecent = view.findViewById(R.id.rv_recent);
        rvRecent.setHasFixedSize(true);
        rvRecent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        extractRecentPlace();

        rvExplore = view.findViewById(R.id.rv_explore);
        rvExplore.setHasFixedSize(true);
        rvExplore.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        extractNearbyPlaces();

        rvCategory = view.findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        GetCategory();


        return view;
    }

    private void GetCategory() {

        StringRequest request = new StringRequest(Request.Method.GET,
                /*"http://192.168.254.191/travel/retrieve.php"*/ Constants.URL_RETRIEVE_CATEGORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        categoryListModels.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (sucess.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String categoryid = object.getString("categoryid");
                                    String categoryname = object.getString("categoryname");

                                    CategoryListModel category = new CategoryListModel(categoryid, categoryname);
                                    categoryListModels.add(category);

                                    categoryAdapter = new CategoryAdapter(categoryListModels, getContext());

                                    rvCategory.setAdapter(categoryAdapter);
                                    categoryAdapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private void extractRecentPlace() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constants.URL_RETRIEVE_RECENT_PLACES,
                null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                placeListModels.clear();
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

                recentPlaceAdapter = new RecentPlaceAdapter(placeListModels, getContext());

                rvRecent.setAdapter(recentPlaceAdapter);
                recentPlaceAdapter.notifyDataSetChanged();

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

    private void extractNearbyPlaces() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constants.URL_RETRIEVE_NEARBY_RECENT_PLACES,
                null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                placeListModels.clear();
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

                rvExplore.setAdapter(placeAdapter);
                placeAdapter.notifyDataSetChanged();

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

}