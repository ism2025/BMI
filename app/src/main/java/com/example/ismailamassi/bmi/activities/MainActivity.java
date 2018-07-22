package com.example.ismailamassi.bmi.activities;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.adapters.TapAdapter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("ResourceAsColor")
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
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void my_profile_action() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    private void setting_action() {
        Intent intent = new Intent(MainActivity.this, settingActivity.class);
        startActivity(intent);
    }
    private void about_app_action() {
        Intent intent = new Intent(MainActivity.this, about_appActivity.class);
        startActivity(intent);
    }
    private void other_apps_action() {
//        Intent intent = new Intent(MainActivity.this, otherAppsActivity.class);
//        startActivity(intent);
    }
}
