package com.example.ismailamassi.bmi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.UIApplication;
import com.example.ismailamassi.bmi.helpers.AppConstant;
import com.example.ismailamassi.bmi.helpers.Methods;
import com.example.ismailamassi.bmi.helpers.Validation;
import com.example.ismailamassi.bmi.helpers.webservices.ApiUrls;
import com.example.ismailamassi.bmi.helpers.webservices.ICompletionListener;
import com.example.ismailamassi.bmi.helpers.SharedPreferencesUtils;
import com.example.ismailamassi.bmi.helpers.webservices.VolleyRequests;
import com.example.ismailamassi.bmi.models.User;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email_ed, password_ed;
    private TextView forget_pass_tv, new_account;
    private Button login_btn;
    private ImageButton fb_ib, gl_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        onClickItem();
    }

    private void onClickItem() {
        new_account.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
        fb_ib.setOnClickListener(v->{});
        gl_ib.setOnClickListener(v->{});
        login_btn.setOnClickListener(v -> {
            if (!Validation.isEmpty(email_ed, "هذا الحقل مطلوب") &&
                    !Validation.isEmpty(password_ed, "هذا الحقل مطلوب") &&
                    Validation.isValidEmail(email_ed, "أدخل بريد الكتروني صالح")) {

                ProgressDialog progressDialog = UIApplication.getInstance().getProgressDialog(this, "الرجاء الانتظار....", false);
                progressDialog.show();

                Map<String, String> postParam = new HashMap<String, String>();
                postParam.put("email", Methods.getStringFromEditText(email_ed));
                postParam.put("password", Methods.getStringFromEditText(password_ed));

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        ApiUrls.LOGIN_URL,
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
                                        email_ed.setError("البريد الالكتروني غير مستخدم");
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
    }

    private void bindViews() {
            email_ed = findViewById(R.id.username_ed);
        password_ed = findViewById(R.id.password_ed);
        forget_pass_tv = findViewById(R.id.forget_pass_tv);
        new_account = findViewById(R.id.new_account);
        login_btn = findViewById(R.id.login_btn);
        fb_ib = findViewById(R.id.fb_ib);
        gl_ib = findViewById(R.id.gl_ib);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
