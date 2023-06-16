package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class Penalti extends AppCompatActivity{
    SQLiteHelper helper;
    SQLiteDatabase db;
    Random random;
    int portero;
    Button izqab,izqarr,medio,derarr,derab;
    ImageView imagen,gol;
    TextView txt;
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
        gol = findViewById(R.id.imageView9);
        Glide.with(this).asGif().load(R.drawable.gol).into(gol);
        txt = findViewById(R.id.textView19);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    public void comprobarPenalti(View view) {
        new CountDownTimer(3000, 3000){
            public void onTick(long millisUntilFinished){
                int opcion=0;
                txt.setText("");
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
                    gol.setVisibility(View.VISIBLE);
                    gol.setImageResource(R.drawable.parada);
                    Glide.with(getApplicationContext()).asGif().load(R.drawable.parada).into(gol);
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
                    Cursor cursor =
                            db.query(EstructuraBBDD.Partido.TABLE_NAME_PARTIDO,null,
                                    null,null,null,null,null);
                    cursor.moveToLast();
                    String equipo1 = cursor.getString(1);
                    String equipo2 = cursor.getString(2);
                    int goles1 = cursor.getInt(3);
                    int anotar = goles1+1;
                    ContentValues values = new ContentValues();
                    values.put("goles1",anotar);
                    db.update("partido",values,"equipo2 == ?",new String[]{equipo2});
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
                    gol.setVisibility(View.VISIBLE);
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
            }
            public  void onFinish(){

                volverAtras();
            }
        }.start();


    }

    private void volverAtras(){
        try {
            Thread.sleep(2000);// Espera durante 2000 milisegundos (2 segundos)
            onBackPressed();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}