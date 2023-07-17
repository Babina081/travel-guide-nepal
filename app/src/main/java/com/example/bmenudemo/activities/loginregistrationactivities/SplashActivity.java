package com.example.bmenudemo.activities.loginregistrationactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.MainActivity;
import com.example.bmenudemo.activities.homePageActivities.SessionManager;
import com.example.bmenudemo.constantsClass.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    boolean is_logged;
    ImageView img1;
    TextView img2;
    Animation top, btm;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //for fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide the action bar
        getSupportActionBar().hide();

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.main_logo_animation);
        btm = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sub_logo_animation);

        img1.setAnimation(top);
        img2.setAnimation(btm);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                sharedPreferences = getSharedPreferences(SessionManager.SHARED_PREF_NAME, MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean(SessionManager.LOGIN, false);
//                if (isFirstTime) {
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean(SessionManager.LOGIN, false);
//                    editor.commit();
//                    openLogin();
//                } else {
//                    openHome();
//                }
                openLogin();
               /* is_logged = SessionManager.getBooleanPreference(SplashActivity.this, SessionManager.LOGIN, false);
                if (is_logged) {
                    openHome();
                } else {
                    openLogin();
                }*/
            }
        }, 4000);
    }

    private void openLogin() {
        Intent loginIntent = new Intent(this, NewActivity.class);
        startActivity(loginIntent);
    }

    private void openHome() {
        Intent homeIntent = new Intent(this, IntroActivity.class);
        startActivity(homeIntent);
    }
}