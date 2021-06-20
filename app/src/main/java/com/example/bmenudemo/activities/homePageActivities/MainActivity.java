package com.example.bmenudemo.activities.homePageActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.fragments.AddPlaceFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.FavouriteFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.HomeFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.ProfileFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_FULLSCREEN,WindowManager.LayoutParams.FLAGS_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //hide the action bar
        getSupportActionBar().hide();

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,
                    new HomeFragment()).commit();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.menu_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.menu_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.menu_upload:
                            selectedFragment = new AddPlaceFragment();
                            break;
                        case R.id.menu_favourites:
                            selectedFragment = new FavouriteFragment();
                            break;
                        case R.id.menu_profile:
                            selectedFragment = new ProfileFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,
                            selectedFragment).commit();
                    return true;
                }
            };

}