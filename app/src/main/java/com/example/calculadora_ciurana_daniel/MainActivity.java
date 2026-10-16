package com.example.calculadora_ciurana_daniel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final double KM_A_MILLAS = 0.621371;
    private static final double MILLAS_A_KM = 1.609344;
    private static final double KG_A_LIBRAS = 2.204623;
    private static final double LIBRAS_A_KG = 0.453592;

    private EditText etValor;
    private Spinner spTipoConversion;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etValor = findViewById(R.id.etValor);
        spTipoConversion = findViewById(R.id.spTipoConversion);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCalcularClick();
            }
        });
    }

    private void onCalcularClick() {
        String texto = etValor.getText().toString().trim();
        int posicion = spTipoConversion.getSelectedItemPosition();

        if (texto.isEmpty()) {
            etValor.setError("Introduce un valor");
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            etValor.setError("Número no válido");
            return;
        }

        String errorValor = validarValor(valor, posicion);
        if (errorValor != null) {
            etValor.setError(errorValor);
            return;
        }

        double resultado = convertir(valor, posicion);
        tvResultado.setText(String.format(Locale.getDefault(), "%.2f %s", resultado, obtenerUnidad(posicion)));
    }

    private String validarValor(double valor, int posicion) {
        switch (posicion) {
            case 2:
                if (valor < -273.15) {
                    return "No puede ser menor que -273,15 °C";
                }
                break;
            case 3:
                if (valor < -459.67) {
                    return "No puede ser menor que -459,67 °F";
                }
                break;
            default:
                if (valor < 0) {
                    return "El valor no puede ser negativo";
                }
                break;
        }
        return null;
    }

    private double convertir(double valor, int posicion) {
        switch (posicion) {
            case 0:
                return valor * KM_A_MILLAS;
            case 1:
                return valor * MILLAS_A_KM;
            case 2:
                return valor * 9 / 5 + 32;
            case 3:
                return (valor - 32) * 5 / 9;
            case 4:
                return valor * KG_A_LIBRAS;
            case 5:
                return valor * LIBRAS_A_KG;
            default:
                return valor;
        }
    }

    private String obtenerUnidad(int posicion) {
        switch (posicion) {
            case 0:
                return "mi";
            case 1:
                return "km";
            case 2:
                return "°F";
            case 3:
                return "°C";
            case 4:
                return "lb";
            case 5:
                return "kg";
            default:
                return "";
        }
    }
}