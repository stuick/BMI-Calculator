package com.example.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Button calculate;
    private RadioButton radioButtonMale;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private RadioButton radioButtonFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpButtonClick();
    }

    private void findViews(){
        resultText = findViewById(R.id.text_view_result);
        radioButtonMale = findViewById(R.id.radio_button_male);
        radioButtonFemale = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);
    }
    
    private void setUpButtonClick() {
        calculate.setOnClickListener(v -> {
            double bmiResult = calculateBmi();

            String ageText = age.getText().toString();
            int ageInt = Integer.parseInt(ageText);

            if(ageInt>=18){
                displayResult(bmiResult);
            } else{
                displayGuidance(bmiResult);
            }
        });
    }

    private double calculateBmi() {
        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();

        //Converting the number 'String' into 'int' variables
        int feetInt = Integer.parseInt(feetText);
        int inchesInt = Integer.parseInt(inchesText);
        int weightInt = Integer.parseInt(weightText);

        int totalInches = (feetInt * 12) + inchesInt;

        double heightInMeters = totalInches * 0.0254;

        return weightInt / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        String fullResultString;
        if(bmi < 18.5){
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        }else if(bmi > 25){
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        }else{
            //Display healthy
            fullResultString = bmiTextResult + " - You are healthy";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        String fullResultString;
        if(radioButtonMale.isChecked()){
            // Display boy guidance
            fullResultString = bmiTextResult + " boyyyyyyyyyy";
        } else if (radioButtonFemale.isChecked()) {
            // Display girl guidance
            fullResultString = bmiTextResult + " Girlllllllll";
        } else {
            // Display general result
            fullResultString = bmiTextResult + " Noonnnnnnnnnneee";
        }
        resultText.setText(fullResultString);
    }

}