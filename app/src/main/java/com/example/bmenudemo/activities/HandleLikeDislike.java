package com.example.bmenudemo.activities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HandleLikeDislike {
    private String checkLike = "http://192.168.1.9/travel/checklike.php";
    private String insertLike = "http://192.168.1.9/travel/insertlike.php";
    private String deleteLike = "http://192.168.1.9/travel/deletelike.php";

    public void checkLikeStatus(Context context, String userID, String placeID, CallBackCheckLike callBackCheckLike) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, checkLike, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Log.d("status", "getLikeButtonStatus: " + "null");
                        callBackCheckLike.onSuccessResponse(true);
                    } else {
                        callBackCheckLike.onSuccessResponse(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", userID);
                params.put("placeid", placeID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context, null);
        requestQueue.add(stringRequest);
    }

    public void insertLike(Context context, String userID, String placeID, CallBackCheckLike callBackCheckLike) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertLike, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(context, "Liked", Toast.LENGTH_LONG).show();
                        callBackCheckLike.onSuccessResponse(true);
                    } else {
                        Toast.makeText(context, "Error " + response, Toast.LENGTH_LONG).show();
                        callBackCheckLike.onSuccessResponse(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", userID);
                params.put("placeid", placeID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context, null);
        requestQueue.add(stringRequest);
    }

    public void deleteLike(Context context, String userID, String placeID, CallBackCheckLike callBackCheckLike) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, deleteLike, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(context, "Disliked", Toast.LENGTH_LONG).show();
                        callBackCheckLike.onSuccessResponse(true);
                    } else {
                        Toast.makeText(context, "Error" + response, Toast.LENGTH_LONG).show();
                        callBackCheckLike.onSuccessResponse(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", userID);
                params.put("placeid", placeID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context, null);
        requestQueue.add(stringRequest);
    }
}
