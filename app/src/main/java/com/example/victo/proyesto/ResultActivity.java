package com.example.victo.proyesto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    double resultado;
    TextView impresion;
    TextView num;
    String PesoIdeal;
    TextView peso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        impresion = findViewById(R.id.textViewResultado);
        peso = findViewById(R.id.textViewResultadoPesoIdeal);
        Intent datos = getIntent();
        resultado = datos.getDoubleExtra("Resultado", 0.0);
        PesoIdeal = datos.getStringExtra("PesoIdeal");

        DecimalFormat decimalFormatter = new DecimalFormat("##.############");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(2);

        impresion.setText(Condicion(resultado));
        num = findViewById(R.id.textViewResultadoBMI);
        num.setText(decimalFormatter.format(resultado));
        peso.setText(PesoIdeal);

    }
    public String Condicion(double R){
        String condition;
        if (R < 18.50)
            return "DELGADO";
        if(R>=18.50 && R<24.99)
            return "NORMAL";
        if (R>25.00 && R<29.99)
            return "SOBREPESO";
        if (R>=30.00)
            return "OBESO";


        return "Balbaro";

    }



}
