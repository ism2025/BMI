package com.example.ismailamassi.bmi.helpers;

import android.widget.EditText;
import android.widget.TextView;

public class Methods {
    public static String getStringFromEditText(EditText editText){
        return editText.getText().toString().trim();
    }
    public static String getStringFromTextView(TextView textView){
        return textView.getText().toString().trim();
    }
}
