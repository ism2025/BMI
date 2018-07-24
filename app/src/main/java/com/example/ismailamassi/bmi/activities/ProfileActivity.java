package com.example.ismailamassi.bmi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.adapters.ProfileAdapter;
import com.example.ismailamassi.bmi.models.ProfileItem;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    Button show_tips_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button show_tips_btn = findViewById(R.id.show_tips_btn);
        show_tips_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileActivity.this, TipsActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<ProfileItem> items = new ArrayList<>();
        for (int i = 1; i <= 20; i++){
            ProfileItem item = new ProfileItem();
            item.date = "date " + i;
            item.height = (double)i;
            item.weight = (double)i;
            item.mass = (Math.round((Math.random()*40) * 100d) / 100d);
            items.add(item);
        }
        RecyclerView recyclerView = findViewById(R.id.rv);
        ProfileAdapter adapter = new ProfileAdapter(this,items);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
