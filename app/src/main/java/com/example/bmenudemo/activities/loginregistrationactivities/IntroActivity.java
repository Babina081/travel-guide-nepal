package com.example.bmenudemo.activities.loginregistrationactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.MainActivity;
import com.example.bmenudemo.adapter.SliderAdapter;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;

    TextView[] dots;
    Button getStarted, next, skip, back;
    Animation animation;

    int currentPos;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String SHARED_PREF_NAME = "myPrefs";
    public static final String IS_INTRO_OPENED = "isIntroOpnend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //hide the action bar
        getSupportActionBar().hide();

        //hide the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       /* //sticky immersive fullscreen
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);*/

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences != null) {
            boolean checkShared = sharedPreferences.getBoolean(IS_INTRO_OPENED, false);
            if (checkShared == true) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }

        if (restorePrefData()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        //hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getStarted = findViewById(R.id.get_started);
        next = findViewById(R.id.next);
        skip = findViewById(R.id.skip_btn);
        back = findViewById(R.id.back);

        //call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPos - 1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPos + 1);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putBoolean(IS_INTRO_OPENED,false);
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(IS_INTRO_OPENED,true);

                savePrefsData();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        return sharedPreferences.getBoolean(IS_INTRO_OPENED, false);
    }

    public void savePrefsData() {
        editor.putBoolean(IS_INTRO_OPENED, true);
        editor.commit();
    }

    private void addDots(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.Twine));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            currentPos = position;

            if (position == 0) {
                getStarted.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                getStarted.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            } else if (position == 2) {
                getStarted.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.button_animation);
                getStarted.setAnimation(animation);
                getStarted.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}