package com.example.wia2007;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

public class P5_DFAP extends AppCompatActivity {
    private final ArrayList<FinancialAid> financialAidList = new ArrayList<>();

    // Initialize financial aid list
    {
        financialAidList.add(new FinancialAid(1, "Mani Group Financial Aid", 500, 2, "02/12/2024"));
        financialAidList.add(new FinancialAid(2, "Wesley Foundation Scholarship", 10000, 1, "15/12/2024"));
        financialAidList.add(new FinancialAid(3, "Hua Chai Scholarship", 15000, 2, "13/12/2024"));
        financialAidList.add(new FinancialAid(4, "Ahmad and Co Fund", 20000, 2, "06/12/2024"));
        financialAidList.add(new FinancialAid(5, "Rodrigo Corporation Scholarship", 1500, 1, "01/01/2025"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_dfap);

        // Bind views
        Button backButton = findViewById(R.id.backButton);
        Button sortButton = findViewById(R.id.sortButton);
        Button selectButton = findViewById(R.id.selectButton);
        RecyclerView recyclerView = findViewById(R.id.locationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(P5_DFAP.this));

        FinancialAidAdapter adapter = new FinancialAidAdapter(financialAidList);
        recyclerView.setAdapter(adapter);
        adapter.setTextSizes(24);
        adapter.setTextColour("#014565");

        // Button functionality for Back navigation
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFromDFAPToHomepage(v);
            }
        });

        // Button functionality for Sort navigation
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFromDFAPToSortDFAP(v);
            }
        });

        // Button functionality for Select navigation
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateFromDFAPToConfirmationDFAP(v);
            }
        });
    }

    // Method to navigate to MainActivity1
    private void navigateFromDFAPToHomepage(View view) {
        Intent intent = new Intent(P5_DFAP.this, P5_Homepage.class);
        startActivity(intent);
    }

    // Method to navigate to MainActivity3
    private void navigateFromDFAPToSortDFAP(View view) {
        Intent intent = new Intent(P5_DFAP.this, MainActivity3.class);
        startActivity(intent);
    }

    // Method to navigate to MainActivity3A
    private void navigateFromDFAPToConfirmationDFAP(View view) {
        Intent intent = new Intent(P5_DFAP.this, MainActivity3A.class);
        startActivity(intent);
    }
}