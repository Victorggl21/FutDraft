package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    SQLiteDatabase db;
    SQLiteHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLiteHelper(this);
        db= helper.getWritableDatabase();



        ContentValues values= new ContentValues();
        values.put("foto",R.drawable._132);
        values.put("nombre", "132");
        db.insert("alineacion",null, values);
        db.delete("alineacion","_id>1", null);

        inserta(R.drawable._141,"141");
        inserta(R.drawable._222,"222");
        inserta(R.drawable._231,"231");
        inserta(R.drawable._312,"312");
        inserta(R.drawable._321,"321");


        inserta2("Ricardo","PT","1K",83);
        inserta2("Canet","PT","1K",81);
        inserta2("Granero","DF","1K",85);
        inserta2("Guerrero","DF","1K",82);
        inserta2("Ledo","DF","1K",80);
        inserta2("Alsina","MC","1K",86);
        inserta2("Cokita","MC","1K",81);
        inserta2("Molas","MC","1K",83);

        inserta2("Zapata","PT","Aniquiladores",83);
        inserta2("Morales","PT","Aniquiladores",80);
        inserta2("Jorquera","DF","Aniquiladores",81);
        inserta2("Reyes","DF","Aniquiladores",84);
        inserta2("Pau ZZ","DF","Aniquiladores",82);
        inserta2("Bernat","MC","Aniquiladores",84);
        inserta2("Jonathan","MC","Aniquiladores",82);
        inserta2("Maca","MC","Aniquiladores",83);
        inserta2("Mañé","DC","Aniquiladores",82);
        inserta2("Alba","DC","Aniquiladores",81);
        inserta2("Guillem ZZ","DC","Aniquiladores",82);

        inserta2("Honorato","DC","Barrio",82);
        inserta2("Marino","DC","Barrio",81);
        inserta2("Torrentbó","DC","Barrio",83);
        inserta2("Juarez","MC","Barrio",80);
        inserta2("Paufer","MC","Barrio",84);
        inserta2("Giles","MC","Barrio",87);
        inserta2("Arché","MC","Barrio",86);
        inserta2("Capilla","MC","Barrio",80);
        inserta2("Iker Lopez","DF","Barrio",81);
        inserta2("Fajardo","PT","Barrio",80);
        inserta2("Aguilar","PT","Barrio",82);

        inserta2("Segovia","PT","Jijantes",85);
        inserta2("Mario León","PT","Jijantes",79);
        inserta2("David López","DF","Jijantes",83);
        inserta2("Arnau ","DF","Jijantes",81);
        inserta2("Sauras","DF","Jijantes",82);
        inserta2("Mengeli","DF","Jijantes",80);
        inserta2("Jordi Ros","MC","Jijantes",84);
        inserta2("Molina","MC","Jijantes",80);
        inserta2("Cortés","MC","Jijantes",79);
        inserta2("Uri Pons","DC","Jijantes",84);
        inserta2("Martinez","DC","Jijantes",81);

        inserta2("Cócera","PT","Kunisport",83);
        inserta2("Baranera","MC","Kunisport",81);
        inserta2("Noel López","MC","Kunisport",84);
        inserta2("Joan Inés","MC","Kunisport",83);
        inserta2("Villalba","MC","Kunisport",82);
        inserta2("Carlos Val","DF","Kunisport",80);
        inserta2("Campu","DF","Kunisport",83);
        inserta2("Carlinhos","DC","Kunisport",81);
        inserta2("Benjamín","DC","Kunisport",80);

        inserta2("Alex Race","DC","Pio",81);
        inserta2("Olaetxea","DC","Pio",82);
        inserta2("Ropero","MC","Pio",83);
        inserta2("Rivero","MC","Pio",81);
        inserta2("Pluvins","MC","Pio",83);
        inserta2("Victor Torres","DF","Pio",84);
        inserta2("Turner","DF","Pio",82);
        inserta2("Alejo Garcia","DF","Pio",80);
        inserta2("Ibáñez","PT","Pio",83);

        inserta2("Jacobo","DC","Porcinos",84);
        inserta2("Dorkis","DC","Porcinos",82);
        inserta2("Carlitos","MC","Porcinos",82);
        inserta2("Temo","MC","Porcinos",83);
        inserta2("Valle","MC","Porcinos",81);
        inserta2("Ruggeri","DF","Porcinos",84);
        inserta2("Soriano","DF","Porcinos",82);
        inserta2("Guti","DF","Porcinos",81);
        inserta2("Bayarri","DF","Porcinos",79);
        inserta2("Briones","PT","Porcinos",87);
        inserta2("Alex Garcia","PT","Porcinos",81);

        inserta2("Edgar Álvaro","DC","RayoBCN",88);
        inserta2("Joselete","DC","RayoBCN",81);
        inserta2("Zea","DC","RayoBCN",77);
        inserta2("Adri","DC","RayoBCN",79);
        inserta2("Santiago","DF","RayoBCN",83);
        inserta2("Gumá","DF","RayoBCN",80);
        inserta2("Andrés","DF","RayoBCN",77);
        inserta2("Franky","MC","RayoBCN",82);
        inserta2("Lluc","DC","RayoBCN",78);
        inserta2("Berché","PT","RayoBCN",83);
        inserta2("Rode","PT","RayoBCN",80);

        inserta2("Aleix","MC","Saiyans",81);
        inserta2("Bonet","MC","Saiyans",80);
        inserta2("Galvany","MC","Saiyans",84);
        inserta2("Ribeiro","MC","Saiyans",79);
        inserta2("Poch","DC","Saiyans",87);
        inserta2("Boada","DC","Saiyans",83);
        inserta2("Llur","DC","Saiyans",79);
        inserta2("Alegre","DF","Saiyans",84);
        inserta2("Jadir","DF","Saiyans",81);
        inserta2("Dani Perez","PT","Saiyans",85);

        inserta2("Iu","PT","Troncos",83);
        inserta2("Corderas","PT","Troncos",81);
        inserta2("Ian","DF","Troncos",85);
        inserta2("Pelaz","MC","Troncos",88);
        inserta2("Lao","MC","Troncos",84);
        inserta2("Salvia","MC","Troncos",81);
        inserta2("Sorroche","DC","Troncos",86);
        inserta2("Biboum","DC","Troncos",82);
        inserta2("Tremiño","DC","Troncos",80);

        inserta2("Ferinu","DC","UMostoles",80);
        inserta2("Sergio","MC","UMostoles",79);
        inserta2("Feliu","MC","UMostoles",85);
        inserta2("Ubón","MC","UMostoles",89);
        inserta2("Cichero","DF","UMostoles",85);
        inserta2("Bañuls","DF","UMostoles",82);
        inserta2("Lechuga","PT","UMostoles",83);

        inserta2("Capi","PT","Xbuyer",83);
        inserta2("Arús","PT","Xbuyer",80);
        inserta2("Miki","DF","Xbuyer",80);
        inserta2("Nuevo","MC","Xbuyer",83);
        inserta2("Carbó","MC","Xbuyer",88);
        inserta2("Daniel","MC","Xbuyer",84);
        inserta2("Beguer","DC","Xbuyer",87);
        inserta2("Dorado","DC","Xbuyer",82);
        inserta2("Hidalgo","DC","Xbuyer",80);



        db.close();
    }

    private void inserta(int foto,String nombre) {
        ContentValues values = new ContentValues();
        values.put("foto", foto);
        values.put("nombre", nombre);
        db.insert("alineacion", null, values);

    }

    private void inserta2(String nombre,String posicion,String equipo, int media) {
        ContentValues values2 = new ContentValues();
        values2.put("nombre", nombre);
        values2.put("posicion", posicion);
        values2.put("equipo", equipo);
        values2.put("media", media);
        db.insert("jugador", null, values2);

    }


    public void empezar(View view) {
        Intent i = new Intent(this,Alineacion.class);
        startActivity(i);
    }
}