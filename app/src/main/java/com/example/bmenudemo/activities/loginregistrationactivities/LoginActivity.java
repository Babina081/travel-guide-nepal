package com.example.bmenudemo.activities.loginregistrationactivities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bmenudemo.R;
import com.example.bmenudemo.activities.homePageActivities.MainActivity;
import com.example.bmenudemo.activities.loginregistrationactivities.forgetpassword.ForgetPassword;
import com.example.bmenudemo.activities.homePageActivities.SessionManager;
import com.example.bmenudemo.constantsClass.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    //initializing SharedPreference for Remembering password
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    private EditText editTextEmail, editTextPassword;
    private TextView textRegister;
    private Button buttonLogin, back;
    private ProgressDialog progressDialog;
    private CheckBox mCheckBox, chRemember;

    SessionManager sessionManager;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //for remember info
        sharedPreferences = getSharedPreferences(SessionManager.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonRegister);
        textRegister = (TextView) findViewById(R.id.textLogin);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        chRemember = (CheckBox) findViewById(R.id.chRemember);
        back = (Button) findViewById(R.id.btnBacktoSigninOptions);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewActivity.class));
                finish();
            }
        });

        //action listener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String mEmail = editTextEmail.getText().toString().trim();
                String mPassword = editTextPassword.getText().toString().trim();
                if (mEmail.isEmpty() == false && mPassword.isEmpty() == false) {
                    login(mEmail, mPassword);
                } else {
                    editTextEmail.setError("Please enter email");
                    editTextPassword.setError("Please enter password");
                }*/



                email= editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if (!email.isEmpty() || !password.isEmpty()) {
                    login(email, password);
//                    login();
                } else {
                    editTextEmail.setError("Please insert email");
                    editTextPassword.setError("Please insert password");
                }

