package com.newbaconwars.justindowty.newbaconwars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;

public class StartMenuActivity extends AppCompatActivity {
    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setTextSize(32f);
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartMenuActivity.this, OptionsActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
