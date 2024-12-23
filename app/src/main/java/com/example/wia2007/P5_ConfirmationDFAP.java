package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class P5_ConfirmationDFAP extends AppCompatActivity {

    private FinancialAid selectedAid;
    private int numberOfSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_confirmationdfap);

        // Retrieve the selected FinancialAid object and number of slots from the intent
        selectedAid = getIntent().getParcelableExtra("selectedAid");
        numberOfSlots = getIntent().getIntExtra("numberOfSlots", 0);

        // Initialize UI elements
        TextView confirmationMessageTextView = findViewById(R.id.ConfirmationDFAP_Question);
        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);

        // Set the confirmation message
        confirmationMessageTextView.setText("Are you sure you want to apply for " + numberOfSlots + " slots?");

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the aid slots by subtracting the number of slots
                selectedAid.setAidSlots(selectedAid.getAidSlots() - numberOfSlots);

                // If the aid slots reach 0, remove the entry from the list
                if (selectedAid.getAidSlots() <= 0) {
                    FinancialAidManager.removeFinancialAid(selectedAid.getAidID());
                }

                // Navigate back to P5_DFAP.java
                Intent intent = new Intent(P5_ConfirmationDFAP.this, P5_DFAP.class);
                intent.putExtra("updatedAid", selectedAid);
                startActivity(intent);
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to P5_DFAP.java without making any changes
                Intent intent = new Intent(P5_ConfirmationDFAP.this, P5_DFAP.class);
                startActivity(intent);
                finish();
            }
        });
    }
}