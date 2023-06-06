package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class EleccionPT extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_pt);
        jugadores();
    }

    public void jugadores(){
        int n1,n2, n3, n4;
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

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