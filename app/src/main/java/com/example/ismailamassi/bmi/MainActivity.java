package com.example.ismailamassi.bmi;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        setupPagerAdapter(pager);
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_002_fetus);

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_004_body_mass_index);
        tabLayout.getTabAt(1).select();



    }

    private void setupPagerAdapter(ViewPager pager) {
        MyViewPager myViewPager = new MyViewPager(getSupportFragmentManager());

        myViewPager.addFragment(new BlankFragment(), "Blank");
        myViewPager.addFragment(new BMIFragment(), "BMI");

        pager.setAdapter(myViewPager);
    }
}
