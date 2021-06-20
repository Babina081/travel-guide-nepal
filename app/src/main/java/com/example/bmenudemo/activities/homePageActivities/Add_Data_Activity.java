package com.example.bmenudemo.activities.homePageActivities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.bmenudemo.constantsClass.Constants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Add_Data_Activity extends AppCompatActivity {

    private EditText txtPlaceName, txtDescription, txtPlaceLocation;
    private ImageView img;
    private Button btn_insert,browse,show;
//        private  String URL = "http://192.168.254.191/travel/insert.php";
    private ProgressDialog progressDialog;
    private String placename, description, placelocation;

    Bitmap bitmap;
    String encodeImageString;

//    private static final String URL = "http://192.168.254.191/travel/uploadimage.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__data_);

        //to bring back button in the app bar (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtPlaceName = (EditText) findViewById(R.id.txtPlaceName);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        txtPlaceLocation = (EditText) findViewById(R.id.txtPlaceLocation);


        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

       /* btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });*/

        progressDialog = new ProgressDialog(this);

        placename = description = placelocation="";

        img = (ImageView) findViewById(R.id.img);

        browse = (Button) findViewById(R.id.browse);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(Add_Data_Activity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
    }

    public void insert() {

        placename = txtPlaceName.getText().toString().trim();
        description = txtDescription.getText().toString().trim();
        placelocation = txtPlaceLocation.getText().toString().trim();


        if (!placename.equals("") && !description.equals("") && !placelocation.equals("")) {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_INSERT_PLACES, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Data Inserted")) {
                        Toast.makeText(getApplicationContext(), "Not inserted", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    } else {

                        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                    data.put("placename",placename);
                    data.put("description", description);
                    data.put("placelocation", placelocation);
                    data.put("upload", encodeImageString);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        } else {
            Toast.makeText(Add_Data_Activity.this, "Fill the empty fields", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                encodeBitmapimage(bitmap);
            } catch (Exception ex) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapimage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString = Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

    public void show(View view) {
        Intent intent = new Intent(Add_Data_Activity.this, MainActivity.class);
        startActivity(intent);
    }
}