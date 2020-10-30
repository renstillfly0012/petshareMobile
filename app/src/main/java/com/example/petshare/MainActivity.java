package com.example.petshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private Button SignUp, SignIn;
    private EditText Email, Password;
    private TextView lblEmail;
    private String email, password;
    private HashMap<String, String> jsonUserData;
    private Intent intent;
    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lblEmail = findViewById(R.id.lblEmail);

        Email = findViewById(R.id.txtEmail);
        Password = findViewById(R.id.txtPassword);

        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();

        SignUp = findViewById(R.id.btnSignup);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //function for new activity
                openSignUp();
            }
        });

        SignIn = findViewById(R.id.btnLogin);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //function for new activity

//                if(email.isEmpty())
//                {
//                    Email.setError();
//                }
//                else if(password.isEmpty()){
//
//                }
//                else if (!(email.isEmpty() && password.isEmpty())) {
                    try {
                        login();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                } else {
//                    Toast.makeText(getBaseContext(), "All Fields Is Required!", Toast.LENGTH_SHORT).show();
//                }

            }
        });

    }



    public void openSignUp() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login() throws IOException {

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Processing");
        dialog.show();

        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        final Request request = new Request.Builder()
                .url("http://pet-share.com/api/guest/login?email=" + email + "&password=" + password)
//                .url("http://pet-share.com/api/guest/users/")
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    try {
                        //get jsondata
                        jsonUserData = new HashMap<String, String>();
                        jsonUserData.put("id", new JSONObject(myResponse).getString("id"));
                        jsonUserData.put("name", new JSONObject(myResponse).getString("name"));
//                        jsonUserData.put("email", new JSONObject(myResponse).getString("email"));
//                        jsonUserData.put("email_verified_at", new JSONObject(myResponse).getString("email_verified_at"));
//                        jsonUserData.put("password", new JSONObject(myResponse).getString("password"));
                        jsonUserData.put("role_id", new JSONObject(myResponse).getString("role_id"));
                        jsonUserData.put("image", new JSONObject(myResponse).getString("image"));
                        jsonUserData.put("status", new JSONObject(myResponse).getString("status"));




                        Log.i("Test Data", ""+jsonUserData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                          lblEmail.setText(jsonUserData.get("name"));
                            dialog.dismiss();
                            Toast.makeText(getBaseContext(), "Welcome "+jsonUserData.get("name")+"\n You have Successfully Login ", LENGTH_LONG).show();
                            intent  = new Intent(getBaseContext(), dashboard_activity.class);
                            intent.putExtra("id", jsonUserData.get("id"));
                            intent.putExtra("name", jsonUserData.get("name"));
                            intent.putExtra("role_id", jsonUserData.get("role_id"));
                            intent.putExtra("image", jsonUserData.get("image"));
                            intent.putExtra("status", jsonUserData.get("status"));
                            startActivity(intent);
                        }
                    });

                }
            }
        });


    }
}