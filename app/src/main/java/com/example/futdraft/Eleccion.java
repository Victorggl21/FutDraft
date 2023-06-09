package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class Eleccion extends AppCompatActivity  implements View.OnClickListener {

    SQLiteHelper helper;
    SQLiteDatabase db;

    ImageView img1,img2,img3,img4;

    int [] delanteros = new int []{
            R.drawable.cartaaniaquiladores9,R.drawable.cartaaniaquiladores10,R.drawable.cartaaniaquiladores11,
            R.drawable.cartabarrio1,R.drawable.cartabarrio2,R.drawable.cartabarrio3,
            R.drawable.cartajijantes10,R.drawable.cartajijantes11,
            R.drawable.cartakuni8,R.drawable.cartakuni9,
            R.drawable.cartapio1,R.drawable.cartapio2,
            R.drawable.cartaporcinos1,R.drawable.cartaporcinos2,
            R.drawable.cartarayo1,R.drawable.cartarayo2,R.drawable.cartarayo3,R.drawable.cartarayo4,R.drawable.cartarayo9,
            R.drawable.cartasaiyans5,R.drawable.cartasaiyans6,R.drawable.cartasaiyans7,
            R.drawable.cartatroncos7,R.drawable.cartatroncos8,R.drawable.cartatroncos9,
            R.drawable.cartaum1,
            R.drawable.cartaxbuyer7,R.drawable.cartaxbuyer8,R.drawable.cartaxbuyer9
    };

    int n1=0,n2=0, n3=0, n4=0;
    Random random = new Random();
    Random random2 = new Random();
    Random random3 = new Random();
    Random random4 = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion);


        jugadores();

    }


    public void jugadores(){
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
        img1 = findViewById(R.id.imageButton43);
        img1.setImageResource(delanteros[n1]);
        img2 = findViewById(R.id.imageButton44);
        img2.setImageResource(delanteros[n2]);
        img3 = findViewById(R.id.imageButton45);
        img3.setImageResource(delanteros[n3]);
        img4 = findViewById(R.id.imageButton46);
        img4.setImageResource(delanteros[n4]);



    }

    public void volveralineacion(View view) {
        onBackPressed();
        consultaEleccion(delanteros[n1]);
    }
    public void volveralineacion2(View view) {
        onBackPressed();
        consultaEleccion(delanteros[n2]);
    }
    public void volveralineacion3(View view) {
        onBackPressed();
        consultaEleccion(delanteros[n3]);
    }
    public void volveralineacion4(View view) {
        onBackPressed();
        consultaEleccion(delanteros[n4]);
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
        switch(view.getId()){
            case R.id.imageButton43:
                volveralineacion(null);
                break;
            case R.id.imageButton44:
                volveralineacion2(null);
                break;
            case R.id.imageButton45:
                volveralineacion3(null);
                break;
            case R.id.imageButton46:
                volveralineacion4(null);
                break;
        }
    }
}