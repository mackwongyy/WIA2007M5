package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "InsurancePrefs";
    private InsuranceDatabaseHelper insuranceDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance4fmt);

        insuranceDatabaseHelper = new InsuranceDatabaseHelper(this);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize views
        medicalInsuranceLabel = findViewById(R.id.medicalInsuranceLabel);
        travelInsuranceLabel = findViewById(R.id.travelInsuranceLabel);
        otherInsuranceLabel = findViewById(R.id.otherInsuranceLabel);
        totalInsuranceLabel = findViewById(R.id.totalInsuranceLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        // Load saved data
        medicalInsuranceLabel.setText(sharedPreferences.getString("medicalInsuranceCost", ""));
        travelInsuranceLabel.setText(sharedPreferences.getString("travelInsuranceCost", ""));
        otherInsuranceLabel.setText(sharedPreferences.getString("otherInsuranceCost", ""));

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("medicalInsuranceCost", medicalInsuranceLabel.getText().toString());
                editor.putString("travelInsuranceCost", travelInsuranceLabel.getText().toString());
                editor.putString("otherInsuranceCost", otherInsuranceLabel.getText().toString());
                editor.apply();

                double medicalInsurance = parseDouble(medicalInsuranceLabel.getText().toString());
                double travelInsurance = parseDouble(travelInsuranceLabel.getText().toString());
                double otherInsurance = parseDouble(otherInsuranceLabel.getText().toString());

                // Insert data into the database
                insuranceDatabaseHelper.insertInsuranceData(0, 0, 0, 0, 0, 0, 0, 0, 0, medicalInsurance, travelInsurance, otherInsurance);

                // Navigate to the next activity
                Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance5FMT.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance3FMT.class);
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