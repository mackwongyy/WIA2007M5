package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "InsurancePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_insurance3fmt);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize views
        lifeInsuranceLabel = findViewById(R.id.lifeInsuranceLabel);
        motorInsuranceLabel = findViewById(R.id.motorInsuranceLabel);
        personalInsuranceLabel = findViewById(R.id.personalInsuranceLabel);
        applyButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        // Load saved data
        lifeInsuranceLabel.setText(sharedPreferences.getString("lifeInsuranceCost", ""));
        motorInsuranceLabel.setText(sharedPreferences.getString("motorInsuranceCost", ""));
        personalInsuranceLabel.setText(sharedPreferences.getString("personalInsuranceCost", ""));

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lifeInsuranceCost", lifeInsuranceLabel.getText().toString());
                editor.putString("motorInsuranceCost", motorInsuranceLabel.getText().toString());
                editor.putString("personalInsuranceCost", personalInsuranceLabel.getText().toString());
                editor.apply();

                // Navigate to the next activity
                Intent intent = new Intent(P5_Insurance3FMT.this, P5_Insurance4FMT.class);
                startActivity(intent);
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_Insurance3FMT.this, P5_Insurance2FMT.class);
                startActivity(intent);
            }
        });
    }
}