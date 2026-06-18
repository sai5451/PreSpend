package com.prespend.database;
import java.util.List;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import com.prespend.models.Expense;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prespend.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EXPENSES = "expenses";

    private static final String CREATE_TABLE_EXPENSES =
            "CREATE TABLE " + TABLE_EXPENSES + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount REAL," +
                    "category TEXT," +
                    "paymentMode TEXT," +
                    "merchant TEXT," +
                    "timestamp TEXT," +
                    "notes TEXT" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_EXPENSES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("amount", expense.getAmount());
        values.put("category", expense.getCategory());
        values.put("paymentMode", expense.getPaymentMode());
        values.put("merchant", expense.getMerchant());
        values.put("timestamp", expense.getTimestamp());
        values.put("notes", expense.getNotes());

        long result = db.insert(
                TABLE_EXPENSES,
                null,
                values
        );

        db.close();

        return result;
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_EXPENSES,
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


}