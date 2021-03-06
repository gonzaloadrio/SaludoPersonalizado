package com.example.gonzalo.saludopersonalizado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText etNombre;
    RadioButton bSr, bSra;
    TextView tvSalida;
    DatePicker datePicker;
    TimePicker timePicker;
    Button enviar;
    CheckBox cbVerFecha;
    ScrollView scrollView;
    RadioGroup radioGroup;


    Spinner spinner;
    ArrayAdapter<CharSequence> adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        bSr = (RadioButton) findViewById(R.id.rbSr);
        bSra = (RadioButton) findViewById(R.id.rbSra);
        tvSalida = (TextView) findViewById(R.id.tvSalida);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        cbVerFecha = (CheckBox) findViewById(R.id.cbFecha);
        enviar = (Button) findViewById(R.id.bEnviar);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapterSpinner = ArrayAdapter.createFromResource(this, R.array.saludo_array, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        cbVerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbVerFecha.isChecked()) {
                    scrollView.setVisibility(View.VISIBLE);
                } else {
                    scrollView.setVisibility(View.GONE);
                }
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etNombre.getText().toString().equals("")) {
                    String fecha = ": " + datePicker.getDayOfMonth() + " - " + datePicker.getMonth() + " - " + datePicker.getYear();
                    String hora = " -> " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
                    String genero = "";
                    String saludo = "";

                    if (spinner.getSelectedItem().toString().equals("Hola")) {
                        saludo = "Hola ";
                    } else if (spinner.getSelectedItem().toString().equals("Adios")) {
                        saludo = "Adios ";

                    }

                    if (bSr.isChecked()) {
                        genero = "Sr. ";
                    } else if (bSra.isChecked()) {
                        genero = "Sra. ";

                    }
                    tvSalida.setText(saludo + genero + etNombre.getText().toString() + fecha + hora);
                } else if (!bSr.isChecked() && !bSra.isChecked()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Elige Sr o Sra");
                    builder.setPositiveButton("Sr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bSr.setChecked(true);
                        }
                    });
                    builder.setNegativeButton("Sra", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bSra.setChecked(true);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(getApplicationContext(), "Nombre Vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
