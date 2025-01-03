package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class P5_SavingsGuide1FMT extends AppCompatActivity {
    private EditText expensesLabel;
    private EditText incomeLabel;
    private EditText insuranceLabel;
    private EditText taxLabel;
    private Button backButton;
    private Button applyButton;
    private SavingsDatabaseHelper savingsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_savingsguide1fmt);

        savingsDatabaseHelper = new SavingsDatabaseHelper(this);

        // Initialize UI elements
        expensesLabel = findViewById(R.id.expensesLabel);
        incomeLabel = findViewById(R.id.incomeLabel);
        insuranceLabel = findViewById(R.id.insuranceLabel);
        taxLabel = findViewById(R.id.taxLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

        // Set up the back button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_SavingsGuide1FMT.this, P5_HomepageFMT.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Set up the apply button listener
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromSavingsGuide1FMTToSavingsGuide2FMT(v);
            }
        });
    }

    public void navigationFromSavingsGuide1FMTToSavingsGuide2FMT(View view) {
        // Retrieve income and expenses from the EditText fields
        String incomeStr = incomeLabel.getText().toString();
        String expensesStr = expensesLabel.getText().toString();
        String insuranceStr = insuranceLabel.getText().toString();
        String taxStr = taxLabel.getText().toString();

        if (incomeStr.isEmpty() || expensesStr.isEmpty() || insuranceStr.isEmpty() || taxStr.isEmpty()) {
            Toast.makeText(this, "Please input all of income, expenses, insurance and tax.", Toast.LENGTH_LONG).show();
            return;
        }

        double income = Double.parseDouble(incomeStr);
        double expenses = Double.parseDouble(expensesStr);
        double insurance = Double.parseDouble(insuranceStr);
        double tax = Double.parseDouble(taxStr);

        // Save income and expenses to the database
        savingsDatabaseHelper.insertSavingsData(income, expenses, insurance, tax,0, 0, 0, 0);

        // Navigate to P5_SavingsGuide2FMT
        Intent intent = new Intent(P5_SavingsGuide1FMT.this, P5_SavingsGuide2FMT.class);
        startActivity(intent);
        finish();
    }
}