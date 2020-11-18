package com.example.petshare.reports;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshare.R;

public class reportActivity extends AppCompatActivity {
    private double address_lat,address_lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    private double setLat(double lat){
        return address_lat = lat;
    }

    private double setLng(double lng){
        return address_lng = lng;
    }
}