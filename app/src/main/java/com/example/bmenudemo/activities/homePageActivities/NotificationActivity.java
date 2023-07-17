package com.example.bmenudemo.activities.homePageActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.adapter.NotifictaionAdapter;
import com.example.bmenudemo.constantsClass.Constants;
import com.example.bmenudemo.models.Message;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView rvNotification;
    NotifictaionAdapter notifictaionAdapter;

    public static List<Message> messages = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvNotification = findViewById(R.id.recyclerViewNotification);
        notifictaionAdapter = new NotifictaionAdapter(getApplicationContext(), messages);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutNotification);

        rvNotification.setHasFixedSize(true);
        rvNotification.setLayoutManager(new LinearLayoutManager(this));
        GetNotification();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetNotification();
            }
        });
    }

    private void GetNotification() {

        swipeRefreshLayout.setRefreshing(true);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                /*"http://192.168.254.191/travel/retrieveNotification.php"*/
                Constants.URL_RETRIEVE_NOTIFICATION
                , null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        swipeRefreshLayout.setRefreshing(false);
                        messages.clear();
                        for (int i = 0; i <= response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                messages.add(new Message(

                                        jsonObject.getString("title"),

                                        jsonObject.getString("message"),

                                        jsonObject.getString("date_notify")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                            notifictaionAdapter = new NotifictaionAdapter(getApplicationContext(), messages);
                            rvNotification.setAdapter(notifictaionAdapter);
                            notifictaionAdapter.notifyDataSetChanged();


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                progressDialog.dismiss();
                Toast.makeText(NotificationActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}