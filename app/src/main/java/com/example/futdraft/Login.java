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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    EditText lgnombre,lgpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lgnombre= findViewById(R.id.txtNombre);
        lgpassword = findViewById(R.id.txtContraseña);
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
    }

    public void login(View view) {
        boolean valido=false, valido1=true,valido2=true,valido3=true,valido4=true,valido5=true;
        Cursor cursor =
                db.query(EstructuraBBDD.Usuario.TABLE_NAME_USUARIO,null,
                        null,null,null,null,null);
        if(cursor.getCount()!=0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String nombre = cursor.getString(1);
                String contraseña = cursor.getString(2);
                if (lgnombre.getText().toString().equals(nombre) && lgpassword.getText().toString().equals(contraseña)) {
                    valido = true;
                } else if (TextUtils.isEmpty(lgnombre.getText())) {
                    valido1 = false;
                    //Toast.makeText(getApplicationContext(), "Introduzca un nombre, por favor", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lgpassword.getText())) {
                    valido3 = false;
                    //Toast.makeText(getApplicationContext(), "Introduzca una contraseña, por favor", Toast.LENGTH_SHORT).show();
                } else if (lgnombre.getText().toString().equals(nombre)) {
                    valido2 = false;
                    //Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                } else if (lgpassword.getText().toString().equals(contraseña)) {
                    valido4 = false;
                    //Toast.makeText(getApplicationContext(), "El usuario es incorrecta", Toast.LENGTH_SHORT).show();
                } else {
                    valido5 = false;
                    //Toast.makeText(getApplicationContext(), "Ninguno de los datos introducidos es correcto, por favor Regístrese", Toast.LENGTH_SHORT).show();
                }
                cursor.moveToNext();
            }
            if (valido) {
                ContentValues values1 = new ContentValues();
                values1.put("nombre",lgnombre.getText().toString());
                values1.put("escudo",R.drawable.kings_league);
                values1.put("valoracion",90);
                db.insert("equipo",null,values1);
                ContentValues values2 = new ContentValues();
                values2.put("equipo1",lgnombre.getText().toString());
                values2.put("equipo2","Jijantes");
                values2.put("goles1",0);
                values2.put("goles2",0);
                db.insert("partido",null,values2);

                Intent i = new Intent(this, Torneo.class);
                startActivity(i);
            } else if (!valido1) {
                Toast.makeText(getApplicationContext(), "Introduzca un nombre, por favor", Toast.LENGTH_SHORT).show();
            } else if (!valido4) {
                Toast.makeText(getApplicationContext(), "El usuario es incorrecto", Toast.LENGTH_SHORT).show();
            } else if (!valido3) {
                Toast.makeText(getApplicationContext(), "Introduzca una contraseña, por favor", Toast.LENGTH_SHORT).show();
            } else if (!valido2) {
                Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
            } else if (!valido5) {
                Toast.makeText(getApplicationContext(), "Ninguno de los datos introducidos es correcto, por favor Regístrese", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Todavía no hay ningun usuario, por favor Regístrese", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    public void registro(View view) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}