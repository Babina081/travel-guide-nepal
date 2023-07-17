package com.example.bmenudemo.activities.loginregistrationactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.SessionManager;

import java.util.HashMap;

public class Success extends AppCompatActivity {

    //    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView firstName, lastName, email;
    Button btnLogout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        sessionManager = new SessionManager(this);

        firstName = findViewById(R.id.tvFirstName);
        lastName = findViewById(R.id.tvLastname);
        email = findViewById(R.id.textViewEmail);
        btnLogout = findViewById(R.id.buttonLogout);

        /*String fname = sessionManager.getFirstname();
        firstName.setText(fname);

        String lname = sessionManager.getLastname();
        lastName.setText(lname);
*/
        String em = sessionManager.getEmail();
        email.setText(em);

        /*String fn=sessionManager.getFirstname();
        firstName.setText(fn);

        String ln=sessionManager.getLastname();
        firstName.setText(ln);*/


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager.setLogin(false);
                        sessionManager.setEmail("");
                       /* sessionManager.setFirstName("");
                        sessionManager.setLastname("");*/
                        moveToLogin();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    private void moveToLogin() {
        Intent intent = new Intent(Success.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}