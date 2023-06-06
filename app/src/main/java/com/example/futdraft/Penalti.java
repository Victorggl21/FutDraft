package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class Penalti extends AppCompatActivity {
    Random random;
    int portero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalti);
        random =new Random();
        portero = random.nextInt(6-1)+1;

    }


    public void comprobarPenalti(View view) {

        if(portero==1){

        }
    }
}