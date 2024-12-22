package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity7 extends AppCompatActivity {
    private TextView monthName, expensesLabel, incomeLabel, expensesValue, incomeValue, netProfitValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        monthName = findViewById(R.id.month_name);
        expensesLabel = findViewById(R.id.expenses_label);
        incomeLabel = findViewById(R.id.income_label);
        expensesValue = findViewById(R.id.expenses_value);
        incomeValue = findViewById(R.id.income_value);
        netProfitValue = findViewById(R.id.net_profit_value);

        // Get the selected month from the intent
        String selectedMonthID = getIntent().getStringExtra("selectedMonthID");
        updateFinancialData(selectedMonthID);

        // Find the backButton
        Button backButton = findViewById(R.id.backButton);

        // SetOnClickListener for backButton to navigate to MainActivity6
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, MainActivity6.class);
                startActivity(intent);
            }
        });
    }

    private void updateFinancialData(String selectedMonthID) {
        FinancialData data = FinancialDataManager.financialDataMap.get(selectedMonthID);
        if (data != null) {
            monthName.setText(data.monthName);
            expensesValue.setText("RM" + data.monthExpenses);
            incomeValue.setText("RM" + data.monthIncome);
            netProfitValue.setText("RM" + data.monthNetProfit);

            // More details can be added
        }
    }
}