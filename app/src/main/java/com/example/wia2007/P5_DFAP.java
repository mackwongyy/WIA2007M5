package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class P5_DFAP extends AppCompatActivity {
    private ArrayList<FinancialAid> financialAidList;
    private int selectedPosition = -1; // Track the selected position in the RecyclerView
    private TextView numberOfListingsTextView; // Reference to the TextView showing the number of listings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_dfap);

        boolean hasClearedAllEntries = false;

        // Initialize the list from the FinancialAidManager
        if(!hasClearedAllEntries) {
            FinancialAidManager.initializeList();
        }
        financialAidList = FinancialAidManager.getFinancialAidList();

        // Initialize the TextView for the number of listings
        numberOfListingsTextView = findViewById(R.id.number_of_listings);
        updateNumberOfListings(); // Set the initial number of listings

        // Check if there is an updated aid from ConfirmationDFAP.java
        FinancialAid updatedAid = getIntent().getParcelableExtra("updatedAid");
        if (updatedAid != null) {
            // Update the aid in the list
            for (int i = 0; i < financialAidList.size(); i++) {
                if (financialAidList.get(i).getAidID() == updatedAid.getAidID()) {
                    financialAidList.set(i, updatedAid);
                    break;
                }
            }

            // If the aid slots are 0, remove the entry from the list
            if (updatedAid.getAidSlots() <= 0) {
                FinancialAidManager.removeFinancialAid(updatedAid.getAidID());
                financialAidList = FinancialAidManager.getFinancialAidList(); // Refresh the list
                updateNumberOfListings(); // Update the number of listings
            }
        }

        // Check if the list is empty and handle it
        if (financialAidList.isEmpty()) {
            handleEmptyList();
            return; // Exit early to avoid setting up the RecyclerView
        }

        // Initialize UI elements
        Button backButton = findViewById(R.id.backButton);
        Button sortButton = findViewById(R.id.sortButton);
        Button selectButton = findViewById(R.id.selectButton);
        RecyclerView recyclerView = findViewById(R.id.locationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter
        FinancialAidAdapter adapter = new FinancialAidAdapter(financialAidList, new FinancialAidAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedPosition = position; // Update the selected position
                Log.d("P5_DFAP", "Selected position: " + selectedPosition + ", Aid: " + financialAidList.get(selectedPosition).getAidName());
            }
        }, this);

        recyclerView.setAdapter(adapter);

        // Disable the selectButton initially
        selectButton.setEnabled(false);

        // Enable the selectButton only when an item is selected
        adapter.setOnItemClickListener(position -> {
            selectedPosition = position;
            selectButton.setEnabled(true); // Enable the button when an item is selected
        });

        backButton.setOnClickListener(v -> {
            navigationFromDFAPToHomepage(v);
        });

        sortButton.setOnClickListener(v -> {
            navigationFromDFAPToSortDFAP(v);
        });

        selectButton.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(P5_DFAP.this, "Please select a financial aid first!", Toast.LENGTH_SHORT).show();
            } else {
                // Navigate to SelectionDFAP.java with the selected aid
                Intent intent = new Intent(P5_DFAP.this, SelectionDFAP.class);
                intent.putExtra("selectedAid", financialAidList.get(selectedPosition));
                startActivity(intent);
            }
        });
    }

    // Update the number of listings in the TextView
    private void updateNumberOfListings() {
        int numberOfListings = financialAidList.size();
        numberOfListingsTextView.setText("Listings of Financial Aid Found: " + numberOfListings);
    }

    // Handle the case when the list is empty
    private void handleEmptyList() {
        // Hide the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.locationRecyclerView);
        recyclerView.setVisibility(View.GONE);

        // Show a message to the user
        Toast.makeText(this, "No financial aid entries available.", Toast.LENGTH_LONG).show();

        // Disable buttons that are no longer relevant
        Button sortButton = findViewById(R.id.sortButton);
        Button selectButton = findViewById(R.id.selectButton);
        sortButton.setEnabled(false);
        selectButton.setEnabled(false);

        // Update the number of listings to 0
        numberOfListingsTextView.setText("Listings of Financial Aid Found: 0");
    }

    public void navigationFromDFAPToHomepage(View view) {
        Intent intent = new Intent(P5_DFAP.this, P5_Homepage.class);
        startActivity(intent);
    }

    public void navigationFromDFAPToSortDFAP(View view) {
        Intent intent = new Intent(P5_DFAP.this, P5_SortDFAP.class);
        startActivity(intent);
    }
}