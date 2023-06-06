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

    public int alineacion =0;
    private List<String> players;
    private List<String> selectedPlayers;
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
        db.close();
    }

    private void inserta(int foto,String nombre) {
        ContentValues values = new ContentValues();
        values.put("foto", foto);
        values.put("nombre", nombre);
        db.insert("alineacion", null, values);

    }


    public void empezar(View view) {
        Intent i = new Intent(this,Alineacion.class);
        startActivity(i);
    }
}