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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ismailamassi.bmi.R;
import com.example.ismailamassi.bmi.helpers.Validation;

import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

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
//        login_btn.setOnClickListener(view -> {
//            RequestQueue queue = Volley.newRequestQueue(this);
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "#fdfdf", null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                }
//
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//            queue.add(request);
//        });
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this ,SignupActivity.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this ,MainActivity.class);
                startActivity(intent);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
