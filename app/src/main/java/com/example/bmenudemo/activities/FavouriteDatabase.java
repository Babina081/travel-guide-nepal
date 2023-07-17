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
import com.example.bmenudemo.models.PlaceListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavouriteDatabase {
    private String retrieve_url="http://192.168.1.9/travel/likeplace.php";
    private String imagePath="http://192.168.1.9/travel/images/";
    private static ArrayList<PlaceListModel> placeListModelArrayList= new ArrayList<>();
    public void favouriteDogInformation(final Context context, final String userID, final VolleyCallBackForDog callBackForDog){
//        StringRequest stringRequest= new StringRequest(Request.Method.POST, retrieve_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                placeListModelArrayList.clear();
//                try {
//                    JSONObject jsonObject= new JSONObject(response);
//                    String success=jsonObject.getString("success");
//                    JSONArray jsonArray=jsonObject.getJSONArray("favourite");
//                    if(success.equals("1")){
//                        for(int i=0;i<jsonArray.length();i++){
//                            JSONObject object= jsonArray.getJSONObject(i);
//                            String placeid= object.getString("placeid");
//                            String placename= object.getString("placename");
//                            String description= object.getString("description");
//                            String placelocation= object.getString("placelocation");
//                            String photo=object.getString("photo");
//                            String longitude= object.getString("longitude");
//                            String latitude= object.getString("latitude");
//                            String categoryid= object.getString("category");
//                            String stateid= object.getString("state");
//                            PlaceListModel placeListModel= new PlaceListModel(placeid,placename,description,placelocation,photo,longitude,latitude,categoryid,stateid);
//                            placeListModel.add(placeListModel);
//                            callBackForDog.onSuccessResponse(placeListModel);
//                        }
//                        callBackForDog.onSuccessResponse(placeListModelArrayList);
//                    }
//                    else{
//                        Toast.makeText(context, "Error Retrieving data", Toast.LENGTH_LONG).show();
//
//                    }
//
//                }catch (JSONException e){
//                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("place", "onErrorResponse: "+error.getMessage());
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params= new HashMap<String, String>();
//                params.put("userid",userID);
//                return params;
//            }
//        };
//        RequestQueue requestQueue= Volley.newRequestQueue(context,null);
//        requestQueue.add(stringRequest);
    }


}

