package com.example.futdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class fd141 extends AppCompatActivity  implements View.OnClickListener{

    ImageButton btn1,btn2, btn3, btn4,btn5, btn6, btn7;

    /*int [] delanteros = new int []{
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
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd141);
        btn1= findViewById(R.id.imageButton);
        btn2= findViewById(R.id.imageButton2);
        btn3= findViewById(R.id.imageButton3);
        btn4= findViewById(R.id.imageButton4);
        btn5= findViewById(R.id.imageButton5);
        btn6= findViewById(R.id.imageButton6);
        btn7= findViewById(R.id.imageButton7);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }




    public void abrirdelantero(View view) {

        /*int n1,n2, n3, n4;
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();

        n1 = random.nextInt(delanteros.length);
        n2 = random2.nextInt(delanteros.length);
        n3 = random3.nextInt(delanteros.length);
        n4 = random4.nextInt(delanteros.length);*/



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



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageButton:
                abrirpt(null);
                btn1.setEnabled(false);
                btn1.setAlpha(0.5F);
                break;
            case R.id.imageButton2:
                abrirdf(null);
                btn2.setEnabled(false);
                btn2.setAlpha(0.5F);
                break;
            case R.id.imageButton3:
                abrirmc(null);
                btn3.setEnabled(false);
                btn3.setAlpha(0.5F);
                break;
            case R.id.imageButton4:
                abrirmc(null);
                btn4.setEnabled(false);
                btn4.setAlpha(0.5F);
                break;
            case R.id.imageButton5:
                abrirmc(null);
                btn5.setEnabled(false);
                btn5.setAlpha(0.5F);
                break;
            case R.id.imageButton6:
                abrirmc(null);
                btn6.setEnabled(false);
                btn6.setAlpha(0.5F);
                break;
            case R.id.imageButton7:
                abrirdelantero(null);
                btn7.setEnabled(false);
                btn7.setAlpha(0.5F);
                break;
            default:

                break;

        }
    }
}