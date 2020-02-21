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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView name, email;
    private Button btn_logout;
    private DrawerLayout drawer;

    Toolbar toolbar;
    SessionManager sessionManager;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        sessionManager = new SessionManager(this);
        sessionManager.checklogin();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new AboutFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_about);
//        }


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        btn_logout = findViewById(R.id.btn_logout);
        HashMap<String, String> user = sessionManager.getUserDetail();

        //retrieves user information
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);

        name.setText(mName);
        email.setText(mEmail);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //BottomNavigationView
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home_item) {
                    Intent intentHome = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return true;
                } else if (item.getItemId() == R.id.search_item) {
                    Intent intentSearch = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(intentSearch);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    return true;

                } else if (item.getItemId() == R.id.favorites_item) {
                    Intent intent_Favorites = new Intent(HomeActivity.this, FavoritesActivity.class);
                    startActivity(intent_Favorites);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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

        if (id == R.id.action_profile) {
            Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
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
                Intent recipesIntent = new Intent(HomeActivity.this, RecipesActivity.class);
                startActivity(recipesIntent);
                break;
            case R.id.nav_about:
                Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.nav_contacts:
                Intent contactsIntent = new Intent(HomeActivity.this, ContactsActivity.class);
                startActivity(contactsIntent);
                break;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.nav_legal:
                Intent legalIntent = new Intent(HomeActivity.this, LegalActivity.class);
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

}
