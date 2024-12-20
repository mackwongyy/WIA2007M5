package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.*;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity3A extends AppCompatActivity {
    private Button clickedYesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3a);
        clickedYesButton = findViewById(R.id.yesButton);
        clickedYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3A.this, "Congratulations, you have applied for the financial aid.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}