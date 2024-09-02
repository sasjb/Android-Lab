package com.example.currency_converter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.currency_converter.R;
import com.example.currency_converter.second_page;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    private Spinner spinnerFrom, spinnerTo;
    private EditText editTextAmount;
    private Button buttonConvert;
    private HashMap<String, Double> exchangeRates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerFrom = findViewById(R.id.spinner_From);
        spinnerTo = findViewById(R.id.spinner_To);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonConvert = findViewById(R.id.buttonConvert);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
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
        exchangeRates.put("EUR_TO_GBP", 0.89);
        exchangeRates.put("GBP_TO_EUR", 1.12);
        exchangeRates.put("EUR_TO_INR", 87.0);
        exchangeRates.put("INR_TO_EUR", 0.011);
        exchangeRates.put("EUR_TO_JPY", 129.53);
        exchangeRates.put("JPY_TO_EUR", 0.0077);
        exchangeRates.put("GBP_TO_INR", 97.0);
        exchangeRates.put("INR_TO_GBP", 0.010);
        exchangeRates.put("GBP_TO_JPY", 145.0);
        exchangeRates.put("JPY_TO_GBP", 0.007);
        exchangeRates.put("INR_TO_JPY", 1.48);
        exchangeRates.put("JPY_TO_INR", 0.68);

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
            return;
        }
        double amount = Double.parseDouble(amountStr);
        double convertedAmount = 0.0;
        String key = fromCurrency + "_TO_" + toCurrency;
        if (exchangeRates.containsKey(key)) {
            convertedAmount = amount * exchangeRates.get(key);
            String result = String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
            showResultOnSecondPage(result);
        } else {
            String result = "Conversion not available.";
            showResultOnSecondPage(result);
        }
    }

    private void showResultOnSecondPage(String result) {
        Intent intent = new Intent(MainActivity.this, second_page.class);
        intent.putExtra("conversionResult", result);
        startActivity(intent);
    }
}
