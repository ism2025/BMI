package com.example.ismailamassi.bmi.helpers.webservices;

import org.json.JSONObject;

public interface ICompletionListener<T> {
    void onCompletionListener(T data);
}
