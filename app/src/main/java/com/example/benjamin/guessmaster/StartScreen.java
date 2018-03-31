package com.example.benjamin.guessmaster;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

/**
 * This is a start screen activity. it opens as the default activity, has a start button, and has a fade in animation
 */
public class StartScreen extends AppCompatActivity {

    /**
     * This is the oncreate method for the start screen. displays the app name and start button, animating them to fade in
     * @param savedInstanceState default variable for oncreate methods
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        TextView startTitle = (TextView) findViewById(R.id.start_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf"); //set the font to something sweet
        startTitle.setTypeface(font);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        startTitle.startAnimation(fadeIn);
//        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
//        TextView textView = (TextView) findViewById(titleId);
//        textView.setTextColor(getResources().getColor(R.color.light_green));
        ActionBar ab = getSupportActionBar();
        ab.hide();//hides action bar for a full screen view

        //sets functionality of the button. listens for a button click, and opens the guessmaster activity on click
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartScreen.this, GuessMaster.class);
                StartScreen.this.startActivity(myIntent);
            }
        });
        start.setTypeface(font);
        start.startAnimation(fadeIn);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
    }
}
