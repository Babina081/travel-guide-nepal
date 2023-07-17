package com.example.bmenudemo.activities.homePageActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.fragments.FavouriteFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.HomeFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.ProfileFragment;
import com.example.bmenudemo.activities.homePageActivities.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView home, search, favourite, profile,mSelected;

    HomeFragment homeFragment;
    SearchFragment searchFragment;
    ProfileFragment profileFragment;
    FavouriteFragment favouriteFragment;

    Fragment currentFragment;

//    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_FULLSCREEN,WindowManager.LayoutParams.FLAGS_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //hide the action bar
        getSupportActionBar().hide();

        /*bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,
                    new HomeFragment()).commit();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        hideSoftKeyboard();*/

        home = findViewById(R.id.homeBtnIv);
        search = findViewById(R.id.searchBtnIv);
        favourite = findViewById(R.id.favouriteBtnIv);
        profile = findViewById(R.id.profileBtnIv);

        setSelected(home);
        setClickListeners();


        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
        favouriteFragment = new FavouriteFragment();

        currentFragment = homeFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, homeFragment, "homeFragments").commit();

        hideSoftKeyboard();

    }


    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setClickListeners() {
        home.setOnClickListener(this);
        search.setOnClickListener(this);
        favourite.setOnClickListener(this);
        profile.setOnClickListener(this);
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener navListener =
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
            };*/

    private void setSelected(ImageView imageView) {
        if (mSelected != null)
            mSelected.setColorFilter(ContextCompat.getColor(this, R.color.de_selected_color), android.graphics.PorterDuff.Mode.SRC_IN);
        imageView.setColorFilter(ContextCompat.getColor(this, R.color.SantaFe), android.graphics.PorterDuff.Mode.SRC_IN);
        mSelected = imageView;

    }

    @Override
    public void onClick(View v) {
        if (v == home) {
            setSelected(home);
            changeFragment(homeFragment);
        } else if (v == search) {

            setSelected(search);
            changeFragment(searchFragment);

        } else if (v == favourite) {
            setSelected(favourite);
            changeFragment(favouriteFragment);

        } else if (v == profile) {
            setSelected(profile);
            changeFragment(profileFragment);

        }
    }

    private void changeFragment(Fragment fragment) {
        if (fragment == currentFragment) {
            return;
        }
        getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        if (fragment.isAdded())
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        else
            getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, fragment, "homeFragments").commit();
        currentFragment = fragment;

    }

}