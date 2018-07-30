package com.example.ismailamassi.bmi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                            Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
                        },
                        error -> {
                            progressDialog.dismiss();
                            switch (error.networkResponse.statusCode) {
                                case 404:
                                    email_ed.requestFocus();
                                    email_ed.setError("البريد الالكتروني غير مسجل");
                                    break;
                                case 500:
                                    Toast.makeText(this, "لقد حدث خطأ ما", Toast.LENGTH_LONG).show();
                            } }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
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
}
