package com.example.pizzeria_android;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final int[] precioFinal = {0};

        final int precioIngrediente = 2;
        final int precioSmall = 5;
        final int precioMedium = 10;
        final int precioBig = 15;

        RadioGroup radioGroup = findViewById(R.id.Grupo1);
        TextView txtPrecioFinal = findViewById(R.id.precioFinal);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.small) {
                precioFinal[0] = precioSmall;
            } else if (checkedId == R.id.medium) {
                precioFinal[0] = precioMedium;
            } else if (checkedId == R.id.big) {
                precioFinal[0] = precioBig;
            }
        });

        final CheckBox atun = findViewById(R.id.atun);
        final CheckBox champinion = findViewById(R.id.champinion);
        final CheckBox pollo = findViewById(R.id.pollo);
        final CheckBox salmon = findViewById(R.id.salmon);
        final CheckBox pavo = findViewById(R.id.pavo);
        final CheckBox quesoExtra = findViewById(R.id.quesoExtra);
        final CheckBox aceitunas = findViewById(R.id.aceitunas);
        final CheckBox cebolla = findViewById(R.id.cebolla);

        CheckBox[] checkBoxes = {atun, champinion, pollo, salmon, pavo, quesoExtra, aceitunas, cebolla};

        Button btnComprar = findViewById(R.id.btnComprar);

        btnComprar.setOnClickListener(v -> {
            int precioTotal = precioFinal[0];

            for (CheckBox cb : checkBoxes) {
                if (cb.isChecked()) {
                    precioTotal += precioIngrediente;
                }
            }
            txtPrecioFinal.setText(precioTotal + "€");
        });

        // Añadir lógica para ocultar el teclado
        EditText edtTxtNombre = findViewById(R.id.edtTxtNombre);
        EditText edtTxtDireccion = findViewById(R.id.edtTxtDireccion);

        edtTxtNombre.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        edtTxtDireccion.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        // Ocultar teclado al tocar fuera de EditText
        findViewById(R.id.main).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View currentFocus = getCurrentFocus();
                if (currentFocus != null && (currentFocus instanceof EditText)) {
                    currentFocus.clearFocus();
                    hideKeyboard(currentFocus);
                }
            }
            return false;
        });
    }

    // Método para ocultar el teclado
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
