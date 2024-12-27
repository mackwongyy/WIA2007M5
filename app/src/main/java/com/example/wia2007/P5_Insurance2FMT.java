package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance2FMT extends AppCompatActivity {

    private EditText medicalInsuranceDeductibleLabel;
    private EditText travelInsuranceDeductibleLabel;
    private EditText otherInsuranceDeductibleLabel;
    private TextView totalDeductiblesLabel;
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
        setContentView(R.layout.p5_insurance2fmt);

        // Initialize views
        medicalInsuranceDeductibleLabel = findViewById(R.id.medicalInsuranceDeductibleLabel);
        travelInsuranceDeductibleLabel = findViewById(R.id.travelInsuranceDeductibleLabel);
        otherInsuranceDeductibleLabel = findViewById(R.id.otherInsuranceDeductibleLabel);
        totalDeductiblesLabel = findViewById(R.id.totalDeductiblesLabel);
        applyButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        // Retrieve data passed from P5_Insurance1FMT
        Intent intent = getIntent();
        lifeInsuranceDeductible = intent.getDoubleExtra("LIFE_INSURANCE", 0.0);
        motorInsuranceDeductible = intent.getDoubleExtra("MOTOR_INSURANCE", 0.0);
        personalInsuranceDeductible = intent.getDoubleExtra("PERSONAL_INSURANCE", 0.0);

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalDeductibles();
                navigationFromInsurance2FMTToInsurance3FMT(v);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromInsurance2FMTToInsurance1FMT(v);
            }
        });
    }

    // Method to calculate total deductibles
    private void calculateTotalDeductibles() {
        medicalInsuranceDeductible = parseDouble(medicalInsuranceDeductibleLabel.getText().toString());
        travelInsuranceDeductible = parseDouble(travelInsuranceDeductibleLabel.getText().toString());
        otherInsuranceDeductible = parseDouble(otherInsuranceDeductibleLabel.getText().toString());

        // Calculate total deductibles
        double totalDeductibles = lifeInsuranceDeductible + motorInsuranceDeductible + personalInsuranceDeductible +
                medicalInsuranceDeductible + travelInsuranceDeductible + otherInsuranceDeductible;

        // Display the result
        totalDeductiblesLabel.setText(String.format("RM %.2f", totalDeductibles));
    }

    // Utility method to parse double values
    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public void navigationFromInsurance2FMTToInsurance3FMT(View view) {
        Intent intent = new Intent(P5_Insurance2FMT.this, P5_Insurance3FMT.class);
        intent.putExtra("LIFE_INSURANCE_DEDUCTIBLE", lifeInsuranceDeductible);
        intent.putExtra("MOTOR_INSURANCE_DEDUCTIBLE", motorInsuranceDeductible);
        intent.putExtra("PERSONAL_INSURANCE_DEDUCTIBLE", personalInsuranceDeductible);
        intent.putExtra("MEDICAL_INSURANCE_DEDUCTIBLE", medicalInsuranceDeductible);
        intent.putExtra("TRAVEL_INSURANCE_DEDUCTIBLE", travelInsuranceDeductible);
        intent.putExtra("OTHER_INSURANCE_DEDUCTIBLE", otherInsuranceDeductible);
        startActivity(intent);
    }

    public void navigationFromInsurance2FMTToInsurance1FMT(View view) {
        Intent intent = new Intent(P5_Insurance2FMT.this, P5_Insurance1FMT.class);
        startActivity(intent);
    }
}