package com.example.wia2007;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class P5_Tax1FMT extends AppCompatActivity {
    private EditText income;
    private EditText taxRelief;
    private TextView chargeableIncome;
    private Button backButton;
    private Button applyButton;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "TaxPrefs";
    private static final String KEY_TOTAL_INCOME = "totalIncome";
    private static final String KEY_TAX_RELIEF = "taxRelief";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_tax1fmt);

        income = findViewById(R.id.totalIncomeLabel);
        taxRelief = findViewById(R.id.taxReliefLabel);
        chargeableIncome = findViewById(R.id.chargeableIncomeLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load the last saved inputs
        loadLastInputs();

        backButton.setOnClickListener(v -> {
            navigationFromTax1FMTToHomepageFMT(v);
        });

        applyButton.setOnClickListener(v -> {
            calculateChargeableIncome();
        });
    }

    private void loadLastInputs() {
        // Load the last saved total income and tax relief
        String lastTotalIncome = sharedPreferences.getString(KEY_TOTAL_INCOME, "");
        String lastTaxRelief = sharedPreferences.getString(KEY_TAX_RELIEF, "");

        // Set the loaded values to the EditText fields
        income.setText(lastTotalIncome);
        taxRelief.setText(lastTaxRelief);
    }

    private void saveInputs(double totalIncome, double taxRelief) {
        // Save the current inputs to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOTAL_INCOME, String.valueOf(totalIncome));
        editor.putString(KEY_TAX_RELIEF, String.valueOf(taxRelief));
        editor.apply();
    }

    private void calculateChargeableIncome() {
        String totalIncomeStr = income.getText().toString();
        String taxReliefStr = taxRelief.getText().toString();
        /*if (totalIncomeStr.isEmpty() || taxReliefStr.isEmpty()) {
            Toast.makeText(this, "Please enter both total income and tax relief.", Toast.LENGTH_SHORT).show();
            return;
        }*/

        double totalIncome = Double.parseDouble(totalIncomeStr);
        double taxReliefAmount = Double.parseDouble(taxReliefStr);
        double chargeable = totalIncome - taxReliefAmount;
        chargeableIncome.setText(String.format("%.2f", chargeable));

        // Save the inputs to SharedPreferences
        saveInputs(totalIncome, taxReliefAmount);

        // Pass the calculated values to the next activity
        Intent intent = new Intent(P5_Tax1FMT.this, P5_Tax2FMT.class);
        intent.putExtra("totalIncome", totalIncome);
        intent.putExtra("taxRelief", taxReliefAmount);
        intent.putExtra("chargeableIncome", chargeable);
        startActivity(intent);
    }

    public void navigationFromTax1FMTToHomepageFMT(View view) {
        Intent intent = new Intent(P5_Tax1FMT.this, P5_HomepageFMT.class);
        startActivity(intent);
    }
}