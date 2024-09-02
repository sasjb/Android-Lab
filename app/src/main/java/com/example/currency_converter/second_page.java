package com.example.currency_converter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.currency_converter.R;
public class second_page extends AppCompatActivity {
    private TextView textViewResultOnSecondPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        textViewResultOnSecondPage = findViewById(R.id.textViewResultOnSecondPage);
        Intent intent = getIntent();
        String conversionResult = intent.getStringExtra("conversionResult");
        textViewResultOnSecondPage.setText(conversionResult);
    }
}
