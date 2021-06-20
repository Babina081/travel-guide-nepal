package com.example.bmenudemo.activities.homePageActivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String SHARED_PREF_NAME = "session";
    public static final String LOGIN = "login";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String Email = "email";
    public static final String Password = "password";
    public static final String REMEMBER = "remember";


    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLogin(boolean login) {
        editor.putBoolean(LOGIN, login);
        editor.commit();
    }

    public boolean getLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void setUsername(String email) {
        editor.putString(Email, email);
        editor.commit();
    }

    public String getUsername() {
        return sharedPreferences.getString(Email, "");
    }

    public void setPassword(String password) {
        editor.putString(Password, password);
        editor.commit();
    }

    public String getPassword() {
        return sharedPreferences.getString(Password, "");
    }

   /* public void setFirstname(String firstname) {
        editor.putString(FIRSTNAME, firstname);
        editor.commit();
    }

    public String getFirstname() {
        return sharedPreferences.getString(FIRSTNAME, "");
    }

    public void setLastname(String lastname) {
        editor.putString(LASTNAME, lastname);
        editor.commit();
    }

    public String getLastname() {
        return sharedPreferences.getString(LASTNAME, "");
    }*/



    public static boolean getBooleanPreference(Context context, String key, boolean defaultValue) {

        boolean value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getBoolean(key, defaultValue);
        }
        return value;
    }

   /* public void createSession(String firstname, String lastname, String email) {
        editor.putBoolean(LOGIN, true);
        editor.putString(FIRSTNAME, firstname);
        editor.putString(LASTNAME, lastname);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLoggin()) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ((Success) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(FIRSTNAME, sharedPreferences.getString(FIRSTNAME, null));
        user.put(LASTNAME, sharedPreferences.getString(LASTNAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        return user;
    }

    public void logout() {
        editor.clear();
        editor.clear();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Success) context).finish();
    }
*/


}
