package com.example.ismailamassi.bmi.fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.UIApplication;
import com.example.ismailamassi.bmi.activities.LoginActivity;
import com.example.ismailamassi.bmi.activities.MainActivity;
import com.example.ismailamassi.bmi.helpers.Methods;
import com.example.ismailamassi.bmi.helpers.SharedPreferencesUtils;
import com.example.ismailamassi.bmi.helpers.Validation;
import com.example.ismailamassi.bmi.helpers.webservices.ApiUrls;
import com.example.ismailamassi.bmi.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class BmiFragment extends Fragment {
    private EditText weight, height;
    private TextView label, goto_login, number_dialog, label_dialog, less_tv_dialog, more_tv_dialog,go_to_login_dialog;
    private Button calc, dialogButton;
private double bmi;
private AlertDialog alertDialog;
    public BmiFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);

        calc = view.findViewById(R.id.calc);
        label = view.findViewById(R.id.label);
        goto_login = view.findViewById(R.id.goto_login);

        if (SharedPreferencesUtils.getUserID().isEmpty())
            goto_login.setVisibility(View.VISIBLE);
        else
            goto_login.setVisibility(View.INVISIBLE);


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calc.setOnClickListener(v -> {
            if (!Validation.isEmpty(height, "الحقل مطلوب")
                    && !Validation.isEmpty(weight, "الحقل مطلوب")) {

                try {
                    int w = Integer.parseInt(weight.getText().toString());
                    int h = Integer.parseInt(height.getText().toString());

                    if (w > 250) {
                        label.setText(R.string.wrong_weight);
                        label.setVisibility(View.VISIBLE);
                    } else if (h > 250) {
                        label.setText(R.string.wrong_height);
                        label.setVisibility(View.VISIBLE);
                    } else {
                        label.setVisibility(View.INVISIBLE);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        View dialogView = getLayoutInflater().inflate(R.layout.bmi_result_dialog, null);

                        number_dialog = dialogView.findViewById(R.id.number_dialog);
                        label_dialog = dialogView.findViewById(R.id.label_dialog);
                        less_tv_dialog = dialogView.findViewById(R.id.less_tv_dialog);
                        more_tv_dialog = dialogView.findViewById(R.id.more_tv_dialog);
                        dialogButton = dialogView.findViewById(R.id.save_bmi_button);
                        go_to_login_dialog = dialogView.findViewById(R.id.go_to_login_dialog);
                        if (SharedPreferencesUtils.getUserID().isEmpty()) {
                            dialogButton.setVisibility(View.GONE);
                            go_to_login_dialog.setVisibility(View.VISIBLE);
                        }
                        else {
                            dialogButton.setVisibility(View.VISIBLE);
                            go_to_login_dialog.setVisibility(View.GONE);
                        }

                        double finalH = h / 100d;
                        bmi = w / (finalH * finalH);
                        bmi = (bmi / 100d) * 100;

                        number_dialog.setText((Math.round(bmi * 100d)) / 100d + "");
                        double more = (25 * (finalH * finalH));
                        int moreWeight = (int) (Math.round(more * 100d) / 100d);

                        double less = (18.5 * (finalH * finalH));
                        int lessWeight = (int) (Math.round(less * 100d) / 100d);

                        if (bmi < 18.5) {
                            number_dialog.setTextColor(getResources().getColor(R.color.blue));
                            label_dialog.setTextColor(getResources().getColor(R.color.blue));
                            label_dialog.setText("تحتاج إلى زيادة وزنك " + (lessWeight - w) + " Kg لتصبح مثالي.");
                            less_tv_dialog.setVisibility(View.INVISIBLE);
                            more_tv_dialog.setText("18.5  مثالي ");
                            more_tv_dialog.setTextColor(getResources().getColor(R.color.green));
                        } else if (bmi >= 18.5 && bmi < 25) {
                            number_dialog.setTextColor(getResources().getColor(R.color.green));
                            label_dialog.setTextColor(getResources().getColor(R.color.green));
                            label_dialog.setText("وزنك مثالي ");

                            less_tv_dialog.setText("18.5 نحيف");
                            less_tv_dialog.setTextColor(getResources().getColor(R.color.blue));

                            more_tv_dialog.setText("25 سمين");
                            more_tv_dialog.setTextColor(getResources().getColor(R.color.yallow));

                        } else if (bmi >= 25 && bmi < 30) {
                            number_dialog.setTextColor(getResources().getColor(R.color.yallow));
                            label_dialog.setTextColor(getResources().getColor(R.color.yallow));
                            label_dialog.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");

                            less_tv_dialog.setText("25 مثالي");
                            less_tv_dialog.setTextColor(getResources().getColor(R.color.green));

                            more_tv_dialog.setText("30 سمنة");
                            more_tv_dialog.setTextColor(getResources().getColor(R.color.orange));

                        } else if (bmi >= 30 && bmi < 35) {
                            number_dialog.setTextColor(getResources().getColor(R.color.orange));
                            label_dialog.setTextColor(getResources().getColor(R.color.orange));
                            label_dialog.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");

                            less_tv_dialog.setText("30 سمين");
                            less_tv_dialog.setTextColor(getResources().getColor(R.color.yallow));

                            more_tv_dialog.setText("35 سمنة مفرطة");
                            more_tv_dialog.setTextColor(getResources().getColor(R.color.red));

                        } else if (bmi > 35) {
                            number_dialog.setTextColor(getResources().getColor(R.color.red));
                            label_dialog.setTextColor(getResources().getColor(R.color.red));
                            label_dialog.setText("تحتاج إلى تخفيف " + (w - moreWeight) + " Kg من وزنك لتصبح مثالي.");

                            less_tv_dialog.setText("35 سمنة");
                            less_tv_dialog.setTextColor(getResources().getColor(R.color.orange));
                        }

                        dialogButton.setOnClickListener(v2 -> {
                            saveNewStatus(h, w, bmi);
                        });
                        builder.setView(dialogView);

                         alertDialog = builder.create();
                        alertDialog.show();
                    }
                } catch (InputMismatchException inputMismatchException) {
                    label.setText("يرجى إدخال قيمة صحيحة");
                } catch (Exception e) {
                    label.setText("يرجى إدخال قيمة صحيحة");
                }
            }
        });

        goto_login.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
    }

    private void saveNewStatus(int h, int w, double bmi) {
        String userId = SharedPreferencesUtils.getUserID();
        String token = SharedPreferencesUtils.getUserToken();

        ProgressDialog progressDialog = UIApplication.getInstance().getProgressDialog(getActivity(), "الرجاء الانتظار....", false);
        progressDialog.show();

        Map<String, String> postParam = new HashMap<String, String>();
        postParam.put("userId", userId);
        postParam.put("height", String.valueOf(h));
        postParam.put("weight", String.valueOf(w));
        postParam.put("bmi", String.valueOf(bmi));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                ApiUrls.STATUS_URL,
                new JSONObject(postParam),
                response -> {
                    progressDialog.dismiss();
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    try {
                        if (response.getBoolean("status")){
                            Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    Log.d("dataII", "onClickItem: " + error.getMessage());
                    if (error.networkResponse != null){
                        if (error.networkResponse.statusCode == 500)
                            Toast.makeText(getActivity(), "لقد حدث خطأ ما", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authrization", token);
                return headers;
            }
        };
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

}
