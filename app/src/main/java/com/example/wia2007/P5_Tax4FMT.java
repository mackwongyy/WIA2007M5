package com.example.wia2007;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class P5_Tax4FMT extends AppCompatActivity {
    private EditText roadTaxLabel;
    private EditText propertyTaxLabel;
    private EditText quitRentLabel;
    private EditText otherTaxLabel;
    private Button backButton;
    private Button applyButton;

    private TaxData userIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax4fmt);

        roadTaxLabel = findViewById(R.id.medicalInsuranceDeductiblesLabel);
        propertyTaxLabel = findViewById(R.id.lifeInsuranceDeductiblesLabel);
        quitRentLabel = findViewById(R.id.otherInsuranceDeductiblesLabel);
        otherTaxLabel = findViewById(R.id.totalDeductiblesLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

        // Retrieve the UserIncomeCalculation object from the previous activity
        Intent intent = getIntent();
        userIncome = (TaxData) intent.getSerializableExtra("userIncome");

        if (userIncome == null) {
            Toast.makeText(this, "Error: No income data found.", Toast.LENGTH_SHORT).show();
            finish();
        }

        backButton.setOnClickListener(v -> {
            navigationFromTax4FMTToTax3FMT(v);
        });

        applyButton.setOnClickListener(v -> {
            saveAdditionalTaxes();
            navigationFromTax4FMTToTax5FMT(v);
        });
    }

    private void saveAdditionalTaxes() {
        String roadTaxStr = roadTaxLabel.getText().toString();
        String propertyTaxStr = propertyTaxLabel.getText().toString();
        String quitRentStr = quitRentLabel.getText().toString();
        String otherTaxStr = otherTaxLabel.getText().toString();

        /*if (roadTaxStr.isEmpty() || propertyTaxStr.isEmpty() || quitRentStr.isEmpty() || otherTaxStr.isEmpty()) {
            Toast.makeText(this, "Please enter all tax values.", Toast.LENGTH_SHORT).show();
            return;
        }*/

        double roadTax = Double.parseDouble(roadTaxStr);
        double propertyTax = Double.parseDouble(propertyTaxStr);
        double quitRent = Double.parseDouble(quitRentStr);
        double otherTax = Double.parseDouble(otherTaxStr);

        // Set the additional tax values in the UserIncomeCalculation object
        userIncome.setRoadTax(roadTax);
        userIncome.setPropertyTax(propertyTax);
        userIncome.setQuitRent(quitRent);
        userIncome.setOtherTax(otherTax);

        // Display a success message
        Toast.makeText(this, "All taxes saved successfully.", Toast.LENGTH_SHORT).show();
    }

    public void navigationFromTax4FMTToTax3FMT(View view) {
        Intent intent = new Intent(P5_Tax4FMT.this, P5_Tax3FMT.class);
        intent.putExtra("userIncome", userIncome); // Pass the UserIncomeCalculation object back
        startActivity(intent);
    }

    public void navigationFromTax4FMTToTax5FMT(View view) {
        Intent intent = new Intent(P5_Tax4FMT.this, P5_Tax5FMT.class);
        intent.putExtra("userIncome", userIncome);
        startActivity(intent);
    }
}