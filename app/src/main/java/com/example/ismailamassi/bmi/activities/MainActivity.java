package com.example.ismailamassi.bmi.activities;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.adapters.TapAdapter;
import com.example.ismailamassi.bmi.helpers.SharedPreferencesUtils;
import com.example.ismailamassi.bmi.models.User;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new TapAdapter(getSupportFragmentManager()));

        tabs.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabs.getTabAt(1)).select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!SharedPreferencesUtils.getUserRole())
            getMenuInflater().inflate(R.menu.main_menu, menu);
        else
            getMenuInflater().inflate(R.menu.admin_menu, menu);

        return !SharedPreferencesUtils.getUserID().isEmpty();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.messages:
                return true;
            case R.id.my_profile:
                my_profile_action();
                return true;
            case R.id.setting:
                setting_action();
                return true;
            case R.id.about_app:
                about_app_action();
                return true;
            case R.id.other_apps:
                other_apps_action();
                return true;
            case R.id.logout:
                SharedPreferencesUtils.clearUser();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void my_profile_action() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    private void setting_action() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    private void about_app_action() {
        Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(intent);
    }
    private void other_apps_action() {
//        Intent intent = new Intent(MainActivity.this, otherAppsActivity.class);
//        startActivity(intent);
    }
}
