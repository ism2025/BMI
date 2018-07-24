package com.example.ismailamassi.bmi.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ismailamassi.bmi.UIApplication;
import com.example.ismailamassi.bmi.models.User;

public class SharedPreferencesUtils {
    private static SharedPreferences prefs = UIApplication.getInstance().getSharedPreferences();
    private static SharedPreferences.Editor editor = UIApplication.getInstance().getSharedPreferencesEditor();

        public static void setUser(User user) {
            editor.putInt(AppConstant.USER_ID, user.getId());
            editor.putString(AppConstant.USER_NAME, user.getUsername());
            editor.putString(AppConstant.USER_EMAIL, user.getEmail());
            editor.putInt(AppConstant.USER_ROLE, user.getRole());
            editor.putString(AppConstant.TOKEN, user.getToken());
            editor.commit();
        }

    public static int getUserID() {
        return prefs.getInt(AppConstant.USER_ID, -1);
    }

    public static String getUserName() {
        return prefs.getString(AppConstant.USER_NAME, "");
    }

    public static String getUserEmail() {
        return prefs.getString(AppConstant.USER_EMAIL, "");
    }

    public static int getUserRole() {
        return prefs.getInt(AppConstant.USER_ROLE, -1);
    }

    public static String getUserToken() {
        return prefs.getString(AppConstant.TOKEN, "");
    }

    public static void setNotificationToken(Context context,String Token) {
        editor.putString(AppConstant.NOTIFICATION_TOKEN, Token);
        editor.commit();
    }

    public static String getNotificationToken() {
        return prefs.getString(AppConstant.NOTIFICATION_TOKEN, "");
    }

}