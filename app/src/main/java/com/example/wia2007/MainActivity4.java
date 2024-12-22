package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    private Button selectButton, sortButton, backButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        selectButton = findViewById(R.id.selectButton);
        sortButton = findViewById(R.id.sortButton);
        backButton = findViewById(R.id.backButton);

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
    public void navigateBackToMainActivity(View view) {
        Intent intent = new Intent(MainActivity4.this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToMainActivity3(View view) {
        Intent intent = new Intent(MainActivity4.this, MainActivity3A.class);
        startActivity(intent);
    }

    public void navigateToMainActivity3A(View view) {
        Intent intent = new Intent(MainActivity4.this, MainActivity3A.class);
        startActivity(intent);
    }
}
