package com.example.christoffer.jagharaldrig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SendAround extends AppCompatActivity {

    private String text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_around);
        Intent intent = getIntent();
        text1 = intent.getStringExtra("correctText");
        text2 = intent.getStringExtra("wrongText");
        TextView textview = (TextView) findViewById(R.id.sendAroundTextview);
        textview.setText(text1);
    }

    public void finishedGame(View view){
        Intent intent = new Intent(this, GuessActivity.class);
        intent.putExtra("correctText", text1);
        intent.putExtra("wrongText", text2);
        startActivity(intent);
    }

    public void goBack(View view){
        finish();
    }
}
