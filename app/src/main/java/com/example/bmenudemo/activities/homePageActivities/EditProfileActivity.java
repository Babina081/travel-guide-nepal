package com.example.bmenudemo.activities.homePageActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bmenudemo.R;

public class EditProfileActivity extends AppCompatActivity {

    ImageView imgProfile;
    EditText etDisplayname, etUsername, etLocation, etDetails;
    Button btnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgProfile = findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        etDisplayname = findViewById(R.id.etDisplayname);
        etUsername = findViewById(R.id.etUsername);
        etLocation = findViewById(R.id.etLocation);
        etDetails = findViewById(R.id.etDetails);

        btnSaveProfile=findViewById(R.id.btnSaveEdit);
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileEdited();
            }
        });
    }

    private void saveProfileEdited() {

    }

    private void chooseImage() {

    }
}