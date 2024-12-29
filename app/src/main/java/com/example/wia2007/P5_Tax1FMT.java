package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Tax1FMT extends AppCompatActivity {
    private EditText income;
    private EditText taxRelief;
    private TextView chargeableIncome;
    private Button backButton;
    private Button applyButton;
    private TaxDatabaseHelper taxDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax1fmt);

        taxDatabaseHelper = new TaxDatabaseHelper(this);

        income = findViewById(R.id.totalIncomeLabel);
        taxRelief = findViewById(R.id.taxReliefLabel);
        chargeableIncome = findViewById(R.id.chargeableIncomeLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        applyButton.setOnClickListener(v -> {
            calculateChargeableIncome();
        });

        backButton.setOnClickListener(v -> {
            navigationFromTax1FMTToHomepageFMT(v);
        });
    }

    private void calculateChargeableIncome() {
        String totalIncomeStr = income.getText().toString();
        String taxReliefStr = taxRelief.getText().toString();

        if (totalIncomeStr.isEmpty() || taxReliefStr.isEmpty()) {
            return;
        }

        double totalIncome = Double.parseDouble(totalIncomeStr);
        double taxReliefAmount = Double.parseDouble(taxReliefStr);
        double chargeable = totalIncome - taxReliefAmount;
        chargeableIncome.setText(String.format("%.2f", chargeable));

        // Insert data into the database
        taxDatabaseHelper.insertTaxData(totalIncome, taxReliefAmount, 0, 0, 0, 0, 0, 0);

        // Navigate to the next activity
        Intent intent = new Intent(P5_Tax1FMT.this, P5_Tax2FMT.class);
        startActivity(intent);
    }

    public void navigationFromTax1FMTToHomepageFMT(View view) {
        Intent intent = new Intent(P5_Tax1FMT.this, P5_HomepageFMT.class);
        startActivity(intent);
    }
}