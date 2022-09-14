package com.practicadappm.bancoutn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.practicadappm.bancoutn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button botonSimular, botonConstituir;
    private EditText nombrePersona, apellidoPersona;
    private Spinner moneda;

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

        botonSimular.setOnClickListener(new ListenerSimular());
        botonConstituir.setOnClickListener(new ListenerConstituir());
    }











    public class ListenerSimular implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), SimularPlazoFijo.class);
            intent.putExtra("moneda", moneda.getSelectedItem().toString());
            startActivity(intent);
        }
    }

        public class ListenerConstituir implements View.OnClickListener {

            @Override
            public void onClick(View v) {


            }
        }
    }

