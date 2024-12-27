package com.example.wia2007;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class P5_HomepageFMT extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_homepagefmt);

        Button taxButton = findViewById(R.id.taxButton);
        Button insuranceCalculator = findViewById(R.id.insuranceButton);
        Button savingsGuide = findViewById(R.id.SavingsGuideButton);
        Button AI_Financial_Chatbot = findViewById(R.id.AI_Financial_Chatbot);
        Button backButton = findViewById(R.id.backButton);

        taxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToTax(v);
            }
        });

        insuranceCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToInsurance(v);
            }
        });

        savingsGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToSavingsGuide(v);
            }
        });

        AI_Financial_Chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToAI_Financial_Chatbot(v);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToHomepage(v);
            }
        });
    }

    public void navigationFromHomepageFMTToTax(View view) {
        Intent intent = new Intent(P5_HomepageFMT.this, P5_Tax1FMT.class);
        startActivity(intent);
    }

    public void navigationFromHomepageFMTToInsurance(View view) {
        Intent intent = new Intent(P5_HomepageFMT.this, P5_Insurance1FMT.class);
        startActivity(intent);
    }

    public void navigationFromHomepageFMTToSavingsGuide(View view) {
        Intent intent = new Intent(P5_HomepageFMT.this, P5_SavingsGuideFMT.class);
        startActivity(intent);
    }

    public void navigationFromHomepageFMTToAI_Financial_Chatbot(View view) {
        Intent intent = new Intent(P5_HomepageFMT.this, P5_AI_Finance_Chatbot.class);
        startActivity(intent);
    }

    public void navigationFromHomepageFMTToHomepage(View view) {
        Intent intent = new Intent(P5_HomepageFMT.this, P5_Homepage.class);
        startActivity(intent);
    }
}
