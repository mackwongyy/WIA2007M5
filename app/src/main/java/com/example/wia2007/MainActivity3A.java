package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3A extends AppCompatActivity {
    private String selectedAid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3a);

        // Safely retrieve the selected aid name
        selectedAid = getIntent().getStringExtra("selectedAid");
        if (selectedAid == null) {
            selectedAid = "Unknown Financial Aid";
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText("Do you wish to apply for " + selectedAid + "?");

        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);

        yesButton.setOnClickListener(v -> {
            Toast.makeText(this, "Congratulations, you have applied for " + selectedAid + ".", Toast.LENGTH_LONG).show();
            navigationFromConfirmationToHomepage(v);
        });

        noButton.setOnClickListener(this::navigationFromConfirmationToDFAP);
    }

    public void navigationFromConfirmationToHomepage(View view) {
        Intent intent = new Intent(MainActivity3A.this, P5_Homepage.class);
        startActivity(intent);
    }

    public void navigationFromConfirmationToDFAP(View view) {
        Intent intent = new Intent(MainActivity3A.this, P5_DFAP.class);
        startActivity(intent);
    }
}