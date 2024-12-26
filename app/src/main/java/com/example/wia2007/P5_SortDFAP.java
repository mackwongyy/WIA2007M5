package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class P5_SortDFAP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_sortdfap);

        // Initialize UI elements
        Button applyButton = findViewById(R.id.homeButton);
        EditText nameEditText = findViewById(R.id.Amount_Of_Slots);
        EditText minAmountEditText = findViewById(R.id.minAmountEditText3);
        EditText maxAmountEditText = findViewById(R.id.maxAmountEditText3);
        EditText minSlotsEditText = findViewById(R.id.minSlotsEditText3);
        EditText maxSlotsEditText = findViewById(R.id.maxSlotsEditText3);
        EditText datelineEditText = findViewById(R.id.dateline);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get user inputs
                    String name = nameEditText.getText().toString();
                    String minAmountStr = minAmountEditText.getText().toString();
                    String maxAmountStr = maxAmountEditText.getText().toString();
                    String minSlotsStr = minSlotsEditText.getText().toString();
                    String maxSlotsStr = maxSlotsEditText.getText().toString();
                    String dateline = datelineEditText.getText().toString();

                    // Log user inputs
                    Log.d("UserInput", "Name: " + name);
                    Log.d("UserInput", "MinAmount: " + minAmountStr);
                    Log.d("UserInput", "MaxAmount: " + maxAmountStr);
                    Log.d("UserInput", "MinSlots: " + minSlotsStr);
                    Log.d("UserInput", "MaxSlots: " + maxSlotsStr);
                    Log.d("UserInput", "Dateline: " + dateline);

                    // Check if all fields are filled
                    if (name.isEmpty() || minAmountStr.isEmpty() || maxAmountStr.isEmpty() || minSlotsStr.isEmpty() || maxSlotsStr.isEmpty() || dateline.isEmpty()) {
                        Toast.makeText(P5_SortDFAP.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate dateline format (DD/MM/YYYY)
                    if (!dateline.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        Toast.makeText(P5_SortDFAP.this, "Please enter the dateline in DD/MM/YYYY format", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Parse user inputs
                    int minAmount = Integer.parseInt(minAmountStr);
                    int maxAmount = Integer.parseInt(maxAmountStr);
                    int minSlots = Integer.parseInt(minSlotsStr);
                    int maxSlots = Integer.parseInt(maxSlotsStr);

                    // Normalize the dateline input to remove any slashes
                    String normalizedDatelineInput = dateline.replace("/", "");

                    // Get the current state of the financialAidList from FinancialAidManager
                    List<FinancialAid> financialAidList = FinancialAidManager.getFinancialAidList();

                    // Filter the financial aid list based on the criteria
                    List<FinancialAid> filteredList = new ArrayList<>();
                    for (FinancialAid aid : financialAidList) {
                        // Normalize the dateline in the financial aid list to remove any slashes
                        String normalizedAidDateline = aid.getAidDateline().replace("/", "");

                        // Check if the name is a substring of the financial aid name
                        boolean nameMatch = aid.getAidName().toLowerCase().contains(name.toLowerCase());
                        boolean amountMatch = aid.getAidAmount() >= minAmount && aid.getAidAmount() <= maxAmount;
                        boolean slotsMatch = aid.getAidSlots() >= minSlots && aid.getAidSlots() <= maxSlots;
                        boolean datelineMatch = normalizedAidDateline.equals(normalizedDatelineInput);

                        // Log the comparison results
                        Log.d("Comparison", "Aid Name: " + aid.getAidName() + ", Name Match: " + nameMatch);
                        Log.d("Comparison", "Aid Amount: " + aid.getAidAmount() + ", Amount Match: " + amountMatch);
                        Log.d("Comparison", "Aid Slots: " + aid.getAidSlots() + ", Slots Match: " + slotsMatch);
                        Log.d("Comparison", "Aid Dateline: " + aid.getAidDateline() + ", Dateline Match: " + datelineMatch);

                        // Add to filtered list if all criteria match
                        if (nameMatch && amountMatch && slotsMatch && datelineMatch) {
                            filteredList.add(aid);
                        }
                    }

                    // Show result to the user
                    if (!filteredList.isEmpty()) {
                        Toast.makeText(P5_SortDFAP.this, "Match found!", Toast.LENGTH_LONG).show();
                        // Pass the filtered list to P5_DFAP.java
                        navigationToDFAPWithFilteredList(filteredList);
                    } else {
                        Toast.makeText(P5_SortDFAP.this, "No match found.", Toast.LENGTH_LONG).show();
                    }

                } catch (NumberFormatException e) {
                    // Handle NumberFormatException
                    Log.e("NumberFormatException", "Invalid number format: " + e.getMessage());
                    Toast.makeText(P5_SortDFAP.this, "Please enter valid numbers for amount and slots", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Handle other exceptions
                    Log.e("Exception", "Error: " + e.getMessage());
                    Toast.makeText(P5_SortDFAP.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to navigate to P5_DFAP.java with the filtered list
    private void navigationToDFAPWithFilteredList(List<FinancialAid> filteredList) {
        Intent intent = new Intent(P5_SortDFAP.this, P5_DFAP.class);
        intent.putParcelableArrayListExtra("filteredList", new ArrayList<>(filteredList));
        startActivity(intent);
    }
}