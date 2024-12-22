package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {

    // Declare TextViews for displaying financial data
    private TextView financialDashboardTextView;
    private TextView expensesValueTextView;
    private TextView incomeValueTextView;
    private TextView netProfitValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // Initialize TextViews
        financialDashboardTextView = findViewById(R.id.financial_dashboard);
        expensesValueTextView = findViewById(R.id.expenses_value);
        incomeValueTextView = findViewById(R.id.income_value);
        netProfitValueTextView = findViewById(R.id.net_profit_value);

        // Fetch the current month's financial data (e.g., "11/2024")
        String currentMonthID = "11/2024"; // Replace with dynamic logic if needed
        FinancialData currentMonthData = FinancialDataManager.financialDataMap.get(currentMonthID);

        // Update the TextViews with the fetched data
        if (currentMonthData != null) {
            financialDashboardTextView.setText("Financial Dashboard - " + currentMonthData.monthName);
            expensesValueTextView.setText("Expenses: RM" + currentMonthData.monthExpenses);
            incomeValueTextView.setText("Income: RM" + currentMonthData.monthIncome);
            netProfitValueTextView.setText("Net Profit: RM" + currentMonthData.monthNetProfit);
        } else {
            // Handle case where data is not available
            financialDashboardTextView.setText("Financial Dashboard - No Data");
            expensesValueTextView.setText("Expenses: RM0");
            incomeValueTextView.setText("Income: RM0");
            netProfitValueTextView.setText("Net Profit: RM0");
        }

        // Find the backButton and discoverMoreButton
        Button backButton = findViewById(R.id.backButton);
        Button discoverMoreButton = findViewById(R.id.discoverMoreButton);

        // Set OnClickListener for backButton to navigate to MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity(v);
            }
        });

        // Set OnClickListener for discoverMoreButton to navigate to MainActivity6
        discoverMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity6(v);
            }
        });
    }

    public void navigateToMainActivity(View view) {
        Intent intent = new Intent(MainActivity5.this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToMainActivity6(View view) {
        Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
        startActivity(intent);
    }
}