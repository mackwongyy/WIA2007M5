package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_homepage);

        Button buttonDFAP = findViewById(R.id.DFAP);
        Button buttonFMT = findViewById(R.id.FMT);
        Button buttonFBSL = findViewById(R.id.FBSL);

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

        /*buttonFBSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageToFBSL(v);
            }
        });*/
    }

    public void navigationFromHomepageToDFAP(View view) {
        Intent intent = new Intent(P5_Homepage.this, P5_DFAP.class);
        startActivity(intent);
    }

    public void navigationFromHomepageToFMT(View view) {
        Intent intent = new Intent(P5_Homepage.this, P5_HomepageFMT.class);
        startActivity(intent);
    }

    /*public void navigationFromHomepageToFBSL(View view) {
        Intent intent = new Intent(P5_Homepage.this, MainActivity9.class);
        startActivity(intent);
    }*/
}