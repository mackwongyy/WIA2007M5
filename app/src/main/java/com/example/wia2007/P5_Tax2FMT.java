package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Tax2FMT extends AppCompatActivity {
    private TextView incomeTaxLabel;
    private TextView taxRebateLabel;
    private TextView balanceIncomeTaxLabel;
    private Button backButton;
    private Button applyButton;
    private TaxDatabaseHelper taxDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax2fmt);

        taxDatabaseHelper = new TaxDatabaseHelper(this);

        incomeTaxLabel = findViewById(R.id.incomeTaxLabel);
        taxRebateLabel = findViewById(R.id.taxRebateLabel);
        balanceIncomeTaxLabel = findViewById(R.id.balanceIncomeTaxLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        // Retrieve the values from the previous activity
        Intent intent = getIntent();
        double totalIncome = intent.getDoubleExtra("totalIncome", 0);
        double taxRelief = intent.getDoubleExtra("taxRelief", 0);
        double chargeableIncome = intent.getDoubleExtra("chargeableIncome", 0); // Retrieve chargeableIncome directly

        // Calculate the tax details
        double incomeTax = calculateIncomeTax(chargeableIncome);
        double taxRebate = (chargeableIncome <= 35000) ? 400 : 0;
        double balanceIncomeTax = Math.max(incomeTax - taxRebate, 0);

        // Display the results
        incomeTaxLabel.setText(String.format("%.2f", incomeTax));
        taxRebateLabel.setText(String.format("%.2f", taxRebate));
        balanceIncomeTaxLabel.setText(String.format("%.2f", balanceIncomeTax));

        // Save incomeTax in the database
        taxDatabaseHelper.updateTaxData(null, null, null, null, null, null, null, null, incomeTax);

        applyButton.setOnClickListener(v -> {
            // Navigate to the next activity
            Intent tax3Intent = new Intent(P5_Tax2FMT.this, P5_Tax3FMT.class);
            startActivity(tax3Intent);
        });

        backButton.setOnClickListener(v -> {
            navigationFromTax2FMTToTax1FMT(v);
        });
    }

    private double calculateIncomeTax(double chargeableIncome) {
        double tax = 0;

        if (chargeableIncome > 0) {
            if (chargeableIncome <= 5000) {
                tax = 0;
            } else if (chargeableIncome <= 20000) {
                tax = (chargeableIncome - 5000) * 0.01;
            } else if (chargeableIncome <= 35000) {
                tax = 150 + (chargeableIncome - 20000) * 0.03;
            } else if (chargeableIncome <= 50000) {
                tax = 600 + (chargeableIncome - 35000) * 0.06;
            } else if (chargeableIncome <= 70000) {
                tax = 1500 + (chargeableIncome - 50000) * 0.11;
            } else if (chargeableIncome <= 100000) {
                tax = 3700 + (chargeableIncome - 70000) * 0.19;
            } else if (chargeableIncome <= 400000) {
                tax = 9400 + (chargeableIncome - 100000) * 0.25;
            } else if (chargeableIncome <= 600000) {
                tax = 84400 + (chargeableIncome - 400000) * 0.26;
            } else if (chargeableIncome <= 2000000) {
                tax = 136400 + (chargeableIncome - 600000) * 0.28;
            } else {
                tax = 528400 + (chargeableIncome - 2000000) * 0.30;
            }
        }

        return tax;
    }

    public void navigationFromTax2FMTToTax1FMT(View view) {
        Intent intent = new Intent(P5_Tax2FMT.this, P5_Tax1FMT.class);
        startActivity(intent);
    }
}