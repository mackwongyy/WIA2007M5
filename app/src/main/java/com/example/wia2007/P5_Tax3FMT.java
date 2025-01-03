package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Tax3FMT extends AppCompatActivity {
    private EditText monthlyTaxDeductionLabel;
    private EditText takafulZakatLabel;
    private TextView incomeTaxPayableLabel;
    private Button backButton;
    private Button applyButton;
    private TaxDatabaseHelper taxDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax3fmt);

        taxDatabaseHelper = new TaxDatabaseHelper(this);

        monthlyTaxDeductionLabel = findViewById(R.id.monthlyTaxDeductionLabel);
        takafulZakatLabel = findViewById(R.id.takafulZakatLabel);
        incomeTaxPayableLabel = findViewById(R.id.incomeTaxPayableLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        applyButton.setOnClickListener(v -> {
            calculateIncomeTaxPayable();
            navigationFromTax3FMTToTax4FMT(v);
        });

        backButton.setOnClickListener(v -> {
            navigationFromTax3FMTToTax2FMT(v);
        });
    }

    private void calculateIncomeTaxPayable() {
        String monthlyTaxDeductionStr = monthlyTaxDeductionLabel.getText().toString();
        String takafulZakatStr = takafulZakatLabel.getText().toString();

        if (monthlyTaxDeductionStr.isEmpty() || takafulZakatStr.isEmpty()) {
            return;
        }

        double monthlyTaxDeduction = Double.parseDouble(monthlyTaxDeductionStr);
        double annualTaxDeduction = monthlyTaxDeduction * 12;
        double takafulZakat = Double.parseDouble(takafulZakatStr);

        // Update the database
        taxDatabaseHelper.updateTaxData(null, null, monthlyTaxDeduction, takafulZakat, null, null, null, null, null);

        // Display the income tax payable
        incomeTaxPayableLabel.setText(String.format("%.2f", annualTaxDeduction + takafulZakat));
    }

    public void navigationFromTax3FMTToTax2FMT(View view) {
        Intent intent = new Intent(P5_Tax3FMT.this, P5_Tax2FMT.class);
        startActivity(intent);
    }

    public void navigationFromTax3FMTToTax4FMT(View view) {
        Intent intent = new Intent(P5_Tax3FMT.this, P5_Tax4FMT.class);
        startActivity(intent);
    }
}