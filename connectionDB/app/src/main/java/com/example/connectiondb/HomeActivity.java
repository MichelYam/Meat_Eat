package com.example.connectiondb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

        private TextView name, email;
        private Button btn_logout;

    Toolbar toolbar;
    SessionManager sessionManager;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager= new SessionManager(this);
        sessionManager.checklogin();

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        name= findViewById(R.id.name);
        email = findViewById(R.id.email);

        btn_logout = findViewById(R.id.btn_logout);
        HashMap<String, String> user = sessionManager.getUserDetail();

        //retrieves user information
        String mName =user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);

        name.setText(mName);
        email.setText(mEmail);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //BottomNavigationView
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home_item) {
                    Intent intentHome = new Intent(HomeActivity.this,HomeActivity.class);
                    startActivity(intentHome);
                    return true;
                } else if (item.getItemId() == R.id.search_item) {
                    Intent intentSearch = new Intent(HomeActivity.this,HomeActivity.class);
                    startActivity(intentSearch);
                    return  true;

                } else if (item.getItemId() == R.id.favorites_item) {
                    Intent intent_Favorites = new Intent(HomeActivity.this,FavoritesActivity.class);
                    startActivity(intent_Favorites);
                    return true;
                }

                return false;
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about){
            Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        else if(id == R.id.action_profile){
            Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
