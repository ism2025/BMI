package com.example.ismailamassi.bmi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.adapters.TipsAdapter;
import com.example.ismailamassi.bmi.helpers.AppConstant;
import com.example.ismailamassi.bmi.models.TipsItem;

import java.util.ArrayList;

public class TipsActivity extends AppCompatActivity {

    private ArrayList<TipsItem> tipsItems;

    private int[] tipsImagesRecourse;
    private String[] tipTitles;
    private String[] tipDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.rv);
        tipsImagesRecourse = AppConstant.tipsImagesRecourse;
        tipTitles = getResources().getStringArray(R.array.tips_titles);
        tipDescriptions = getResources().getStringArray(R.array.tips_descriptions);

        initTips();

        TipsAdapter adapter = new TipsAdapter(this, tipsItems);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initTips() {
        for (int i = 0; i < tipsImagesRecourse.length; i++)
            try {
                tipsItems.add(new TipsItem(tipsImagesRecourse[i], tipTitles[i], tipDescriptions[i]));
            } catch (Exception ignored) {
            }
    }
}
