package com.example.ismailamassi.bmi.activities;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.example.ismailamassi.bmi.R;

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

public class MainActivity extends AppCompatActivity {


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText weight = findViewById(R.id.weight);
        final EditText height = findViewById(R.id.height);
        final TextView title = findViewById(R.id.title);
        final Button calc = findViewById(R.id.calc);

        calc.setOnClickListener(v -> {

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
                    Button sign_to_tip_btn = findViewById(R.id.sign_to_tip_btn);
                    sign_to_tip_btn.setVisibility(View.VISIBLE);

                    double finalH = h / 100d;
                    double bmi = w / (finalH * finalH);
                    bmi = (bmi / 100d) * 100;
                    number.setText((Math.round(bmi * 100d)) / 100d + "");
                    double more = (25 * (finalH * finalH));
                    int moreWeight = (int) (Math.round(more * 100d) / 100d);

                    double less = (18.5 * (finalH * finalH));
                    int lessWeight = (int) (Math.round(less * 100d) / 100d);

                    final boolean isLogin = false;
                    if (isLogin == true) {
                        sign_to_tip_btn.setText(" الذهاب إلى الملف الشخصي للمتابعة ");
                        sign_to_tip_btn.setTextSize(22);
                        sign_to_tip_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }
                        });

                    } else {
                        sign_to_tip_btn.setText(" سجل الدخول للحصول على نصائح و متابعة صحتك ");
                        sign_to_tip_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    if (bmi < 18.5) {
                        number.setTextColor(getResources().getColor(R.color.blue));
                        label.setTextColor(getResources().getColor(R.color.blue));
                        label.setText("تحتاج إلى زيادة وزنك " + (lessWeight - w) + " Kg لتصبح مثالي.");
                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_bg));
                    } else if (bmi >= 18.5 && bmi < 25) {
                        number.setTextColor(getResources().getColor(R.color.green));
                        label.setTextColor(getResources().getColor(R.color.green));
                        label.setText("وزنك مثالي ");
                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_green));
                    } else if (bmi >= 25 && bmi < 30) {
                        number.setTextColor(getResources().getColor(R.color.yallow));
                        label.setTextColor(getResources().getColor(R.color.yallow));
                        label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");
                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_yallow));
//                            sign_to_tip_btn.setTextColor(getResources().getColor(R.color.black));
                    } else if (bmi >= 30 && bmi < 35) {
                        number.setTextColor(getResources().getColor(R.color.orange));
                        label.setTextColor(getResources().getColor(R.color.orange));
                        label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");
                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_orange));
                    } else if (bmi > 35) {
                        number.setTextColor(getResources().getColor(R.color.red));
                        label.setTextColor(getResources().getColor(R.color.red));
                        label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");
                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_red));
                    }
                }
            } catch (InputMismatchException inputMismatchException) {
                label.setText("يرجى إدخال قيمة صحيحة");
            } catch (Exception e) {
                label.setText("يرجى إدخال قيمة صحيحة");
            }

        });
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
        Intent intent = new Intent(MainActivity.this, infoActivity.class);
        startActivity(intent);
    }
    private void other_apps_action() {
//        Intent intent = new Intent(MainActivity.this, otherAppsActivity.class);
//        startActivity(intent);
    }
}
