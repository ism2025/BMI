package com.example.ismailamassi.bmi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.helpers.Validation;

public class loginActivity extends AppCompatActivity {

    private EditText username_ed, password_ed;
    private TextView forget_pass_tv,new_account;
    private Button login_btn;
    private ImageButton fb_ib,gl_ib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        onClickItem();
    }

    private void onClickItem() {
        login_btn.setOnClickListener(view->{
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
