package com.example.ismailamassi.bmi.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.activities.MainActivity;
import com.example.ismailamassi.bmi.activities.ProfileActivity;
import com.example.ismailamassi.bmi.activities.loginActivity;

import java.util.InputMismatchException;

/**
 * A simple {@link Fragment} subclass.
 */
public class bmiFragment extends Fragment {


    public bmiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);
        final EditText weight = view.findViewById(R.id.weight);
        final EditText height = view.findViewById(R.id.height);
        final TextView title = view.findViewById(R.id.title);
        final Button calc = view.findViewById(R.id.calc);
        final TextView number = view.findViewById(R.id.number);
        final TextView label = view.findViewById(R.id.label);
        final TextView less_tv = view.findViewById(R.id.less_tv);
        final TextView more_tv = view.findViewById(R.id.more_tv);

        calc.setOnClickListener(v -> {
            title.setVisibility(View.VISIBLE);
            less_tv.setVisibility(View.VISIBLE);
            number.setVisibility(View.VISIBLE);
            more_tv.setVisibility(View.VISIBLE);
            label.setVisibility(View.VISIBLE);

            try {
                int w = Integer.parseInt(weight.getText().toString());
                int h = Integer.parseInt(height.getText().toString());
                if (w > 250) {
                    label.setText(R.string.wrong_weight);
                } else if (h > 250) {
                    label.setText(R.string.wrong_height);
                } else {
                    Button sign_to_tip_btn = view.findViewById(R.id.sign_to_tip_btn);
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
                                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                                startActivity(intent);
                            }
                        });

                    } else {
                        sign_to_tip_btn.setText(" سجل الدخول للحصول على نصائح و متابعة صحتك ");
                        sign_to_tip_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), loginActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    if (bmi < 18.5) {
                        number.setTextColor(getResources().getColor(R.color.blue));
                        label.setTextColor(getResources().getColor(R.color.blue));
                        label.setText("تحتاج إلى زيادة وزنك " + (lessWeight - w) + " Kg لتصبح مثالي.");
                        less_tv.setVisibility(View.INVISIBLE);
                        more_tv.setText(" < 18.5" +
                                " مثالي ");
                        more_tv.setTextColor(getResources().getColor(R.color.green));
                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_bg));
                    } else if (bmi >= 18.5 && bmi < 25) {
                        number.setTextColor(getResources().getColor(R.color.green));
                        label.setTextColor(getResources().getColor(R.color.green));
                        label.setText("وزنك مثالي ");

                        less_tv.setText(" > 18.5" +
                                " نحيف ");
                        less_tv.setTextColor(getResources().getColor(R.color.blue));

                        more_tv.setText(" < 25" +
                                " سمين ");
                        more_tv.setTextColor(getResources().getColor(R.color.yallow));

                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_green));
                    } else if (bmi >= 25 && bmi < 30) {
                        number.setTextColor(getResources().getColor(R.color.yallow));
                        label.setTextColor(getResources().getColor(R.color.yallow));
                        label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");

                        less_tv.setText(" > 25" +
                                " مثالي ");
                        less_tv.setTextColor(getResources().getColor(R.color.green));

                        more_tv.setText(" < 30" +
                                " سمنة ");
                        more_tv.setTextColor(getResources().getColor(R.color.orange));

                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_yallow));
                    } else if (bmi >= 30 && bmi < 35) {
                        number.setTextColor(getResources().getColor(R.color.orange));
                        label.setTextColor(getResources().getColor(R.color.orange));
                        label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");

                        less_tv.setText(" > 30" +
                                " سمين ");
                        less_tv.setTextColor(getResources().getColor(R.color.yallow));

                        more_tv.setText(" < 35" +
                                " سمنة مفرطة ");
                        more_tv.setTextColor(getResources().getColor(R.color.red));

                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_orange));
                    } else if (bmi > 35) {
                        number.setTextColor(getResources().getColor(R.color.red));
                        label.setTextColor(getResources().getColor(R.color.red));
                        label.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");

                        less_tv.setText(" > 35" +
                                " سمنة ");
                        less_tv.setTextColor(getResources().getColor(R.color.orange));

                        sign_to_tip_btn.setBackground(getResources().getDrawable(R.drawable.btn_red));
                    }
                }
            } catch (InputMismatchException inputMismatchException) {
                label.setText("يرجى إدخال قيمة صحيحة");
            } catch (Exception e) {
                label.setText("يرجى إدخال قيمة صحيحة");
            }

        });

        return view;

    }

}
