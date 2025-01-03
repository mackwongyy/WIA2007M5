package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_SavingsGuide3FMT extends AppCompatActivity {

    private TextView numberOfMonthsTextView;
    private Button homeButton;
    private Button backButton;
    private SavingsDatabaseHelper savingsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_savingsguide3fmt);

        savingsDatabaseHelper = new SavingsDatabaseHelper(this);

        // Initialize UI elements
        numberOfMonthsTextView = findViewById(R.id.numberOfMonths);
        homeButton = findViewById(R.id.homeButton);
        backButton = findViewById(R.id.backButton);

        // Retrieve values from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            float aggressivenessValue = extras.getFloat("aggressivenessValue", 0.01f);
            double savingsTarget = extras.getDouble("savingsTarget", 0);
            double positiveCashFlow = extras.getDouble("positiveCashFlow", 0);

            // Calculate the number of months
            int numberOfMonths = (int) Math.ceil(savingsTarget / positiveCashFlow);

            // Display the result
            numberOfMonthsTextView.setText(String.valueOf(numberOfMonths));

            // Save the number of months to the database
            savingsDatabaseHelper.insertSavingsData(0, 0, 0, 0, savingsTarget, aggressivenessValue, positiveCashFlow, numberOfMonths);
        }

        // Set up the home button listener
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(P5_SavingsGuide3FMT.this, P5_HomepageFMT.class);
            startActivity(intent);
            finish(); // Close the current activity
        });

        // Set up the back button listener
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(P5_SavingsGuide3FMT.this, P5_SavingsGuide2FMT.class);
            startActivity(intent);
            finish(); // Close the current activity
        });
    }
}