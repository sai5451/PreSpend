package com.prespend.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prespend.database.DatabaseHelper;
import com.prespend.models.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {

    private DatabaseHelper databaseHelper;

    public ExpenseRepository(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }


    public long insertExpense(Expense expense) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("amount", expense.getAmount());
        values.put("category", expense.getCategory());
        values.put("paymentMode", expense.getPaymentMode());
        values.put("merchant", expense.getMerchant());
        values.put("timestamp", expense.getTimestamp());
        values.put("notes", expense.getNotes());

        long result = db.insert(
                DatabaseHelper.TABLE_EXPENSES,
                null,
                values
        );

        db.close();

        return result;
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + databaseHelper.TABLE_EXPENSES,
                null
        );

        if (cursor.moveToFirst()) {
            do {

                Expense expense = new Expense();

                expense.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                expense.setAmount(cursor.getDouble(cursor.getColumnIndexOrThrow("amount")));
                expense.setCategory(cursor.getString(cursor.getColumnIndexOrThrow("category")));
                expense.setPaymentMode(cursor.getString(cursor.getColumnIndexOrThrow("paymentMode")));
                expense.setMerchant(cursor.getString(cursor.getColumnIndexOrThrow("merchant")));
                expense.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));
                expense.setNotes(cursor.getString(cursor.getColumnIndexOrThrow("notes")));

                expenseList.add(expense);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return expenseList;
    }


    public double getTotalExpense() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT SUM(amount) FROM " + databaseHelper.TABLE_EXPENSES,
                null
        );

        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();

        return total;
    }


}