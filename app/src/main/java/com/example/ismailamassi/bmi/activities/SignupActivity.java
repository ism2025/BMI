package com.example.ismailamassi.bmi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.UIApplication;
import com.example.ismailamassi.bmi.helpers.Methods;
import com.example.ismailamassi.bmi.helpers.SharedPreferencesUtils;
import com.example.ismailamassi.bmi.helpers.Validation;
import com.example.ismailamassi.bmi.helpers.webservices.ApiUrls;
import com.example.ismailamassi.bmi.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private EditText username_ed, email_ed, password_ed;
    private TextView dob_ed;
    private TextView have_account_tv;
    private Button register_btn ;
    private ImageButton fb_ib ,gl_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindViews();
        onClickItem();
    }

    private void bindViews() {
        username_ed = findViewById(R.id.username_ed);
        email_ed = findViewById(R.id.email_ed);
        password_ed = findViewById(R.id.password_ed);
        dob_ed = findViewById(R.id.dob_ed);
        have_account_tv = findViewById(R.id.have_account_tv);
        register_btn = findViewById(R.id.register_btn);
        fb_ib = findViewById(R.id.fb_ib);
        gl_ib = findViewById(R.id.gl_ib);
    }

    private void onClickItem(){
        dob_ed.setOnClickListener(v->{
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String data = year+ "-" + monthOfYear+"-"+dayOfMonth;
                        dob_ed.setText(data);
                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "DatePickerDialog");
        });

        have_account_tv.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this ,LoginActivity.class);
            startActivity(intent);
            finish();
        });

        register_btn.setOnClickListener((View v) -> {
            if (!Validation.isEmpty(username_ed, "هذا الحقل مطلوب")&&
                    !Validation.isEmpty(email_ed, "هذا الحقل مطلوب")&&
                    !Validation.isEmpty(password_ed, "هذا الحقل مطلوب")&&
                    !Validation.isEmpty(dob_ed, "هذا الحقل مطلوب")&&
                    Validation.isValidEmail(email_ed, "أدخل بريد الكتروني صالح")&&
                    Validation.isLengthCorrect(username_ed, 6,32, "أدخل الاسم من 6 الى 32 حرف فقط")&&
                    Validation.isLengthCorrect(password_ed, 6,32, "أدخل كلمة المرور من 6 الى 32 حرف")){

                ProgressDialog progressDialog = UIApplication.getInstance().getProgressDialog(this,"الرجاء الانتظار...." , false);
                progressDialog.show();

                Map<String, String> postParam= new HashMap<>();
                postParam.put("name", Methods.getStringFromEditText(username_ed));
                postParam.put("email", Methods.getStringFromEditText(email_ed));
                postParam.put("password", Methods.getStringFromEditText(password_ed));
                postParam.put("dateOfBirth", Methods.getStringFromTextView(dob_ed));

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        ApiUrls.REGISTER_URL,
                        new JSONObject(postParam),
                        response -> {
                            progressDialog.dismiss();
                            Log.d("dataII", "onClickItem: " + response.toString());
                            try {
                                if (response.getBoolean("status")){
                                    //Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
                                    Gson gson = new GsonBuilder().create();
                                    User user = gson.fromJson(response.getJSONObject("user").toString(), User.class);
                                    SharedPreferencesUtils.setUser(user);
                                    Toast.makeText(this, SharedPreferencesUtils.getUserID() + " " + SharedPreferencesUtils.getUserEmail() + " " + SharedPreferencesUtils.getUserName(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(this, "خطأ في البيانات", Toast.LENGTH_SHORT).show();
                                    password_ed.setText("");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {
                            progressDialog.dismiss();
                            Log.d("dataII", "onClickItem: " + error.getMessage());

                            //Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            if (error.networkResponse != null){
                                password_ed.setText("");
                                switch (error.networkResponse.statusCode){
                                    case 409:
                                        email_ed.requestFocus();
                                        email_ed.setError("البريد الالكتروني مستخدم");
                                        break;
                                    case 500:
                                        Toast.makeText(this, "لقد حدث خطأ ما", Toast.LENGTH_LONG).show();
                                }
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
            }
        });

        fb_ib.setOnClickListener(v->{

        });
        gl_ib.setOnClickListener(v->{

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
