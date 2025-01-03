package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class P5_SavingsGuide4FMT extends AppCompatActivity {

    private EditText numberOfMonthsSelectedLabel;
    private TextView achievableLabel;
    private TextView descriptionLabel;
    private TextView amountLabel;
    private TextView amountDescription;
    private Button backButton;
    private Button homeButton;
    private SavingsDatabaseHelper savingsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_savingsguide4fmt);

        savingsDatabaseHelper = new SavingsDatabaseHelper(this);

        // Initialize UI elements
        numberOfMonthsSelectedLabel = findViewById(R.id.numberOfMonthsSelectedLabel);
        achievableLabel = findViewById(R.id.achievableLabel);
        descriptionLabel = findViewById(R.id.descriptionLabel);
        amountLabel = findViewById(R.id.amountLabel);
        amountDescription = findViewById(R.id.amountDescription);
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Retrieve values from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double savingsTarget = extras.getDouble("savingsTarget", 0);
            double positiveCashFlow = extras.getDouble("positiveCashFlow", 0);
            float aggressivenessValue = extras.getFloat("aggressivenessValue", 0.01f);

            // Retrieve income, expenses, insurance, and tax from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserInput", MODE_PRIVATE);
            float income = sharedPreferences.getFloat("income", 0);
            float expenses = sharedPreferences.getFloat("expenses", 0);
            float insurance = sharedPreferences.getFloat("insurance", 0);
            float tax = sharedPreferences.getFloat("tax", 0);

            // Set up the apply button listener
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculateAdditionalAmount(savingsTarget, positiveCashFlow, aggressivenessValue, income, expenses, insurance, tax);
                }
            });
        }

        // Set up the back button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_SavingsGuide4FMT.this, P5_SavingsGuide2FMT.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }

    private void calculateAdditionalAmount(double savingsTarget, double positiveCashFlow, float aggressivenessValue, float income, float expenses, float insurance, float tax) {
        // Retrieve the number of months
        String numberOfMonthsStr = numberOfMonthsSelectedLabel.getText().toString();
        if (numberOfMonthsStr.isEmpty()) {
            Toast.makeText(this, "Please input the number of months.", Toast.LENGTH_LONG).show();
            return;
        }

        int numberOfMonths;
        try {
            numberOfMonths = Integer.parseInt(numberOfMonthsStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number of months. Please enter a valid number.", Toast.LENGTH_LONG).show();
            return;
        }

        // Calculate the total savings possible in the given number of months
        double totalSavings = (positiveCashFlow * (1 / aggressivenessValue)) * numberOfMonths;

        // Check if the savings target is achievable
        if (totalSavings >= savingsTarget) {
            achievableLabel.setText("Yes");
            descriptionLabel.setText("Congratulations! You're on course of\nachieving your savings target!\nKeep it up and you'll edge\ncloser to the goal of\nSDG 2: Zero Hunger!");
            amountLabel.setText(""); // Clear the amount label
            amountDescription.setText(""); // Clear the amount description
        } else {
            // Calculate the additional amount needed
            double additionalAmount = (savingsTarget - totalSavings) / numberOfMonths;

            if (additionalAmount <= 0) {
                // If additionalAmount is zero or negative, the target is achievable
                achievableLabel.setText("Yes");
                descriptionLabel.setText("Congratulations! You're on course of\nachieving your savings target!\nKeep it up and you'll edge\ncloser to the goal of\nSDG 2: Zero Hunger!");
                amountLabel.setText(""); // Clear the amount label
                amountDescription.setText(""); // Clear the amount description
            } else {
                achievableLabel.setText("No");
                descriptionLabel.setText("You're not on course of\nachieving your savings target.\nYou need to save up ");
                amountLabel.setText(String.format("RM %.2f", additionalAmount));
                amountDescription.setText(" more to reach your savings target! Keep it up and you can do it.");
            }
        }

        // Save the additional amount to the database
        savingsDatabaseHelper.insertSavingsData(income, expenses, insurance, tax, savingsTarget, aggressivenessValue, positiveCashFlow, numberOfMonths);
    }
}