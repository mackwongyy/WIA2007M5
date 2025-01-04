package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class P5_Tax4FMT extends AppCompatActivity {
    private EditText roadTaxLabel;
    private EditText propertyTaxLabel;
    private EditText quitRentLabel;
    private EditText otherTaxLabel;
    private Button backButton;
    private Button applyButton;
    private TaxDatabaseHelper taxDatabaseHelper;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "TaxPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax4fmt);

        taxDatabaseHelper = new TaxDatabaseHelper(this);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        roadTaxLabel = findViewById(R.id.roadTaxLabel);
        propertyTaxLabel = findViewById(R.id.propertyTaxLabel);
        quitRentLabel = findViewById(R.id.quitRentLabel);
        otherTaxLabel = findViewById(R.id.otherTaxLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        // Retrieve saved data and populate EditText fields
        roadTaxLabel.setText(sharedPreferences.getString("roadTax", ""));
        propertyTaxLabel.setText(sharedPreferences.getString("propertyTax", ""));
        quitRentLabel.setText(sharedPreferences.getString("quitRent", ""));
        otherTaxLabel.setText(sharedPreferences.getString("otherTax", ""));

        // Set click listeners for apply and back buttons
        applyButton.setOnClickListener(v -> {
            saveAdditionalTaxes();
            navigationFromTax4FMTToTax5FMT(v);
        });

        backButton.setOnClickListener(v -> {
            navigationFromTax4FMTToTax3FMT(v);
        });
    }

    private void saveAdditionalTaxes() {
        String roadTaxStr = roadTaxLabel.getText().toString();
        String propertyTaxStr = propertyTaxLabel.getText().toString();
        String quitRentStr = quitRentLabel.getText().toString();
        String otherTaxStr = otherTaxLabel.getText().toString();

        if (roadTaxStr.isEmpty() || propertyTaxStr.isEmpty() || quitRentStr.isEmpty() || otherTaxStr.isEmpty()) {
            Toast.makeText(this, "Please enter all additional taxes. Enter 0 for taxes you don't need to pay.", Toast.LENGTH_SHORT).show();
            return;
        }

        double roadTax = Double.parseDouble(roadTaxStr);
        double propertyTax = Double.parseDouble(propertyTaxStr);
        double quitRent = Double.parseDouble(quitRentStr);
        double otherTax = Double.parseDouble(otherTaxStr);

        // Save data to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("roadTax", roadTaxStr);
        editor.putString("propertyTax", propertyTaxStr);
        editor.putString("quitRent", quitRentStr);
        editor.putString("otherTax", otherTaxStr);
        editor.apply();

        // Update the database
        taxDatabaseHelper.updateTaxData(null, null, null, null, roadTax, propertyTax, quitRent, otherTax, null);
    }

    public void navigationFromTax4FMTToTax3FMT(View view) {
        Intent intent = new Intent(P5_Tax4FMT.this, P5_Tax3FMT.class);
        startActivity(intent);
    }

    public void navigationFromTax4FMTToTax5FMT(View view) {
        Intent intent = new Intent(P5_Tax4FMT.this, P5_Tax5FMT.class);
        startActivity(intent);
    }
}