package com.example.futdraft;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "bdAlineaciones.db";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_ALINEACION);
        db.execSQL(EstructuraBBDD.SQL_CREATE_TITULARES);
        db.execSQL(EstructuraBBDD.SQL_CREATE_JUGADOR);
        db.execSQL(EstructuraBBDD.SQL_CREATE_USUARIO);
        db.execSQL(EstructuraBBDD.SQL_CREATE_EQUIPO);
        db.execSQL(EstructuraBBDD.SQL_CREATE_PARTIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EstructuraBBDD.SQL_DELETE_ALINEACION);
        //Se crea la nueva versión de la tabla
        db.execSQL(EstructuraBBDD.SQL_DELETE_TITULARES);
        db.execSQL(EstructuraBBDD.SQL_DELETE_JUGADOR);
        db.execSQL(EstructuraBBDD.SQL_DELETE_EQUIPO);
        db.execSQL(EstructuraBBDD.SQL_DELETE_PARTIDO);
    }
}
