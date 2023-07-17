package com.example.bmenudemo.activities.loginregistrationactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.SessionManager;
import com.example.bmenudemo.constantsClass.Constants;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFirstname, editTextLastname, editTextEmail, editTextPassword, editTextConfirm;
    private TextView textLogin;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    private String firstname, lastname, email, password, confirm;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hide the action bar
        getSupportActionBar().hide();

        sessionManager = new SessionManager(this);

        editTextFirstname = (EditText) findViewById(R.id.editTextFirstname);
        editTextLastname = (EditText) findViewById(R.id.editTextLastname);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirm = (EditText) findViewById(R.id.editTextConfirm);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        textLogin = (TextView) findViewById(R.id.textLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        firstname = lastname = email = password = confirm = "";

        //onclick listener for register button in registration
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        //onclick listener for already have an account signin to go to login page
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginPage();
            }
        });

        hideSoftKeyboard();

    }

    public void register() {

        firstname = editTextFirstname.getText().toString().trim();
        lastname = editTextLastname.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        confirm = editTextConfirm.getText().toString().trim();

        //check if password doesnt match
        if (!password.equals(confirm)) {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_LONG).show();
        }
        //check if firstname,lastname,email, password and confirm is empty or not
        else if (!firstname.equals("") && !lastname.equals("") && !email.equals("") && !password.equals("") && !confirm.equals("")) {
            progressDialog.show();

            //get response from server
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Registration Success")) {
                        Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    } else {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("firstname", firstname);
                    data.put("lastname", lastname);
                    data.put("email", email);
                    data.put("password", password);
                    data.put("confirm", confirm);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(stringRequest);

        } else {
            Toast.makeText(RegisterActivity.this, "Fill the empty fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}