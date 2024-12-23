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

public class P5_SortDFAP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Initialize UI elements
        Button applyButton = findViewById(R.id.applyButton);
        EditText nameEditText = findViewById(R.id.NameEditText3);
        EditText minAmountEditText = findViewById(R.id.minAmountEditText3);
        EditText maxAmountEditText = findViewById(R.id.maxAmountEditText3);
        EditText minSlotsEditText = findViewById(R.id.minSlotsEditText3);
        EditText maxSlotsEditText = findViewById(R.id.maxSlotsEditText3);
        EditText datelineEditText = findViewById(R.id.dateline);

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
                financialAidList.add(new FinancialAid(2, "Wesley Foundation Aid", 10000, 1, "15/12/2024"));
                financialAidList.add(new FinancialAid(3, "Hua Chai Aid", 15000, 2, "13/12/2024"));
                financialAidList.add(new FinancialAid(4, "Ahmad and Co Fund", 20000, 2, "06/12/2024"));
                financialAidList.add(new FinancialAid(5, "Rodrigo Corporation Fund", 1500, 1, "01/01/2025"));

                // Compare user inputs with FinancialAid objects
                boolean matchFound = false;
                for (FinancialAid aid : financialAidList) {
                    if (aid.getAidName().equals(name) && aid.getAidAmount() >= minAmount &&
                            aid.getAidAmount() <= maxAmount &&
                            aid.getAidSlots() >= minSlots && aid.getAidSlots() <= maxSlots &&
                            aid.getAidDateline().equals(dateline)) {
                        matchFound = true;
                        break;
                    }
                }

                //Show result to the user
                if (matchFound) {
                    Toast.makeText(P5_SortDFAP.this, "Match found!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(P5_SortDFAP.this, "No match found.", Toast.LENGTH_LONG).show();
                }

                // Navigate to the next activity if needed
                navigationFromSortDFAPToDFAP(v);
            }
        });
    }

    public void navigationFromSortDFAPToDFAP(View view) {
        Intent intent = new Intent(P5_SortDFAP.this, P5_DFAP.class);
        startActivity(intent);
    }
}