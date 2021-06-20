package com.example.bmenudemo.activities.loginregistrationactivities.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bmenudemo.R;

public class OTP_Activity extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        btn1=findViewById(R.id.btnchangepw_step2_verify);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTP_Activity.this,ChangePassword.class));
                finish();
            }
        });
    }
}