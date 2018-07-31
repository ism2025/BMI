package com.example.ismailamassi.bmi;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.ismailamassi.bmi.helpers.SharedPreferencesUtils;
import com.example.ismailamassi.bmi.helpers.webservices.VolleyImageCache;
import com.example.ismailamassi.bmi.models.User;

public class UIApplication extends Application {

    private static UIApplication anInstance = null;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private ProgressDialog progressDialog;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        anInstance = this;
    }

    public synchronized static UIApplication getInstance() {
        return anInstance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }

    public void addRequestQueue(Request request) {
        getRequestQueue().add(request);
    }

    public void addRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelRequest(String tag) {
        getRequestQueue().cancelAll(tag);
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null)
            return imageLoader = new ImageLoader(getRequestQueue(), new VolleyImageCache());
        return imageLoader;
    }

    public SharedPreferences.Editor getSharedPreferencesEditor() {
        if (editor == null) {
            prefs = getSharedPreferences();
            editor = prefs.edit();
        }
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return prefs;
    }

    public ProgressDialog getProgressDialog(Context context, String message, boolean isCcancelable) {
        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(message);
        progressDialog.setCancelable(isCcancelable);

        return progressDialog;
    }
}