package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class fd312 extends AppCompatActivity implements View.OnClickListener{
    ImageButton btn1,btn2, btn3, btn4,btn5, btn6, btn7;
    RatingBar puntQuimica;
    TextView txtMedia;
    SQLiteHelper helper;
    SQLiteDatabase db;
    Button btnAcabar;
    String posicion,equipo;
    int foto,delantero=0,delantero2=0,portero=0,defensa=0,defensa2=0,defensa3=0,medio1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd312);
        btn1= findViewById(R.id.imageButton29);
        btn2= findViewById(R.id.imageButton30);
        btn3= findViewById(R.id.imageButton32);
        btn3.setEnabled(false);
        btn4= findViewById(R.id.imageButton33);
        btn4.setEnabled(false);
        btn5= findViewById(R.id.imageButton31);
        btn6= findViewById(R.id.imageButton34);
        btn6.setEnabled(false);
        btn7= findViewById(R.id.imageButton35);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        puntQuimica= findViewById(R.id.ratingBar5);
        txtMedia = findViewById(R.id.textView14);
        btnAcabar = findViewById(R.id.btnacabar5);
        btnAcabar.setAlpha(0);
        btnAcabar.setEnabled(false);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        consultaEleccion();
        consultaQuimica();
        consultaMedia();
        consultaAcabar();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    private void consultaQuimica() {
        Cursor cursor=  db.rawQuery("SELECT COUNT(equipo) from titulares group by equipo",null);
        if(cursor.getCount()!=0){
            puntQuimica.setRating(0);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                int cantidad2= cursor.getInt(0);
                if(cantidad2==7){
                    puntQuimica.setRating(5);
                }else if(cantidad2==6){
                    puntQuimica.setRating(4.5F);
                }else if(cantidad2==5){
                    puntQuimica.setRating(puntQuimica.getRating()+4);
                }else if(cantidad2==4){
                    puntQuimica.setRating(puntQuimica.getRating()+3);
                }else if(cantidad2==3){
                    puntQuimica.setRating(puntQuimica.getRating()+2);
                }else if(cantidad2==2){
                    puntQuimica.setRating(puntQuimica.getRating()+1.5F);
                }
                cursor.moveToNext();
            }

        }
    }

    private void consultaMedia() {
        Cursor cursor2 =
                db.query(EstructuraBBDD.Titulares.TABLE_NAME_TITULARES, null,
                        null, null, null, null, null);
        if(cursor2.getCount()!=0) {
            cursor2.moveToFirst();
            int jugadores = 0, total = 0;
            while (!cursor2.isAfterLast()) {
                int media = cursor2.getInt(4);
                jugadores += 1;
                total += media;
                cursor2.moveToNext();
            }
            txtMedia.setText(String.valueOf(total / jugadores));
        }
    }

    private void consultaAcabar(){
        Cursor cursor=
                db.rawQuery("SELECT COUNT(*) from titulares",null);
        if(cursor.getCount()!=0) {
            cursor.moveToFirst();
            int cantidad = cursor.getInt(0);
            if(cantidad==7){
                btnAcabar.setEnabled(true);
                btnAcabar.setAlpha(1);
            }
        }
    }

    public void acabar(View view) {
        ContentValues values=new ContentValues();
        int media=Integer.parseInt(txtMedia.getText().toString());
        float quimica=puntQuimica.getRating();
        values.put("valoracion",media+quimica);
        db.update("equipo",values,"valoracion = ?", new String[]{"0"});
        Intent i = new Intent(this,Torneo.class);
        startActivity(i);
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
                    btn6.setEnabled(true);
                }else if(delantero2==0 && foto!=delantero){
                    delantero2= foto;
                    btn6.setBackgroundResource(delantero2);
                }
            }else if(posicion.equals("PT")){
                portero=foto;
                btn1.setBackgroundResource(portero);
            }else if(posicion.equals("DF")){
                if(defensa==0){
                    defensa=foto;
                    btn2.setBackgroundResource(defensa);
                    btn3.setEnabled(true);
                }else if(defensa2==0 && foto!=defensa){
                    defensa2=foto;
                    btn3.setBackgroundResource(defensa2);
                    btn4.setEnabled(true);
                }else if(defensa3==0 && foto!=defensa && foto!=defensa2){
                    defensa3=foto;
                    btn4.setBackgroundResource(defensa3);
                }
            }else if(posicion.equals("MC")){
                if(medio1==0){
                    medio1=foto;
                    btn5.setBackgroundResource(medio1);
                }
            }
            cursor2.moveToNext();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageButton29:
                abrirpt(null);
                consultaEleccion();
                btn1.setEnabled(false);
                break;
            case R.id.imageButton30:
                abrirdf(null);
                consultaEleccion();
                btn2.setEnabled(false);
                break;
            case R.id.imageButton32:
                abrirdf(null);
                consultaEleccion();
                btn3.setEnabled(false);
                break;
            case R.id.imageButton33:
                abrirdf(null);
                consultaEleccion();
                btn4.setEnabled(false);

                break;
            case R.id.imageButton31:
                abrirmc(null);
                consultaEleccion();
                btn5.setEnabled(false);

                break;
            case R.id.imageButton34:
                abrirdelantero(null);
                consultaEleccion();
                btn6.setEnabled(false);

                break;
            case R.id.imageButton35:
                abrirdelantero(null);
                consultaEleccion();
                btn7.setEnabled(false);
                break;
            case R.id.btnacabar5:
                acabar(null);
            default:
                break;
        }
    }
}