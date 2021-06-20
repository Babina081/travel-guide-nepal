package com.example.bmenudemo.activities.loginregistrationactivities.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.loginregistrationactivities.LoginActivity;

public class ForgetPassword extends AppCompatActivity {

    ImageView img1;
    EditText et1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        img1=findViewById(R.id.backtologin);
        et1=findViewById(R.id.forgetPwEditText);
        btn1=findViewById(R.id.btnchangepw_step1);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPassword.this, OTP_Activity.class));
                finish();
            }
        });

    }

    public void send(View view) {
        startActivity(new Intent(ForgetPassword.this,PasswordChangeBYMsg.class));
        finish();
    }
}