package com.example.bmenudemo.activities.loginregistrationactivities.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.loginregistrationactivities.LoginActivity;

public class PasswordUpdateSuccess extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update_success);

        btn1=findViewById(R.id.btnchangepw_success);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordUpdateSuccess.this, LoginActivity.class));
                finish();
            }
        });
    }
}