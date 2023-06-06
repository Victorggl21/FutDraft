package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class EleccionDF extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;

    int [] defensa = new int []{
            R.drawable.carta1k3,R.drawable.carta1k4,R.drawable.carta1k5,
            R.drawable.cartaaniaquiladores3,R.drawable.cartaaniaquiladores4,R.drawable.cartaaniaquiladores5,
            R.drawable.cartabarrio9,
            R.drawable.cartajijantes3,R.drawable.cartajijantes4,R.drawable.cartajijantes5,R.drawable.cartajijantes6,
            R.drawable.cartakuni6,R.drawable.cartakuni7,
            R.drawable.cartapio6,R.drawable.cartapio7,R.drawable.cartapio8,
            R.drawable.cartaporcinos6,R.drawable.cartaporcinos7,R.drawable.cartaporcinos8,R.drawable.cartaporcinos9,
            R.drawable.cartarayo5,R.drawable.cartarayo6,R.drawable.cartarayo7,
            R.drawable.cartasaiyans8,R.drawable.cartasaiyans9,
            R.drawable.cartatroncos3,
            R.drawable.cartaum5,R.drawable.cartaum6,
            R.drawable.cartaxbuyer3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_df);
        jugadores();
    }

    public void jugadores(){
        int n1,n2, n3, n4;
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

        n1 = random.nextInt(defensa.length);
        n2 = random2.nextInt(defensa.length);
        while(defensa[n2]==defensa[n1] ){
            n2 = random.nextInt(defensa.length);
        }
        n3 = random3.nextInt(defensa.length);
        while(defensa[n3]==defensa[n2] || defensa[n3]==defensa[n1] ){
            n3 = random.nextInt(defensa.length);
        }
        n4 = random4.nextInt(defensa.length);
        while(defensa[n4]==defensa[n3] || defensa[n4]==defensa[n2] || defensa[n4]==defensa[n1] ){
            n4 = random.nextInt(defensa.length);
        }

        ImageView img1 = findViewById(R.id.imageButton51);
        img1.setImageResource(defensa[n1]);
        ImageView img2 = findViewById(R.id.imageButton52);
        img2.setImageResource(defensa[n2]);
        ImageView img3 = findViewById(R.id.imageButton53);
        img3.setImageResource(defensa[n3]);
        ImageView img4 = findViewById(R.id.imageButton54);
        img4.setImageResource(defensa[n4]);
    }

    public void volveralineacion(View view) {
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor =
                db.query(EstructuraBBDD.Alineacion.TABLE_NAME_ALINEACION, null,
                        null, null, null, null, null);

        String nombre= cursor.getString(2);
        if(nombre=="132"){
            Intent i = new Intent(this,fd132.class);
            startActivity(i);
        }else if(nombre=="141"){
            Intent i = new Intent(this,fd141.class);
            startActivity(i);
        }else if(nombre=="222"){
            Intent i = new Intent(this,fd222.class);
            startActivity(i);
        }else if(nombre=="231"){
            Intent i = new Intent(this,fd231.class);
            startActivity(i);
        }else if(nombre=="312"){
            Intent i = new Intent(this,fd312.class);
            startActivity(i);
        }else if(nombre=="321"){
            Intent i = new Intent(this,fd321.class);
            startActivity(i);
        }


    }
}