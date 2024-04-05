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

    private ImageView dragon, flame;

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
            init();
            flame.setVisibility(View.INVISIBLE);
            linearAnimation();
        } else if (selectedAction == R.id.bRotation){
            setContentView(R.layout.activity_action);
            init();
            flame.setVisibility(View.INVISIBLE);
            rotationAnimation();
        } else if (selectedAction == R.id.bFade){
            setContentView(R.layout.activity_action);
            init();
            flame.setVisibility(View.INVISIBLE);
            fadeAnimation();
        } else if (selectedAction == R.id.bColor){
            setContentView(R.layout.activity_action);
            init();
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
        flameVisibility();
        layout.setBackgroundResource(R.drawable.mordor);
        ValueAnimator colorAnim = ObjectAnimator.ofInt(flame.getDrawable(), "tint", Color.rgb(255, 255, 0), Color.rgb(255, 0, 0));

        colorAnim.setDuration(3000);
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);

        colorAnim.setEvaluator(new ArgbEvaluator());

        colorAnim.start();
    }

    public void flameVisibility(){
        flame.setVisibility(View.VISIBLE);
    }

    private void init (){
        dragon = findViewById(R.id.dragon);
        layout = findViewById(R.id.layout);
        flame = findViewById(R.id.flame);
    }

}