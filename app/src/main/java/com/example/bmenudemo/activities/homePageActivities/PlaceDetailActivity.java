package com.example.bmenudemo.activities.homePageActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.map.MapActivityFragment;
import com.example.bmenudemo.constantsClass.Constants;

public class PlaceDetailActivity extends AppCompatActivity {

    TextView tvplacename, tvdescription, tvplacelocation, likeText;
    ImageView ivphoto, btnAddComment;
    Button navigate, review, btnlikeunlike;

    //    RecyclerView rvComments;
    EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvplacename = (TextView) findViewById(R.id.placename);
        tvdescription = (TextView) findViewById(R.id.placedescription);
        tvplacelocation = (TextView) findViewById(R.id.placelocation);
        ivphoto = (ImageView) findViewById(R.id.placeimage);
        likeText = (TextView) findViewById(R.id.likeText);

        navigate = (Button) findViewById(R.id.btnNavigate);
        review = (Button) findViewById(R.id.btnReview);

        btnAddComment = (ImageView) findViewById(R.id.btnAddComment);
        etComment = (EditText) findViewById(R.id.txtComment);
        btnlikeunlike = (Button) findViewById(R.id.btnlikeunlike);
//        rvComments = (RecyclerView) findViewById(R.id.rvComments);

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapActivityFragment.class));
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnlikeunlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*imageViewBackToHome = (ImageView) findViewById(R.id.imageViewBackToHome);
        imageViewBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        getIncomingIntent();

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("photo") && getIntent().hasExtra("placename") && getIntent().hasExtra("description") && getIntent().hasExtra("placelocation") && getIntent().hasExtra("likes")) {

            String photo = getIntent().getStringExtra("photo");
            String placename = getIntent().getStringExtra("placename");
            String description = getIntent().getStringExtra("description");
            String placelocation = getIntent().getStringExtra("placelocation");
            String likes = getIntent().getStringExtra("likes");

            setImage(photo, placename, description, placelocation, likes);
        }
    }

    private void setImage(String photo, String placename, String description, String placelocation, String likes) {

        tvplacename.setText(placename);
        tvdescription.setText(description);
        tvplacelocation.setText(placelocation);
        likeText.setText(likes);
        /*Glide.with(this)
                .asBitmap()
                .load(photo)
                .into(ivphoto);*/
        Glide.with(this).load(Constants.URL_GLIDE_IMAGE + photo).into(ivphoto);


    }
}