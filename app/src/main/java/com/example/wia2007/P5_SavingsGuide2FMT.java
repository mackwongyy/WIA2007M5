package com.example.wia2007;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.slider.Slider;

public class P5_SavingsGuide2FMT extends AppCompatActivity {

    private TextView aggressivenessDescription;
    private Slider aggressivenessSlider;
    private EditText savingsTargetLabel;
    private Spinner criterionChoiceSpinner;
    private Button backButton;
    private Button applyButton;
    private double savingsTarget;

    private String selectedCriterion; // To store the selected spinner item
    private float aggressivenessValue; // To store the slider value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_savingsguide2fmt);

        // Initialize UI elements
        aggressivenessDescription = findViewById(R.id.aggressivenessDescription);
        aggressivenessSlider = findViewById(R.id.aggressivenessSlider);
        savingsTargetLabel = findViewById(R.id.savingsTargetLabel);
        criterionChoiceSpinner = findViewById(R.id.criterionChoiceSpinner);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

        // Retrieve saved income and expenses from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserInput", MODE_PRIVATE);
        float income = sharedPreferences.getFloat("income", 0);
        float expenses = sharedPreferences.getFloat("expenses", 0);

        // Set up the spinner with options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.criterion_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        criterionChoiceSpinner.setAdapter(adapter);

        // Set up the spinner listener
        criterionChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCriterion = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCriterion = "Months"; // Default value
            }
        });

        // Set up the slider listener
        aggressivenessSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {
                aggressivenessValue = value; // Store the slider value
            }
        });

        // Set up the back button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_SavingsGuide2FMT.this, P5_SavingsGuide1FMT.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Set up the apply button listener
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applySavingsGuide();
            }
        });
    }

    private void applySavingsGuide() {
        // Retrieve the savings target
        String savingsTargetStr = savingsTargetLabel.getText().toString();
        if (savingsTargetStr.isEmpty()) {
            Toast.makeText(this, "Please input your savings target.", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            savingsTarget = Double.parseDouble(savingsTargetStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid savings target. Please enter a valid number.", Toast.LENGTH_LONG).show();
            return;
        }

        // Save savingsTarget to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserInput", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("savingsTarget", (float) savingsTarget);
        editor.apply();

        // Retrieve income and expenses from SharedPreferences
        float income = sharedPreferences.getFloat("income", 0);
        float expenses = sharedPreferences.getFloat("expenses", 0);

        // Calculate positive cash flow
        double positiveCashFlow = income - expenses;

        // Check the selected criterion
        if (selectedCriterion.equals("Months")) {
            // Navigate to P5_SavingsGuide3FMT and pass the values
            Intent intent = new Intent(P5_SavingsGuide2FMT.this, P5_SavingsGuide3FMT.class);
            intent.putExtra("aggressivenessValue", aggressivenessValue);
            intent.putExtra("savingsTarget", savingsTarget);
            intent.putExtra("positiveCashFlow", positiveCashFlow); // Pass positiveCashFlow
            startActivity(intent);
            finish(); // Close the current activity
        } else if (selectedCriterion.equals("Amount")) {
            // Calculate the additional amount needed
            double additionalAmount = savingsTarget - (aggressivenessValue / 0.01);
            String result = "Additional amount to save: RM " + String.format("%.2f", additionalAmount);
            aggressivenessDescription.setText(result); // Update the label
        }
    }
}