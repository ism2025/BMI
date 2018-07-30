package com.example.ismailamassi.bmi.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ismailamassi.bmi.UIApplication;
import com.example.ismailamassi.bmi.models.User;

public class SharedPreferencesUtils {
    private static SharedPreferences prefs = UIApplication.getInstance().getSharedPreferences();
    private static SharedPreferences.Editor editor = UIApplication.getInstance().getSharedPreferencesEditor();

        public static void setUser(User user) {
            editor.putString(AppConstant.USER_ID, user.getId());
            editor.putString(AppConstant.USER_NAME, user.getName());
            editor.putString(AppConstant.USER_EMAIL, user.getEmail());
            editor.putBoolean(AppConstant.USER_ROLE, user.isAdmin());
            editor.putString(AppConstant.USER_BIRTH_DATE, user.getDateOfBirth());
            editor.putString(AppConstant.USER_CREATED_AT, user.getDateOfBirth());
            editor.putString(AppConstant.TOKEN, user.getToken());
            editor.commit();
        }

    public static String getUserID() {
        return prefs.getString(AppConstant.USER_ID, "");
    }

    public static String getUserName() {
        return prefs.getString(AppConstant.USER_NAME, "");
    }

    public static String getUserEmail() {
        return prefs.getString(AppConstant.USER_EMAIL, "");
    }

    public static String getUserAge() {
        return prefs.getString(AppConstant.USER_BIRTH_DATE, "");
    }

    public static boolean getUserRole() {
        return prefs.getBoolean(AppConstant.USER_ROLE, false);
    }

    public static String getUserCreateAt() {
        return prefs.getString(AppConstant.USER_CREATED_AT, "");
    }

    public static String getUserToken() {
        return prefs.getString(AppConstant.TOKEN, "");
    }

    public static void clearUser(){
            editor.clear();
            editor.commit();
    }
    public static void setNotificationToken(Context context,String Token) {
        editor.putString(AppConstant.NOTIFICATION_TOKEN, Token);
        editor.commit();
    }

    public static String getNotificationToken() {
        return prefs.getString(AppConstant.NOTIFICATION_TOKEN, "");
    }

}