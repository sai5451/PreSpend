package com.prespend.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import com.prespend.models.Expense;

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


}