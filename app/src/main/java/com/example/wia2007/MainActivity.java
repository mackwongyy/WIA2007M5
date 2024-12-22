package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonDFAP;
    private Button buttonFMT;
    private Button buttonFBSL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonDFAP = findViewById(R.id.DFAP);
        buttonFMT = findViewById(R.id.FMT);
        buttonFBSL = findViewById(R.id.FBSL);

        buttonDFAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageToDFAP(v);
            }
        });

        buttonFMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageToFMT(v);
            }
        });

        buttonFBSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageToFBSL(v);
            }
        });
    }

    public void navigationFromHomepageToDFAP(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void navigationFromHomepageToFMT(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity5.class);
        startActivity(intent);
    }

    public void navigationFromHomepageToFBSL(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity9.class);
        startActivity(intent);
    }
}