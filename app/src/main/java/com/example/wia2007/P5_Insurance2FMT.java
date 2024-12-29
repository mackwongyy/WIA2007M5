package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "InsurancePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance2fmt);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize views
        medicalInsuranceDeductibleLabel = findViewById(R.id.medicalInsuranceDeductibleLabel);
        travelInsuranceDeductibleLabel = findViewById(R.id.travelInsuranceDeductibleLabel);
        otherInsuranceDeductibleLabel = findViewById(R.id.otherInsuranceDeductibleLabel);
        totalDeductiblesLabel = findViewById(R.id.totalDeductiblesLabel);
        applyButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        // Load saved data
        medicalInsuranceDeductibleLabel.setText(sharedPreferences.getString("medicalInsurance", ""));
        travelInsuranceDeductibleLabel.setText(sharedPreferences.getString("travelInsurance", ""));
        otherInsuranceDeductibleLabel.setText(sharedPreferences.getString("otherInsurance", ""));

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText fields
                double lifeInsurance = parseDouble(sharedPreferences.getString("lifeInsurance", "0.0"));
                double motorInsurance = parseDouble(sharedPreferences.getString("motorInsurance", "0.0"));
                double personalInsurance = parseDouble(sharedPreferences.getString("personalInsurance", "0.0"));
                double medicalInsurance = parseDouble(medicalInsuranceDeductibleLabel.getText().toString());
                double travelInsurance = parseDouble(travelInsuranceDeductibleLabel.getText().toString());
                double otherInsurance = parseDouble(otherInsuranceDeductibleLabel.getText().toString());

                // Calculate total deductibles
                double totalDeductibles = lifeInsurance + motorInsurance + personalInsurance +
                        medicalInsurance + travelInsurance + otherInsurance;

                // Display the result
                totalDeductiblesLabel.setText(String.format("RM %.2f", totalDeductibles));

                // Save data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("medicalInsurance", medicalInsuranceDeductibleLabel.getText().toString());
                editor.putString("travelInsurance", travelInsuranceDeductibleLabel.getText().toString());
                editor.putString("otherInsurance", otherInsuranceDeductibleLabel.getText().toString());
                editor.apply();

                // Navigate to the next activity
                Intent intent = new Intent(P5_Insurance2FMT.this, P5_Insurance3FMT.class);
                startActivity(intent);
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_Insurance2FMT.this, P5_Insurance1FMT.class);
                startActivity(intent);
            }
        });
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