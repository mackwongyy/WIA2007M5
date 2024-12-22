package com.example.wia2007;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private Button selectButton, sortButton, backButton;
    private RecyclerView recyclerView;
    private ArrayList<FinancialAid> financialAidList = new ArrayList<>();

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
        setContentView(R.layout.activity_main2);

        // Bind views
        backButton = findViewById(R.id.backButton);
        sortButton = findViewById(R.id.sortButton);
        selectButton = findViewById(R.id.selectButton);
        recyclerView = findViewById(R.id.locationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));

        // Populate ListView with financial aid data
        /*List<String> financialAidStrings = new ArrayList<>();
        for (FinancialAid aid : financialAidList) {
            financialAidStrings.add(aid.getAidName() + " - RM " + aid.getAidAmount());
        }
        RecyclerView.Adapter<String> adapter = new RecyclerView<>(this, android.R.layout.simple_list_item_1, financialAidStrings);
        recyclerView.setAdapter(adapter);*/
        FinancialAidAdapter adapter = new FinancialAidAdapter(financialAidList);
        recyclerView.setAdapter(adapter);

        // Button functionality for Back navigation
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMainActivity(v);
            }
        });

        // Button functionality for Sort navigation
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity3(v);
            }
        });

        // Button functionality for Select navigation
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity3A(v);
            }
        });
    }

    // Method to navigate to MainActivity1
    private void navigateBackToMainActivity(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    // Method to navigate to MainActivity3
    private void navigateToMainActivity3(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
    }

    // Method to navigate to MainActivity3A
    private void navigateToMainActivity3A(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3A.class);
        startActivity(intent);
    }
}