package com.example.christoffer.jagharaldrig;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    private boolean topIsCorrect = false, bottomisCorrect = false;
    private TextView topText, bottomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        topText = (TextView) findViewById(R.id.topStoryText);
        bottomText = (TextView) findViewById(R.id.bottomStoryText);
        Random r = new Random();
        if(r.nextBoolean()){
            bottomText.setText(getIntent().getStringExtra("wrongText"));
            topText.setText(getIntent().getStringExtra("correctText"));
            topIsCorrect = true;
        } else {
            topText.setText(getIntent().getStringExtra("wrongText"));
            bottomText.setText(getIntent().getStringExtra("correctText"));
            bottomisCorrect = true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void chooseAnswer(View view){
        topText.setTextColor(0xFF000000);
        bottomText.setTextColor(0xFF000000);
        /*if(topIsCorrect){
            topText.setBackground( getResources().getDrawable(R.drawable.my_border_correct_selected));
            bottomText.setBackground( getResources().getDrawable(R.drawable.my_border_wrong_selected));
        } else {
            bottomText.setBackground( getResources().getDrawable(R.drawable.my_border_correct_selected));
            topText.setBackgroundColor(0xFFA3181C);
        }*/
        if(view.getId() == R.id.topStoryText){
            bottomText.setClickable(false);
            if(topIsCorrect){
                topText.setBackground( getResources().getDrawable(R.drawable.my_border_correct_selected));
                bottomText.setBackground(getResources().getDrawable(R.drawable.my_border_wrong)); //Sätt röd
                topText.setClickable(false);
                Toast.makeText(this, "Korrekt!", Toast.LENGTH_LONG).show();
            } else {
                topText.setBackground( getResources().getDrawable(R.drawable.my_border_wrong_selected));
                bottomText.setBackground(getResources().getDrawable(R.drawable.my_border_correct)); //Sätt grön
                topText.setClickable(false);
                Toast.makeText(this, "Fel!", Toast.LENGTH_LONG).show();
            }
        } else {
            topText.setClickable(false);
            if(bottomisCorrect){
                bottomText.setBackground( getResources().getDrawable(R.drawable.my_border_correct_selected));
                topText.setBackground(getResources().getDrawable(R.drawable.my_border_wrong)); //Sätt röd
                bottomText.setClickable(false);
                Toast.makeText(this, "Korrekt!", Toast.LENGTH_LONG).show();

            } else {
                bottomText.setBackground( getResources().getDrawable(R.drawable.my_border_wrong_selected));
                topText.setBackground(getResources().getDrawable(R.drawable.my_border_correct)); //Sätt grön
                bottomText.setClickable(false);
                Toast.makeText(this, "Fel!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void endGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void goBack(View view){
        finish();
    }
}
