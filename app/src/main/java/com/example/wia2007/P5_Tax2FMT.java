package com.example.wia2007;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;
import java.lang.Math;

public class P5_Tax2FMT extends AppCompatActivity {
    private TextView incomeTaxLabel;
    private TextView taxRebateLabel;
    private TextView balanceIncomeTaxLabel;
    private Button backButton;
    private Button applyButton;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "TaxPrefs";
    private static final String KEY_TOTAL_INCOME = "totalIncome";
    private static final String KEY_TAX_RELIEF = "taxRelief";
    private double incomeTax;
    private double taxRebate;
    private double balanceIncomeTax;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax2fmt);

        incomeTaxLabel = findViewById(R.id.taxPayableLevel);
        taxRebateLabel = findViewById(R.id.taxRebateLabel);
        balanceIncomeTaxLabel = findViewById(R.id.balanceIncomeTaxLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        backButton.setOnClickListener(v -> {
            navigationFromTax2FMTToTax1FMT(v);
        });

        // Retrieve the values from the previous activity or SharedPreferences
        Intent intent = getIntent();
        double totalIncome = intent.getDoubleExtra("totalIncome", 0);
        double taxRelief = intent.getDoubleExtra("taxRelief", 0);
        double chargeableIncome = intent.getDoubleExtra("chargeableIncome", 0);

        // If no new values are passed, retrieve from SharedPreferences
        if (totalIncome == 0 && taxRelief == 0) {
            String totalIncomeStr = sharedPreferences.getString(KEY_TOTAL_INCOME, "0");
            String taxReliefStr = sharedPreferences.getString(KEY_TAX_RELIEF, "0");
            totalIncome = Double.parseDouble(totalIncomeStr);
            taxRelief = Double.parseDouble(taxReliefStr);
            chargeableIncome = totalIncome - taxRelief;
        }

        // Calculate the tax details
        incomeTax = calculateIncomeTax(chargeableIncome);
        taxRebate = (chargeableIncome <= 35000) ? 400 : 0;
        balanceIncomeTax = Math.max(incomeTax - taxRebate, 0);

        // Display the results
        incomeTaxLabel.setText(String.format("%.2f", incomeTax));
        taxRebateLabel.setText(String.format("%.2f", taxRebate));
        balanceIncomeTaxLabel.setText(String.format("%.2f", balanceIncomeTax));

        double finalTotalIncome = totalIncome;
        double finalTaxRelief = taxRelief;
        applyButton.setOnClickListener(v -> {
            // Create a UserIncomeCalculation object with the current values
            TaxData userIncome = new TaxData(finalTotalIncome, finalTaxRelief);
            userIncome.setMonthlyTaxDeduction(0); // Initialize with default value
            userIncome.setTakafulZakat(0); // Initialize with default value

            // Pass the UserIncomeCalculation object to the next activity
            Intent tax3Intent = new Intent(P5_Tax2FMT.this, P5_Tax3FMT.class);
            tax3Intent.putExtra("userIncome", userIncome);
            startActivity(tax3Intent);
        });
    }

    private double calculateIncomeTax(double chargeableIncome) {
        //Rates are accurate to LHDN Malaysia's tax rates as of 2024
        double tax = 0;

        if (chargeableIncome > 0) {
            if (chargeableIncome <= 5000) {
                tax = 0;
            } else if (chargeableIncome <= 20000) {
                tax = (chargeableIncome - 5000) * 0.08;
            } else if (chargeableIncome <= 35000) {
                tax = 150 + (chargeableIncome - 20000) * 0.14;
            } else if (chargeableIncome <= 50000) {
                tax = 600 + (chargeableIncome - 35000) * 0.21;
            } else if (chargeableIncome <= 70000) {
                tax = 1500 + (chargeableIncome - 50000) * 0.24;
            } else if (chargeableIncome <= 100000) {
                tax = 3700 + (chargeableIncome - 70000) * 0.245;
            } else if (chargeableIncome <= 400000) {
                tax = 9400 + (chargeableIncome - 100000) * 0.25;
            } else if (chargeableIncome <= 600000) {
                tax = 84400 + (chargeableIncome - 400000) * 0.26;
            } else if (chargeableIncome <= 2000000) {
                tax = 158400 + (chargeableIncome - 600000) * 0.28;
            } else {
                tax = 578400 + (chargeableIncome - 2000000) * 0.3;
            }
        }

        return tax;
    }

    public void navigationFromTax2FMTToTax1FMT(View view) {
        Intent intent = new Intent(P5_Tax2FMT.this, P5_Tax1FMT.class);
        startActivity(intent);
    }
}