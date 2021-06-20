package com.example.bmenudemo.activities.homePageActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bmenudemo.R;
import com.example.bmenudemo.constantsClass.Constants;

public class PlaceDetailActivity extends AppCompatActivity {

    TextView tvplacename, tvdescription, tvplacelocation, like,comment,share;
    ImageView ivphoto;
    RecyclerView commentRV;

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
        like=(TextView) findViewById(R.id.tvLike);
        comment=(TextView) findViewById(R.id.tvComment);
        share=(TextView) findViewById(R.id.tvShare);
        commentRV=(RecyclerView) findViewById(R.id.commentsRV);


        /*imageViewBackToHome = (ImageView) findViewById(R.id.imageViewBackToHome);
        imageViewBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        */
        getIncomingIntent();



    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("photo") && getIntent().hasExtra("placename") && getIntent().hasExtra("description") && getIntent().hasExtra("placelocation")) {

            String photo = getIntent().getStringExtra("photo");
            String placename = getIntent().getStringExtra("placename");
            String description = getIntent().getStringExtra("description");
            String placelocation = getIntent().getStringExtra("placelocation");

            setImage(photo, placename, description, placelocation);
        }
    }

    private void setImage(String photo, String placename, String description, String placelocation) {

        tvplacename.setText(placename);
        tvdescription.setText(description);
        tvplacelocation.setText(placelocation);
        /*Glide.with(this)
                .asBitmap()
                .load(photo)
                .into(ivphoto);*/
        Glide.with(this).load(Constants.URL_GLIDE_IMAGE + photo).into(ivphoto);


    }
}