//                Login();

            }
        });


        //condition to check if the remember checkbox is checked or not
        if (sharedPreferences.getBoolean(SessionManager.REMEMBER, false)) {
            chRemember.setChecked(true);
        } else {
            chRemember.setChecked(false);
        }

        editTextEmail.setText(sharedPreferences.getString(SessionManager.Email, ""));
        editTextPassword.setText(sharedPreferences.getString(SessionManager.Password, ""));

        editTextEmail.addTextChangedListener(this);
        editTextPassword.addTextChangedListener(this);
        chRemember.setOnCheckedChangeListener(this);

    }

  /*  public void login(){

        progressDialog.show();

//        String email = editTextEmail.getText().toString();
//        String password = editTextPassword.getText().toString();


    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        String success = object.getString("success");
                        if (success.equals("1")) {
                            progressDialog.dismiss();

                            Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_LONG).show();

                               *//* DatabaseHelper helper = new DatabaseHelper();
                                helper.databaseHelper(getApplicationContext(), sessionManager.getEmail(),
                                        sessionManager.getPassword(), new VolleyCallBack() {
                                    @Override
                                    public void onSuccessResponse(List<UserGetterSetter> list) {

                                    }
                                });*//*

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                            sessionManager.setLogin(true);
//                            sessionManager.setUsername(email);
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }) {
        @Nullable
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("pass", password);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
}*/

    // Login Process Here
    public void Login() {

        progressDialog.setTitle("Please wait...");
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        // Check if empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "You must provide email and password ", Toast.LENGTH_SHORT).show();

        }
        // Otherwise
        else {
            progressDialog.show();
            // use Volley StringRequest
            StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                    Log.d("response", "onResponse: " + response);
                    try {
                        JSONObject object = new JSONObject(response);
                        String success = object.getString("success");
//                        Log.d("check", "onResponse: " + success);

                        if (success.equals("1")) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();

                            //store login in session
                            sessionManager.setLogin(true);
                            sessionManager.setUsername(email);

                            /*//Set Profile
                            DatabaseHelper helper= new DatabaseHelper();
                            helper.databaseHelper(getApplicationContext(), sessionManager.getEmail(), sessionManager.getPassword(), new VolleyCallBack() {
                                @Override
                                public void onSuccessResponse(List<UserGetterSetter> list) {

                                }
                            });*/

                            //Redirect Activity
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "JSon Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("pass", password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this, null);
            requestQueue.add(request);
        }
    }


         private void login(String em, String pw) {

             progressDialog.show();

             if (password.equals("")) {
                 editTextPassword.setError("Please enter password");
                 progressDialog.dismiss();
             }
             if (email.equals("babitamoo333@gmail.com") && password.equals("root")) {
                 sessionManager.setLogin(true);
                 sessionManager.setUsername(email);
                 moveToMainActivity();
                 progressDialog.dismiss();

             } else {
                 Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                 progressDialog.dismiss();
             }
         }/*

         *//*

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.254.191/travel/login_practice.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String  success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")){
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject object= jsonArray.getJSONObject(i);
                                    String  firstname=object.getString("firstname").trim();
                                    String  lastname=object.getString("lastname").trim();
                                    String email=object.getString("email").trim();

                                    Toast.makeText(LoginActivity.this, "Success Login. \nYour firstname: " + firstname +
                                            "\nYour lastname: " +lastname +
                                            "\nYour email: " +email, Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                }
                            }
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error " + error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String ,String> params=new HashMap<>();
                params.put("email",em);
                params.put("password",pw);
                return params;

            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
*//*

    }*/

        private void moveToMainActivity() {
            Intent intent = new Intent(LoginActivity.this, IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    /*private void login(final String email, final String password) {

        progressDialog.show();

        // get response from the server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        *//*
                        // ATTEMPT 1
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean responsestatus = jsonObject.getBoolean("success");
                            if (responsestatus) {
                                String firstname = jsonObject.getString("firstname").trim();
                                String lastname = jsonObject.getString("lastname").trim();
                                String email = jsonObject.getString("email").trim();
                                Intent intent = new Intent(LoginActivity.this, Success.class);
                                intent.putExtra("firstname", firstname);
                                intent.putExtra("lastname", lastname);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//*

         *//*
                              //ATTEMPT 2
                              try {
                               JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String firstname = object.getString("firstname").trim();
                                    String lastname = object.getString("lastname").trim();
                                    String email = object.getString("email").trim();

                                    sessionManager.createSession(firstname, lastname, email);

                                    Toast.makeText(LoginActivity.this,
                                            "Success Login. \nYour full name: "
                                                    + firstname + "\nYour last name"
                                                    + lastname + "\n Your Email: "
                                                    + email, Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(LoginActivity.this, Success.class);
                                    intent.putExtra("firstname", firstname);
                                    intent.putExtra("lastname", lastname);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                     progressDialog.dismiss();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "JSON EXCEPTION: " + e.toString(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }*//*

                        //ATTEMPT 3
                        progressDialog.dismiss();
                        if (response.equalsIgnoreCase("found")) {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {

                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                        }

                       *//* //ATTEMPT 4
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String result=jsonObject.getString("status");
                            if(result.equals("success")){
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//*

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("password", password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    *//*else {
        Toast.makeText(getApplicationContext(), "Fill the empty fields", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }*//*
         */

        /*onclick listener for create an account and go to registration page*/
        public void register (View view){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }

        /*  methods created by implementing TextWatcher and Compound Button*/
        @Override
        public void beforeTextChanged (CharSequence s,int start, int count, int after){

        }

        @Override
        public void onTextChanged (CharSequence s,int start, int before, int count){
            managePrefs();
        }

        @Override
        public void afterTextChanged (Editable s){

        }

        @Override
        public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
            managePrefs();
        }

        private void managePrefs () {
            if (chRemember.isChecked()) {
                editor.putString(SessionManager.Email, editTextEmail.getText().toString().trim());
                editor.putString(SessionManager.Password, editTextPassword.getText().toString().trim());
                editor.putBoolean(SessionManager.REMEMBER, true);
                editor.apply();
            } else {
                editor.putString(SessionManager.Email, "");
                editor.putString(SessionManager.Password, "");
                editor.putBoolean(SessionManager.REMEMBER, false);
                editor.apply();
            }
        }
        /*implemented methods ends*/

        public void forgetPw (View view){
            startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            finish();
        }


    }