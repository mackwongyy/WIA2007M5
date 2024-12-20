package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private Spinner financialAidSpinner;
    private Button selectButton, sortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //financialAidSpinner = findViewById(R.id.financialAidSpinner);
        selectButton = findViewById(R.id.selectButton);
        sortButton = findViewById(R.id.sortButton);

        //Will change to more, now insert dummy data first
        //String[] financialAidList = {"Mani Group Financial Aid", "Wesley Foundation Scholarship"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, financialAidList);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //financialAidSpinner.setAdapter(adapter);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3A.class);
                startActivity(intent);
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3A.class);
                startActivity(intent);
            }
        });
    }
}