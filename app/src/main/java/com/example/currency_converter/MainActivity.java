package com.example.currency_converter;

import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerTo;
    private EditText editTextAmount;
    private Button buttonConvert;
    private TextView textViewResult;

    private HashMap<String, Double> exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        Context context = null;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.currencies,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD_TO_EUR", 0.85);
        exchangeRates.put("EUR_TO_USD", 1.18);
        exchangeRates.put("USD_TO_GBP", 0.76);
        exchangeRates.put("GBP_TO_USD", 1.32);
        exchangeRates.put("USD_TO_INR", 74.0);
        exchangeRates.put("INR_TO_USD", 0.013);
        exchangeRates.put("USD_TO_JPY", 110.0);
        exchangeRates.put("JPY_TO_USD", 0.009);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String fromCurrency = spinnerFrom.getSelectedItem().toString();
        String toCurrency = spinnerTo.getSelectedItem().toString();
        String amountStr = editTextAmount.getText().toString();

        if (amountStr.isEmpty()) {
            textViewResult.setText("Please enter an amount.");
            return;
        }

        double amount = Double.parseDouble(amountStr);
        double convertedAmount = 0.0;

        String key = fromCurrency + "_TO_" + toCurrency;
        if (exchangeRates.containsKey(key)) {
            convertedAmount = amount * exchangeRates.get(key);
            textViewResult.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
        } else {
            textViewResult.setText("Conversion not available.");
        }
    }
}
