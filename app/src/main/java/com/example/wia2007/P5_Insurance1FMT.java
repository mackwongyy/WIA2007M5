package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "InsurancePrefs";
    private InsuranceDatabaseHelper insuranceDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance1fmt);

        insuranceDatabaseHelper = new InsuranceDatabaseHelper(this);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize views
        lifeInsuranceDeductibleLabel = findViewById(R.id.roadTaxLabel);
        motorInsuranceDeductibleLabel = findViewById(R.id.lifeInusranceDeductiblesLabel);
        personalInsuranceDeductibleLabel = findViewById(R.id.personalInsuranceDeductibleLabel);
        applyButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        // Load saved data
        lifeInsuranceDeductibleLabel.setText(sharedPreferences.getString("lifeInsurance", ""));
        motorInsuranceDeductibleLabel.setText(sharedPreferences.getString("motorInsurance", ""));
        personalInsuranceDeductibleLabel.setText(sharedPreferences.getString("personalInsurance", ""));

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Parse input values
                double lifeInsuranceDeductible = parseDouble(lifeInsuranceDeductibleLabel.getText().toString());
                double motorInsuranceDeductible = parseDouble(motorInsuranceDeductibleLabel.getText().toString());
                double personalInsuranceDeductible = parseDouble(personalInsuranceDeductibleLabel.getText().toString());

                // Insert data into the database and get the ID of the inserted row
                long id = insuranceDatabaseHelper.insertInsuranceData(lifeInsuranceDeductible, motorInsuranceDeductible, personalInsuranceDeductible, 0, 0, 0, 0, 0, 0, 0, 0, 0);

                Intent intent = new Intent(P5_Insurance1FMT.this, P5_Insurance2FMT.class);
                intent.putExtra("lifeInsuranceDeductible", lifeInsuranceDeductible);
                intent.putExtra("motorInsuranceDeductible", motorInsuranceDeductible);
                intent.putExtra("personalInsuranceDeductible", personalInsuranceDeductible);
                intent.putExtra("id", id); // Pass the ID
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_Insurance1FMT.this, P5_HomepageFMT.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}