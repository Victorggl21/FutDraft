package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Random;

public class fd141 extends AppCompatActivity  implements View.OnClickListener{
    RatingBar puntQuimica;
    ImageButton btn1,btn2, btn3, btn4,btn5, btn6, btn7;
    SQLiteHelper helper;
    SQLiteDatabase db;
    Button btnQuimica;
    String posicion,equipo;
    int foto,delantero,portero=0,defensa=0,medio1=0,medio2=0,medio3=0,medio4=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd141);
        btn1= findViewById(R.id.imageButton);
        btn2= findViewById(R.id.imageButton2);
        btn3= findViewById(R.id.imageButton3);
        btn4= findViewById(R.id.imageButton4);
        btn5= findViewById(R.id.imageButton5);
        btn6= findViewById(R.id.imageButton6);
        btn7= findViewById(R.id.imageButton7);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        puntQuimica = findViewById(R.id.ratingBar);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();

    }

    @Override
    protected void onResume() {
        super.onResume();
        consultaEleccion();

    }

    private void consultaQuimica() {

            puntQuimica.setAlpha(1);
            Cursor cursor=  db.rawQuery("SELECT COUNT(equipo) from titulares group BY equipo",null);
            if(cursor.getCount()!=0){
                cursor.moveToFirst();
                int cantidad2= cursor.getInt(0);
                while(!cursor.isAfterLast()){
                    if(cantidad2==7){
                        puntQuimica.setRating(5);
                    }else if(cantidad2==6){
                        puntQuimica.setRating(4.5F);
                    }else if(cantidad2==5){
                        puntQuimica.setRating(puntQuimica.getRating()+4);
                    }else if(cantidad2==4){
                        puntQuimica.setRating(puntQuimica.getRating()+3);
                    }else if(cantidad2==3){
                        puntQuimica.setRating(puntQuimica.getRating()+2);
                    }else if(cantidad2==2){
                        puntQuimica.setRating(puntQuimica.getRating()+1.5F);
                    }
                }

            }




    }

    public void abrirdelantero(View view) {

        Intent i = new Intent(this,Eleccion.class);
        startActivity(i);
        //consultaEleccion();
    }

    public void abrirmc(View view) {
        Intent i = new Intent(this,EleccionMC.class);
        startActivity(i);
        //consultaEleccion();
    }
    public void abrirdf(View view) {
        Intent i = new Intent(this,EleccionDF.class);
        startActivity(i);
        //consultaEleccion();
    }
    public void abrirpt(View view) {
        Intent i = new Intent(this,EleccionPT.class);
        startActivity(i);
        //consultaEleccion();
    }

    private void consultaEleccion(){

        Cursor cursor2 =
                db.query(EstructuraBBDD.Titulares.TABLE_NAME_TITULARES, null,
                        null, null, null, null, null);
        cursor2.moveToFirst();

        while(!cursor2.isAfterLast()){
            posicion = cursor2.getString(1);
            equipo = cursor2.getString(2);
            foto = cursor2.getInt(3);
            if(posicion.equals("DC")){
                delantero=foto;
                btn7.setBackgroundResource(delantero);
            }else if(posicion.equals("PT")){
                portero=foto;
                btn1.setBackgroundResource(portero);
            }else if(posicion.equals("DF")){
                defensa=foto;
                btn2.setBackgroundResource(defensa);
            }else if(posicion.equals("MC")){
                if(medio1==0){
                    medio1=foto;
                    btn3.setBackgroundResource(medio1);
                }else if(medio2==0 && foto!=medio1){
                    medio2=foto;
                    btn4.setBackgroundResource(medio2);
                }else if(medio3==0 && foto!=medio1 && foto!=medio2){
                    medio3=foto;
                    btn5.setBackgroundResource(medio3);
                }else if(medio4==0 && foto!=medio1 && foto!=medio2 && foto!=medio3){
                    medio4=foto;
                    btn6.setBackgroundResource(medio4);
                }
            }

            cursor2.moveToNext();
        }
        db.close();

    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageButton:
                abrirpt(null);

                consultaQuimica();
                btn1.setEnabled(false);
                break;
            case R.id.imageButton2:
                abrirdf(null);
                btn2.setEnabled(false);
                consultaQuimica();
                break;
            case R.id.imageButton3:
                abrirmc(null);
                btn3.setEnabled(false);
                consultaQuimica();
                break;
            case R.id.imageButton4:
                abrirmc(null);
                btn4.setEnabled(false);
                consultaQuimica();
                break;
            case R.id.imageButton5:
                abrirmc(null);
                btn5.setEnabled(false);
                consultaQuimica();
                break;
            case R.id.imageButton6:
                abrirmc(null);
                btn6.setEnabled(false);
                consultaQuimica();
                break;
            case R.id.imageButton7:
                abrirdelantero(null);
                btn7.setEnabled(false);
                consultaQuimica();
                break;

            default:

                break;

        }
    }


}