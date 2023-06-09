package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class EleccionPT extends AppCompatActivity implements View.OnClickListener{

    SQLiteHelper helper;
    SQLiteDatabase db;

    int [] portero = new int []{
            R.drawable.carta1k1,R.drawable.carta1k2,
            R.drawable.cartaaniaquiladores1,R.drawable.cartaaniaquiladores2,
            R.drawable.cartabarrio10,R.drawable.cartabarrio11,
            R.drawable.cartajijantes1,R.drawable.cartajijantes2,
            R.drawable.cartakuni1,
            R.drawable.cartapio9,
            R.drawable.cartaporcinos10,R.drawable.cartaporcinos11,
            R.drawable.cartarayo10,R.drawable.cartarayo11,
            R.drawable.cartasaiyans10,
            R.drawable.cartatroncos1,R.drawable.cartatroncos2,
            R.drawable.cartaum7,
            R.drawable.cartaxbuyer1,R.drawable.cartaxbuyer2,
    };

    int n1=0,n2=0, n3=0, n4=0;
    Random random = new Random();
    Random random2 = new Random();
    Random random3 = new Random();
    Random random4 = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_pt);
        jugadores();
    }

    public void jugadores(){
        n1 = random.nextInt(portero.length);
        n2 = random2.nextInt(portero.length);
        while(portero[n2]==portero[n1] ){
            n2 = random.nextInt(portero.length);
        }
        n3 = random3.nextInt(portero.length);
        while(portero[n3]==portero[n2] || portero[n3]==portero[n1] ){
            n3 = random.nextInt(portero.length);
        }
        n4 = random4.nextInt(portero.length);
        while(portero[n4]==portero[n3] || portero[n4]==portero[n2] || portero[n4]==portero[n1] ){
            n4 = random.nextInt(portero.length);
        }

        ImageView img1 = findViewById(R.id.imageButton55);
        img1.setImageResource(portero[n1]);
        ImageView img2 = findViewById(R.id.imageButton56);
        img2.setImageResource(portero[n2]);
        ImageView img3 = findViewById(R.id.imageButton57);
        img3.setImageResource(portero[n3]);
        ImageView img4 = findViewById(R.id.imageButton58);
        img4.setImageResource(portero[n4]);
    }

    public void volveralineacion(View view) {
        onBackPressed();
        consultaEleccion(portero[n1]);
    }
    public void volveralineacion2(View view) {
        onBackPressed();
        consultaEleccion(portero[n2]);
    }
    public void volveralineacion3(View view) {
        onBackPressed();
        consultaEleccion(portero[n3]);
    }
    public void volveralineacion4(View view) {
        onBackPressed();
        consultaEleccion(portero[n4]);
    }

    private void consultaEleccion(int eleccion){
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor2 =
                db.query(EstructuraBBDD.Jugador.TABLE_NAME_JUGADOR, null,
                        null, null, null, null, null);
        cursor2.moveToFirst();
        String posicion = cursor2.getString(2);
        String equipo = cursor2.getString(3);
        int foto = cursor2.getInt(5);
        while(foto!=eleccion){
            cursor2.moveToNext();
            posicion = cursor2.getString(2);
            equipo = cursor2.getString(3);
            foto = cursor2.getInt(5);

        }
        if(foto==eleccion){
            ContentValues values2 = new ContentValues();
            values2.put("posicion", posicion);
            values2.put("equipo", equipo);
            values2.put("foto", foto);
            db.insert("titulares",null,values2);
        }
        db.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButton55:
                volveralineacion(null);
                break;
            case R.id.imageButton56:
                volveralineacion2(null);
                break;
            case R.id.imageButton57:
                volveralineacion3(null);
                break;
            case R.id.imageButton58:
                volveralineacion4(null);
                break;
        }
    }
}