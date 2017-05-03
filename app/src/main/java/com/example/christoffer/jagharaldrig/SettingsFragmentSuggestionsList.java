package com.example.christoffer.jagharaldrig;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Christoffer on 2017-05-03.
 */

public class SettingsFragmentSuggestionsList extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(getActivity());

        SQLiteDatabase db = getActivity().openOrCreateDatabase("Suggestions", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT suggestion, date FROM Suggestions", null);
        final ArrayList<MyCheckBoxPreference> suggestions = new ArrayList<>();

        Preference acceptButton = new Preference(getActivity());
        acceptButton.setTitle("Delete all marked");
        acceptButton.setIcon(ContextCompat.getDrawable(getActivity(), android.R.drawable.ic_delete));
        screen.addPreference(acceptButton);
        acceptButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                for (MyCheckBoxPreference cbf : suggestions) {
                    if (cbf.isChecked()) {
                        SQLiteDatabase db = getActivity().openOrCreateDatabase("Suggestions", Context.MODE_PRIVATE, null);
                        db.execSQL("DELETE FROM Suggestions WHERE suggestion = \"" + cbf.getTitle().toString() + "\"");
                        screen.removePreference(cbf);
                    }
                }
                return false;
            }
        });

        for(int i = 0; c.moveToNext(); i++){
            MyCheckBoxPreference checkBoxPref = new MyCheckBoxPreference(getActivity());
            checkBoxPref.setTitle(c.getString(0));
            checkBoxPref.setSummary("Added: " + c.getString(1));
            checkBoxPref.setChecked(false);
            suggestions.add(checkBoxPref);
            screen.addPreference(checkBoxPref);
        }

        screen.addPreference(acceptButton);
        setPreferenceScreen(screen);
    }

}
