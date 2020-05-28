package com.example.pawfinder.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.pawfinder.R;

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();

    }

    //metoda za citanje login statusa iz sharedPreferences
    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    //metoda za cuvanje email-a user-a nakon uspesne prijave

    public void writeUserEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_email), email);
        editor.commit();
    }

    public String readUserEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_email), "User");
    }

    public boolean logout() {
        SharedPreferences sp = context.getSharedPreferences(context.getString(R.string.pref_user_email), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        this.writeLoginStatus(false);

        return true;
    }
}

