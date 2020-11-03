package com.example.petshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtName, txtEmail, txtPassword, txtConfirm;
    private Button btnSubmit, btnCancel;
    private String name, email, password, confirm;
    private Intent intent;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirm = findViewById(R.id.txtConfirm);

        name = txtName.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();
        confirm = txtConfirm.getText().toString();


        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password != confirm) {
                    Toast.makeText(getBaseContext(), "Password and confirm didn't match", Toast.LENGTH_SHORT).show();
                }
                submitForm();

            }
        });
    }

    private void submitForm() {

        name = txtName.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();
        confirm = txtConfirm.getText().toString().trim();
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Processing");
        dialog.show();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody formBody = new FormBody.Builder()
                .add("type", JSON.toString())
                .add("name", name)
                .add("email", email)
                .add("password", password)
                .add("confirm", confirm)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.REGISTER) //which is http://pet-share.com/api/guest/register
//              .url("http://pet-share.com/api/guest/register?name="+name+"&email="+email+"&password="+password+"&password_confirmation="+confirm)
//                .method("POST", formBody)
                .post(formBody)
                .build();

//        Log.i("Response Message", ""+request.body());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
//                    final String myResponse = response.body().string();
                    dialog.dismiss();
                    Log.i("Response Message", ""+response);

                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getBaseContext(), "You have successfully registered", LENGTH_LONG).show();
                            intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dialog.dismiss();
                    Toast.makeText(getBaseContext(), "There is something in your registration\nPlease Try again.", LENGTH_LONG).show();
                            Log.i("Response Message", ""+response);
                        }
                    });
                }
            }

        });


    }
}