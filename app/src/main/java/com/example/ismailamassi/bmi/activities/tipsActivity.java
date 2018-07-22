package com.example.ismailamassi.bmi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.adapters.ProfileAdapter;
import com.example.ismailamassi.bmi.adapters.TipsAdapter;
import com.example.ismailamassi.bmi.items.ProfileItem;
import com.example.ismailamassi.bmi.items.TipsItem;

import java.util.ArrayList;

public class tipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        ArrayList<TipsItem> items = new ArrayList<>();
        addTipsItem(items ,R.drawable.sample_0,"هذا النص تجريبي رقم 1","هذا النص تجريبي رقم 1 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_1,"هذا النص تجريبي رقم 2","هذا النص تجريبي رقم 2 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_2,"هذا النص تجريبي رقم 3","هذا النص تجريبي رقم 3 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_3,"هذا النص تجريبي رقم 3","هذا النص تجريبي رقم 3 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_4,"هذا النص تجريبي رقم 3","هذا النص تجريبي رقم 3 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_5,"هذا النص تجريبي رقم 3","هذا النص تجريبي رقم 3 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_6,"هذا النص تجريبي رقم 3","هذا النص تجريبي رقم 3 ويمكنك إستبداله بنص مماثل في أي وقت تريده");
        addTipsItem(items ,R.drawable.sample_7,"هذا النص تجريبي رقم 3","هذا النص تجريبي رقم 3 ويمكنك إستبداله بنص مماثل في أي وقت تريده");


        RecyclerView recyclerView = findViewById(R.id.rv);

        TipsAdapter adapter = new TipsAdapter(this, items);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void addTipsItem(ArrayList<TipsItem> items ,int tips_img , String tips_title ,String tips_descriptions) {
        TipsItem item = new TipsItem();
        item.tips_img = tips_img;
        item.tips_title = tips_title;
        item.tips_descriptions = tips_descriptions;
        items.add(item);
    }
}
