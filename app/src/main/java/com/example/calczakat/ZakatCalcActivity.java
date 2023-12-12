package com.example.calczakat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ZakatCalcActivity extends AppCompatActivity {

    EditText weightEditText, valueEditText;
    RadioGroup typeRadioGroup;
    TextView outputTextView;
    Toolbar mytoolbar;
    RadioButton radioKeep, radioWear;
    Button calculateButton, resetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_calc);

        weightEditText = findViewById(R.id.editTextNumberDecimal3);
        valueEditText = findViewById(R.id.editTextNumberDecimal4);
        outputTextView = findViewById(R.id.output);
        mytoolbar = findViewById(R.id.my_toolbar_zakat);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        typeRadioGroup = findViewById(R.id.radioGroupType);
        radioKeep = findViewById(R.id.radioKeep);
        radioWear = findViewById(R.id.radioWear);

        calculateButton = findViewById(R.id.btnCalculate);
        resetButton = findViewById(R.id.btnReset);

        showInstructionDialog();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
    }
    private void showInstructionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Instructions to use Zakat Calculator");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_input_instruction, null);
        builder.setView(view);

        builder.setPositiveButton("Got It", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        } else if (item.getItemId() == R.id.btnHelp) {
            showInstructionDialog(); // Call the method to show the instruction dialog
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuzakat) {
        getMenuInflater().inflate(R.menu.menuzakat, menuzakat);
        return true;
    }
    private void resetValues() {
        // Clear EditText fields
        weightEditText.setText("");
        valueEditText.setText("");

        // Clear RadioGroup selection
        typeRadioGroup.clearCheck();

        // Clear the output TextView
        outputTextView.setText("");
    }

    private void calculateZakat() {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            String type;
            if (radioKeep.isChecked()) {
                type = "keep";
                if (weight < 85) {
                    outputTextView.setText("Minimum weight for 'Keep' type is 85 grams.");
                    return;
                }
            } else if (radioWear.isChecked()) {
                type = "wear";
                if (weight < 200) {
                    outputTextView.setText("Minimum weight for 'Wear' type is 200 grams.");
                    return;
                }
            } else {
                outputTextView.setText("Please select 'Keep' or 'Wear' for the type.");
                return;
            }

            double value = Double.parseDouble(valueEditText.getText().toString());

            double weightMinusX = 0;
            if (type.equalsIgnoreCase("keep")) {
                weightMinusX = weight - 85;
            } else if (type.equalsIgnoreCase("wear")) {
                weightMinusX = weight - 200;
            }

            double totalGoldValue = weight * value;
            double zakatPayable = Math.max(weightMinusX, 0) * value;
            double totalZakat = zakatPayable * 0.025;

            String result = String.format(
                    "Total Gold Value: RM%.2f\nTotal Gold Value that is Zakat Payable: RM%.2f\nTotal Zakat: RM%.2f",
                    totalGoldValue, zakatPayable, totalZakat);

            outputTextView.setText(result);

        } catch (NumberFormatException e) {
            outputTextView.setText("Please enter valid numbers for weight and value.");
        }
    }

}
