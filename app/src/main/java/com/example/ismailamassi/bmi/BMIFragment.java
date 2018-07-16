package com.example.ismailamassi.bmi;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.InputMismatchException;


/**
 * A simple {@link Fragment} subclass.
 */
public class BMIFragment extends Fragment {


    public BMIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_bmi, container, false);

        final EditText weight = view.findViewById(R.id.weight);
        final EditText height = view.findViewById(R.id.height);
        final TextView title = view.findViewById(R.id.title);
        Button calc = view.findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                title.setVisibility(View.VISIBLE);
                TextView number = view.findViewById(R.id.number);
                number.setVisibility(View.VISIBLE);
                TextView label = view.findViewById(R.id.label);
                label.setVisibility(View.VISIBLE);

                try {

                    int w = Integer.parseInt(weight.getText().toString());
                    int h = Integer.parseInt(height.getText().toString());
                    if (w > 250) {
                        label.setText(R.string.wrong_weight);
                    } else if (h > 250) {
                        label.setText(R.string.wrong_height);
                    } else {
                        Button tips = view.findViewById(R.id.tips);
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
                                    Intent intent = new Intent(getActivity(), tips_to_less_weight.class);
                                    startActivity(intent);
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
                                    Intent intent = new Intent(getActivity(), tips_to_more_wieght.class);
                                    startActivity(intent);
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
                                    Intent intent = new Intent(getActivity(), tips_to_more_wieght.class);
                                    startActivity(intent);
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
                                    Intent intent = new Intent(getActivity(), tips_to_more_wieght.class);
                                    startActivity(intent);
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


        return view;
    }
}
