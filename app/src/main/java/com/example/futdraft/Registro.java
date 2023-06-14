package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    EditText lgnombre,lgapellidos,lglocalidad,lgpassword,lgpassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        lgnombre= findViewById(R.id.txtNombreR);
        lgapellidos = findViewById(R.id.txtApellidos);
        lglocalidad = findViewById(R.id.txtLocalidad);
        lgpassword = findViewById(R.id.TxtContraseñaR);
        lgpassword2 = findViewById(R.id.txtContraseñaR2);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    public void registro(View view) {
        boolean valido=false;
        Cursor cursor =
                db.query(EstructuraBBDD.Usuario.TABLE_NAME_USUARIO,null,
                        null,null,null,null,null);
        if(cursor.getCount()!=0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String nombre = cursor.getString(1);
                if (lgnombre.getText().toString().equals(nombre)) {
                    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                } else if (!lgnombre.getText().toString().equals(nombre) && lgpassword.getText().toString().equals(lgpassword2.getText().toString()) && !TextUtils.isEmpty(lgapellidos.getText()) && !TextUtils.isEmpty(lglocalidad.getText())) {
                    valido = true;
                } else if (!lgpassword.getText().toString().equals(lgpassword2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lgnombre.getText())) {
                    Toast.makeText(getApplicationContext(), "Introduzca un nombre, por favor", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lgapellidos.getText())) {
                    Toast.makeText(getApplicationContext(), "Introduzca sus apellidos, por favor", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lglocalidad.getText())) {
                    Toast.makeText(getApplicationContext(), "Introduzca su localidad, por favor", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lgpassword.getText())) {
                    Toast.makeText(getApplicationContext(), "Introduzca una contraseña, por favor", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lgpassword2.getText())) {
                    Toast.makeText(getApplicationContext(), "Confirme la contraseña, por favor", Toast.LENGTH_SHORT).show();
                }
                cursor.moveToNext();
            }
        }else{
            valido=true;
        }
        if(valido){
            ContentValues values = new ContentValues();
            values.put("nombre",lgnombre.getText().toString());
            values.put("contraseña",lgpassword.getText().toString());
            values.put("apellidos",lgapellidos.getText().toString());
            values.put("localidad",lglocalidad.getText().toString());
            db.insert("usuario",null,values);
            ContentValues values1 = new ContentValues();
            values1.put("nombre",lgnombre.getText().toString());
            values1.put("escudo",R.drawable.kings_league);
            values1.put("valoracion",0);
            db.insert("equipo",null,values1);
            ContentValues values2 = new ContentValues();
            values2.put("equipo1",lgnombre.getText().toString());
            values2.put("equipo2","jijantes");
            values2.put("goles1",0);
            values2.put("goles2",0);
            db.insert("partido",null,values2);
            Intent i = new Intent(this, Alineacion.class);
            startActivity(i);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }
}