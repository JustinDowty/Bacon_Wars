package com.newbaconwars.justindowty.newbaconwars;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import static android.graphics.Bitmap.createBitmap;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;
    private Random rand;
    private ImageView bacon;
    private TextView headCountText;
    private TextView eggCountText;
    private int headCount;
    private int eggCount;
    private ImageView faceView1;
    private ImageView faceView2;
    private ImageView faceView3;
    private ImageView faceView4;
    private ImageView faceView5;
    private ImageView faceView6;
    private ImageView faceView7;
    private ImageView faceView8;
    private ImageView faceView9;
    private ImageView faceView10;
    private ImageView faceView11;
    private ImageView faceView12;
    private ImageView faceView13;
    private ImageView faceView14;
    private ImageView faceView15;
    private ImageView faceView16;
    private ImageView faceView17;
    private ImageView faceView18;
    private ImageView faceView19;
    private ImageView faceView20;
    private ImageView faceView21;
    private ImageView faceView22;
    private ImageView faceView23;
    private ImageView faceView24;
    private ImageView eggView;
    private ImageView eggView2;
    private ImageView[] views;
    private ImageView[] views2;
    private int[] faces;
    private BaconPositions[] baconPositions;
    private Handler handler;
    private BaconPositions baconPosition;
    private int dpHeight, dpWidth;
    private int farMargin, centerMargin; // for margins from bacon to screen
    private float density;
    private boolean lost;
    private int[] faceImages;
    public int segmentsCompleted;
    private int[] highScores;
    private String avatar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Canvas backCanvas;
    private Bitmap backBitmap;


    // for swipe detection
    private float x1,x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        handler = new Handler();
        mainLayout = (RelativeLayout) findViewById(R.id.activity_main);
        rand= new Random();
        headCountText = (TextView) findViewById(R.id.headCountText);
        eggCountText = (TextView) findViewById(R.id.eggCountText);
        headCount = 0;
        eggCount = 0;
        bacon = (ImageView) findViewById(R.id.bacon);
        faceView1 = (ImageView) findViewById(R.id.faceView1);
        faceView2 = (ImageView) findViewById(R.id.faceView2);
        faceView3 = (ImageView) findViewById(R.id.faceView3);
        faceView4 = (ImageView) findViewById(R.id.faceView4);
        faceView5 = (ImageView) findViewById(R.id.faceView5);
        faceView6 = (ImageView) findViewById(R.id.faceView6);
        faceView7 = (ImageView) findViewById(R.id.faceView7);
        faceView8 = (ImageView) findViewById(R.id.faceView8);
        faceView9 = (ImageView) findViewById(R.id.faceView9);
        faceView10 = (ImageView) findViewById(R.id.faceView10);
        faceView11 = (ImageView) findViewById(R.id.faceView11);
        faceView12 = (ImageView) findViewById(R.id.faceView12);
        faceView13 = (ImageView) findViewById(R.id.faceView13);
        faceView14 = (ImageView) findViewById(R.id.faceView14);
        faceView15 = (ImageView) findViewById(R.id.faceView15);
        faceView16 = (ImageView) findViewById(R.id.faceView16);
        faceView17 = (ImageView) findViewById(R.id.faceView17);
        faceView18 = (ImageView) findViewById(R.id.faceView18);
        faceView19 = (ImageView) findViewById(R.id.faceView19);
        faceView20 = (ImageView) findViewById(R.id.faceView20);
        faceView21 = (ImageView) findViewById(R.id.faceView21);
        faceView22 = (ImageView) findViewById(R.id.faceView22);
        faceView23 = (ImageView) findViewById(R.id.faceView23);
        faceView24 = (ImageView) findViewById(R.id.faceView24);
        eggView = (ImageView) findViewById(R.id.eggView);
        eggView2 = (ImageView) findViewById(R.id.eggView2);
        faceImages = new int[] {R.drawable.trump1, R.drawable.trump2, R.drawable.freeman1, R.drawable.freeman2,
                R.drawable.reptar1, R.drawable.reptar2, R.drawable.yoda1, R.drawable.yoda2,
                R.drawable.herman1, R.drawable.herman2, R.drawable.powers1, R.drawable.powers2,
                R.drawable.sam1, R.drawable.sam2, R.drawable.morty1, R.drawable.morty2,
                R.drawable.spade1, R.drawable.spade2, R.drawable.pac1, R.drawable.pac2, R.drawable.tupac1, R.drawable.tupac2,
                R.drawable.owen1, R.drawable.owen2, R.drawable.batman1, R.drawable.batman2, R.drawable.dowty1, R.drawable.dowty2};
        views = new ImageView[] {faceView1, faceView2, faceView3, faceView4, faceView5, faceView6, faceView7, faceView8, faceView9, faceView10, faceView11, faceView12};
        views2 = new ImageView[] {faceView13, faceView14, faceView15, faceView16, faceView17, faceView18, faceView19, faceView20, faceView21, faceView22, faceView23, faceView24};
        baconPositions = new BaconPositions[] {BaconPositions.LEFT, BaconPositions.MIDLEFT, BaconPositions.MID, BaconPositions.MIDRIGHT, BaconPositions.RIGHT};
        baconPosition = BaconPositions.MID;
        segmentsCompleted = 0;
        lost = false;
        faces = new int[5];
        setFacesIndexArray();

        /*
         * Reading high scores from shared preferences and inserting into highScores array
         */
        highScores = new int[5];
        String scoresString = sharedPreferences.getString("HighScores", "0\n0\n0\n0\n0"); // retrieving high scores with default being 0 value scores
        String[] scores = scoresString.split("\n");
        for(int i = 0; i < highScores.length; i++){
            highScores[i] = Integer.parseInt(scores[i]);
        }

        /*
         * Gets the devices size
         */
        Display display = getWindowManager().getDefaultDisplay();
        density = getResources().getDisplayMetrics().density;
        Point size = new Point();
        display.getRealSize(size);
        dpHeight = size.y;
        dpWidth = size.x;
        farMargin = dpWidth/30;
        centerMargin = farMargin/2;

        /*
         * Sets Background randomly
         */
        int r = rand.nextInt(5);
        if(r == 0){
            mainLayout.setBackgroundResource(R.drawable.back);
        }
        if(r == 1){
            mainLayout.setBackgroundResource(R.drawable.back2);
        }
        if(r == 2){
            mainLayout.setBackgroundResource(R.drawable.back3);
        }
        if(r == 3){
            mainLayout.setBackgroundResource(R.drawable.back4);
        }
        if(r == 4){
            mainLayout.setBackgroundResource(R.drawable.back5);
        }

        /*
         * Setting bacon avatar
         */
        avatar = sharedPreferences.getString("Avatar", "");

        if(avatar.equals("Darth")){
            bacon.setImageResource(R.drawable.bacondarth);
        }
        else if(avatar.equals("Link")){
            bacon.setImageResource(R.drawable.baconlink);
        }
        else if(avatar.equals("Pennywise")){
            bacon.setImageResource(R.drawable.baconpennywise);
        }
        else if(avatar.equals("Hogan")){
            bacon.setImageResource(R.drawable.baconhogan);
        }
        else if(avatar.equals("Finn")){
            bacon.setImageResource(R.drawable.baconfinn);
        }
        else if(avatar.equals("Greenranger")){
            bacon.setImageResource(R.drawable.bacongreenranger);
        }
        else if(avatar.equals("Quagmire")){
            bacon.setImageResource(R.drawable.baconquagmire);
        }
        else if(avatar.equals("Cloud")){
            bacon.setImageResource(R.drawable.baconcloud);
        }
        else if(avatar.equals("Green")){
            bacon.setImageResource(R.drawable.bacongreen);
        }
        else if(avatar.equals("Jason")){
            bacon.setImageResource(R.drawable.baconjason);
        }
        else if(avatar.equals("Butthead")){
            bacon.setImageResource(R.drawable.baconbutthead);
        }
        else if(avatar.equals("Jesus")){
            bacon.setImageResource(R.drawable.baconjesus);
        }
        else if(avatar.equals("Snoop")){
            bacon.setImageResource(R.drawable.baconsnoop);
        }
        else if(avatar.equals("Squidward")){
            bacon.setImageResource(R.drawable.baconsquidward);
        }
        else if(avatar.equals("Gene")){
            bacon.setImageResource(R.drawable.bacongene);
        }
        else if(avatar.equals("Black Knight")){
            bacon.setImageResource(R.drawable.baconblackknight);
        }
        else if(avatar.equals("Kevin")){
            bacon.setImageResource(R.drawable.baconkevin);
        }
        else if(avatar.equals("Michael")){
            bacon.setImageResource(R.drawable.baconmichael);
        }
        else if(avatar.equals("Pope")){
            bacon.setImageResource(R.drawable.baconpope);
        }
        else if(avatar.equals("Kermit")){
            bacon.setImageResource(R.drawable.baconkermit);
        }

        /*** Starts off the first set ***/
        chooseRandomSetEasy();
    }

    public void chooseNextSet(){
        if(segmentsCompleted <= 4){
            chooseRandomSetEasy();
        }
        else if(segmentsCompleted > 4 && segmentsCompleted <= 10){
            chooseRandomSetMedium();
        }
        else if(segmentsCompleted > 10){
            chooseRandomSetHard();
        }
    }

    /*
     * Sets on touch listener to listen for swipe or tap
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (deltaX > 50){
                    moveBaconRight();
                }
                else if (deltaX < -50){
                    moveBaconLeft();
                }
                else{
                    if(x2 < .5*dpWidth){
                        moveBaconLeft();
                    }
                    else{
                        moveBaconRight();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void moveBaconLeft(){
        float posFrom;
        float posTo;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bacon.getLayoutParams();
        if(baconPosition == BaconPositions.MIDLEFT){
            baconPosition = BaconPositions.LEFT;
            posFrom = (float)-.25*dpWidth+centerMargin;
            posTo = (float)-.5*dpWidth+farMargin;
            params.addRule(RelativeLayout.ALIGN_LEFT);
            moveBacon(posFrom, posTo, params);
        }
        else if(baconPosition == BaconPositions.MID){
            baconPosition = BaconPositions.MIDLEFT;
            posFrom = 0;
            posTo = (float)-.25*dpWidth+centerMargin;
            params.addRule(RelativeLayout.ALIGN_LEFT);
            moveBacon(posFrom, posTo, params);
        }
        else if(baconPosition == BaconPositions.MIDRIGHT){
            baconPosition = BaconPositions.MID;
            posFrom = (float).25*dpWidth-centerMargin;
            posTo = 0;
            params.addRule(RelativeLayout.ALIGN_TOP);
            moveBacon(posFrom, posTo, params);
        }
        else if(baconPosition == BaconPositions.RIGHT){
            baconPosition = BaconPositions.MIDRIGHT;
            posFrom = (float).5*dpWidth-farMargin;
            posTo = (float).25*dpWidth-centerMargin;
            params.addRule(RelativeLayout.ALIGN_RIGHT);
            moveBacon(posFrom, posTo, params);
        }
    }

    public void moveBaconRight(){
        float posFrom;
        float posTo;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bacon.getLayoutParams();
        if(baconPosition == BaconPositions.LEFT){
            baconPosition = BaconPositions.MIDLEFT;
            posFrom = (float)-.5*dpWidth+farMargin;
            posTo = (float)-.25*dpWidth+centerMargin;
            params.addRule(RelativeLayout.ALIGN_LEFT);
            moveBacon(posFrom, posTo, params);
        }
        else if(baconPosition == BaconPositions.MIDLEFT){
            baconPosition = BaconPositions.MID;
            posFrom = (float)-.25*dpWidth+centerMargin;
            posTo = 0;
            params.addRule(RelativeLayout.ALIGN_TOP);
            moveBacon(posFrom, posTo, params);
        }
        else if(baconPosition == BaconPositions.MID){
            baconPosition = BaconPositions.MIDRIGHT;
            posFrom = 0;
            posTo = (float).25*dpWidth-centerMargin;
            params.addRule(RelativeLayout.ALIGN_RIGHT);
            moveBacon(posFrom, posTo, params);
        }
        else if(baconPosition == BaconPositions.MIDRIGHT){
            baconPosition = BaconPositions.RIGHT;
            posFrom = (float).25*dpWidth-centerMargin;
            posTo = (float).5*dpWidth-farMargin;
            params.addRule(RelativeLayout.ALIGN_RIGHT);
            moveBacon(posFrom, posTo, params);
        }
    }

    /*
     * Moves all imageviews off screen and clears their current animation before finish of activity
     */
    protected void onDestroy(){
        handler.removeCallbacksAndMessages(null);
        for(ImageView i: views){
            i.clearAnimation();
            ObjectAnimator animY = new ObjectAnimator().ofFloat(i, "translationY", 1800f, 1800f);
            ObjectAnimator animX = new ObjectAnimator().ofFloat(i, "translationX", 0f, 0f);
            AnimatorSet set = new AnimatorSet();
            set.setDuration(1);
            set.playTogether(animX, animY);
            set.start();
        }
        for(ImageView i: views2){
            i.clearAnimation();
            ObjectAnimator animY = new ObjectAnimator().ofFloat(i, "translationY", 1800f, 1800f);
            ObjectAnimator animX = new ObjectAnimator().ofFloat(i, "translationX", 0f, 0f);
            AnimatorSet set = new AnimatorSet();
            set.setDuration(1);
            set.playTogether(animX, animY);
            set.start();
        }
        super.onDestroy();
    }

    /*
     * Moves Bacon left or right
     */
    private void moveBacon(float from, float to, final RelativeLayout.LayoutParams params){
        TranslateAnimation anim = new TranslateAnimation(from, to, 0, 0);
        anim.setDuration(200);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        bacon.startAnimation(anim);
        new CountDownTimer(700, 1000) {
            public void onTick(long millisUntilFinished) {      }
            public void onFinish() {
                bacon.setLayoutParams(params);
            }
        }.start();
    }

    /*
     * Initiates the ObjectAnimator translation for a given face view
     * @param ImageView i - view to be animated
     * @param int speed - duration of translation in ms
     * @param BaconPositions pos - position of translation
     */
    private void faceMotion(final ImageView i, int speed, final BaconPositions pos){
        float xPos = 0;
        i.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        if(pos == BaconPositions.LEFT){
            xPos = (float)-.5*dpWidth+farMargin;
        }
        if(pos == BaconPositions.MIDLEFT){
            xPos = (float)-.25*dpWidth+centerMargin;
        }
        if(pos == BaconPositions.MID){
            xPos = 0;
        }
        if(pos == BaconPositions.MIDRIGHT){
            xPos = (float).25*dpWidth-centerMargin;
        }
        if(pos == BaconPositions.RIGHT){
            xPos = (float).5*dpWidth-farMargin;
        }
        if(!lost) {
            final ObjectAnimator animY = new ObjectAnimator().ofFloat(i, "translationY", (float) dpHeight + 200, (float)-(dpHeight + 200));
            final ObjectAnimator animX = new ObjectAnimator().ofFloat(i, "translationX", xPos, xPos);
            final AnimatorSet set = new AnimatorSet();
            set.setDuration(speed);
            set.playTogether(animX, animY);
            set.start();
            animY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    int[] coords = new int[2];
                    i.getLocationOnScreen(coords);
                    if ((coords[1] < .4 * bacon.getMeasuredHeightAndState() && coords[1] > -.5 * bacon.getMeasuredHeightAndState() && baconPosition == pos && !lost)) {
                        Intent i = new Intent(MainActivity.this, GameOverActivity.class);
                        lost = true;
                        String highScores = addToHighScores(headCount);
                        addToGlobalScores(headCount);
                        // Retrieves total head count, defaults to 0, increments by the current count at end of play
                        int totalHeadCount = sharedPreferences.getInt("TotalHeadCount", 0);
                        totalHeadCount += headCount;
                        // Retrieves total egg count, defaults to 0, increments for current egg count at end of play
                        int totalEggCount = sharedPreferences.getInt("TotalEggCount", 0);
                        int previousEggCount = totalEggCount;
                        totalEggCount += eggCount;
                        // increments of unlocking new avatars
                        int[] unlocks = new int[]{5,15,30,50,100,150,200};
                        avatarUnlockToast(previousEggCount, totalEggCount, unlocks);
                        // Retrieves highest egg count for single round, updates if current egg count is greater
                        int highEggCount = sharedPreferences.getInt("HighEggCount", 0);
                        if(eggCount > highEggCount){
                            highEggCount = eggCount;
                        }
                        editor.putString("HighScores", highScores);
                        editor.putInt("TotalHeadCount", totalHeadCount);
                        editor.putInt("TotalEggCount", totalEggCount);
                        editor.putInt("HighEggCount", highEggCount);
                        editor.commit();
                        handler.removeCallbacksAndMessages(null);
                        set.cancel();
                        startActivity(i);
                        finish();
                    }
                    if(coords[1] < -.85 * bacon.getMeasuredHeightAndState() && !lost && baconPosition != pos){
                        headCount++;
                        headCountText.setText(""+headCount);
                        i.setLayerType(View.LAYER_TYPE_NONE, null);
                        animY.cancel();
                        set.cancel();
                    }
                }
            });
        }
    }

    /*
     * Creates the faces chomping animation
     */
    private void faceAnimation(final int first, final int second, final int millis, final ImageView i){
        i.setImageResource(first);
        new CountDownTimer(millis, 1000) {
            final int secondImage = second;
            public void onTick(long millisUntilFinished) { }
            public void onFinish() {
                i.setImageResource(secondImage);
            }
        }.start();
    }

    /*
     * Uses helper functions together to begin the next face motion
     * millisToWait, mouthMillis(more is faster), ImageView i, firstImage, secondImage, row, speed(less is faster)
     */
    public void nextAction(final int millisToWait, final int mouthSpeed, final ImageView i, final int firstImage, final int secondImage, final BaconPositions pos, final int speed){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("NEXT ACTION", "HIT");
                faceMotion(i, speed, pos);
                i.setImageResource(secondImage);
                new CountDownTimer(speed, mouthSpeed*2){
                    public void onTick(long millisUntilFinished){
                        faceAnimation(firstImage, secondImage, mouthSpeed, i);
                    }
                    public void onFinish(){   }
                }.start();
            }
        }, millisToWait);
    }

    /*
     * Initiates the ObjectAnimator translation for a given egg view
     * @param ImageView i - view to be animated
     * @param int speed - duration of translation in ms
     * @param BaconPositions pos - position of translation
     */
    private void eggAction(final int millisToWait){
        final int speed = rand.nextInt(6000) + 2000;
        final BaconPositions pos = baconPositions[rand.nextInt(5)];
        final ImageView i;
        // Alternates egg views to avoid overlapping calls
        if(segmentsCompleted % 2 == 0){
            i = eggView;
            i.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else{
            i = eggView2;
            i.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                float xPos = 0;
                if(pos == BaconPositions.LEFT){
                    xPos = (float)-.5*dpWidth+farMargin;
                }
                if(pos == BaconPositions.MIDLEFT){
                    xPos = (float)-.25*dpWidth+centerMargin;
                }
                if(pos == BaconPositions.MID){
                    xPos = 0;
                }
                if(pos == BaconPositions.MIDRIGHT){
                    xPos = (float).25*dpWidth-centerMargin;
                }
                if(pos == BaconPositions.RIGHT){
                    xPos = (float).5*dpWidth-farMargin;
                }
                // sets egg image to green egg if suess character
                if(avatar.equals("Green")){
                    i.setImageResource(R.drawable.greenegg);
                }
                else{
                    i.setImageResource(R.drawable.egg);
                }
                final ObjectAnimator animY = new ObjectAnimator().ofFloat(i, "translationY", (float) dpHeight + 200, (float)-(dpHeight + 200));
                final ObjectAnimator animX = new ObjectAnimator().ofFloat(i, "translationX", xPos, xPos);
                final AnimatorSet set = new AnimatorSet();
                set.setDuration(speed);
                set.playTogether(animX, animY);
                set.start();
                animY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int[] coords = new int[2];
                        i.getLocationOnScreen(coords);
                        if ((coords[1] < .75 * bacon.getMeasuredHeightAndState() && coords[1] > -.45 * bacon.getMeasuredHeightAndState() && baconPosition == pos && !lost)) {
                            eggCount++;
                            eggCountText.setText(""+eggCount);
                            Toast toast = Toast.makeText(MainActivity.this, "HUZZAH!", Toast.LENGTH_SHORT);
                            ViewGroup view = (ViewGroup) toast.getView();
                            view.setBackgroundColor(Color.TRANSPARENT);
                            TextView toastText = (TextView) view.getChildAt(0);
                            if(dpWidth/density >= 720) {
                                toastText.setTextSize(50);
                            }
                            else if(dpWidth/density >= 600){
                                toastText.setTextSize(40);
                            }
                            else if(dpWidth/density >= 360){
                                toastText.setTextSize(30);
                            }
                            else{
                                toastText.setTextSize(20);
                            }
                            toast.show();
                            i.setImageResource(0);
                            animY.cancel();
                            set.cancel();
                        }
                    }
                });
            }
        }, millisToWait);
    }

    /*
     * Shuffles 5 element array to be used for face animation positioning
     */
    private int[] randomPositions(){
        int[] array = {0,1,2,3,4};
        for(int i = 0; i < 5; i++){
            int r = rand.nextInt(5);
            int temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
        return array;
    }

    /*
     * Used as indexes of faceImages, must be even numbers, odd numbers are open mouths corresponding to their
     * closed mouth even image counterpart
     */
    private void setFacesIndexArray(){
        for(int i = 0; i < faces.length; i++){
            int r = rand.nextInt(faceImages.length)/2*2;
            faces[i] = r;
        }
    }

    /*
     * Adds value to high score array, returns string of scores to be saved
     */
    private String addToHighScores(int currentScore){
        for(int i = 0; i < highScores.length; i++){
            if(currentScore > highScores[i]){
                int newIndex = i;
                for(int j = highScores.length-1; j > newIndex; j--){
                    highScores[j] = highScores[j-1];
                }
                highScores[newIndex] = currentScore;
                break;
            }
        }
        String scoresString = "" + highScores[0];
        for(int i = 1; i < highScores.length; i++){
            scoresString += "\n" + highScores[i];
        }
        return scoresString;
    }

    private void addToGlobalScores(final int currentScore){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                String data = dataSnapshot.getValue(String.class);
                String[] scores = data.split("\n");
                int[] globalScores = new int[5];
                for(int i = 0; i < scores.length; i++){
                    globalScores[i] = Integer.parseInt(scores[i]);
                }
                for(int i = 0; i < globalScores.length; i++){
                    if(currentScore > globalScores[i]){
                        int newIndex = i;
                        for(int j = globalScores.length-1; j > newIndex; j--){
                            globalScores[j] = globalScores[j-1];
                        }
                        globalScores[newIndex] = currentScore;
                        break;
                    }
                }
                String scoresString = "" + globalScores[0];
                for(int i = 1; i < globalScores.length; i++){
                    scoresString += "\n" + globalScores[i];
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();
                ref.setValue(scoresString);
            }
            @Override
            public void onCancelled(DatabaseError error){
                Toast.makeText(MainActivity.this, "Error posting global high score, please contact developer", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*
     * Launches a toast alerting the user of new avatars unlocked
     * @param int previous - egg count before current play ends
     * @param int current - new egg count after play
     * @param int[] - egg counts where an unlock occurs
     */
    private void avatarUnlockToast(int prev, int curr, int[] unlocks){
        for(int i = 0; i < unlocks.length; i++){
            if(prev < unlocks[i] && curr >= unlocks[i]){
                Toast toast = Toast.makeText(MainActivity.this, "CONGRATS! YOU HAVE COLLECTED " + unlocks[i] + " OR MORE EGGS! NEW BACON AVATARS UNLOCKED", Toast.LENGTH_LONG);
                ViewGroup view = (ViewGroup) toast.getView();
                TextView toastText = (TextView) view.getChildAt(0);
                toastText.setGravity(Gravity.CENTER);
                toastText.setTypeface(Typeface.SANS_SERIF);
                if(dpWidth/density >= 720) {
                    toastText.setTextSize(40);
                }
                else if(dpWidth/density >= 600){
                    toastText.setTextSize(30);
                }
                else{
                    toastText.setTextSize(20);
                }
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    /*
     * <<<---ANIMATION SETS FOR FACE ANIMATIONS--->>>
     * @param BaconPositions[] positions - The 5 available positions, chosen randomly
     * @param ImageView[] views - available ImageViews (12) for translations (indexes 0 - 11)
     */
    private void animationSetEasy1(BaconPositions[] positions, final ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[0]], 6000);
        nextAction(0, 350, views[1], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[1]], 6000);
        nextAction(600, 850, views[2], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 6000);
        nextAction(1200, 950, views[3], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[2]], 6000);
        nextAction(1800, 550, views[4], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 6000);
        nextAction(2400, 250, views[5], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[1]], 6000);
        nextAction(2400, 350, views[6], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 6000);
        nextAction(3600, 750, views[7], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[2]], 6000);
        nextAction(3600, 450, views[8], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 6000);
        nextAction(4000, 450, views[9], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[0]], 6000);
        nextAction(4000, 350, views[10], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[3]], 6000);
        nextAction(4600, 650, views[11], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[2]], 6000);
        eggAction(3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 6500);
    }

    private void animationSetEasy2(BaconPositions[] positions, final ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 5000);
        nextAction(600, 350, views[1], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 6000);
        nextAction(600, 850, views[2], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 6000);
        nextAction(1200, 950, views[3], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 5000);
        nextAction(1800, 550, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[4]], 6000);
        nextAction(1800, 250, views[5], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 6000);
        nextAction(2400, 350, views[6], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 5000);
        nextAction(3000, 750, views[7], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 6000);
        nextAction(3600, 450, views[8], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 4000);
        nextAction(3800, 450, views[9], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 4500);
        nextAction(3900, 350, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 5200);
        nextAction(4600, 650, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 6000);
        eggAction(4300);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 6500);
    }

    private void animationSetEasy3(BaconPositions[] positions, final ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 6000);
        nextAction(0, 350, views[1], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 6000);
        //nextAction(0, 850, views[2], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 6000);
        nextAction(0, 950, views[3], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[2]], 6000);
        nextAction(1000, 550, views[4], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[4]], 6000);
        nextAction(1000, 250, views[5], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[1]], 6000);
        nextAction(1000, 350, views[6], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[3]], 6000);
        //nextAction(1000, 750, views[7], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 6000);
        nextAction(2000, 450, views[8], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 6000);
        nextAction(2000, 450, views[9], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 6000);
        nextAction(2000, 350, views[10], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 6000);
        //nextAction(2000, 650, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 6000);
        eggAction(1500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 4500);
    }

    private void animationSetEasy4(BaconPositions[] positions, final ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 5000);
        nextAction(0, 350, views[1], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 6000);
        nextAction(600, 850, views[2], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 6000);
        nextAction(1200, 950, views[3], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[0]], 5000);
        nextAction(1800, 550, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[4]], 6000);
        nextAction(1800, 250, views[5], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 6000);
        nextAction(1800, 350, views[6], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 5000);
        nextAction(3000, 750, views[7], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 6000);
        nextAction(3600, 450, views[8], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 4000);
        nextAction(3600, 450, views[9], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 4500);
        nextAction(4300, 350, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 5200);
        nextAction(4300, 650, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 6000);
        eggAction(3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 6200);
    }

    /*
     * Back and forth with 3 row ending
     */
    private void animationSetMedium1(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(600, 450, views[1], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 3000);
        nextAction(1200, 450, views[2], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[2]], 3000);
        nextAction(1800, 450, views[3], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 3000);
        nextAction(2400, 450, views[4], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 3000);
        nextAction(2400, 450, views[5], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 3000);
        nextAction(3600, 450, views[6], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(3600, 450, views[7], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[1]], 3000);
        nextAction(3600, 450, views[8], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 3000);
        nextAction(4000, 450, views[9], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(4000, 450, views[10], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 3000);
        nextAction(4600, 450, views[11], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[2]], 3000);
        eggAction(2800);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 5600);
    }

    /*
    * Back and forth
    */
    private void animationSetMedium2(BaconPositions[] positions, ImageView[] views){
        // randomizes whether it starts left and moves right or vice versa
        int r = rand.nextInt(2);
        int[] randPosIndex = {0,1,2,3,4};
        if(r == 0){randPosIndex = new int[] {0,1,2,3,4};}
        if(r == 1){randPosIndex = new int[] {4,3,2,1,0};}
        nextAction(0, 450, views[0], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(400, 450, views[1], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 3000);
        nextAction(800, 450, views[2], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(1200, 450, views[3], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[3]], 3000);
        nextAction(1600, 450, views[4], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 3000);
        nextAction(2000, 450, views[5], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 3000);
        nextAction(2400, 450, views[6], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(2800, 450, views[7], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[1]], 3000);
        nextAction(3200, 450, views[8], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(3600, 450, views[9], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 3000);
        nextAction(4000, 450, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 3000);
        nextAction(4400, 450, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 3000);
        eggAction(2800);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 5600);
    }

    private void animationSetMedium3(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 5000);
        nextAction(0, 350, views[1], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 6700);
        nextAction(600, 850, views[2], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 5700);
        nextAction(1200, 950, views[3], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(1800, 550, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[4]], 5500);
        nextAction(1800, 250, views[5], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 5000);
        nextAction(1800, 350, views[6], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 3000);
        nextAction(3000, 750, views[7], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 5500);
        nextAction(3600, 450, views[8], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 3000);
        nextAction(3600, 450, views[9], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 4500);
        nextAction(4300, 350, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 3800);
        nextAction(4300, 650, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 4200);
        eggAction(3500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 5600);
    }

    /*
    * Back and forth beginning only
    */
    private void animationSetMedium4(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(400, 450, views[1], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 3000);
        nextAction(800, 450, views[2], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(1200, 450, views[3], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[3]], 3000);
        nextAction(2000, 450, views[4], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 3000);
        nextAction(2000, 450, views[5], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 3000);
        nextAction(2000, 450, views[6], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(3200, 450, views[7], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[3]], 3000);
        nextAction(3200, 450, views[8], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(3600, 450, views[9], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 3000);
        nextAction(4000, 450, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 3000);
        nextAction(4400, 450, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 3000);
        eggAction(2800);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 5600);
    }

    private void animationSetMedium5(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 2500);
        nextAction(600, 350, views[1], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[2]], 3000);
        nextAction(600, 850, views[2], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 3000);
        nextAction(1200, 950, views[3], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[1]], 2500);
        nextAction(1800, 550, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[3]], 3000);
        nextAction(1800, 250, views[5], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 3000);
        nextAction(2400, 350, views[6], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 2500);
        nextAction(3000, 750, views[7], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 3000);
        nextAction(3600, 450, views[8], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 2500);
        nextAction(3800, 450, views[9], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[3]], 2500);
        nextAction(3900, 350, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 2700);
        nextAction(4600, 650, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[4]], 3000);
        eggAction(4300);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 5000);
    }

    private void animationSetHard1(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 900, views[0], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[2]], 2900);
        nextAction(0, 150, views[1], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[0]], 3100);
        nextAction(0, 500, views[2], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 2850);
        nextAction(0, 350, views[3], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 2650);
        nextAction(500, 450, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[1]], 3300);
        nextAction(500, 800, views[5], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[2]], 3300);
        nextAction(1500, 800, views[6], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 2500);
        nextAction(1500, 400, views[7], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[4]], 2500);
        nextAction(1500, 450, views[8], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 2500);
        nextAction(1500, 300, views[9], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[0]], 4000);
        nextAction(1500, 200, views[10], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[1]], 4000);
        nextAction(1500, 800, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 4000);
        eggAction(700);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 3000);
    }

    private void animationSetHard2(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 8000);
        nextAction(0, 850, views[1], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[1]], 4500);
        //nextAction(1000, 650, views[2], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[3]], 3000);
        nextAction(1000, 850, views[3], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[2]], 4500);
        nextAction(1000, 650, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 4500);
        nextAction(1000, 450, views[5], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 4000);
        nextAction(2000, 480, views[6], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 4700);
        //nextAction(2500, 750, views[8], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[3]], 2500);
        nextAction(3000, 650, views[9], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[1]], 2500);
        nextAction(3000, 550, views[10], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[4]], 2500);
        eggAction(2200);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 4200);
    }

    private void animationSetHard3(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 450, views[0], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[0]], 3000);
        nextAction(200, 450, views[1], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[1]], 3000);
        nextAction(400, 450, views[2], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 3000);
        nextAction(600, 450, views[3], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 3000);
        nextAction(800, 450, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[0]], 3000);
        nextAction(1000, 450, views[5], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[3]], 3000);
        nextAction(1200, 450, views[6], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[4]], 3000);
        //nextAction(1400, 450, views[7], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[2]], 3000);
        nextAction(1600, 450, views[8], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[3]], 3000);
        nextAction(1800, 450, views[9], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[1]], 3000);
        nextAction(2000, 450, views[10], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 3000);
        nextAction(2200, 450, views[11], faceImages[faces[3]], faceImages[faces[3]]+1, positions[randPosIndex[4]], 3000);
        eggAction(1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 3200);
    }

    private void animationSetHard4(BaconPositions[] positions, ImageView[] views){
        int[] randPosIndex = randomPositions();
        nextAction(0, 900, views[0], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[2]], 2900);
        //nextAction(0, 150, views[1], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[0]], 3100);
        nextAction(0, 500, views[2], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[2]], 5000);
        nextAction(500, 350, views[3], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[4]], 2650);
        nextAction(500, 450, views[4], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[1]], 3300);
        nextAction(500, 800, views[5], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[2]], 3300);
        nextAction(1500, 800, views[6], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[3]], 4000);
        nextAction(1500, 400, views[7], faceImages[faces[0]], faceImages[faces[0]+1], positions[randPosIndex[4]], 4000);
        nextAction(1500, 450, views[8], faceImages[faces[1]], faceImages[faces[1]+1], positions[randPosIndex[1]], 2500);
        nextAction(1500, 300, views[9], faceImages[faces[4]], faceImages[faces[4]+1], positions[randPosIndex[0]], 2900);
        nextAction(1500, 200, views[10], faceImages[faces[3]], faceImages[faces[3]+1], positions[randPosIndex[1]], 3100);
        nextAction(1500, 800, views[11], faceImages[faces[2]], faceImages[faces[2]+1], positions[randPosIndex[2]], 4000);
        eggAction(500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                segmentsCompleted++;
                chooseNextSet();
            }
        }, 3300);
    }

    /*
     * Chooses animation set randomly
     */
    private void chooseRandomSetEasy(){
        ImageView[] viewsToPass;
        if(segmentsCompleted % 2 == 0){
            viewsToPass = views;
        } else {
            viewsToPass = views2;
        }
        int r = rand.nextInt(4);
        if(r == 0){ animationSetEasy1(baconPositions, viewsToPass); }
        else if(r == 1){ animationSetEasy2(baconPositions, viewsToPass); }
        else if(r == 2){ animationSetEasy3(baconPositions, viewsToPass); }
        else if(r == 3){ animationSetEasy4(baconPositions, viewsToPass); }
    }
    private void chooseRandomSetMedium(){
        ImageView[] viewsToPass;
        if(segmentsCompleted % 2 == 0){
            viewsToPass = views;
        } else {
            viewsToPass = views2;
        }
        int r = rand.nextInt(6);
        if(r == 0){ animationSetMedium1(baconPositions, viewsToPass); }
        else if(r == 1){ animationSetMedium2(baconPositions, viewsToPass); }
        else if(r == 2){ animationSetMedium3(baconPositions, viewsToPass); }
        else if(r == 3){ animationSetMedium4(baconPositions, viewsToPass); }
        else if(r == 4){ animationSetMedium5(baconPositions, viewsToPass); }
        else if(r == 5){ chooseRandomSetEasy(); }
    }
    private void chooseRandomSetHard(){
        ImageView[] viewsToPass;
        if(segmentsCompleted % 2 == 0){
            viewsToPass = views;
        } else {
            viewsToPass = views2;
        }
        int r = rand.nextInt(6);
        if(r == 0){ animationSetHard1(baconPositions, viewsToPass); }
        else if(r == 1){ animationSetHard2(baconPositions, viewsToPass); }
        else if(r == 2){ animationSetHard3(baconPositions, viewsToPass); }
        else if(r == 3){ animationSetHard4(baconPositions, viewsToPass); }
        else if(r == 4){ chooseRandomSetEasy(); }
        else if(r == 5){ chooseRandomSetMedium(); }
    }
}