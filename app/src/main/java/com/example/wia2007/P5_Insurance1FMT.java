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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance1fmt);

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
                // Save data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lifeInsurance", lifeInsuranceDeductibleLabel.getText().toString());
                editor.putString("motorInsurance", motorInsuranceDeductibleLabel.getText().toString());
                editor.putString("personalInsurance", personalInsuranceDeductibleLabel.getText().toString());
                editor.apply();

                // Navigate to the next activity
                Intent intent = new Intent(P5_Insurance1FMT.this, P5_Insurance2FMT.class);
                startActivity(intent);
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_Insurance1FMT.this, P5_HomepageFMT.class);
                startActivity(intent);
            }
        });
    }
}