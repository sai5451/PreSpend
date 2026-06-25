package com.prespend;

import android.content.Intent;
import android.widget.Button;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.prespend.activities.AddExpenseActivity;
import com.prespend.activities.ExpenseHistoryActivity;
import android.widget.TextView;
import com.prespend.database.DatabaseHelper;
import com.prespend.repository.ExpenseRepository;


public class MainActivity extends AppCompatActivity {

    private Button btnAddExpense;
    private Button btnHistory;

    private TextView tvToday;
    private TextView tvMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvToday = findViewById(R.id.tvToday);
        tvMonth = findViewById(R.id.tvMonth);

        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnHistory = findViewById(R.id.btnHistory);

        btnAddExpense.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExpenseHistoryActivity.class);
            startActivity(intent);
        });


        updateDashboard();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDashboard();
    }

    private void updateDashboard() {
        ExpenseRepository repository = new ExpenseRepository(this);

        double total = repository.getTotalExpense();
        tvMonth.setText("Total Spending: ₹" + total);
    }
}