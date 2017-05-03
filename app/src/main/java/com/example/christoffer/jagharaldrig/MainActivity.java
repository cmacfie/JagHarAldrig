package com.example.christoffer.jagharaldrig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText tv1, tv2;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (EditText) findViewById(R.id.Inmat1);
        tv2 = (EditText) findViewById(R.id.Inmat2);
        tv1.setText("Jag har aldrig ");
        tv2.setText("Jag har aldrig ");
        db = openOrCreateDatabase("Suggestions", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Suggestions(number INTEGER PRIMARY KEY AUTOINCREMENT," +
                " date DATETIME , suggestion VARCHAR UNIQUE);");

    }

    public void getSuggestions(View view){
        Cursor c = db.rawQuery("SELECT suggestion FROM Suggestions ORDER BY RANDOM() LIMIT 1", null);
        if(c.moveToFirst()) {
            if(view.getId() == R.id.topSuggestionBotton){
                tv1.setText(c.getString(0));
            } else {
                tv2.setText(c.getString(0));
            }
        } else {
            Toast.makeText(this, "No suggstions", Toast.LENGTH_LONG).show();
        }
    }

    public void saveSuggestions(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        db.execSQL("INSERT OR IGNORE INTO suggestions VALUES(NULL, '" + dateFormat.format(date) + "', '" + tv1.getText() + "')");
        db.execSQL("INSERT OR IGNORE INTO suggestions VALUES(NULL, '" + dateFormat.format(date) + "', '" + tv2.getText() + "')");
    }

    public void startGame(View view){
        Intent intent = new Intent(this, SendAround.class);
        String correctText = tv1.getText().toString();
        String wrongText = tv2.getText().toString();
        if(correctText.length() > ("Jag har aldrig ").length() && wrongText.length() > ("Jag har aldrig ").length()){
            intent.putExtra("correctText", correctText);
            intent.putExtra("wrongText", wrongText);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            if(prefs.getBoolean("saveSuggestions", false)) {
                saveSuggestions();
            }
            startActivity(intent);
        } else {
            Toast.makeText(this, "Båda fälten måste vara ifyllda", Toast.LENGTH_LONG).show();
        }
    }

    public void closeKeyboard(View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void switchText(View view){
        Editable text1 = tv1.getText();
        tv1.setText(tv2.getText());
        tv2.setText(text1);
    }

    public void startSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}

