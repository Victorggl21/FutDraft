package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

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

    }

    public void login(View view) {
        boolean valido=false;
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor =
                db.query(EstructuraBBDD.Usuario.TABLE_NAME_USUARIO,null,
                        null,null,null,null,null);
        cursor.moveToFirst();
        ArrayList<EstructuraBBDD.Usuario> usuarios;
        EstructuraBBDD.Usuario usuario;
        while (!cursor.isAfterLast()) {
            String nombre = cursor.getString(1);
            String contraseña = cursor.getString(2);
            //usuario= new EstructuraBBDD.Usuario(nombre,contraseña);
            if(lgnombre.getText().toString().equals(nombre)&& lgpassword.getText().toString().equals(contraseña)){
                Intent i = new Intent(this, Alineacion.class);
                startActivity(i);
            }else{
                if(TextUtils.isEmpty(lgnombre.getText())){
                    Toast.makeText(getApplicationContext(), "Introduzca un nombre, por favor", Toast.LENGTH_SHORT).show();
                }else if(lgnombre.getText().toString().equals(nombre)){
                    Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(lgpassword.getText())){
                    Toast.makeText(getApplicationContext(), "Introduzca una contraseña, por favor", Toast.LENGTH_SHORT).show();
                }else if(lgpassword.getText().toString().equals(contraseña)){
                    Toast.makeText(getApplicationContext(), "El usuario es incorrecta", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Ninguno de los datos introducidos es correcto, por favor Regístrese", Toast.LENGTH_SHORT).show();
                }
                cursor.moveToNext();
            }

        }
        if(valido){

        }
    }

    public void registro(View view) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}