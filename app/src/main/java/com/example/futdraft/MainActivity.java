package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

        Cursor cursor =
                db.query(EstructuraBBDD.Jugador.TABLE_NAME_JUGADOR,null,
                        null,null,null,null,null);
        if(cursor.getCount()==0) {
            inserta2("Ricardo", "PT", "1K", 83, R.drawable.carta1k1);
            inserta2("Canet", "PT", "1K", 81, R.drawable.carta1k2);
            inserta2("Granero", "DF", "1K", 85, R.drawable.carta1k3);
            inserta2("Guerrero", "DF", "1K", 82, R.drawable.carta1k4);
            inserta2("Ledo", "DF", "1K", 80, R.drawable.carta1k5);
            inserta2("Alsina", "MC", "1K", 86, R.drawable.carta1k6);
            inserta2("Cokita", "MC", "1K", 81, R.drawable.carta1k7);
            inserta2("Molas", "MC", "1K", 83, R.drawable.carta1k8);

            inserta2("Zapata", "PT", "Aniquiladores", 83, R.drawable.cartaaniaquiladores1);
            inserta2("Morales", "PT", "Aniquiladores", 80, R.drawable.cartaaniaquiladores2);
            inserta2("Jorquera", "DF", "Aniquiladores", 81, R.drawable.cartaaniaquiladores3);
            inserta2("Reyes", "DF", "Aniquiladores", 84, R.drawable.cartaaniaquiladores4);
            inserta2("Pau ZZ", "DF", "Aniquiladores", 82, R.drawable.cartaaniaquiladores5);
            inserta2("Bernat", "MC", "Aniquiladores", 84, R.drawable.cartaaniaquiladores6);
            inserta2("Jonathan", "MC", "Aniquiladores", 82, R.drawable.cartaaniaquiladores7);
            inserta2("Maca", "MC", "Aniquiladores", 83, R.drawable.cartaaniaquiladores8);
            inserta2("Mañé", "DC", "Aniquiladores", 82, R.drawable.cartaaniaquiladores9);
            inserta2("Alba", "DC", "Aniquiladores", 81, R.drawable.cartaaniaquiladores10);
            inserta2("Guillem ZZ", "DC", "Aniquiladores", 82, R.drawable.cartaaniaquiladores11);

            inserta2("Honorato", "DC", "Barrio", 82, R.drawable.cartabarrio1);
            inserta2("Marino", "DC", "Barrio", 81, R.drawable.cartabarrio2);
            inserta2("Torrentbó", "DC", "Barrio", 83, R.drawable.cartabarrio3);
            inserta2("Juarez", "MC", "Barrio", 80, R.drawable.cartabarrio4);
            inserta2("Paufer", "MC", "Barrio", 84, R.drawable.cartabarrio5);
            inserta2("Giles", "MC", "Barrio", 87, R.drawable.cartabarrio6);
            inserta2("Arché", "MC", "Barrio", 86, R.drawable.cartabarrio7);
            inserta2("Capilla", "MC", "Barrio", 80, R.drawable.cartabarrio8);
            inserta2("Iker Lopez", "DF", "Barrio", 81, R.drawable.cartabarrio9);
            inserta2("Fajardo", "PT", "Barrio", 80, R.drawable.cartabarrio10);
            inserta2("Aguilar", "PT", "Barrio", 82, R.drawable.cartabarrio11);

            inserta2("Segovia", "PT", "Jijantes", 85, R.drawable.cartajijantes1);
            inserta2("Mario León", "PT", "Jijantes", 79, R.drawable.cartajijantes2);
            inserta2("David López", "DF", "Jijantes", 83, R.drawable.cartajijantes3);
            inserta2("Arnau ", "DF", "Jijantes", 81, R.drawable.cartajijantes4);
            inserta2("Sauras", "DF", "Jijantes", 82, R.drawable.cartajijantes5);
            inserta2("Mengeli", "DF", "Jijantes", 80, R.drawable.cartajijantes6);
            inserta2("Jordi Ros", "MC", "Jijantes", 84, R.drawable.cartajijantes7);
            inserta2("Molina", "MC", "Jijantes", 80, R.drawable.cartajijantes8);
            inserta2("Cortés", "MC", "Jijantes", 79, R.drawable.cartajijantes9);
            inserta2("Uri Pons", "DC", "Jijantes", 84, R.drawable.cartajijantes10);
            inserta2("Martinez", "DC", "Jijantes", 81, R.drawable.cartajijantes11);

            inserta2("Cócera", "PT", "Kunisport", 83, R.drawable.cartakuni1);
            inserta2("Baranera", "MC", "Kunisport", 81, R.drawable.cartakuni2);
            inserta2("Noel López", "MC", "Kunisport", 84, R.drawable.cartakuni3);
            inserta2("Joan Inés", "MC", "Kunisport", 83, R.drawable.cartakuni4);
            inserta2("Villalba", "MC", "Kunisport", 82, R.drawable.cartakuni5);
            inserta2("Carlos Val", "DF", "Kunisport", 80, R.drawable.cartakuni6);
            inserta2("Campu", "DF", "Kunisport", 83, R.drawable.cartakuni7);
            inserta2("Carlinhos", "DC", "Kunisport", 81, R.drawable.cartakuni8);
            inserta2("Benjamín", "DC", "Kunisport", 80, R.drawable.cartakuni9);

            inserta2("Alex Race", "DC", "Pio", 81, R.drawable.cartapio1);
            inserta2("Olaetxea", "DC", "Pio", 82, R.drawable.cartapio2);
            inserta2("Ropero", "MC", "Pio", 83, R.drawable.cartapio3);
            inserta2("Rivero", "MC", "Pio", 81, R.drawable.cartapio4);
            inserta2("Pluvins", "MC", "Pio", 83, R.drawable.cartapio5);
            inserta2("Victor Torres", "DF", "Pio", 84, R.drawable.cartapio6);
            inserta2("Turner", "DF", "Pio", 82, R.drawable.cartapio7);
            inserta2("Alejo Garcia", "DF", "Pio", 80, R.drawable.cartapio8);
            inserta2("Ibáñez", "PT", "Pio", 83, R.drawable.cartapio9);

            inserta2("Jacobo", "DC", "Porcinos", 84, R.drawable.cartaporcinos1);
            inserta2("Dorkis", "DC", "Porcinos", 82, R.drawable.cartaporcinos2);
            inserta2("Carlitos", "MC", "Porcinos", 82, R.drawable.cartaporcinos3);
            inserta2("Temo", "MC", "Porcinos", 83, R.drawable.cartaporcinos4);
            inserta2("Valle", "MC", "Porcinos", 81, R.drawable.cartaporcinos5);
            inserta2("Ruggeri", "DF", "Porcinos", 84, R.drawable.cartaporcinos6);
            inserta2("Soriano", "DF", "Porcinos", 82, R.drawable.cartaporcinos7);
            inserta2("Guti", "DF", "Porcinos", 81, R.drawable.cartaporcinos8);
            inserta2("Bayarri", "DF", "Porcinos", 79, R.drawable.cartaporcinos9);
            inserta2("Briones", "PT", "Porcinos", 87, R.drawable.cartaporcinos10);
            inserta2("Alex Garcia", "PT", "Porcinos", 81, R.drawable.cartaporcinos11);

            inserta2("Edgar Álvaro", "DC", "RayoBCN", 88, R.drawable.cartarayo1);
            inserta2("Joselete", "DC", "RayoBCN", 81, R.drawable.cartarayo2);
            inserta2("Zea", "DC", "RayoBCN", 77, R.drawable.cartarayo3);
            inserta2("Adri", "DC", "RayoBCN", 79, R.drawable.cartarayo4);
            inserta2("Santiago", "DF", "RayoBCN", 83, R.drawable.cartarayo5);
            inserta2("Gumá", "DF", "RayoBCN", 80, R.drawable.cartarayo6);
            inserta2("Andrés", "DF", "RayoBCN", 77, R.drawable.cartarayo7);
            inserta2("Franky", "MC", "RayoBCN", 82, R.drawable.cartarayo8);
            inserta2("Lluc", "DC", "RayoBCN", 78, R.drawable.cartarayo9);
            inserta2("Berché", "PT", "RayoBCN", 83, R.drawable.cartarayo10);
            inserta2("Rode", "PT", "RayoBCN", 80, R.drawable.cartarayo11);

            inserta2("Aleix", "MC", "Saiyans", 81, R.drawable.cartasaiyans1);
            inserta2("Bonet", "MC", "Saiyans", 80, R.drawable.cartasaiyans2);
            inserta2("Galvany", "MC", "Saiyans", 84, R.drawable.cartasaiyans3);
            inserta2("Ribeiro", "MC", "Saiyans", 79, R.drawable.cartasaiyans4);
            inserta2("Poch", "DC", "Saiyans", 87, R.drawable.cartasaiyans5);
            inserta2("Boada", "DC", "Saiyans", 83, R.drawable.cartasaiyans6);
            inserta2("Llur", "DC", "Saiyans", 79, R.drawable.cartasaiyans7);
            inserta2("Alegre", "DF", "Saiyans", 84, R.drawable.cartasaiyans8);
            inserta2("Jadir", "DF", "Saiyans", 81, R.drawable.cartasaiyans9);
            inserta2("Dani Perez", "PT", "Saiyans", 85, R.drawable.cartasaiyans10);

            inserta2("Iu", "PT", "Troncos", 83, R.drawable.cartatroncos1);
            inserta2("Corderas", "PT", "Troncos", 81, R.drawable.cartatroncos2);
            inserta2("Ian", "DF", "Troncos", 85, R.drawable.cartatroncos3);
            inserta2("Pelaz", "MC", "Troncos", 88, R.drawable.cartatroncos4);
            inserta2("Lao", "MC", "Troncos", 84, R.drawable.cartatroncos5);
            inserta2("Salvia", "MC", "Troncos", 81, R.drawable.cartatroncos6);
            inserta2("Sorroche", "DC", "Troncos", 86, R.drawable.cartatroncos7);
            inserta2("Biboum", "DC", "Troncos", 82, R.drawable.cartatroncos8);
            inserta2("Tremiño", "DC", "Troncos", 80, R.drawable.cartatroncos9);

            inserta2("Ferinu", "DC", "UMostoles", 80, R.drawable.cartaum1);
            inserta2("Sergio", "MC", "UMostoles", 79, R.drawable.cartaum2);
            inserta2("Feliu", "MC", "UMostoles", 85, R.drawable.cartaum3);
            inserta2("Ubón", "MC", "UMostoles", 89, R.drawable.cartaum4);
            inserta2("Cichero", "DF", "UMostoles", 85, R.drawable.cartaum5);
            inserta2("Bañuls", "DF", "UMostoles", 82, R.drawable.cartaum6);
            inserta2("Lechuga", "PT", "UMostoles", 83, R.drawable.cartaum7);

            inserta2("Capi", "PT", "Xbuyer", 83, R.drawable.cartaxbuyer1);
            inserta2("Arús", "PT", "Xbuyer", 80, R.drawable.cartaxbuyer2);
            inserta2("Miki", "DF", "Xbuyer", 80, R.drawable.cartaxbuyer3);
            inserta2("Nuevo", "MC", "Xbuyer", 83, R.drawable.cartaxbuyer4);
            inserta2("Carbó", "MC", "Xbuyer", 88, R.drawable.cartaxbuyer5);
            inserta2("Daniel", "MC", "Xbuyer", 84, R.drawable.cartaxbuyer6);
            inserta2("Beguer", "DC", "Xbuyer", 87, R.drawable.cartaxbuyer7);
            inserta2("Dorado", "DC", "Xbuyer", 82, R.drawable.cartaxbuyer8);
            inserta2("Hidalgo", "DC", "Xbuyer", 80, R.drawable.cartaxbuyer9);
        }


        db.delete("titulares",null, null);

        //inserta3();

        db.close();
    }

    private void inserta(int foto,String nombre) {
        ContentValues values = new ContentValues();
        values.put("foto", foto);
        values.put("nombre", nombre);
        db.insert("alineacion", null, values);

    }

    private void inserta2(String nombre,String posicion,String equipo, int media,int foto) {
        ContentValues values2 = new ContentValues();
        values2.put("nombre", nombre);
        values2.put("posicion", posicion);
        values2.put("equipo", equipo);
        values2.put("media", media);
        values2.put("foto", foto);
        db.insert("jugador", null, values2);

    }
    private void inserta3(){
        Cursor cursor =
                db.query(EstructuraBBDD.Usuario.TABLE_NAME_USUARIO,null,
                        null,null,null,null,null);

        cursor.moveToFirst();
        String nombre= cursor.getString(1);
        if(nombre.equals("victorggl21")){
        }else{
            ContentValues values2= new ContentValues();
            values2.put("nombre","victorggl21");
            values2.put("contraseña", "Pfg16Jun!");
            //SI ES LA PRIMERA SE INSERTA, SI NO NO
            db.insert("usuario",null, values2);
        }
        db.close();
    }

    public void empezar(View view) {
        Intent i = new Intent(this,Alineacion.class);
        startActivity(i);
    }

    public void login(View view) {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}