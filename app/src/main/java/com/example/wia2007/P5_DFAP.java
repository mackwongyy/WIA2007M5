package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class P5_DFAP extends AppCompatActivity {
    private ArrayList<FinancialAid> financialAidList;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_dfap);

        // Retrieve the filtered list from the intent
        financialAidList = getIntent().getParcelableArrayListExtra("filteredList");

        // If no filtered list is received, use the default list
        if (financialAidList == null || financialAidList.isEmpty()) {
            financialAidList = new ArrayList<>();
            financialAidList.add(new FinancialAid(1, "Mani Group Financial Aid", 500, 2, "02/12/2024", R.drawable.workspace1));
            financialAidList.add(new FinancialAid(2, "Wesley Foundation Aid", 10000, 1, "15/12/2024", R.drawable.workspace2));
            financialAidList.add(new FinancialAid(3, "Hua Chai Aid", 15000, 2, "13/12/2024", R.drawable.workspace3));
            financialAidList.add(new FinancialAid(4, "Ahmad and Co Fund", 20000, 2, "06/12/2024", R.drawable.workspace4));
            financialAidList.add(new FinancialAid(5, "Rodrigo Corporation Fund", 1500, 1, "01/01/2025", R.drawable.workspace5));
        }

        Button backButton = findViewById(R.id.backButton);
        Button sortButton = findViewById(R.id.sortButton);
        Button selectButton = findViewById(R.id.selectButton);
        RecyclerView recyclerView = findViewById(R.id.locationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FinancialAidAdapter adapter = new FinancialAidAdapter(financialAidList, new FinancialAidAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                selectedPosition = position;
            }
        });

        recyclerView.setAdapter(adapter);

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
                // Debugging log
                Log.d("P5_DFAP", "Selected position: " + selectedPosition + ", Aid: " + financialAidList.get(selectedPosition).getAidName());

                Intent intent = new Intent(P5_DFAP.this, P5_ConfirmationDFAP.class);
                intent.putExtra("selectedAid", financialAidList.get(selectedPosition).getAidName());
                startActivity(intent);
            }
        });
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