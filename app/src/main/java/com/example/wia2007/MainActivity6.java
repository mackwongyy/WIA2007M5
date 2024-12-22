package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {
    private Spinner monthSpinner;
    private TextView expensesLabel, incomeLabel, expensesValue, incomeValue, netProfitValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        monthSpinner = findViewById(R.id.spinner);
        expensesLabel = findViewById(R.id.expenses_label);
        incomeLabel = findViewById(R.id.income_label);
        expensesValue = findViewById(R.id.expenses_value);
        incomeValue = findViewById(R.id.income_value);
        netProfitValue = findViewById(R.id.net_profit_value);

        // Populate the spinner with month IDs
        String[] financialAidList = FinancialDataManager.financialDataMap.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, financialAidList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMonthID = (String) parent.getItemAtPosition(position);
                updateFinancialData(selectedMonthID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Find the Calendar button
        Button backButton = findViewById(R.id.calendarButton);

        // Set OnClickListener for Calendar button to navigate back to MainActivity5
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6.this, MainActivity5.class);
                startActivity(intent);
            }
        });
    }

    private void updateFinancialData(String selectedMonthID) {
        FinancialData data = FinancialDataManager.financialDataMap.get(selectedMonthID);
        if (data != null) {
            expensesValue.setText("RM" + data.monthExpenses);
            incomeValue.setText("RM" + data.monthIncome);
            netProfitValue.setText("RM" + data.monthNetProfit);
        }
    }
}