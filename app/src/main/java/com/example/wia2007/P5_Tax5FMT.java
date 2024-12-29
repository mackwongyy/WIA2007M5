package com.example.wia2007;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class P5_Tax5FMT extends AppCompatActivity {
    private TextView taxPayableLabel;
    private TextView incomeTaxLabel;
    private TextView taxRebateReliefLabel;
    private TextView taxDeductionAnnualLabel;
    private TextView takafulZakatLabel;
    private TextView roadTaxLabel;
    private TextView propertyTaxLabel;
    private TextView quitRentLabel;
    private TextView otherTaxLabel;
    private Button backButton;
    private Button homeButton;

    private TaxData userIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax5fmt);

        // Initialize TextViews
        taxPayableLabel = findViewById(R.id.insurancePayableLabel);
        incomeTaxLabel = findViewById(R.id.incomeTaxLabel);
        taxRebateReliefLabel = findViewById(R.id.taxRebateReliefLabel);
        taxDeductionAnnualLabel = findViewById(R.id.lifeInusranceDeductiblesLabel);
        takafulZakatLabel = findViewById(R.id.takafulZakatLabel);
        roadTaxLabel = findViewById(R.id.roadTaxLabel);
        propertyTaxLabel = findViewById(R.id.propertyTaxLabel);
        quitRentLabel = findViewById(R.id.quitRentLabel);
        otherTaxLabel = findViewById(R.id.totalDeductiblesLabel);

        // Initialize Buttons
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Retrieve the UserIncomeCalculation object from the previous activity
        Intent intent = getIntent();
        userIncome = (TaxData) intent.getSerializableExtra("userIncome");

        if (userIncome == null) {
            // Handle error: No income data found
            finish();
        } else {
            // Display the tax summary
            displayTaxSummary();
        }

        // Set up button click listeners
        backButton.setOnClickListener(v -> {
            navigationFromTax5FMTToTax4FMT(v);
        });

        homeButton.setOnClickListener(v -> {
            navigationFromTax5FMTToHomepage(v);
        });
    }

    private void displayTaxSummary() {
        // Display the total tax payable
        taxPayableLabel.setText(String.format("RM %.2f", userIncome.getTotalTaxPayable()));

        // Display income tax
        incomeTaxLabel.setText(String.format("RM %.2f", userIncome.getIncomeTax()));

        // Display tax rebate and relief
        taxRebateReliefLabel.setText(String.format("RM %.2f", userIncome.getTaxRebate()));

        // Display annual tax deduction (monthly tax deduction * 12)
        taxDeductionAnnualLabel.setText(String.format("RM %.2f", userIncome.getMonthlyTaxDeduction() * 12));

        // Display takaful/zakat
        takafulZakatLabel.setText(String.format("RM %.2f", userIncome.getTakafulZakat()));

        // Display road tax
        roadTaxLabel.setText(String.format("RM %.2f", userIncome.getRoadTax()));

        // Display property tax
        propertyTaxLabel.setText(String.format("RM %.2f", userIncome.getPropertyTax()));

        // Display quit rent
        quitRentLabel.setText(String.format("RM %.2f", userIncome.getQuitRent()));

        // Display other tax
        otherTaxLabel.setText(String.format("RM %.2f", userIncome.getOtherTax()));
    }

    public void navigationFromTax5FMTToTax4FMT(View view) {
        Intent intent = new Intent(P5_Tax5FMT.this, P5_Tax4FMT.class);
        intent.putExtra("userIncome", userIncome); // Pass the UserIncomeCalculation object back
        startActivity(intent);
    }

    public void navigationFromTax5FMTToHomepage(View view) {
        Intent intent = new Intent(P5_Tax5FMT.this, P5_HomepageFMT.class);
        startActivity(intent);
    }
}