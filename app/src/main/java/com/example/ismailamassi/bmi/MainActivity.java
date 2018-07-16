package com.example.ismailamassi.bmi;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        final EditText weight = findViewById(R.id.weight);
        final EditText height = findViewById(R.id.height);
        final TextView title = findViewById(R.id.title);
        Button calc = findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                title.setVisibility(View.VISIBLE);
                TextView number = findViewById(R.id.number);
                number.setVisibility(View.VISIBLE);
                TextView label = findViewById(R.id.label);
                label.setVisibility(View.VISIBLE);

                try {

                    int w = Integer.parseInt(weight.getText().toString());
                    int h = Integer.parseInt(height.getText().toString());
                    if (w > 250) {
                        label.setText(R.string.wrong_weight);
                    } else if (h > 250) {
                        label.setText(R.string.wrong_height);
                    } else {
                        Button tips = findViewById(R.id.tips);
                        tips.setVisibility(View.VISIBLE);

                        double finalH = h / 100d;
                        double bmi = w / (finalH * finalH);
                        bmi=(bmi/100d)*100;
                        number.setText((Math.round(bmi*100d))/100d +"");
                        double more = (25 *(finalH *finalH));
                        int moreWeight = (int) (Math.round(more*100d)/100d);

                        double less = (18.5 *(finalH *finalH));
                        int lessWeight = (int) (Math.round(less*100d)/100d);

                        if (bmi < 18.5) {
                            number.setTextColor(getResources().getColor(R.color.blue));
                            label.setTextColor(getResources().getColor(R.color.blue));
                            label.setText("تحتاج إلى زيادة وزنك " + (lessWeight - w) + " Kg لتصبح مثالي.");
                            tips.setBackground(getResources().getDrawable(R.drawable.btn_bg));
                            tips.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Intent intent = new Intent(MainActivity.this, tips_to_less_weight.class);
                                    //startActivity(intent);
                                }
                            });
                            tips.setText(" إقرأ إرشادات صحية لزيادة الوزن ");
                        } else if (bmi >= 18.5 && bmi < 25) {
                            number.setTextColor(getResources().getColor(R.color.green));
                            label.setTextColor(getResources().getColor(R.color.green));
                            label.setText("وزنك مثالي ");
                            tips.setVisibility(View.INVISIBLE);
                        } else if (bmi >= 25 && bmi < 30) {
                            number.setTextColor(getResources().getColor(R.color.yallow));
                            label.setTextColor(getResources().getColor(R.color.yallow));
                            label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");
                            tips.setBackground(getResources().getDrawable(R.drawable.btn_yallow));
                            tips.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent = new Intent(MainActivity.this, tips_to_more_wieght.class);
//                                    startActivity(intent);
                                }
                            });
                            tips.setText("إقرأ إرشادات صحية لتخفيف الوزن ");
                        } else if (bmi >= 30 && bmi < 35) {
                            number.setTextColor(getResources().getColor(R.color.orange));
                            label.setTextColor(getResources().getColor(R.color.orange));
                            label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");
                            tips.setBackground(getResources().getDrawable(R.drawable.btn_orange));
                            tips.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent = new Intent(MainActivity.this, tips_to_more_wieght.class);
//                                    startActivity(intent);
                                }
                            });
                            tips.setText("إقرأ إرشادات صحية لتخفيف الوزن ");
                        } else if (bmi > 35){
                            number.setTextColor(getResources().getColor(R.color.red));
                            label.setTextColor(getResources().getColor(R.color.red));
                            label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");
                            tips.setBackground(getResources().getDrawable(R.drawable.btn_red));
                            tips.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent = new Intent(MainActivity.this, tips_to_more_wieght.class);
//                                    startActivity(intent);
                                }
                            });
                            tips.setText("إقرأ إرشادات صحية لتخفيف الوزن ");
                        }

                    }
                } catch (InputMismatchException inputMismatchException) {
                    label.setText("يرجى إدخال قيمة صحيحة");
                } catch (Exception e) {
                    label.setText("يرجى إدخال قيمة صحيحة");
                }

            }
        });


//        ViewPager pager = findViewById(R.id.view_pager);
//        TabLayout tabLayout = findViewById(R.id.tabs);
//
//        setupPagerAdapter(pager);
//        tabLayout.setupWithViewPager(pager);
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_002_fetus);
//
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_004_body_mass_index);
//        tabLayout.getTabAt(1).select();



    }

//    private void setupPagerAdapter(ViewPager pager) {
//        MyViewPager myViewPager = new MyViewPager(getSupportFragmentManager());
//
//        myViewPager.addFragment(new BlankFragment(), "Blank");
//        myViewPager.addFragment(new BMIFragment(), "BMI");
//
//        pager.setAdapter(myViewPager);
//    }
}
