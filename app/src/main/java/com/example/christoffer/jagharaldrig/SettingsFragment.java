package com.example.christoffer.jagharaldrig;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context hostActivity = getActivity();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(hostActivity);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.prefrences);

        //Delete all suggestions
        /*
        Preference myPref = (Preference) findPreference("deleteAllSuggestions");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsActivity) getActivity()).deleteAllSuggestions(null);
                return true;
            }
        });*/



        Preference suggestionsList = (Preference) findPreference("multi_suggestions_list");
        suggestionsList.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsActivity) getActivity()).launchListings();
                return true;
            }
        });

        Preference standardSuggestions = (Preference) findPreference("standardSuggestions");
        standardSuggestions.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                ((SettingsActivity)getActivity()).fillWithStandards();
                return true;
            }
        });

    }
}