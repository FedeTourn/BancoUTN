package com.practicadappm.bancoutn;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.practicadappm.bancoutn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_CONSTRUIR_PLAZO = 777;
    private ActivityMainBinding binding;
    private Button botonSimular, botonConstituir;
    private EditText nombrePersona, apellidoPersona;
    private Spinner moneda;

    private Float capital;
    private Integer dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        botonSimular = binding.botonSimular;
        botonConstituir = binding.botonConstituir;
        nombrePersona = binding.nombrePersona;
        apellidoPersona = binding.apellidoPersona;
        moneda = binding.moneda;

        botonConstituir.setEnabled(false);
        botonSimular.setEnabled(false);

        nombrePersona.addTextChangedListener(textWatcher);
        apellidoPersona.addTextChangedListener(textWatcher);

        botonSimular.setOnClickListener(new ListenerSimular());
        botonConstituir.setOnClickListener(new ListenerConstituir());
    }

    public class ListenerSimular implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), SimularPlazoFijo.class);
            intent.putExtra("moneda", moneda.getSelectedItem().toString());
            startActivityForResult(intent,CODIGO_CONSTRUIR_PLAZO);
        }
    }

    public class ListenerConstituir implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Bundle argumentos = new Bundle();
            argumentos.putString("titulo", "Felicidades "+nombrePersona.getText().toString()+" "+apellidoPersona.getText().toString()+"!");
            argumentos.putString("mensaje", "Tu plazo fijo de " + capital.toString() + " " + moneda.getSelectedItem().toString() + " por " + dias.toString() + " dias ha sido constituido!");
            //Crea el objeto AlertDialog
            DialogoExito dialogo = new DialogoExito();

            dialogo.setArguments(argumentos);

            dialogo.show(getSupportFragmentManager(), null);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==CODIGO_CONSTRUIR_PLAZO){
                //Lo que devuelve simular
                Float capitalS = null;
                Integer diasS = null;
                if (data.getExtras() != null){
                    capitalS = data.getExtras().getFloat("capital");
                    diasS = data.getExtras().getInt("dias");
                }

                botonConstituir.setEnabled(true);
                nombrePersona.setEnabled(false);
                apellidoPersona.setEnabled(false);
                moneda.setEnabled(false);
                capital = capitalS;
                dias = diasS;
            }
        }
    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable s) {
            if(nombrePersona.getText().toString().trim().length() > 0 && apellidoPersona.getText().toString().trim().length() > 0)
                botonSimular.setEnabled(true);
            else
                botonSimular.setEnabled(false);
        }
    };


}