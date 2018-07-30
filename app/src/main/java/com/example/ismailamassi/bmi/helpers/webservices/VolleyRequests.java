package com.example.ismailamassi.bmi.helpers.webservices;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ismailamassi.bmi.UIApplication;
import com.example.ismailamassi.bmi.models.StatusItem;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequests{

    public void login(String email, String password, final ICompletionListener completionListener){
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("email", email);
        postParam.put("password", password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,ApiUrls.LOGIN_URL,
                new JSONObject(postParam),
                completionListener::onCompletionListener,
                error -> completionListener.onCompletionListener(null)){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void register(String url , String username, String email, String password, String dob, final ICompletionListener completionListener){
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("name", username);
        postParam.put("email", email);
        postParam.put("password", password);
        postParam.put("dateOfBirth", dob);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,
                new JSONObject(postParam),
                response -> completionListener.onCompletionListener(response),
                error -> completionListener.onCompletionListener(error.networkResponse.statusCode)){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void saveStatus(String url , StatusItem statusItem, final ICompletionListener completionListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,null,
                response -> { Log.d("JSON",response.toString());completionListener.onCompletionListener(response); },
                error -> { });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void getStatus(String url , final ICompletionListener completionListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,
                null,
                response -> { Log.d("JSON",response.toString());completionListener.onCompletionListener(response); },
                error -> {
                });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void getAllUsers(String url , final ICompletionListener completionListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,
                null,
                response -> { Log.d("JSON",response.toString());completionListener.onCompletionListener(response); },
                error -> {
                });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void updateUserAdmin(String url , int userId, final ICompletionListener completionListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,
                null,
                response -> { Log.d("JSON",response.toString());completionListener.onCompletionListener(response); },
                error -> {
                });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void getMessages(String url , int page, final ICompletionListener completionListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,
                null,
                response -> { Log.d("JSON",response.toString());completionListener.onCompletionListener(response); },
                error -> {
                });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

    public void sendMessage(String url , String message, final ICompletionListener completionListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,
                null,
                response -> { Log.d("JSON",response.toString());completionListener.onCompletionListener(response); },
                error -> {
                });
        UIApplication.getInstance().addRequestQueue(jsonObjectRequest);
    }

}