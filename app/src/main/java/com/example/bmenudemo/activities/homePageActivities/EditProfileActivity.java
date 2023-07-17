package com.example.bmenudemo.activities.homePageActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.constantsClass.Constants;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    ImageView imgProfile;
    EditText etEmail, etFirstname, etLastname, etLocation, etDetails;
    Button btnSaveProfile;
    TextView cPass;
    SessionManager sessionManager;
    String memail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager= new SessionManager(this);

        imgProfile = findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etEmail = findViewById(R.id.etEmail);

        cPass = findViewById(R.id.cPass);
        cPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view1 = LayoutInflater.from(EditProfileActivity.this).inflate(R.layout.change_password_layout, null);

                final EditText Oldpass = view1.findViewById(R.id.etOldPass);
                final EditText Newpass = view1.findViewById(R.id.etNewPass);
                final EditText Confirmpass = view1.findViewById(R.id.etConfirmPass);


                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Change Password");
                builder.setView(view1);
                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldpassword = Oldpass.getText().toString().trim();
                        String newpassword = Newpass.getText().toString().trim();
                        String confirmpassword = Confirmpass.getText().toString().trim();

                        if (oldpassword.isEmpty() || newpassword.isEmpty() || confirmpassword.isEmpty()) {
                            Toast.makeText(EditProfileActivity.this, "some field are empty", Toast.LENGTH_SHORT).show();

                        } else {

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.5/travel/changepass.php", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("oldpassword", oldpassword);
                                    params.put("newpassword", newpassword);
                                    params.put("confirmpassword", confirmpassword);

                                    params.put("email", memail);

                                    return super.getParams();
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(stringRequest);

                        }
                    }
                });
            }
        });

        //        etLocation = findViewById(R.id.etLocation);
        //        etDetails = findViewById(R.id.etDetails);

        btnSaveProfile = findViewById(R.id.btnSaveEdit);
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileEdited();

            }
        });

//        String fn = sessionManager.getFirstname();
//        etFirstname.setText(fn);
//        String ln = sessionManager.getLastname();
//        etLastname.setText(ln);
//        String em = sessionManager.getEmail();
//        etEmail.setText(em);

        Intent intent = getIntent();
        memail= intent.getStringExtra("email");
        etEmail.setText(memail);


    }



    private void saveProfileEdited() {

        String firstname = etFirstname.getText().toString();
        String lastname = etLastname.getText().toString();
        String email = etEmail.getText().toString();


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_USERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("oldemail", memail);

                return super.getParams();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void chooseImage() {

    }


}