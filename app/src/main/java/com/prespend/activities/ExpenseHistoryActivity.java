package com.prespend.activities;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prespend.R;
import com.prespend.adapters.ExpenseAdapter;
import com.prespend.database.DatabaseHelper;
import com.prespend.models.Expense;

import java.util.List;

import com.prespend.R;

public class ExpenseHistoryActivity extends AppCompatActivity {

    private RecyclerView rvExpenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_history);

        rvExpenses = findViewById(R.id.rvExpenses);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        List<Expense> expenseList = databaseHelper.getAllExpenses();

        ExpenseAdapter adapter = new ExpenseAdapter(expenseList);

        rvExpenses.setLayoutManager(new LinearLayoutManager(this));
        rvExpenses.setAdapter(adapter);
    }
}