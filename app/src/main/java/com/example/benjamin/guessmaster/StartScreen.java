package com.example.benjamin.guessmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
//        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
//        TextView textView = (TextView) findViewById(titleId);
//        textView.setTextColor(getResources().getColor(R.color.light_green));
        ActionBar ab = getSupportActionBar();
        ab.hide();

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartScreen.this, GuessMaster.class);
                StartScreen.this.startActivity(myIntent);
            }
        });

        TextView textView = (TextView) findViewById(R.id.start_title);
        textView.animate().alpha(1f).setDuration(1000);
        start.animate().alpha(1f).setDuration(1000);

    }
}
