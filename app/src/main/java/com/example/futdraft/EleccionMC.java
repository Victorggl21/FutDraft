package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class EleccionMC extends AppCompatActivity {

    SQLiteHelper helper;
    SQLiteDatabase db;

    int [] medios = new int []{
            R.drawable.carta1k6,R.drawable.carta1k7,R.drawable.carta1k8,
            R.drawable.cartaaniaquiladores6,R.drawable.cartaaniaquiladores7,R.drawable.cartaaniaquiladores8,
            R.drawable.cartabarrio4,R.drawable.cartabarrio5,R.drawable.cartabarrio6,R.drawable.cartabarrio7,R.drawable.cartabarrio8,
            R.drawable.cartajijantes7,R.drawable.cartajijantes8,R.drawable.cartajijantes9,
            R.drawable.cartakuni2,R.drawable.cartakuni3,R.drawable.cartakuni4,R.drawable.cartakuni5,
            R.drawable.cartapio3,R.drawable.cartapio4,R.drawable.cartapio5,
            R.drawable.cartaporcinos3,R.drawable.cartaporcinos4,R.drawable.cartaporcinos5,
            R.drawable.cartarayo8,
            R.drawable.cartasaiyans1,R.drawable.cartasaiyans2,R.drawable.cartasaiyans3,R.drawable.cartasaiyans4,
            R.drawable.cartatroncos4,R.drawable.cartatroncos5,R.drawable.cartatroncos6,
            R.drawable.cartaum2,R.drawable.cartaum3,R.drawable.cartaum4,
            R.drawable.cartaxbuyer4,R.drawable.cartaxbuyer5,R.drawable.cartaxbuyer6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_mc);
        jugadores();
    }

    public void jugadores(){
        int n1,n2, n3, n4;
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

        n1 = random.nextInt(medios.length);
        n2 = random2.nextInt(medios.length);
        while(medios[n2]==medios[n1] ){
            n2 = random.nextInt(medios.length);
        }
        n3 = random3.nextInt(medios.length);
        while(medios[n3]==medios[n2] || medios[n3]==medios[n1] ){
            n3 = random.nextInt(medios.length);
        }
        n4 = random4.nextInt(medios.length);
        while(medios[n4]==medios[n3] || medios[n4]==medios[n2] || medios[n4]==medios[n1] ){
            n4 = random.nextInt(medios.length);
        }

        ImageView img1 = findViewById(R.id.imageButton47);
        img1.setImageResource(medios[n1]);
        ImageView img2 = findViewById(R.id.imageButton48);
        img2.setImageResource(medios[n2]);
        ImageView img3 = findViewById(R.id.imageButton49);
        img3.setImageResource(medios[n3]);
        ImageView img4 = findViewById(R.id.imageButton50);
        img4.setImageResource(medios[n4]);
    }

    public void volveralineacion(View view) {
        /*helper = new SQLiteHelper(this);
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
        }*/
        onBackPressed();

    }

}