package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Alineacion extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SQLiteHelper helper;
    SQLiteDatabase db;
    ListView lv;
    TextView txtTexto1;
    ImageView imgViewFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion);
        lv = findViewById(R.id.lstListaModif);
        consultaOperas();
        lv.setOnItemClickListener(this);
    }

    private void consultaOperas(){
        helper = new SQLiteHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor =
                db.query(EstructuraBBDD.Alineacion.TABLE_NAME_ALINEACION, null,
                        null, null, null, null, null);
        //adaptamos el cursor a nuestro ListView
        String[] from = {EstructuraBBDD.Alineacion.COLUMN_FOTO,
                EstructuraBBDD.Alineacion.COLUMN_NOMBRE_ALINEACION};
        int[] to = {R.id.imageView,R.id.textView5};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.alineaciones, cursor, from, to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adaptador);
        db.close();

    }

    @Override
    public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

        Cursor cursor=(Cursor) listView.getItemAtPosition(position);
        int _id=cursor.getInt(0);

        if(_id==1){
            Intent i = new Intent(this,fd132.class);
            startActivity(i);
        }else if(_id==2){
            Intent i = new Intent(this,fd141.class);
            startActivity(i);
        }else if(_id==3){
            Intent i = new Intent(this,fd222.class);
            startActivity(i);
        }else if(_id==4){
            Intent i = new Intent(this,fd231.class);
            startActivity(i);
        }else if(_id==5){
            Intent i = new Intent(this,fd312.class);
            startActivity(i);
        }else if(_id==6){
            Intent i = new Intent(this,fd321.class);
            startActivity(i);
        }


    }
}