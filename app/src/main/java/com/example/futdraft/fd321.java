package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class fd321 extends AppCompatActivity implements View.OnClickListener{
    ImageButton btn1,btn2, btn3, btn4,btn5, btn6, btn7;
    SQLiteHelper helper;
    SQLiteDatabase db;
    String posicion,equipo;
    int foto,delantero=0,portero=0,defensa=0,defensa2=0,defensa3=0,medio1=0,medio2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd321);
        btn1= findViewById(R.id.imageButton36);
        btn2= findViewById(R.id.imageButton37);
        btn3= findViewById(R.id.imageButton39);
        btn4= findViewById(R.id.imageButton40);
        btn5= findViewById(R.id.imageButton41);
        btn6= findViewById(R.id.imageButton42);
        btn7= findViewById(R.id.imageButton38);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        consultaEleccion();
    }

    public void abrirdelantero(View view) {
        Intent i = new Intent(this,Eleccion.class);
        startActivity(i);
    }

    public void abrirmc(View view) {
        Intent i = new Intent(this,EleccionMC.class);
        startActivity(i);
    }
    public void abrirdf(View view) {
        Intent i = new Intent(this,EleccionDF.class);
        startActivity(i);
    }
    public void abrirpt(View view) {
        Intent i = new Intent(this,EleccionPT.class);
        startActivity(i);
    }

    private void consultaEleccion(){
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor2 =
                db.query(EstructuraBBDD.Titulares.TABLE_NAME_TITULARES, null,
                        null, null, null, null, null);
        cursor2.moveToFirst();

        while(!cursor2.isAfterLast()){
            posicion = cursor2.getString(1);
            equipo = cursor2.getString(2);
            foto = cursor2.getInt(3);
            if(posicion.equals("DC")){
                if(delantero==0){
                    delantero=foto;
                    btn7.setBackgroundResource(delantero);
                }
            }else if(posicion.equals("PT")){
                portero=foto;
                btn1.setBackgroundResource(portero);
            }else if(posicion.equals("DF")){
                if(defensa==0){
                    defensa=foto;
                    btn2.setBackgroundResource(defensa);
                }else if(defensa2==0 && foto!=defensa){
                    defensa2=foto;
                    btn3.setBackgroundResource(defensa2);
                }else if(defensa3==0 && foto!=defensa && foto!=defensa2){
                    defensa3=foto;
                    btn4.setBackgroundResource(defensa3);
                }
            }else if(posicion.equals("MC")){
                if(medio1==0){
                    medio1=foto;
                    btn5.setBackgroundResource(medio1);
                }else if(medio2==0 && foto!=medio1){
                    medio2= foto;
                    btn6.setBackgroundResource(medio2);
                }
            }
            cursor2.moveToNext();
        }
        db.close();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageButton36:
                abrirpt(null);
                consultaEleccion();
                btn1.setEnabled(false);
                break;
            case R.id.imageButton37:
                abrirdf(null);
                consultaEleccion();
                btn2.setEnabled(false);
                break;
            case R.id.imageButton39:
                abrirdf(null);
                consultaEleccion();
                btn3.setEnabled(false);
                break;
            case R.id.imageButton40:
                abrirdf(null);
                consultaEleccion();
                btn4.setEnabled(false);

                break;
            case R.id.imageButton41:
                abrirmc(null);
                consultaEleccion();
                btn5.setEnabled(false);

                break;
            case R.id.imageButton42:
                abrirmc(null);
                consultaEleccion();
                btn6.setEnabled(false);

                break;
            case R.id.imageButton38:
                abrirdelantero(null);
                consultaEleccion();
                btn7.setEnabled(false);
                break;
            default:
                break;
        }
    }
}