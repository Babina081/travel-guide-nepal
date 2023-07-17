package com.example.bmenudemo.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.CallBackCheckLike;
import com.example.bmenudemo.activities.HandleLikeDislike;
import com.example.bmenudemo.models.PlaceListModel;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

        private String url="http://192.168.1.9/travel/checklike.php";
    public TextView placename, description, placelocation, likecount;
    public ImageView img;
    public CardView cardView;
    public ImageButton isliked;

    public boolean testclick = false;
    /*//added for swiping
    public LinearLayout regularLayout;
    public LinearLayout swipeLayout;
    public TextView undo;*/

    public PlaceViewHolder(@NonNull View itemView) {
        super(itemView);

        placename = (TextView) itemView.findViewById(R.id.row_place_name);
//        description = (TextView) itemView.findViewById(R.id.row_description);
        placelocation = (TextView) itemView.findViewById(R.id.row_place_location);
        img = (ImageView) itemView.findViewById(R.id.imageholder);
        likecount = (TextView) itemView.findViewById(R.id.like);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        isliked = itemView.findViewById(R.id.btnIsLiked);


    }

    public void getLikeButtonStatus(Context context,String placeId,String userId){
        new HandleLikeDislike().checkLikeStatus(context, userId, placeId, new CallBackCheckLike() {
            @Override
            public void onSuccessResponse(boolean checklike) {
                if(checklike==true){
                    isliked.setImageResource(R.drawable.like);
                }
                else{
                    isliked.setImageResource(R.drawable.favorite);
                }
            }
        });
    }

}
