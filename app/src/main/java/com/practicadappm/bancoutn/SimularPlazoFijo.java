package com.practicadappm.bancoutn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.practicadappm.bancoutn.databinding.ActivityMainBinding;
import com.practicadappm.bancoutn.databinding.ActivitySimularPlazoFijoBinding;

public class SimularPlazoFijo extends AppCompatActivity {
    private EditText capital, tasaNominal, tasaEfectiva;
    private TextView simulaPlazo, simulaCapital, simulaIntereses, simulaTotal, simulaTotalAnual, monedaTextView;
    private String moneda;
    private ActivitySimularPlazoFijoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            moneda = extras.getString("moneda");
        }
        binding = ActivitySimularPlazoFijoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        capital = binding.capital;
        tasaNominal = binding.tasaNominal;
        tasaEfectiva = binding.tasaEfectiva;
        simulaCapital = binding.simulaCapital;
        simulaIntereses = binding.simulaIntereses;
        simulaPlazo = binding.simulaPlazo;
        simulaTotal = binding.simulaTotal;
        simulaTotalAnual = binding.simulaTotalAnual;
        monedaTextView = binding.monedaTextView;
        monedaTextView.setText(moneda);

    }
}