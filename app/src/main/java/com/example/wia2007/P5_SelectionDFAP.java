package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class P5_SelectionDFAP extends AppCompatActivity {

    private FinancialAid selectedAid;
    private EditText numberOfSlotsEditText;
    private TextView totalAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_selection_dfap);

        // Retrieve the selected FinancialAid object from the intent
        selectedAid = getIntent().getParcelableExtra("selectedAid");

        // Initialize UI elements
        TextView titleTextView = findViewById(R.id.SelectionAmountTitle);
        TextView subtitleTextView = findViewById(R.id.SelectionAmountSubtitle);
        TextView amountOfSlotsTextView = findViewById(R.id.SelectionAmountAmountOfSlots);
        numberOfSlotsEditText = findViewById(R.id.Amount_Of_Slots);
        TextView totalAmountLabelTextView = findViewById(R.id.SelectionAmountTotalAmount);
        totalAmountTextView = findViewById(R.id.Total_Amount);
        Button applyButton = findViewById(R.id.applyButton);

        // Set the UI elements with the selected aid details
        titleTextView.setText("Selection for Financial Aid");
        subtitleTextView.setText("Enter the amount of slots you want to apply for:");
        amountOfSlotsTextView.setText("Amount of Slots: ");
        totalAmountLabelTextView.setText("Total Amount: ");

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get user input for the number of slots
                    int numberOfSlots = Integer.parseInt(numberOfSlotsEditText.getText().toString());

                    // Check if the number of slots is valid
                    if (numberOfSlots > selectedAid.getAidSlots()) {
                        Toast.makeText(P5_SelectionDFAP.this, "Not enough slots available!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calculate the total amount
                    int totalAmount = numberOfSlots * selectedAid.getAidAmount();

                    // Display the total amount in the format: numberOfSlots × aid.getAmount() = totalAmount
                    totalAmountTextView.setText(numberOfSlots + " × " + selectedAid.getAidAmount() + " = " + totalAmount);

                    // Show a toast message for 5 seconds
                    Toast.makeText(P5_SelectionDFAP.this, "Total Amount: " + totalAmount, Toast.LENGTH_LONG).show();

                    // Delay the navigation to ConfirmationDFAP.java by 5 seconds
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(P5_SelectionDFAP.this, P5_ConfirmationDFAP.class);
                        intent.putExtra("selectedAid", selectedAid);
                        intent.putExtra("numberOfSlots", numberOfSlots);
                        startActivity(intent);
                    }, 5000); // 5000 milliseconds = 5 seconds

                } catch (NumberFormatException e) {
                    Toast.makeText(P5_SelectionDFAP.this, "Please enter a valid number for slots", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}