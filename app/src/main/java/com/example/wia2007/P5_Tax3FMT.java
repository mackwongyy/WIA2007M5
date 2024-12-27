package com.example.wia2007;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class P5_Tax3FMT extends AppCompatActivity {
    private EditText monthlyTaxDeductionLabel;
    private EditText takafulZakatLabel;
    private TextView incomeTaxPayableLabel;
    private Button backButton;
    private Button applyButton;

    private TaxData userIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax3fmt);

        monthlyTaxDeductionLabel = findViewById(R.id.medicalInsuranceDeductiblesLabel);
        takafulZakatLabel = findViewById(R.id.lifeInsuranceDeductiblesLabel);
        incomeTaxPayableLabel = findViewById(R.id.insurancePayableLabel);
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
            navigationFromTax3FMTToTax2FMT(v);
        });

        applyButton.setOnClickListener(v -> {
            calculateIncomeTaxPayable();
            navigationFromTax3FMTToTax4FMT(v);
        });
    }

    private void calculateIncomeTaxPayable() {
        String monthlyTaxDeductionStr = monthlyTaxDeductionLabel.getText().toString();
        String takafulZakatStr = takafulZakatLabel.getText().toString();

        /*if (monthlyTaxDeductionStr.isEmpty() || takafulZakatStr.isEmpty()) {
            Toast.makeText(this, "Please enter both monthly tax deduction and takaful/zakat.", Toast.LENGTH_SHORT).show();
            return;
        }*/

        double monthlyTaxDeduction = Double.parseDouble(monthlyTaxDeductionStr);
        double takafulZakat = Double.parseDouble(takafulZakatStr);

        // Set the monthly tax deduction and takaful/zakat in the UserIncomeCalculation object
        userIncome.setMonthlyTaxDeduction(monthlyTaxDeduction);
        userIncome.setTakafulZakat(takafulZakat);

        // Display the income tax payable
        double incomeTaxPayable = Math.max(userIncome.getIncomeTaxPayable(), 0);
        incomeTaxPayableLabel.setText(String.format("%.2f", incomeTaxPayable));
    }

    public void navigationFromTax3FMTToTax2FMT(View view) {
        Intent intent = new Intent(P5_Tax3FMT.this, P5_Tax2FMT.class);
        intent.putExtra("userIncome", userIncome); // Pass the UserIncomeCalculation object back
        startActivity(intent);
    }

    public void navigationFromTax3FMTToTax4FMT(View view) {
        Intent intent = new Intent(P5_Tax3FMT.this, P5_Tax4FMT.class);
        intent.putExtra("userIncome", userIncome); // Pass the UserIncomeCalculation object back
        startActivity(intent);
    }
}