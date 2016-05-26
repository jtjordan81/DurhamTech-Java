package com.example.student976.medicalconverter;

import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    //define vars
    double conversionRate = 2.2;
    double weightEntered;
    double convertedWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final EditText weight = (EditText) findViewById(R.id.txtWeight);
        final RadioButton lbsToKilo = (RadioButton) findViewById(R.id.radLbToKilo);
        final RadioButton kiloToLbs = (RadioButton) findViewById(R.id.radKiloToLb);
        final TextView results = (TextView) findViewById(R.id.txtResults);
        Button convert = (Button) findViewById(R.id.btnConvert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightEntered = Double.parseDouble(weight.getText().toString());
                DecimalFormat tenth = new DecimalFormat("#.#");

                if(lbsToKilo.isChecked()) {
                    if(weightEntered <= 500) {
                        convertedWeight = weightEntered / conversionRate;
                        results.setText(tenth.format(convertedWeight) + " kilograms");
                    }else {
                        Toast.makeText(MainActivity.this, "Pounds must be less than 500", Toast.LENGTH_LONG).show();
                    } // end else
                } // end if

                if(kiloToLbs.isChecked()) {
                    if(weightEntered<= 225) {
                        convertedWeight = weightEntered * conversionRate;
                        results.setText(tenth.format(convertedWeight) + " pounds");
                    }else {
                        Toast.makeText(MainActivity.this, "Kilos must be less than 225", Toast.LENGTH_LONG).show();
                    } // end else
                } // end if
            } // end on click
        });






    }



}
