package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance1FMT extends AppCompatActivity {

    private EditText lifeInsuranceDeductibleLabel;
    private EditText motorInsuranceDeductibleLabel;
    private EditText personalInsuranceDeductibleLabel;
    private Button applyButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance1fmt);

        // Initialize views
        lifeInsuranceDeductibleLabel = findViewById(R.id.medicalInsuranceDeductiblesLabel);
        motorInsuranceDeductibleLabel = findViewById(R.id.lifeInsuranceDeductiblesLabel);
        personalInsuranceDeductibleLabel = findViewById(R.id.personalInsuranceDeductibleLabel);
        applyButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText fields
                double lifeInsurance = parseDouble(lifeInsuranceDeductibleLabel.getText().toString());
                double motorInsurance = parseDouble(motorInsuranceDeductibleLabel.getText().toString());
                double personalInsurance = parseDouble(personalInsuranceDeductibleLabel.getText().toString());

                // Navigate to P5_Insurance2FMT and pass the values
                navigationFromInsurance1FMTToInsurance2FMT(lifeInsurance, motorInsurance, personalInsurance);
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the homepage
                Intent intent = new Intent(P5_Insurance1FMT.this, P5_HomepageFMT.class);
                startActivity(intent);
            }
        });
    }

    // Method to navigate to P5_Insurance2FMT and pass data
    private void navigationFromInsurance1FMTToInsurance2FMT(double lifeInsurance, double motorInsurance, double personalInsurance) {
        Intent intent = new Intent(P5_Insurance1FMT.this, P5_Insurance2FMT.class);
        intent.putExtra("LIFE_INSURANCE_DEDUCTIBLE", lifeInsurance);
        intent.putExtra("MOTOR_INSURANCE_DEDUCTIBLE", motorInsurance);
        intent.putExtra("PERSONAL_INSURANCE_DEDUCTIBLE", personalInsurance);
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