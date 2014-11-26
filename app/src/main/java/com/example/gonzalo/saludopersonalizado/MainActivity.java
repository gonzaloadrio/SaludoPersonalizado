package com.example.gonzalo.saludopersonalizado;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText etNombre;
    RadioButton bSr, bSra;
    TextView tvSalida;
    DatePicker datePicker;
    Button enviar;
    CheckBox cbVerFecha;
    ScrollView scrollView;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        bSr = (RadioButton) findViewById(R.id.rbSr);
        bSra = (RadioButton) findViewById(R.id.rbSra);
        tvSalida = (TextView) findViewById(R.id.tvSalida);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        cbVerFecha = (CheckBox) findViewById(R.id.cbFecha);
        enviar = (Button) findViewById(R.id.bEnviar);
        scrollView = (ScrollView) findViewById(R.id.horizontalScrollView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

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
                if (etNombre.getText().equals("")) {
                    String fecha = ": " + datePicker.getDayOfMonth() + " - " + datePicker.getMonth() + " - " + datePicker.getYear();
                    String genero = "";
                    if (bSr.isChecked()) {
                        genero = "Sr. ";
                    } else if (bSra.isChecked()) {
                        genero = "Sra. ";

                    }
                    tvSalida.setText(genero + etNombre.getText().toString() + fecha);
                } else {
                    Toast.makeText(getApplicationContext(), "Nombre Vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
