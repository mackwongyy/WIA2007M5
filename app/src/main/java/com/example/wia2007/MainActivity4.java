package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button selectButton = findViewById(R.id.selectButton);
        Button sortButton = findViewById(R.id.sortButton);
        Button backButton = findViewById(R.id.backButton);

        // Button functionality for Back navigation
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMainActivity(v);
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity3(v);
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity3A(v);
            }
        });
    }

    // Method to handle navigation back to MainActivity
    private void navigateBackToMainActivity(View view) {
        Intent intent = new Intent(MainActivity4.this, P5_Homepage.class);
        startActivity(intent);
    }

    private void navigateToMainActivity3(View view) {
        Intent intent = new Intent(MainActivity4.this, P5_ConfirmationDFAP.class);
        startActivity(intent);
    }

    private void navigateToMainActivity3A(View view) {
        Intent intent = new Intent(MainActivity4.this, P5_ConfirmationDFAP.class);
        startActivity(intent);
    }
}
