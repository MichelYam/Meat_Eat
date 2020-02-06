package com.example.connectiondb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class FavoritesActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;

    private TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        sessionManager= new SessionManager(this);
        sessionManager.checklogin();

        HashMap<String, String> user=sessionManager.getUserDetail();

        //retrieves user information
        String mName =user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);

        name= findViewById(R.id.name);
        email = findViewById(R.id.email);

        name.setText(mName);
        email.setText(mEmail);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //BottomNavigationView
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                if (item.getItemId() == R.id.home_item) {
                    Intent intentHome = new Intent(FavoritesActivity.this,HomeActivity.class);
                    startActivity(intentHome);

                } else if (item.getItemId() == R.id.search_item) {
                    Intent intentSearch = new Intent(FavoritesActivity.this,HomeActivity.class);
                    startActivity(intentSearch);

                } else if (item.getItemId() == R.id.favorites_item) {
                    Intent intent_Favorites = new Intent(FavoritesActivity.this,FavoritesActivity.class);
                    startActivity(intent_Favorites);

                }

                return false;
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}