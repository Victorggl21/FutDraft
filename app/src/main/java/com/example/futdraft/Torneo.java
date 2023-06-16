package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Torneo extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    ImageView img;
    Button btnCerrar,btnJugar;
    MediaPlayer media,media2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        img = findViewById(R.id.imageView2);
        btnCerrar = findViewById(R.id.button3);
        btnJugar = findViewById(R.id.button10);
        btnCerrar.setAlpha(0);
        btnCerrar.setEnabled(false);
        comprobarProgreso();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        comprobarProgreso();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    public void comprobarProgreso(){
        Cursor cursor =
                db.query(EstructuraBBDD.Partido.TABLE_NAME_PARTIDO,null,
                        null,null,null,null,null,null);
        int num = cursor.getCount();
        if(num!=0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                int goles1 = cursor.getInt(3);
                int goles2 = cursor.getInt(4);
                if (id == num) {
                    if (num == 1) {
                        if(goles1!=0 || goles2!=0){
                            if(goles1>goles2){
                                img.setImageResource(R.drawable.progreso1fd);
                            }else {
                                img.setImageResource(R.drawable.progresoperdido1);
                                btnJugar.setEnabled(false);
                                btnJugar.setAlpha(0);
                                play();
                                btnCerrar.setAlpha(1);
                                btnCerrar.setEnabled(true);
                            }
                        }


                    }
                    if (num == 2) {
                        if(goles1==0 && goles2==0){
                            img.setImageResource(R.drawable.progreso1fd);
                        }else{
                        if (goles1 > goles2) {
                            img.setImageResource(R.drawable.progreso2fd);
                        } else {
                            img.setImageResource(R.drawable.progresoperdido2);
                            btnJugar.setEnabled(false);
                            btnJugar.setAlpha(0);
                            play();
                            btnCerrar.setAlpha(1);
                            btnCerrar.setEnabled(true);
                        }
                        }
                    }
                    if (num == 3) {
                        if(goles1==0 && goles2==0){
                            img.setImageResource(R.drawable.progreso2fd);
                        }else {
                            if (goles1 > goles2) {
                                img.setImageResource(R.drawable.progreso3fd);
                            } else {
                                img.setImageResource(R.drawable.progresoperdido3);
                                btnJugar.setEnabled(false);
                                btnJugar.setAlpha(0);
                                play();
                                btnCerrar.setAlpha(1);
                                btnCerrar.setEnabled(true);
                            }
                        }
                    }
                    if (num == 4) {
                        if(goles1==0 && goles2==0){
                            img.setImageResource(R.drawable.progreso3fd);
                        }else {
                            if (goles1 > goles2) {
                                img.setImageResource(R.drawable.progreso4fd);
                                playWin();
                            } else {
                                img.setImageResource(R.drawable.progresoperdido4);
                                btnJugar.setEnabled(false);
                                btnJugar.setAlpha(0);
                                play();
                                btnCerrar.setAlpha(1);
                                btnCerrar.setEnabled(true);
                            }
                        }
                    }
                }
                cursor.moveToNext();
            }
        }
    }

    public void play (){
        if(media==null) {
            media= MediaPlayer.create(this, R.raw.gameover);
        }
        if(!media.isPlaying()){
            media.start();
        }
    }

    public void playWin (){
        if(media2==null) {
            media2= MediaPlayer.create(this, R.raw.shempions);
        }
        if(!media2.isPlaying()){
            media2.start();
        }
    }

    public void stop (){
        if(media.isPlaying()){
            media.stop();
            media.release();
            media=null;
        }
        if(media2.isPlaying()){
            media2.stop();
            media2.release();
            media2=null;
        }
    }

    public void abrirPartido(View view) {
        Intent i = new Intent(this,Partido.class);
        startActivity(i);
    }

    public void cerrar(View view){
        finishAffinity();
    }
}