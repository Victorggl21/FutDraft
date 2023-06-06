package com.example.futdraft;

import android.provider.BaseColumns;

public final class EstructuraBBDD {
    public static final String SQL_CREATE_ALINEACION =
            "CREATE TABLE IF NOT EXISTS "+ Alineacion.TABLE_NAME_ALINEACION
                    +"(" + Alineacion._ID + " integer PRIMARY KEY, "
                    + Alineacion.COLUMN_FOTO + " integer, "
                    + Alineacion.COLUMN_NOMBRE_ALINEACION+ " text);";
    public static final String SQL_DELETE_ALINEACION =
            "DROP TABLE IF EXISTS " + Alineacion.TABLE_NAME_ALINEACION;

    public static final String SQL_CREATE_JUGADOR =
            "CREATE TABLE IF NOT EXISTS "+ Jugador.TABLE_NAME_JUGADOR
                    +"(" + Jugador._ID + " integer PRIMARY KEY, "
                    + Jugador.COLUMN_NOMBRE + " text, "
                    + Jugador.COLUMN_POSICION + " text, "
                    + Jugador.COLUMN_MEDIA+ " integer);";

    public static final String SQL_DELETE_JUGADOR =
            "DROP TABLE IF EXISTS " + Jugador.TABLE_NAME_JUGADOR;


    public static final String SQL_CREATE_EQUIPO =
            "CREATE TABLE IF NOT EXISTS "+ Equipo.TABLE_NAME_EQUIPO
                    +"(" + Equipo._ID + " integer PRIMARY KEY, "
                    + Equipo.COLUMN_NOMBRE_EQUIPO + " text, "
                    + Equipo.COLUMN_PRESIDENTE + " text, "
                    + Equipo.COLUMN_FOTO+ " integer);";

    public static final String SQL_DELETE_EQUIPO =
            "DROP TABLE IF EXISTS " + Equipo.TABLE_NAME_EQUIPO;

    private EstructuraBBDD() {}
    /* Clase interna que define la estructura de la tabla de operas */
    public static class Alineacion implements BaseColumns {
        public static final String TABLE_NAME_ALINEACION = "alineacion";
        public static final String COLUMN_FOTO = "foto";
        public static final String COLUMN_NOMBRE_ALINEACION = "nombre";

    }

    public static class Equipo implements BaseColumns {
        public static final String TABLE_NAME_EQUIPO = "equipo";
        public static final String COLUMN_NOMBRE_EQUIPO = "nombreEquipo";
        public static final String COLUMN_PRESIDENTE = "presidente";
        public static final String COLUMN_FOTO = "foto";
    }

    public static class Jugador implements BaseColumns {
        public static final String TABLE_NAME_JUGADOR = "jugador";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_POSICION = "posicion";
        public static final String COLUMN_MEDIA = "posicion";

    }
}
