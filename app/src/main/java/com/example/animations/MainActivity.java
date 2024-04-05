package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bDrawCercle, bTween, bLinear, bRotation, bFade, bPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        setButtons();
    }

    public void startAction(int id) {
        Intent i = new Intent(this, Action.class);
        int button = id;
        i.putExtra("button", button);
        startActivity(i);
    }

    private void setButtons(){
        bDrawCercle.setOnClickListener(v -> startAction(R.id.bDrawCercle));
        bTween.setOnClickListener(v -> animacioTween());
        bLinear.setOnClickListener(v -> startAction(R.id.bLinear));
        bRotation.setOnClickListener(v -> startAction(R.id.bRotation));
        bFade.setOnClickListener(v -> startAction(R.id.bFade));
        bPersonal.setOnClickListener(v -> startAction(R.id.bPersonal));
    }

    private void initialize(){
        bDrawCercle = findViewById(R.id.bDrawCercle);
        bTween = findViewById(R.id.bTween);
        bLinear = findViewById(R.id.bLinear);
        bRotation = findViewById(R.id.bRotation);
        bFade = findViewById(R.id.bFade);
        bPersonal = findViewById(R.id.bPersonal);
    }

    public void animacioTween(){
        Button bTween = findViewById(R.id.bTween);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animacio);
        bTween.startAnimation(animation);
    }





}