package com.example.wia2007;

import android.content.Intent;
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

    private double lifeInsuranceDeductible;
    private double motorInsuranceDeductible;
    private double personalInsuranceDeductible;
    private double medicalInsuranceDeductible;
    private double travelInsuranceDeductible;
    private double otherInsuranceDeductible;

    private double lifeInsuranceCost;
    private double motorInsuranceCost;
    private double personalInsuranceCost;
    private double medicalInsuranceCost;
    private double travelInsuranceCost;
    private double otherInsuranceCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance5fmt);

        // Initialize TextViews
        insurancePayableLabel = findViewById(R.id.insurancePayableLabel);
        totalDeductiblesLabel = findViewById(R.id.totalDeductiblesLabel);
        totalInsuranceCostLabel = findViewById(R.id.otherInsuranceCostLabel);

        // Initialize Buttons
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Retrieve data passed from previous activities
        Intent intent = getIntent();
        lifeInsuranceDeductible = intent.getDoubleExtra("LIFE_INSURANCE_DEDUCTIBLE", lifeInsuranceDeductible);
        motorInsuranceDeductible = intent.getDoubleExtra("MOTOR_INSURANCE_DEDUCTIBLE", motorInsuranceDeductible);
        personalInsuranceDeductible = intent.getDoubleExtra("PERSONAL_INSURANCE_DEDUCTIBLE", personalInsuranceDeductible);
        medicalInsuranceDeductible = intent.getDoubleExtra("MEDICAL_INSURANCE_DEDUCTIBLE", medicalInsuranceDeductible);
        travelInsuranceDeductible = intent.getDoubleExtra("TRAVEL_INSURANCE_DEDUCTIBLE", travelInsuranceDeductible);
        otherInsuranceDeductible = intent.getDoubleExtra("OTHER_INSURANCE_DEDUCTIBLE", otherInsuranceDeductible);

        lifeInsuranceCost = intent.getDoubleExtra("LIFE_INSURANCE_COST", lifeInsuranceCost);
        motorInsuranceCost = intent.getDoubleExtra("MOTOR_INSURANCE_COST", motorInsuranceCost);
        personalInsuranceCost = intent.getDoubleExtra("PERSONAL_INSURANCE_COST", personalInsuranceCost);
        medicalInsuranceCost = intent.getDoubleExtra("MEDICAL_INSURANCE_COST", medicalInsuranceCost);
        travelInsuranceCost = intent.getDoubleExtra("TRAVEL_INSURANCE_COST", travelInsuranceCost);
        otherInsuranceCost = intent.getDoubleExtra("OTHER_INSURANCE_COST", otherInsuranceCost);

        // Calculate and display the results
        displayInsuranceSummary();

        // Set up button click listeners
        backButton.setOnClickListener(v -> {
            navigationFromInsurance5FMTToInsurance4FMT(v);
        });

        homeButton.setOnClickListener(v -> {
            navigationFromInsurance5FMTToHomepage(v);
        });
    }

    private void displayInsuranceSummary() {
        // Calculate total deductibles
        double totalDeductibles = lifeInsuranceDeductible + motorInsuranceDeductible + personalInsuranceDeductible +
                medicalInsuranceDeductible + travelInsuranceDeductible + otherInsuranceDeductible;

        // Calculate total insurance cost
        double totalInsuranceCost = lifeInsuranceCost + motorInsuranceCost + personalInsuranceCost +
                medicalInsuranceCost + travelInsuranceCost + otherInsuranceCost;

        // Calculate insurance payable
        double insurancePayable = totalDeductibles + totalInsuranceCost;

        // Display the results
        totalDeductiblesLabel.setText(String.format("RM %.2f", totalDeductibles));
        totalInsuranceCostLabel.setText(String.format("RM %.2f", totalInsuranceCost));
        insurancePayableLabel.setText(String.format("RM %.2f", insurancePayable));

        // Display individual deductibles and insurance costs
        ((TextView) findViewById(R.id.lifeInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", lifeInsuranceDeductible));
        ((TextView) findViewById(R.id.motorInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", motorInsuranceDeductible));
        ((TextView) findViewById(R.id.personalInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", personalInsuranceDeductible));
        ((TextView) findViewById(R.id.medicalInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", medicalInsuranceDeductible));
        ((TextView) findViewById(R.id.travelInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", travelInsuranceDeductible));
        ((TextView) findViewById(R.id.otherInsuranceDeductiblesLabel)).setText(String.format("RM %.2f", otherInsuranceDeductible));
        ((TextView) findViewById(R.id.totalDeductiblesLabel)).setText(String.format("RM %.2f", totalDeductibles));

        ((TextView) findViewById(R.id.lifeInsuranceCostLabel)).setText(String.format("RM %.2f", lifeInsuranceCost));
        ((TextView) findViewById(R.id.motorInsuranceCostLabel)).setText(String.format("RM %.2f", motorInsuranceCost));
        ((TextView) findViewById(R.id.personalInsuranceCostLabel)).setText(String.format("RM %.2f", personalInsuranceCost));
        ((TextView) findViewById(R.id.medicalInsuranceCostLabel)).setText(String.format("RM %.2f", medicalInsuranceCost));
        ((TextView) findViewById(R.id.travelInsuranceCostLabel)).setText(String.format("RM %.2f", travelInsuranceCost));
        ((TextView) findViewById(R.id.otherInsuranceCostLabel)).setText(String.format("RM %.2f", otherInsuranceCost));
        ((TextView) findViewById(R.id.totalInsuranceLabel)).setText(String.format("RM %.2f", totalInsuranceCost));
    }

    public void navigationFromInsurance5FMTToInsurance4FMT(View view) {
        Intent intent = new Intent(P5_Insurance5FMT.this, P5_Insurance4FMT.class);
        startActivity(intent);
    }

    public void navigationFromInsurance5FMTToHomepage(View view) {
        Intent intent = new Intent(P5_Insurance5FMT.this, P5_HomepageFMT.class);
        startActivity(intent);
    }
}