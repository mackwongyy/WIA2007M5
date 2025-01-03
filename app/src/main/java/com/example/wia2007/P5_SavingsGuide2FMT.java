package com.example.wia2007;

import android.content.Intent;
import android.database.Cursor;
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
    private String selectedCriterion;
    private float aggressivenessValue;
    private SavingsDatabaseHelper savingsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5_savingsguide2fmt);

        savingsDatabaseHelper = new SavingsDatabaseHelper(this);

        // Initialize UI elements
        aggressivenessDescription = findViewById(R.id.aggressivenessDescription);
        aggressivenessSlider = findViewById(R.id.aggressivenessSlider);
        savingsTargetLabel = findViewById(R.id.savingsTargetLabel);
        criterionChoiceSpinner = findViewById(R.id.criterionChoiceSpinner);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.homeButton);

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

        // Retrieve income and expenses from the database
        Cursor cursor = savingsDatabaseHelper.getSavingsData();
        double income = 0;
        double expenses = 0;
        double insurance = 0;
        double tax = 0;
        if (cursor.moveToFirst()) {
            income = cursor.getDouble(cursor.getColumnIndex("income"));
            expenses = cursor.getDouble(cursor.getColumnIndex("expenses"));
            insurance = cursor.getDouble(cursor.getColumnIndex("insurance"));
            tax = cursor.getDouble(cursor.getColumnIndex("tax"));
        }
        cursor.close();

        // Calculate positive cash flow
        double positiveCashFlow = income - expenses - insurance - tax;

        // Save savings target and aggressiveness to the database
        savingsDatabaseHelper.insertSavingsData(income, expenses, insurance, tax, savingsTarget, aggressivenessValue, positiveCashFlow, 0);

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
            // Navigate to P5_SavingsGuide4FMT and pass the values
            Intent intent = new Intent(P5_SavingsGuide2FMT.this, P5_SavingsGuide4FMT.class);
            intent.putExtra("savingsTarget", savingsTarget);
            intent.putExtra("positiveCashFlow", positiveCashFlow); // Pass positiveCashFlow
            intent.putExtra("aggressivenessValue", aggressivenessValue); // Pass aggressivenessValue
            startActivity(intent);
            finish(); // Close the current activity
        }
    }
}