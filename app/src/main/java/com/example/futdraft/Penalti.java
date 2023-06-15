package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Penalti extends AppCompatActivity{
    Random random;
    int portero;
    Button izqab,izqarr,medio,derarr,derab;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalti);
        random =new Random();
        portero = random.nextInt(6-1)+1;
        izqab = findViewById(R.id.btnizqAb);
        izqarr = findViewById(R.id.btnizqArr);
        medio = findViewById(R.id.btnmedio);
        derab = findViewById(R.id.btnderAb);
        derarr = findViewById(R.id.btnderArr);
        imagen = findViewById(R.id.imageView7);
    }


    public void comprobarPenalti(View view) {
        int opcion=0;
        switch(view.getId()){
            case R.id.btnizqArr:
                opcion=Integer.parseInt(izqarr.getText().toString());
                break;
            case R.id.btnizqAb:
                opcion=Integer.parseInt(izqab.getText().toString());
                break;
            case R.id.btnmedio:
                opcion=Integer.parseInt(medio.getText().toString());
                break;
            case R.id.btnderAb:
                opcion=Integer.parseInt(derab.getText().toString());
                break;
            case R.id.btnderArr:
                opcion=Integer.parseInt(derarr.getText().toString());
                break;
        }
        if(portero==opcion){
            if(portero==1){
                imagen.setBackgroundResource(R.drawable.paradaizqarriba);
            }else if(portero==2){
                imagen.setBackgroundResource(R.drawable.paradaizqabajo);
            }else if(portero==3){
                imagen.setBackgroundResource(R.drawable.paradamedio);
            }else if(portero==4){
                imagen.setBackgroundResource(R.drawable.paradaderarriba);
            }else if(portero==5){
                imagen.setBackgroundResource(R.drawable.paradaderabajo2);
            }
        }else{
            if(opcion==1){
                imagen.setBackgroundResource(R.drawable.golizqarriba);
            }else if(opcion==2){
                imagen.setBackgroundResource(R.drawable.golizqabajo);
            }else if(opcion==3){
                imagen.setBackgroundResource(R.drawable.golmedio);
            }else if(opcion==4){
                imagen.setBackgroundResource(R.drawable.golderarriba);
            }else if(opcion==5){
                imagen.setBackgroundResource(R.drawable.golderabajo);
            }
        }

        izqarr.setEnabled(false);
        izqab.setEnabled(false);
        medio.setEnabled(false);
        derarr.setEnabled(false);
        derab.setEnabled(false);
        izqarr.setAlpha(0);
        izqab.setAlpha(0);
        medio.setAlpha(0);
        derarr.setAlpha(0);
        derab.setAlpha(0);
        onBackPressed();
    }

}