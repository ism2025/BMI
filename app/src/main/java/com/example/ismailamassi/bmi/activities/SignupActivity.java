package com.example.ismailamassi.bmi.activities;

import android.content.Intent;
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
import com.example.ismailamassi.bmi.helpers.Validation;
import com.example.ismailamassi.bmi.helpers.VolleyRequests;
import com.example.ismailamassi.bmi.models.User;
import com.fourhcode.forhutils.FUtilsValidation;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    private EditText username_ed, email_ed, password_ed, dob_ed;
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
                        String bod = year+ "-" + monthOfYear+"-"+dayOfMonth;
                        dob_ed.setText(bod);
                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
        });

        have_account_tv.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this ,LoginActivity.class);
            startActivity(intent);
        });

        register_btn.setOnClickListener(v -> {
            if (!Validation.isEmpty(username_ed, "الحقل مطلوب")&&
                    !Validation.isEmpty(password_ed, "الحقل مطلوب")&&
                    !Validation.isEmpty(email_ed, "هذا الحقل مطلوب")&&
                    !Validation.isEmpty(dob_ed, "هذا الحق مطلوب")&&
                    Validation.isValidEmail(username_ed, "ادخل بريد الكتروني صالح")){
                if (!Validation.isLengthCorrect(password_ed.getText().toString(), 6, 32)){
                    password_ed.setError("كلمة المر");
                }else {
                    VolleyRequests volleyRequests = new VolleyRequests();
                    //TODO Add Register Url
                    String url = "";
                    volleyRequests.register(url, username_ed.getText().toString(), email_ed.getText().toString(), password_ed.getText().toString(), dob_ed.getText().toString(),
                            jsonObject -> {
                                if (jsonObject != null){
                                    //TODO Implement Success register
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

                                        Intent intent = new Intent(SignupActivity.this ,MainActivity.class);
                                        startActivity(intent);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }

        });
    }
}
