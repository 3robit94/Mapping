package com.example.a3robit94.mapping;

/**
 * Created by 3robit94 on 23/02/2017.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class MapPrefsActivity extends PreferenceActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }


}
