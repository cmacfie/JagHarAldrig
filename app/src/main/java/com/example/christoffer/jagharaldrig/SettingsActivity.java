package com.example.christoffer.jagharaldrig;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.preference.ListPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsActivity extends Activity {

    public static final String KEY_PREF_SAVE_SUGGESTIONS = "saveSuggestions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    /*
    public void deleteAllSuggestions(View view){
        SQLiteDatabase db = openOrCreateDatabase("Suggestions", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS suggestions");
        db.execSQL("CREATE TABLE IF NOT EXISTS suggestions(number integer PRIMARY KEY autoincrement," +
                " date DATETIME , suggestion VARCHAR UNIQUE);");
        Toast.makeText(this, "Removed saved suggestions", Toast.LENGTH_LONG).show();
    }
    */

    public void launchListings(){
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragmentSuggestionsList()).commit();
    }

    private void addSuggestion(String s){
        SQLiteDatabase db = openOrCreateDatabase("Suggestions", Context.MODE_PRIVATE, null);DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        db.execSQL("INSERT OR IGNORE INTO suggestions VALUES(NULL, '" + dateFormat.format(date) + "', '" + s + "')");
    }

    public void fillWithStandards(){
        addSuggestion("Jag har aldrig pratat med polisen pga att jag är så full");
        addSuggestion("Jag har aldrig testat en illegal drog");
        addSuggestion("Jag har aldrig haft sex på en plats jag inte vill berätta om för folk i detta rum");
        addSuggestion("Jag har haft en sexdröm om det motsatta könet");
        addSuggestion("Jag har aldrig sagt att jag \"knappt känner\" en full vän på en fest pga jag skäms för att känna dem just då");
        addSuggestion("Jag har aldrig varit den fullaste på en fest");
        addSuggestion("Jag har aldrig hånglat med två olika personer under samma kväll");
        addSuggestion("Jag har aldrig legat med två olika personer under samma kväll");
        addSuggestion("Jag har aldrig överdrivet på ett CV eller arbetsintervju");
        addSuggestion("Jag har aldrig snott med mig något från en fest");
        addSuggestion("Jag har aldrig ansett att jag har den bästa musiksmaken på en fest");
        addSuggestion("Jag har aldrig sagt att min väns outfit var snygg fastän den såg ut som fan");
        addSuggestion("Jag har aldrig ljugit i detta spel");
        addSuggestion("Jag har aldrig sett någon i detta rum helt eller delvis naken");
        addSuggestion("Jag har aldrig haft sex med någon i detta rum");
        addSuggestion("Jag har aldrig haft sex med det motsatta könet");
        addSuggestion("Jag har aldrig sagt att jag inte var oskuld när jag var det");
        addSuggestion("Jag har aldrig köpt alkohol av en okänd langare");
        addSuggestion("Jag har aldrig langat till någon under 18 år");
        addSuggestion("Jag har aldrig blivit utslängd från en bar eller klubb");
        addSuggestion("Jag har aldrig haft sex utomlands");
        addSuggestion("Jag har aldrig velat ha sex med en arbetskollega");
        addSuggestion("Jag har aldrig haft sex med en arbetskollega");
        addSuggestion("Jag har aldrig velat ha sex med en lärare");
        addSuggestion("Jag har aldrig busringt till 112");
        addSuggestion("Jag har aldrig skickat en bild nakenbild via mobilen");
        addSuggestion("Jag har aldrig stalkat en crush på Instagram");
    }


}
