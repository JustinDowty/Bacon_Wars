package com.newbaconwars.justindowty.newbaconwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TutorialActivity extends AppCompatActivity {

    private ImageView screenView;
    private int clicks;
    private RelativeLayout tutorialLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        screenView = (ImageView) findViewById(R.id.screenView);
        tutorialLayout = (RelativeLayout) findViewById(R.id.activity_tutorial);
        clicks = 0;
        screenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
                if(clicks == 1){
                    tutorialLayout.setBackgroundResource(R.drawable.tutorial2);
                }
                if(clicks == 2){
                    tutorialLayout.setBackgroundResource(R.drawable.tutorial3);
                }
                if(clicks == 3){
                    tutorialLayout.setBackgroundResource(R.drawable.tutorial4);
                }
                if(clicks == 4){
                    tutorialLayout.setBackgroundResource(R.drawable.tutorial5);
                }
                if(clicks > 4){
                    Intent i = new Intent(TutorialActivity.this, OptionsActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });
    }
}
