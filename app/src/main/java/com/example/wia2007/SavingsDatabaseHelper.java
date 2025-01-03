package com.example.wia2007;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavingsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SavingsDatabase1.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_SAVINGS = "savings";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INCOME = "income";
    private static final String COLUMN_EXPENSES = "expenses";
    private static final String COLUMN_INSURANCE = "insurance";
    private static final String COLUMN_TAX = "tax";
    private static final String COLUMN_SAVINGS_TARGET = "savings_target";
    private static final String COLUMN_AGGRESSIVENESS = "aggressiveness";
    private static final String COLUMN_POSITIVE_CASH_FLOW = "positive_cash_flow";
    private static final String COLUMN_NUMBER_OF_MONTHS = "number_of_months";

    // Create table query
    private static final String CREATE_TABLE_SAVINGS = "CREATE TABLE " + TABLE_SAVINGS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_INCOME + " REAL,"
            + COLUMN_EXPENSES + " REAL,"
            + COLUMN_INSURANCE + " REAL,"
            + COLUMN_TAX + " REAL,"
            + COLUMN_SAVINGS_TARGET + " REAL,"
            + COLUMN_AGGRESSIVENESS + " REAL,"
            + COLUMN_POSITIVE_CASH_FLOW + " REAL,"
            + COLUMN_NUMBER_OF_MONTHS + " INTEGER"
            + ")";

    public SavingsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SAVINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVINGS);
        onCreate(db);
    }

    // Insert savings data
    public void insertSavingsData(double income, double expenses, double insurance, double tax, double savingsTarget, float aggressiveness, double positiveCashFlow, int numberOfMonths) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_INCOME, income);
        values.put(COLUMN_EXPENSES, expenses);
        values.put(COLUMN_INSURANCE, insurance);
        values.put(COLUMN_TAX, tax);
        values.put(COLUMN_SAVINGS_TARGET, savingsTarget);
        values.put(COLUMN_AGGRESSIVENESS, aggressiveness);
        values.put(COLUMN_POSITIVE_CASH_FLOW, positiveCashFlow);
        values.put(COLUMN_NUMBER_OF_MONTHS, numberOfMonths);
        db.insert(TABLE_SAVINGS, null, values);
    }

    // Retrieve savings data
    public Cursor getSavingsData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_SAVINGS, null, null, null, null, null, null);
    }
}