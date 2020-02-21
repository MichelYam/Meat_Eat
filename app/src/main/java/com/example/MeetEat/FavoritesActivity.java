package com.example.MeetEat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class FavoritesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    SessionManager sessionManager;

    private TextView name, email;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        sessionManager = new SessionManager(this);
        sessionManager.checklogin();
        Toolbar toolbar;


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        HashMap<String, String> user = sessionManager.getUserDetail();

        //retrieves user information
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        name.setText(mName);
        email.setText(mEmail);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //BottomNavigationView
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.home_item) {
                    Intent intentHome = new Intent(FavoritesActivity.this, HomeActivity.class);
                    startActivity(intentHome);

                } else if (item.getItemId() == R.id.search_item) {
                    Intent intentSearch = new Intent(FavoritesActivity.this, SearchActivity.class);
                    startActivity(intentSearch);

                } else if (item.getItemId() == R.id.favorites_item) {
                    Intent intent_Favorites = new Intent(FavoritesActivity.this, FavoritesActivity.class);
                    startActivity(intent_Favorites);

                }

                return false;
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_profile) {
            Intent profileIntent = new Intent(FavoritesActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_recipes:
                Intent recipesIntent = new Intent(FavoritesActivity.this, RecipesActivity.class);
                startActivity(recipesIntent);
                break;
            case R.id.nav_about:
                Intent aboutIntent = new Intent(FavoritesActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.nav_contacts:
                Intent contactsIntent = new Intent(FavoritesActivity.this, ContactsActivity.class);
                startActivity(contactsIntent);
                break;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(FavoritesActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.nav_legal:
                Intent legalIntent = new Intent(FavoritesActivity.this, LegalActivity.class);
                startActivity(legalIntent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}