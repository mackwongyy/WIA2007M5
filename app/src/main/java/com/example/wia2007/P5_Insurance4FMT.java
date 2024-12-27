package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance4FMT extends AppCompatActivity {

    private EditText medicalInsuranceLabel;
    private EditText travelInsuranceLabel;
    private EditText otherInsuranceLabel;
    private TextView totalInsuranceLabel;
    private Button backButton;
    private Button applyButton;
    private double lifeInsuranceDeductible;
    private double motorInsuranceDeductible;
    private double personalInsuranceDeductible;
    private double medicalInsuranceDeductible;
    private double travelInsuranceDeductible;
    private double otherInsuranceDeductible;
    private double totalDeductibles;

    private double lifeInsurance;
    private double motorInsurance;
    private double personalInsurance;
    private double medicalInsurance;
    private double travelInsurance;
    private double otherInsurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance4fmt);

        // Initialize views
        medicalInsuranceLabel = findViewById(R.id.medicalInsuranceLabel);
        travelInsuranceLabel = findViewById(R.id.travelInsuranceLabel);
        otherInsuranceLabel = findViewById(R.id.otherInsuranceLabel);
        totalInsuranceLabel = findViewById(R.id.totalInsuranceLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

        Intent intent = getIntent();
        lifeInsuranceDeductible = intent.getDoubleExtra("LIFE_INSURANCE_DEDUCTIBLE", 0.0);
        motorInsuranceDeductible = intent.getDoubleExtra("MOTOR_INSURANCE_DEDUCTIBLE", 0.0);
        personalInsuranceDeductible = intent.getDoubleExtra("PERSONAL_INSURANCE_DEDUCTIBLE", 0.0);
        medicalInsuranceDeductible = intent.getDoubleExtra("MEDICAL_INSURANCE_DEDUCTIBLE", 0.0);
        travelInsuranceDeductible = intent.getDoubleExtra("TRAVEL_INSURANCE_DEDUCTIBLE", 0.0);
        otherInsuranceDeductible = intent.getDoubleExtra("OTHER_INSURANCE_DEDUCTIBLE", 0.0);
        totalDeductibles = intent.getDoubleExtra("TOTAL_DEDUCTIBLES", 0.0);
        lifeInsurance = intent.getDoubleExtra("LIFE_INSURANCE", 0.0);
        motorInsurance = intent.getDoubleExtra("MOTOR_INSURANCE", 0.0);
        personalInsurance = intent.getDoubleExtra("PERSONAL_INSURANCE", 0.0);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromInsurance4ToInsurance3FMT(v);
            }
        });

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalInsurance();
                navigationFromInsurance4ToInsurance5FMT(v);
            }
        });
    }

    // Method to calculate total insurance
    private void calculateTotalInsurance() {
        // Get values from EditText fields
        double medicalInsurance = parseDouble(medicalInsuranceLabel.getText().toString());
        double travelInsurance = parseDouble(travelInsuranceLabel.getText().toString());
        double otherInsurance = parseDouble(otherInsuranceLabel.getText().toString());

        // Calculate total insurance
        double totalInsurance = lifeInsurance + motorInsurance + personalInsurance +
                medicalInsurance + travelInsurance + otherInsurance;

        // Display the result
        totalInsuranceLabel.setText(String.format("RM %.2f", totalInsurance));

        // Pass data to P5_Insurance5FMT
        Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance5FMT.class);
        intent.putExtra("LIFE_INSURANCE_DEDUCTIBLE", lifeInsuranceDeductible);
        intent.putExtra("MOTOR_INSURANCE_DEDUCTIBLE", motorInsuranceDeductible);
        intent.putExtra("PERSONAL_INSURANCE_DEDUCTIBLE", personalInsuranceDeductible);
        intent.putExtra("MEDICAL_INSURANCE_DEDUCTIBLE", medicalInsuranceDeductible);
        intent.putExtra("TRAVEL_INSURANCE_DEDUCTIBLE", travelInsuranceDeductible);
        intent.putExtra("OTHER_INSURANCE_DEDUCTIBLE", otherInsuranceDeductible);
        intent.putExtra("LIFE_INSURANCE_COST", lifeInsurance);
        intent.putExtra("MOTOR_INSURANCE_COST", motorInsurance);
        intent.putExtra("PERSONAL_INSURANCE_COST", personalInsurance);
        intent.putExtra("MEDICAL_INSURANCE_COST", medicalInsurance);
        intent.putExtra("TRAVEL_INSURANCE_COST", travelInsurance);
        intent.putExtra("OTHER_INSURANCE_COST", otherInsurance);
        intent.putExtra("TOTAL_DEDUCTIBLES", totalDeductibles);
        intent.putExtra("TOTAL_INSURANCE", totalInsurance);
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

    public void navigationFromInsurance4ToInsurance5FMT(View view) {
        Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance5FMT.class);
        startActivity(intent);
    }

    public void navigationFromInsurance4ToInsurance3FMT(View view) {
        Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance3FMT.class);
        startActivity(intent);
    }
}