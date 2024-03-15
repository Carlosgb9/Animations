package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bCercle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        setButtons();
    }

    public void bStartAction() {
        Intent i = new Intent(this, Action.class);
        int button = R.id.bDrawCercle;
        i.putExtra("button", button);
        startActivity(i);
    }

    private void setButtons(){
        bCercle.setOnClickListener(v -> bStartAction());
    }

    private void initialize(){
        bCercle=findViewById(R.id.bDrawCercle);
    }





}