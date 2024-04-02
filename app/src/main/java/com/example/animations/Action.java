package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class Action extends AppCompatActivity {

    private ImageView dragon;

    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int selectedAction = bundle.getInt("button");
        if (selectedAction == R.id.bDrawCercle){
            setContentView(new CircleView(this));
        } else if (selectedAction == R.id.bLinear){
            setContentView(R.layout.activity_action);
            linearAnimation();
        } else if (selectedAction == R.id.bRotation){
            setContentView(R.layout.activity_action);
            rotationAnimation();
        } else if (selectedAction == R.id.bFade){
            setContentView(R.layout.activity_action);
            fadeAnimation();
        } else if (selectedAction == R.id.bColor){
            setContentView(R.layout.activity_action);
            colorAnimation();
        }
    }

    public void linearAnimation(){
        init();
        layout.setBackgroundResource(R.drawable.mordor);
        @SuppressLint("ResourceType") AnimatorSet sunSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.dragon_moving);
        sunSet.setTarget(dragon);
        sunSet.start();
    }

    public void rotationAnimation(){
        init();
        dragonVisibility();
        layout.setBackgroundResource(R.drawable.mordor);
        AnimatorSet dragonSet = new AnimatorSet();
        TimeInterpolator inter = new LinearInterpolator();

        ValueAnimator rotateDragon = ObjectAnimator.ofFloat(dragon, "rotation", 360, 0);
        rotateDragon.setDuration(3000).setRepeatCount(ValueAnimator.INFINITE);
        rotateDragon.setInterpolator(inter);
        dragonSet.play(rotateDragon);

        ValueAnimator moveDragon = ObjectAnimator.ofFloat(dragon, "x", 300);
        moveDragon.setDuration(3000)
                .setRepeatMode(ValueAnimator.REVERSE);
        moveDragon.setRepeatCount(ValueAnimator.INFINITE);
        moveDragon.setInterpolator(inter);

        dragonSet.playTogether(rotateDragon,moveDragon);
        dragonSet.start();
    }

    public void fadeAnimation(){
        init();
        dragonVisibility();
        layout.setBackgroundResource(R.drawable.mordor);

        /*ValueAnimator fadeAnim = ObjectAnimator.ofFloat(dragon, "alpha", 1.0f, 0.0f);
        fadeAnim.setDuration(3000);
        fadeAnim.setRepeatCount(ValueAnimator.INFINITE);
        fadeAnim.setRepeatMode(ValueAnimator.REVERSE);*/

        AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
        fade.setDuration(3000);
        fade.setFillAfter(true);

        dragon.setAnimation(fade);
    }

    public void colorAnimation(){
        ValueAnimator skyAnim = ObjectAnimator.ofInt( findViewById(R.id.layout), "backgroundColor", Color.rgb(0x66, 0xcc, 0xff), Color.rgb(0x00, 0x66, 0x99));

        skyAnim.setDuration(3000);
        skyAnim.setRepeatCount(ValueAnimator.INFINITE);
        skyAnim.setRepeatMode(ValueAnimator.REVERSE);

        skyAnim.setEvaluator(new ArgbEvaluator()); // parecido al interpolador en cuanto a función

        skyAnim.start();
    }

    public void dragonVisibility(){
        dragon.setVisibility(View.VISIBLE);
    }

    private void init (){
        dragon = findViewById(R.id.dragon);
        layout = findViewById(R.id.layout);
    }

}