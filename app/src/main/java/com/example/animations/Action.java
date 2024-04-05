package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


public class Action extends AppCompatActivity {

    private ImageView dragon, flame, f1;

    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int selectedAction = bundle.getInt("button");
        if (selectedAction == R.id.bDrawCercle){
            setContentView(new CircleView(this));
        } else if (selectedAction == R.id.bLinear){
            inicioDragon();
            linearAndColorAnimation();
        } else if (selectedAction == R.id.bRotation){
            inicioDragon();
            rotationAnimation();
        } else if (selectedAction == R.id.bFade){
            inicioDragon();
            fadeAnimation();
        } else if (selectedAction == R.id.bPersonal){
            inicioF1();
            curveAnimation();
        }
    }

    public void linearAndColorAnimation(){

        @SuppressLint("ResourceType") AnimatorSet flyingDragon = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.dragon_moving);
        @SuppressLint("ResourceType") AnimatorSet flyingFlame = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.dragon_moving);
        flyingDragon.setTarget(dragon);
        flyingFlame.setTarget(flame);
        flyingDragon.start();
        flyingFlame.start();

        flameVisibility();
        ValueAnimator colorAnim = ObjectAnimator.ofInt(flame.getDrawable(), "tint", Color.rgb(255, 255, 0), Color.rgb(255, 0, 0));

        colorAnim.setDuration(3000);
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);

        colorAnim.setEvaluator(new ArgbEvaluator());

        colorAnim.start();
    }

    public void rotationAnimation(){

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

        /*ValueAnimator fadeAnim = ObjectAnimator.ofFloat(dragon, "alpha", 1.0f, 0.0f);
        fadeAnim.setDuration(3000);
        fadeAnim.setRepeatCount(ValueAnimator.INFINITE);
        fadeAnim.setRepeatMode(ValueAnimator.REVERSE);*/

        AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
        fade.setDuration(3000);
        fade.setFillAfter(true);

        dragon.setAnimation(fade);
    }

    public void flameVisibility(){
        flame.setVisibility(View.VISIBLE);
    }

    private void init (){
        dragon = findViewById(R.id.dragon);
        layout = findViewById(R.id.layout);
        flame = findViewById(R.id.flame);
        f1 = findViewById(R.id.f1);
    }

    public void inicioDragon(){
        setContentView(R.layout.activity_action);
        init();
        flame.setVisibility(View.INVISIBLE);
        dragon.setVisibility(View.VISIBLE);
        f1.setVisibility(View.INVISIBLE);
        layout.setBackgroundResource(R.drawable.mordor);
    }

    private Path createf1Path() {
        Path path = new Path();
        path.moveTo(0f, 800f);
        path.lineTo(400f, 500f);
        path.cubicTo(400f, 500f, 300f, 450f, 300f, 450f);
        path.lineTo(450f, 340f);
        return path;
    }

    private void curveAnimation() {
        Path path = createf1Path();
        ValueAnimator curveAnimation = ValueAnimator.ofObject(new PathEvaluator(path), new PointF(0f, 0f), new PointF(400f, 0f));
        curveAnimation.setDuration(3000);
        curveAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        curveAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                f1.setX(point.x);
                f1.setY(point.y);
            }
        });
        curveAnimation.start();
        sizeAnimation();
    }

    private void sizeAnimation() {
        ValueAnimator f1SizeX = ObjectAnimator.ofFloat(f1, "scaleX", 2, 0);
        ValueAnimator f1SizeY = ObjectAnimator.ofFloat(f1, "scaleY", 2, 0);
        f1SizeX.setDuration(3050);
        f1SizeY.setDuration(3050);
        f1SizeY.start();
        f1SizeX.start();
    }

    private void inicioF1(){
        setContentView(R.layout.activity_action);
        init();
        flame.setVisibility(View.INVISIBLE);
        dragon.setVisibility(View.INVISIBLE);
        f1.setVisibility(View.VISIBLE);
        layout.setBackgroundResource(R.drawable.eaurouge1);
        f1.setMaxHeight(85);
    }

}