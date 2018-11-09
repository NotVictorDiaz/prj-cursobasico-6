package com.example.victo.proyesto;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    int[] ids = {R.id.button, R.id.imageButton, R.id.radioGroupPeso,
            R.id.radioGroupEstatura};

    EditText editTextPeso, editTextEstatura;
    RadioGroup radioGroupPeso, radioGroupEstatura;

    public String PesoIdeal(EditText estatura, RadioGroup uniPeso, RadioGroup uniEstatura){
        String Peso;
        DecimalFormat decimalFormatter = new DecimalFormat("##.############");
        decimalFormatter.setMinimumFractionDigits(2);
        decimalFormatter.setMaximumFractionDigits(2);
        double est = Double.parseDouble(estatura.getText().toString());
        if(uniEstatura.getCheckedRadioButtonId() == R.id.radioButtonMetro){

            if (uniPeso.getCheckedRadioButtonId() == R.id.radioButtonKilo){
                Peso = decimalFormatter.format(20*est*est) + "Kg" + " - " + decimalFormatter.format(23*est*est) + "Kg";
            }
            else{
                Peso = decimalFormatter.format(20*est*est*2.2) + "lb" + " - " + decimalFormatter.format(23*est*est*2.2) + "lb";
            }
        }
        else{
            est = est*2.54/100;

            if (uniPeso.getCheckedRadioButtonId() == R.id.radioButtonKilo){
                Peso = decimalFormatter.format(20*est*est) + "Kg" + " - " + decimalFormatter.format(23*est*est) + "Kg";
            }
            else{
                Peso = decimalFormatter.format(20*est*est*2.2) + "lb" + " - " + decimalFormatter.format(23*est*est*2.2) + "lb";
            }
        }
        return Peso;
    }

    public double CalcularIMC (EditText peso, EditText estatura, RadioGroup uniPeso, RadioGroup uniEstatura){

        double index;
        double pes = Double.parseDouble(peso.getText().toString());
        double est = Double.parseDouble(estatura.getText().toString());

        if (uniPeso.getCheckedRadioButtonId() == R.id.radioButtonKilo){
            if (uniEstatura.getCheckedRadioButtonId() == R.id.radioButtonMetro){
                index = pes/(est*est);
            }
            else{
                est = (est*2.54)/100;
                index = pes/(est*est);
            }
        }
        else{
            pes = pes/2.2;
            if (uniEstatura.getCheckedRadioButtonId() == R.id.radioButtonMetro){
                index = pes/(est*est);
            }
            else{
                est = (est*2.54)/100;
                index = pes/(est*est);
            }
        }
        return index;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int id: ids) {
            findViewById(id).setOnClickListener(this);
        }
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextEstatura = findViewById(R.id.editTextEstatura);
        radioGroupPeso = findViewById(R.id.radioGroupPeso);
        radioGroupEstatura = findViewById(R.id.radioGroupEstatura);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageButton:
                String msg = "El Índice de Masa Corporal es un sencillo índice sobre la relación entre el peso y la altura, generalmente utilizado para clasificar el peso insuficiente, el peso excesivo y la obesidad en los adultos.";
                new AlertDialog.Builder(this).setTitle("Indice de Masa Corporal").setMessage(msg).setNeutralButton("Entendido", this).create().show();
                break;

            case R.id.button:

                if(radioGroupPeso.getCheckedRadioButtonId() == -1){
                    Toast.makeText(this, "Debe marcar la opcion de unidad de peso correspondiente",Toast.LENGTH_SHORT).show();

                }
                if(editTextPeso.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Debe escribir su peso en la unidad seleccionada", Toast.LENGTH_SHORT).show();
                }
                if(radioGroupEstatura.getCheckedRadioButtonId() == -1){
                    Toast.makeText(this, "Debe marcar la opcion de unidad de estatura correspondiente",Toast.LENGTH_SHORT).show();
                }
                if(editTextEstatura.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Debe escribir su estatura en la unidad seleccionada", Toast.LENGTH_SHORT).show();
                }
                else{

                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("Resultado", CalcularIMC(editTextPeso, editTextEstatura, radioGroupPeso, radioGroupEstatura));
                    intent.putExtra("PesoIdeal", PesoIdeal(editTextEstatura, radioGroupPeso, radioGroupEstatura));
                    startActivity(intent);
                }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == AlertDialog.BUTTON_NEUTRAL)
            Toast.makeText(this, "Introduzca los datos correspondientes", Toast.LENGTH_LONG).show();

    }
}
