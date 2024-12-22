package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private Button applyButton;
    private EditText nameEditText, minAmountEditText, maxAmountEditText, minSlotsEditText, maxSlotsEditText, datelineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Initialize UI elements
        applyButton = findViewById(R.id.applyButton);
        nameEditText = findViewById(R.id.NameEditText3);
        minAmountEditText = findViewById(R.id.minAmountEditText3);
        maxAmountEditText = findViewById(R.id.maxAmountEditText3);
        minSlotsEditText = findViewById(R.id.minSlotsEditText3);
        maxSlotsEditText = findViewById(R.id.maxSlotsEditText3);
        datelineEditText = findViewById(R.id.dateline);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user inputs
                String name = nameEditText.getText().toString();
                int minAmount = Integer.parseInt(minAmountEditText.getText().toString());
                int maxAmount = Integer.parseInt(maxAmountEditText.getText().toString());
                int minSlots = Integer.parseInt(minSlotsEditText.getText().toString());
                int maxSlots = Integer.parseInt(maxSlotsEditText.getText().toString());
                String dateline = datelineEditText.getText().toString();

                // Create a list of FinancialAid objects for comparison
                List<FinancialAid> financialAidList = new ArrayList<>();
                financialAidList.add(new FinancialAid(1, "Mani Group Financial Aid", 500, 2, "02/12/2024"));
                financialAidList.add(new FinancialAid(2, "Wesley Foundation Scholarship", 10000, 1, "15/12/2024"));
                financialAidList.add(new FinancialAid(3, "Hua Chai Scholarship", 15000, 2, "13/12/2024"));
                financialAidList.add(new FinancialAid(4, "Ahmad and Co Fund", 20000, 2, "06/12/2024"));
                financialAidList.add(new FinancialAid(5, "Rodrigo Corporation Scholarship", 1500, 1, "01/01/2025"));

                // Compare user inputs with FinancialAid objects
                boolean matchFound = false;
                for (FinancialAid aid : financialAidList) {
                    if (aid.getAidName().equals(name) &&
                            aid.getAidAmount() >= minAmount && aid.getAidAmount() <= maxAmount &&
                            aid.getAidSlots() >= minSlots && aid.getAidSlots() <= maxSlots &&
                            aid.getAidDateline().equals(dateline)) {
                        matchFound = true;
                        break;
                    }
                }

                // Show result to the user
                if (matchFound) {
                    Toast.makeText(MainActivity3.this, "Match found!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity3.this, "No match found.", Toast.LENGTH_LONG).show();
                }

                // Navigate to the next activity if needed
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}