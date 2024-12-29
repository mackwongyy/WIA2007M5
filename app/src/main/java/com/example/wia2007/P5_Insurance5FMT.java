package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance5FMT extends AppCompatActivity {

    private TextView insurancePayableLabel;
    private TextView totalDeductiblesLabel;
    private TextView totalInsuranceCostLabel;
    private Button backButton;
    private Button homeButton;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "InsurancePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance5fmt);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize TextViews
        insurancePayableLabel = findViewById(R.id.insurancePayableLabel);
        totalDeductiblesLabel = findViewById(R.id.totalDeductiblesLabel);
        totalInsuranceCostLabel = findViewById(R.id.totalInsuranceCostLabel);

        // Initialize Buttons
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Retrieve and display data from SharedPreferences
        displayInsuranceSummary();

        // Set up button click listeners
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(P5_Insurance5FMT.this, P5_Insurance4FMT.class);
            startActivity(intent);
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(P5_Insurance5FMT.this, P5_HomepageFMT.class);
            startActivity(intent);
        });
    }

    private void displayInsuranceSummary() {
        // Retrieve values from SharedPreferences
        double lifeInsuranceDeductible = parseDouble(sharedPreferences.getString("lifeInsurance", "0.0"));
        double motorInsuranceDeductible = parseDouble(sharedPreferences.getString("motorInsurance", "0.0"));
        double personalInsuranceDeductible = parseDouble(sharedPreferences.getString("personalInsurance", "0.0"));
        double medicalInsuranceDeductible = parseDouble(sharedPreferences.getString("medicalInsurance", "0.0"));
        double travelInsuranceDeductible = parseDouble(sharedPreferences.getString("travelInsurance", "0.0"));
        double otherInsuranceDeductible = parseDouble(sharedPreferences.getString("otherInsurance", "0.0"));
        double lifeInsuranceCost = parseDouble(sharedPreferences.getString("lifeInsuranceCost", "0.0"));
        double motorInsuranceCost = parseDouble(sharedPreferences.getString("motorInsuranceCost", "0.0"));
        double personalInsuranceCost = parseDouble(sharedPreferences.getString("personalInsuranceCost", "0.0"));
        double medicalInsuranceCost = parseDouble(sharedPreferences.getString("medicalInsuranceCost", "0.0"));
        double travelInsuranceCost = parseDouble(sharedPreferences.getString("travelInsuranceCost", "0.0"));
        double otherInsuranceCost = parseDouble(sharedPreferences.getString("otherInsuranceCost", "0.0"));

        // Calculate totals
        double totalDeductibles = lifeInsuranceDeductible + motorInsuranceDeductible + personalInsuranceDeductible +
                medicalInsuranceDeductible + travelInsuranceDeductible + otherInsuranceDeductible;
        double totalInsuranceCost = lifeInsuranceCost + motorInsuranceCost + personalInsuranceCost +
                medicalInsuranceCost + travelInsuranceCost + otherInsuranceCost;
        double insurancePayable = totalDeductibles + totalInsuranceCost;

        // Display the results
        totalDeductiblesLabel.setText(String.format("RM %.2f", totalDeductibles));
        totalInsuranceCostLabel.setText(String.format("RM %.2f", totalInsuranceCost));
        insurancePayableLabel.setText(String.format("RM %.2f", insurancePayable));

        // Display individual deductibles and insurance costs
        ((TextView) findViewById(R.id.lifeInusranceDeductiblesLabel)).setText(String.format("RM %.2f", lifeInsuranceDeductible));
        ((TextView) findViewById(R.id.motorInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", motorInsuranceDeductible));
        ((TextView) findViewById(R.id.personalInsuranceDeductibleLabel)).setText(String.format("RM %.2f", personalInsuranceDeductible));
        ((TextView) findViewById(R.id.medicalInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", medicalInsuranceDeductible));
        ((TextView) findViewById(R.id.travelInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", travelInsuranceDeductible));
        ((TextView) findViewById(R.id.otherInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", otherInsuranceDeductible));
        ((TextView) findViewById(R.id.lifeInsuranceCostLabel)).setText(String.format("RM %.2f", lifeInsuranceCost));
        ((TextView) findViewById(R.id.motorInsuranceCostLabel)).setText(String.format("RM %.2f", motorInsuranceCost));
        ((TextView) findViewById(R.id.personalInsuranceCostLabel)).setText(String.format("RM %.2f", personalInsuranceCost));
        ((TextView) findViewById(R.id.medicalInsuranceCostLabel)).setText(String.format("RM %.2f", medicalInsuranceCost));
        ((TextView) findViewById(R.id.travelInsuranceCostLabel)).setText(String.format("RM %.2f", travelInsuranceCost));
        ((TextView) findViewById(R.id.otherInsuranceCostLabel)).setText(String.format("RM %.2f", otherInsuranceCost));
    }

    // Utility method to parse double values
    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}