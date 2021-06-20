package com.example.bmenudemo.activities.loginregistrationactivities.forgetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bmenudemo.R;

public class ChangePassword extends AppCompatActivity {

    EditText et1,et2,et3;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        et1=findViewById(R.id.editTextOldPasswordFP);
        et2=findViewById(R.id.editTextNewPasswordFP);
        et2=findViewById(R.id.editTextConfirmPasswordFP);
        btn1=findViewById(R.id.btnchangepw_update);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePassword.this,PasswordUpdateSuccess.class));
                finish();
            }
        });
    }
}