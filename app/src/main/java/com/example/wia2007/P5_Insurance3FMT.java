package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance3FMT extends AppCompatActivity {

    private EditText lifeInsuranceLabel;
    private EditText motorInsuranceLabel;
    private EditText personalInsuranceLabel;
    private Button applyButton;
    private Button backButton;
    private double lifeInsuranceDeductible;
    private double motorInsuranceDeductible;
    private double personalInsuranceDeductible;
    private double medicalInsuranceDeductible;
    private double travelInsuranceDeductible;
    private double otherInsuranceDeductible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance3fmt);

        // Initialize views
        lifeInsuranceLabel = findViewById(R.id.lifeInsuranceLabel);
        motorInsuranceLabel = findViewById(R.id.motorInsuranceLabel);
        personalInsuranceLabel = findViewById(R.id.personalInsuranceLabel);
        applyButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        lifeInsuranceDeductible = intent.getDoubleExtra("LIFE_INSURANCE_DEDUCTIBLE", 0.0);
        motorInsuranceDeductible = intent.getDoubleExtra("MOTOR_INSURANCE_DEDUCTIBLE", 0.0);
        personalInsuranceDeductible = intent.getDoubleExtra("PERSONAL_INSURANCE_DEDUCTIBLE", 0.0);
        medicalInsuranceDeductible = intent.getDoubleExtra("MEDICAL_INSURANCE_DEDUCTIBLE", 0.0);
        travelInsuranceDeductible = intent.getDoubleExtra("TRAVEL_INSURANCE_DEDUCTIBLE", 0.0);
        otherInsuranceDeductible = intent.getDoubleExtra("OTHER_INSURANCE_DEDUCTIBLE", 0.0);

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText fields
                double lifeInsurance = parseDouble(lifeInsuranceLabel.getText().toString());
                double motorInsurance = parseDouble(motorInsuranceLabel.getText().toString());
                double personalInsurance = parseDouble(personalInsuranceLabel.getText().toString());

                // Navigate to P5_Insurance4FMT and pass the values
                navigationFromInsurance3FMTToInsurance4FMT(lifeInsurance, motorInsurance, personalInsurance);
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the homepage
                navigationFromInsurance3FMTToInsurance2FMT(v);
            }
        });
    }

    // Method to navigate to P5_Insurance4FMT and pass data
    private void navigationFromInsurance3FMTToInsurance4FMT(double lifeInsurance, double motorInsurance, double personalInsurance) {
        Intent intent = new Intent(P5_Insurance3FMT.this, P5_Insurance4FMT.class);
        intent.putExtra("LIFE_INSURANCE_DEDUCTIBLE", lifeInsuranceDeductible);
        intent.putExtra("MOTOR_INSURANCE_DEDUCTIBLE", motorInsuranceDeductible);
        intent.putExtra("PERSONAL_INSURANCE_DEDUCTIBLE", personalInsuranceDeductible);
        intent.putExtra("MEDICAL_INSURANCE_DEDUCTIBLE", medicalInsuranceDeductible);
        intent.putExtra("TRAVEL_INSURANCE_DEDUCTIBLE", travelInsuranceDeductible);
        intent.putExtra("OTHER_INSURANCE_DEDUCTIBLE", otherInsuranceDeductible);
        intent.putExtra("LIFE_INSURANCE", lifeInsurance);
        intent.putExtra("MOTOR_INSURANCE", motorInsurance);
        intent.putExtra("PERSONAL_INSURANCE", personalInsurance);
        startActivity(intent);
    }

    public void navigationFromInsurance3FMTToInsurance2FMT(View view) {
        Intent intent = new Intent(P5_Insurance3FMT.this, P5_Insurance2FMT.class);
        startActivity(intent);
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