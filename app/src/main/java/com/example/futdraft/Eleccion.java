package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class Eleccion extends AppCompatActivity {

    SQLiteHelper helper;
    SQLiteDatabase db;

    int [] delanteros = new int []{
            R.drawable.cartaaniaquiladores9,R.drawable.cartaaniaquiladores10,R.drawable.cartaaniaquiladores11,
            R.drawable.cartabarrio1,R.drawable.cartabarrio2,R.drawable.cartabarrio3,
            R.drawable.cartajijantes10,R.drawable.cartajijantes11,
            R.drawable.cartakuni8,R.drawable.cartakuni9,
            R.drawable.cartapio1,R.drawable.cartapio2,
            R.drawable.cartaporcinos1,R.drawable.cartaporcinos2,
            R.drawable.cartarayo2,R.drawable.cartarayo3,R.drawable.cartarayo4,R.drawable.cartarayo9,R.drawable.eacarta,
            R.drawable.cartasaiyans5,R.drawable.cartasaiyans6,R.drawable.cartasaiyans7,
            R.drawable.cartatroncos7,R.drawable.cartatroncos8,R.drawable.cartatroncos9,
            R.drawable.cartaum1,
            R.drawable.cartaxbuyer7,R.drawable.cartaxbuyer8,R.drawable.cartaxbuyer9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion);
        jugadores();
    }


    public void jugadores(){
        int n1,n2, n3, n4;
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

        n1 = random.nextInt(delanteros.length);
        n2 = random2.nextInt(delanteros.length);
        while(delanteros[n2]==delanteros[n1] ){
            n2 = random.nextInt(delanteros.length);
        }
        n3 = random3.nextInt(delanteros.length);
        while(delanteros[n3]==delanteros[n2] || delanteros[n3]==delanteros[n1] ){
            n3 = random.nextInt(delanteros.length);
        }
        n4 = random4.nextInt(delanteros.length);
        while(delanteros[n4]==delanteros[n3] || delanteros[n4]==delanteros[n2] || delanteros[n4]==delanteros[n1] ){
            n4 = random.nextInt(delanteros.length);
        }

        ImageView img1 = findViewById(R.id.imageButton43);
        img1.setImageResource(delanteros[n1]);
        ImageView img2 = findViewById(R.id.imageButton44);
        img2.setImageResource(delanteros[n2]);
        ImageView img3 = findViewById(R.id.imageButton45);
        img3.setImageResource(delanteros[n3]);
        ImageView img4 = findViewById(R.id.imageButton46);
        img4.setImageResource(delanteros[n4]);
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

        //onBackPressed();

    }


}