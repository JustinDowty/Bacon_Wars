package com.newbaconwars.justindowty.newbaconwars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OptionsActivity extends AppCompatActivity {

    private ImageView baconView;
    private ImageView darthView;
    private ImageView linkView;
    private ImageView pennywiseView;
    private ImageView hoganView;
    private ImageView finnView;
    private ImageView greenrangerView;
    private ImageView quagmireView;
    private ImageView cloudView;
    private ImageView kermitView;
    private ImageView jasonView;
    private ImageView buttheadView;
    private ImageView jesusView;
    private ImageView snoopView;
    private ImageView squidwardView;
    private ImageView geneView;
    private ImageView blackknightView;
    private ImageView kevinView;
    private ImageView michaelView;
    private ImageView popeView;
    private ImageView greenView;
    private Button tutorialButton;
    private Button scoresButton;
    private int totalEggCount;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        baconView = (ImageView) findViewById(R.id.baconView);
        darthView = (ImageView) findViewById(R.id.darthView);
        linkView = (ImageView) findViewById(R.id.linkView);
        pennywiseView = (ImageView) findViewById(R.id.pennywiseView);
        hoganView = (ImageView) findViewById(R.id.hoganView);
        finnView = (ImageView) findViewById(R.id.finnView);
        greenrangerView = (ImageView) findViewById(R.id.greenrangerView);
        quagmireView = (ImageView) findViewById(R.id.quagmireView);
        cloudView = (ImageView) findViewById(R.id.cloudView);
        kermitView = (ImageView) findViewById(R.id.kermitView);
        jasonView = (ImageView) findViewById(R.id.jasonView);
        buttheadView = (ImageView) findViewById(R.id.buttheadView);
        jesusView = (ImageView) findViewById(R.id.jesusView);
        snoopView = (ImageView) findViewById(R.id.snoopView);
        squidwardView = (ImageView) findViewById(R.id.squidwardView);
        geneView = (ImageView) findViewById(R.id.geneView);
        blackknightView = (ImageView) findViewById(R.id.blackknightView);
        kevinView = (ImageView) findViewById(R.id.kevinView);
        michaelView = (ImageView) findViewById(R.id.michaelView);
        popeView = (ImageView) findViewById(R.id.popeView);
        greenView = (ImageView) findViewById(R.id.greenView);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);
        scoresButton = (Button) findViewById(R.id.scoresButton);

        sharedPref = getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        totalEggCount = sharedPref.getInt("TotalEggCount", 0);
        //totalEggCount = 200;
        // Sets views to appropriate images if unlocked with enough eggs
        baconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("Avatar", "Bacon");
                editor.commit();
                launchGame();
            }
        });

        if(totalEggCount >= 5){
            darthView.setImageResource(R.drawable.bacondarth);
            linkView.setImageResource(R.drawable.baconlink);
            darthView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Darth");
                    editor.commit();
                    launchGame();
                }
            });
            linkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Link");
                    editor.commit();
                    launchGame();
                }
            });
        }

        if(totalEggCount >= 15){
            hoganView.setImageResource(R.drawable.baconhogan);
            greenrangerView.setImageResource(R.drawable.bacongreenranger);
            greenView.setImageResource(R.drawable.bacongreen);
            greenView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Green");
                    editor.commit();
                    launchGame();
                }
            });
            hoganView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Hogan");
                    editor.commit();
                    launchGame();
                }
            });
            greenrangerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Greenranger");
                    editor.commit();
                    launchGame();
                }
            });
        }
        if(totalEggCount >= 30){
            cloudView.setImageResource(R.drawable.baconcloud);
            finnView.setImageResource(R.drawable.baconfinn);
            finnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Finn");
                    editor.commit();
                    launchGame();
                }
            });
            pennywiseView.setImageResource(R.drawable.baconpennywise);
            pennywiseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Pennywise");
                    editor.commit();
                    launchGame();
                }
            });
            cloudView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Cloud");
                    editor.commit();
                    launchGame();
                }
            });
        }

        if(totalEggCount >= 50){
            jasonView.setImageResource(R.drawable.baconjason);
            buttheadView.setImageResource(R.drawable.baconbutthead);
            jesusView.setImageResource(R.drawable.baconjesus);
            jasonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Jason");
                    editor.commit();
                    launchGame();
                }
            });
            buttheadView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Butthead");
                    editor.commit();
                    launchGame();
                }
            });
            jesusView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Jesus");
                    editor.commit();
                    launchGame();
                }
            });
        }
        if(totalEggCount >= 100){
            quagmireView.setImageResource(R.drawable.baconquagmire);
            quagmireView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Quagmire");
                    editor.commit();
                    launchGame();
                }
            });
            snoopView.setImageResource(R.drawable.baconsnoop);
            snoopView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Snoop");
                    editor.commit();
                    launchGame();
                }
            });
            squidwardView.setImageResource(R.drawable.baconsquidward);
            squidwardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Squidward");
                    editor.commit();
                    launchGame();
                }
            });
        }
        if(totalEggCount >= 150){
            geneView.setImageResource(R.drawable.bacongene);
            geneView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Gene");
                    editor.commit();
                    launchGame();
                }
            });
            blackknightView.setImageResource(R.drawable.baconblackknight);
            blackknightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Black Knight");
                    editor.commit();
                    launchGame();
                }
            });
            kevinView.setImageResource(R.drawable.baconkevin);
            kevinView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Kevin");
                    editor.commit();
                    launchGame();
                }
            });
        }
        if(totalEggCount >= 200){
            michaelView.setImageResource(R.drawable.baconmichael);
            michaelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Michael");
                    editor.commit();
                    launchGame();
                }
            });
            popeView.setImageResource(R.drawable.baconpope);
            popeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Pope");
                    editor.commit();
                    launchGame();
                }
            });
            kermitView.setImageResource(R.drawable.baconkermit);
            kermitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("Avatar", "Kermit");
                    editor.commit();
                    launchGame();
                }
            });
        }
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OptionsActivity.this, TutorialActivity.class);
                finish();
                startActivity(i);
            }
        });
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHighScoreDialog();
            }
        });

        //Checks if phone is connected to internet, necessary to retrieve data
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){ }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(OptionsActivity.this);
            alert.setTitle("WARNING!");
            alert.setMessage("You are not connected to the internet, global scores will not be posted or retrieved.");
            alert.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { }
                    });
            alert.show();
        }
    }

    private void launchGame(){
        Intent i = new Intent(OptionsActivity.this, MainActivity.class);
        finish();
        startActivity(i);
    }

    public void createHighScoreDialog(){
        int layout = R.layout.highscoresdialoglayout;
        String scoresString = sharedPref.getString("HighScores", "0\n0\n0\n0\n0"); // retrieving high scores with default being 0 value scores
        final Dialog dialog = new Dialog(OptionsActivity.this);
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
                    final Dialog dialog = new Dialog(OptionsActivity.this);
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
                    Toast.makeText(OptionsActivity.this, "Failed to retrieve Global High Scores: Bad Server Connection", Toast.LENGTH_LONG).show();
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
        String scoresString = sharedPref.getString("HighScores", "0\n0\n0\n0\n0");; // retrieving high scores with default being 0 value scores
        int totalHeadCount = sharedPref.getInt("TotalHeadCount", 0);
        int highEggCount = sharedPref.getInt("HighEggCount", 0);
        int totalEggCount = sharedPref.getInt("TotalEggCount", 0);
        String[] scores = scoresString.split("\n");
        int highScore = Integer.parseInt(scores[0]);
        final Dialog dialog = new Dialog(OptionsActivity.this);
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