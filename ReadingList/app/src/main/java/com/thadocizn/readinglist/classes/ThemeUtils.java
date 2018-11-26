package com.thadocizn.readinglist.classes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.thadocizn.readinglist.R;

public class ThemeUtils {

    static Boolean bool;

    public static int getSelectedTheme(Activity activity){
        int theme;

        SharedPreferences preferences = getPreferences(activity);
        bool = preferences.getBoolean(Constants.THEME, false);

        if (bool){
            theme = R.style.AppThemeDark;
        }else {
            theme = R.style.AppTheme;
        }
        return theme;
    }
    public static void toggleTheme(Activity activity){
        getCurrentTheme(activity);
        int theme;
        theme     = getSelectedTheme(activity);
        storeCurrentTheme(activity, theme);
        refreshActivity(activity);
    }

    public static void storeCurrentTheme(Activity activity, int theme){
        SharedPreferences preferences = getPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();

        if (theme == R.style.AppThemeDark){
            editor.putBoolean("Theme", true);
        } else {
            editor.putBoolean(Constants.THEME, true);
        }
        editor.apply();
    }

    public static int getCurrentTheme(Activity activity){
        SharedPreferences preferences = getPreferences(activity);
        int cTheme = preferences.getInt(Constants.THEME, R.style.AppTheme);
        return cTheme;
    }

    public static void onActivityCreateSetTheme(Activity activity){
        activity.setTheme(getCurrentTheme(activity));
    }

    public static void refreshActivity(Activity activity){
        Intent intent = activity.getIntent();
        activity.finish();
        activity.startActivity(intent);
    }

    private static SharedPreferences getPreferences(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
    }
}
