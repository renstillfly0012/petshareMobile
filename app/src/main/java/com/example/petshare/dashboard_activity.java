package com.example.petshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class dashboard_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtUser,txtRole;
    ImageView imgUserImg;
    Log log;
    String id,name,role_id,image,status,imgUrl;
    private Intent intent;
    private SharedPreferences sharedPreferences;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


//
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);
//        menu.findItem(R.id.nav_view_profile).setVisible(false);
//        menu.findItem(R.id.nav_edit).setVisible(false);



        //getting data from mainACtivity
//        Intent intent = getIntent();
//        id = intent.getStringExtra("id");
//        name = intent.getStringExtra("name");
//        role_id = intent.getStringExtra("role_id");
//
//        image = intent.getStringExtra("image");
//        status = intent.getStringExtra("status");

        sharedPreferences = getSharedPreferences("KEY_USER_INFO", MODE_PRIVATE);
        id = sharedPreferences.getString("KEY_ID", null);
        name = sharedPreferences.getString("KEY_NAME", null);
        role_id = sharedPreferences.getString("KEY_ROLE_ID", null);
        image = sharedPreferences.getString("KEY_IMAGE", null);
        status = sharedPreferences.getString("KEY_STATUS", null);



       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        View header = navigationView.getHeaderView(0);
        txtUser = header.findViewById(R.id.txtUsername);
        txtRole = header.findViewById(R.id.txtRole);
        imgUserImg = header.findViewById(R.id.imgUserImg);
        imgUrl = "https://pet-share.com/assets/images/"+image;
        Glide.with(this).load(imgUrl).into(imgUserImg);
        txtUser.setText(name);
//        if(role_id == "1"){
//            txtRole.setText("Foster User");
//        }else{
//            txtRole.setText("Admin User");
//        }

        setRole(getRole(role_id));





    }

    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_adopt:
                intent = new Intent(this, howToAdopt.class);
                startActivity(intent);
                break;
            case R.id.nav_report:
                break;
            case R.id.nav_donate:
                break;
            case R.id.nav_view_profile:
                intent = new Intent(this, viewProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_edit:
                intent = new Intent(this, editProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                intent = new Intent(this, MainActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(this, "Log out Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

        }

        drawerLayout.closeDrawer((GravityCompat.START));

        return true;
    }

    public String getRole(String role_id){
        return role_id == "1" ? "Admin User": "Foster User";
    }

    public void setRole(String role_id){
        txtRole.setText(role_id);
    }
}