package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Tax4FMT extends AppCompatActivity {
    private EditText roadTaxLabel;
    private EditText propertyTaxLabel;
    private EditText quitRentLabel;
    private EditText otherTaxLabel;
    private Button backButton;
    private Button applyButton;
    private TaxDatabaseHelper taxDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax4fmt);

        taxDatabaseHelper = new TaxDatabaseHelper(this);

        roadTaxLabel = findViewById(R.id.roadTaxLabel);
        propertyTaxLabel = findViewById(R.id.roadTaxLabel);
        quitRentLabel = findViewById(R.id.quitRentLabel);
        otherTaxLabel = findViewById(R.id.totalDeductiblesLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        applyButton.setOnClickListener(v -> {
            saveAdditionalTaxes();
            navigationFromTax4FMTToTax5FMT(v);
        });

        backButton.setOnClickListener(v -> {
            navigationFromTax4FMTToTax3FMT(v);
        });
    }

    private void saveAdditionalTaxes() {
        String roadTaxStr = roadTaxLabel.getText().toString();
        String propertyTaxStr = propertyTaxLabel.getText().toString();
        String quitRentStr = quitRentLabel.getText().toString();
        String otherTaxStr = otherTaxLabel.getText().toString();

        if (roadTaxStr.isEmpty() || propertyTaxStr.isEmpty() || quitRentStr.isEmpty() || otherTaxStr.isEmpty()) {
            return;
        }

        double roadTax = Double.parseDouble(roadTaxStr);
        double propertyTax = Double.parseDouble(propertyTaxStr);
        double quitRent = Double.parseDouble(quitRentStr);
        double otherTax = Double.parseDouble(otherTaxStr);

        // Update the database
        taxDatabaseHelper.insertTaxData(0, 0, 0, 0, roadTax, propertyTax, quitRent, otherTax);
    }

    public void navigationFromTax4FMTToTax3FMT(View view) {
        Intent intent = new Intent(P5_Tax4FMT.this, P5_Tax3FMT.class);
        startActivity(intent);
    }

    public void navigationFromTax4FMTToTax5FMT(View view) {
        Intent intent = new Intent(P5_Tax4FMT.this, P5_Tax5FMT.class);
        startActivity(intent);
    }
}