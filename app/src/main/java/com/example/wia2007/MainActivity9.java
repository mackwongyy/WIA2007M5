package com.example.wia2007;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        // Initialize ListView
        ListView locationListView = findViewById(R.id.locationListView);

        // Sample data for locations
        String[] locations = {
                "Kam Sing Mixed Rice Shop\nArea: Petaling Jaya\nOpen Hours: 9 am-9 pm (Monday-Sunday)\nCautions: Non-Halal, Contains organic food",
                "Iskandar Food Corner\nArea: Brickfields\nOpen Hours: 6 am-10 pm (Monday-Sunday)\nCautions: Halal, Self-service with staffs on standby"
        };

        // Set up the adapter for the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
        locationListView.setAdapter(adapter);

        // Find the backButton
        Button backButton = findViewById(R.id.backButton);

        // SetOnClickListener for backButton to navigate to MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9.this, P5_Homepage.class);
                startActivity(intent);
            }
        });
    }

    // Handle map button click
    public void onMapButtonClick(View view) {
        Toast.makeText(this, "Map feature coming soon!", Toast.LENGTH_SHORT).show();
    }
}