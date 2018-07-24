package com.example.ismailamassi.bmi.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.helpers.AppConstant;
import com.example.ismailamassi.bmi.helpers.ICompletionListener;
import com.example.ismailamassi.bmi.helpers.SharedPreferencesUtils;
import com.example.ismailamassi.bmi.helpers.VolleyRequests;
import com.example.ismailamassi.bmi.models.User;
import com.fourhcode.forhutils.FUtilsValidation;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText username_ed, password_ed;
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
            Intent intent = new Intent(LoginActivity.this ,SignupActivity.class);
            startActivity(intent);
        });

        login_btn.setOnClickListener(v -> {
            if (!FUtilsValidation.isEmpty(username_ed, "الحقل مطلوب")&&
                    FUtilsValidation.isValidEmail(username_ed, "ادخل بريد الكتروني صالح")&&
                    !FUtilsValidation.isEmpty(password_ed, "الحقل مطلوب")){
                if (!FUtilsValidation.isLengthCorrect(password_ed.getText().toString(), 6, 32)){
                    password_ed.setError("كلمة المر");
                }else {
                    VolleyRequests volleyRequests = new VolleyRequests();
                    //TODO Add Login Url
                    String url = "";
                    volleyRequests.login(url, username_ed.getText().toString(), password_ed.getText().toString(), new ICompletionListener() {
                        @Override
                        public void onCompletionListener(JSONObject jsonObject) {
                            if (jsonObject != null){
                                //TODO Implement Success login
                                int id;
                                String username;
                                String email;
                                String age;
                                int role;
                                String token;
                                try {
                                    id = jsonObject.getInt("user_id");
                                    username = jsonObject.getString("username");
                                    email = jsonObject.getString("user_email");
                                    age = jsonObject.getString("user_age");
                                    role = jsonObject.getInt("user_role");
                                    token = jsonObject.getString("user_token");
                                    User user = new User(id, username, email,age, role, token);
                                    SharedPreferencesUtils.setUser(user);

                                    if (role == AppConstant.SUPER_ADMIN_ROLE || role == AppConstant.REGULAR_ADMIN_ROLE){
                                        //TODO (Login)
                                    }else {
                                        //TODO (Login)
                                    }
                                    Intent intent = new Intent(LoginActivity.this ,MainActivity.class);
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void bindViews() {
        username_ed = findViewById(R.id.username_ed);
        password_ed = findViewById(R.id.password_ed);
        forget_pass_tv = findViewById(R.id.forget_pass_tv);
        new_account = findViewById(R.id.new_account);
        login_btn = findViewById(R.id.login_btn);
        fb_ib = findViewById(R.id.fb_ib);
        gl_ib = findViewById(R.id.gl_ib);

    }
}
