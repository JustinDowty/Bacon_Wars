package com.newbaconwars.justindowty.newbaconwars;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Displays Game Over page when user loses, retry button returns to start menu screen
 */
public class GameOverActivity extends AppCompatActivity {
    private Button retryButton;
    private Button highScoresButton;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);

        /*
         * Reading high scores from shared preferences and inserting into highScores array
         */
        setContentView(R.layout.activity_game_over);
        retryButton = (Button) findViewById(R.id.retryButton);
        highScoresButton = (Button) findViewById(R.id.highScoresButton);
        retryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameOverActivity.this, OptionsActivity.class);
                finish();
                startActivity(i);
            }
        });
        highScoresButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createHighScoresDialog();
            }
        });
    }

    public void createHighScoresDialog(){
        int layout = R.layout.highscoresdialoglayout;
        String scoresString = sharedPreferences.getString("HighScores", "0\n0\n0\n0\n0"); // retrieving high scores with default being 0 value scores
        final Dialog dialog = new Dialog(GameOverActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layout, null);
        dialog.setContentView(view);
        TextView highScoresTitle = (TextView) dialog.findViewById(R.id.highScoresTitle);
        TextView scoresText = (TextView) dialog.findViewById(R.id.scoresText);
        scoresText.setText(scoresString);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/impact.ttf");
        scoresText.setTypeface(tf);
        highScoresTitle.setTypeface(tf);
        Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.dismiss();
            }
        });
        dialog.show();
        Button globalButton = (Button) dialog.findViewById(R.id.globalButton);
        globalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.dismiss();
                createGlobalScoresDialog();
            }
        });
    }

    public void createGlobalScoresDialog() {
        //Checks if phone is connected to internet, necessary to retrieve data
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference();
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    String globalScores = dataSnapshot.getValue(String.class);
                    int layout = R.layout.global_scores_layout;
                    final Dialog dialog = new Dialog(GameOverActivity.this);
                    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(layout, null);
                    dialog.setContentView(view);
                    TextView globalScoresTitle = (TextView) dialog.findViewById(R.id.globalScoresTitle);
                    TextView globalScoresText = (TextView) dialog.findViewById(R.id.globalScoresText);
                    globalScoresText.setText(globalScores);
                    Typeface tf = Typeface.createFromAsset(getAssets(), "font/impact.ttf");
                    globalScoresText.setTypeface(tf);
                    globalScoresTitle.setTypeface(tf);
                    Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    Button statsButton = (Button) dialog.findViewById(R.id.statsButton);
                    statsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            createStatsDialog();
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(GameOverActivity.this, "Failed to retrieve Global High Scores: Bad Server Connection", Toast.LENGTH_LONG).show();
                    createStatsDialog();
                }
            });
        }
        else{
            createStatsDialog();
        }
    }

    public void createStatsDialog(){
        int layout = R.layout.statistics_dialog_layout;
        String scoresString = sharedPreferences.getString("HighScores", "0\n0\n0\n0\n0");; // retrieving high scores with default being 0 value scores
        int totalHeadCount = sharedPreferences.getInt("TotalHeadCount", 0);
        int highEggCount = sharedPreferences.getInt("HighEggCount", 0);
        int totalEggCount = sharedPreferences.getInt("TotalEggCount", 0);
        String[] scores = scoresString.split("\n");
        int highScore = Integer.parseInt(scores[0]);
        final Dialog dialog = new Dialog(GameOverActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layout, null);
        dialog.setContentView(view);
        TextView statsText = (TextView) dialog.findViewById(R.id.statsText);
        statsText.setText("High Score: " + highScore + "\nHeads Dodged: " + totalHeadCount
                + "\nEggs Per Round: " + highEggCount + "\nTotal Eggs: " + totalEggCount);
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/impact.ttf");
        TextView statsTitle = (TextView) dialog.findViewById(R.id.statsTitle);
        statsText.setTypeface(tf);
        statsTitle.setTypeface(tf);
        Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}