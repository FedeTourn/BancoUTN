package com.practicadappm.bancoutn;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.practicadappm.bancoutn.databinding.ActivitySimularPlazoFijoBinding;

public class SimularPlazoFijo extends AppCompatActivity {
    private EditText capital, tasaNominal, tasaEfectiva;
    private Float capitalFloat= Float.valueOf(0), tasaNominalFloat = Float.valueOf(0), tasaEfectivaFloat = Float.valueOf(0);
    private Integer cantMeses;
    private TextView simulaPlazo, simulaCapital, simulaIntereses, simulaTotal, simulaTotalAnual, monedaTextView, cantDias;
    private SeekBar meses;
    private String moneda;
    private ActivitySimularPlazoFijoBinding binding;
    private Button botonConfirmar;
    private PlazoFijo plazoFijo;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
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
        cantDias = binding.cantidadDias;
        meses = binding.seekDias;
        botonConfirmar = binding.botonConfirmar;


        monedaTextView.setText(moneda);
        capital.addTextChangedListener(textWatcher);
        tasaNominal.addTextChangedListener(textWatcher);
        tasaEfectiva.addTextChangedListener(textWatcher);
        meses.setOnSeekBarChangeListener(listenerCambio);
        plazoFijo = new PlazoFijo();

        botonConfirmar.setOnClickListener(new ListenerConfirmar());

    }



    //Cambio de TextViews
    private void calcular() {

        if(tasaNominal.getText().toString().trim().length() > 0)
            tasaNominalFloat = Float.parseFloat(tasaNominal.getText().toString());
        else
            tasaNominalFloat = Float.valueOf(0);
        if(tasaEfectiva.getText().toString().trim().length() > 0)
            tasaEfectivaFloat = Float.parseFloat(tasaEfectiva.getText().toString());
        else
            tasaEfectivaFloat = Float.valueOf(0);
        if(capital.getText().toString().trim().length() > 0)
            capitalFloat = Float.parseFloat(capital.getText().toString());
        else
            capitalFloat = Float.valueOf(0);
        cantMeses = meses.getProgress();

        if(plazoFijo.setearYChequear(tasaNominalFloat, tasaEfectivaFloat, capitalFloat, cantMeses)) {
            plazoFijo.simular();
            simulaCapital.setText(plazoFijo.getCapital().toString());
            simulaIntereses.setText(plazoFijo.getInteresesGanados().toString());
            simulaPlazo.setText(plazoFijo.getDias().toString());
            simulaTotal.setText(plazoFijo.getMontoTotal().toString());
            simulaTotalAnual.setText(plazoFijo.getMontoTotalAnual().toString());
            botonConfirmar.setEnabled(true);
        } else {
            simulaCapital.setText("");
            simulaIntereses.setText("");
            simulaPlazo.setText("");
            simulaTotal.setText("");
            simulaTotalAnual.setText("");
            botonConfirmar.setEnabled(false);
        }
    }


    public class ListenerConfirmar implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intentConfirmar = new Intent();
            intentConfirmar.putExtra("capital",capitalFloat);
            intentConfirmar.putExtra("dias",plazoFijo.getDias());
            setResult(Activity.RESULT_OK,intentConfirmar);
            finish();
        }
    }

    //Listeners
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable s) {
            calcular();
        }
    };

    private SeekBar.OnSeekBarChangeListener listenerCambio = new SeekBar.OnSeekBarChangeListener() {


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            cantDias.setText(meses.getProgress()*30 + " dias");
            calcular();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };
}