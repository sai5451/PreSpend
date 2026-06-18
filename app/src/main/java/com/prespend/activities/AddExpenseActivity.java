package com.prespend.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import com.prespend.database.DatabaseHelper;
import com.prespend.models.Expense;
import android.widget.Toast;

import com.prespend.R;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etAmount;
    private EditText etMerchant;
    private EditText etNotes;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);

        etAmount = findViewById(R.id.etAmount);
        etMerchant = findViewById(R.id.etMerchant);
        etNotes = findViewById(R.id.etNotes);
        btnSave = findViewById(R.id.btnSave);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String amountText = etAmount.getText().toString();
            String merchant = etMerchant.getText().toString();
            String notes = etNotes.getText().toString();

            if (amountText.isEmpty()) {
                etAmount.setError("Enter amount");
                return;
            }
            double amount = Double.parseDouble(amountText);
            Expense expense = new Expense();

            expense.setAmount(amount);
            expense.setMerchant(merchant);
            expense.setNotes(notes);

            expense.setCategory("General");
            expense.setPaymentMode("Cash");
            expense.setTimestamp(String.valueOf(System.currentTimeMillis()));


            long result = databaseHelper.insertExpense(expense);

            if (result != -1) {
                etAmount.setText("");
                etMerchant.setText("");
                etNotes.setText("");

                Toast.makeText(
                        AddExpenseActivity.this,
                        "Expense Saved",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        AddExpenseActivity.this,
                        "Failed to Save Expense",
                        Toast.LENGTH_SHORT
                ).show();

            }
        });



    }
}