package com.example.petshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
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
//                if (!(email.isEmpty() && password.isEmpty())) {
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

        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("http://pet-share.com/api/guest/login?email=" + email + "&password=" + password)
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


                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            lblEmail.setText(myResponse);
                            Toast.makeText(getBaseContext(), myResponse, LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


    }
}