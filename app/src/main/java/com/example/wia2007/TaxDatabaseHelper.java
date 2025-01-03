package com.example.wia2007;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaxDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Tax_Database.db";
    public static final int DATABASE_VERSION = 1;

    // Table and column names
    public static final String TABLE_TAX = "tax_data";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TOTAL_INCOME = "total_income";
    public static final String COLUMN_TAX_RELIEF = "tax_relief";
    public static final String COLUMN_MONTHLY_TAX_DEDUCTION = "monthly_tax_deduction";
    public static final String COLUMN_TAKAFUL_ZAKAT = "takaful_zakat";
    public static final String COLUMN_ROAD_TAX = "road_tax";
    public static final String COLUMN_PROPERTY_TAX = "property_tax";
    public static final String COLUMN_QUIT_RENT = "quit_rent";
    public static final String COLUMN_OTHER_TAX = "other_tax";
    public static final String COLUMN_INCOME_TAX = "income_tax"; // New column for incomeTax

    // Create table query
    public static final String CREATE_TABLE_TAX = "CREATE TABLE " + TABLE_TAX + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TOTAL_INCOME + " REAL,"
            + COLUMN_TAX_RELIEF + " REAL,"
            + COLUMN_MONTHLY_TAX_DEDUCTION + " REAL,"
            + COLUMN_TAKAFUL_ZAKAT + " REAL,"
            + COLUMN_ROAD_TAX + " REAL,"
            + COLUMN_PROPERTY_TAX + " REAL,"
            + COLUMN_QUIT_RENT + " REAL,"
            + COLUMN_OTHER_TAX + " REAL,"
            + COLUMN_INCOME_TAX + " REAL" // New column for incomeTax
            + ")";

    public TaxDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TAX);

        // Insert a default row with all values set to 0
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOTAL_INCOME, 0);
        values.put(COLUMN_TAX_RELIEF, 0);
        values.put(COLUMN_MONTHLY_TAX_DEDUCTION, 0);
        values.put(COLUMN_TAKAFUL_ZAKAT, 0);
        values.put(COLUMN_ROAD_TAX, 0);
        values.put(COLUMN_PROPERTY_TAX, 0);
        values.put(COLUMN_QUIT_RENT, 0);
        values.put(COLUMN_OTHER_TAX, 0);
        values.put(COLUMN_INCOME_TAX, 0); // Initialize incomeTax to 0
        db.insert(TABLE_TAX, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAX);
        onCreate(db);
    }

    // Retrieve the current row from the database
    public ContentValues getCurrentRow() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TAX, null, null, null, null, null, null);
        ContentValues values = new ContentValues();
        if (cursor.moveToFirst()) {
            values.put(COLUMN_TOTAL_INCOME, cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL_INCOME)));
            values.put(COLUMN_TAX_RELIEF, cursor.getDouble(cursor.getColumnIndex(COLUMN_TAX_RELIEF)));
            values.put(COLUMN_MONTHLY_TAX_DEDUCTION, cursor.getDouble(cursor.getColumnIndex(COLUMN_MONTHLY_TAX_DEDUCTION)));
            values.put(COLUMN_TAKAFUL_ZAKAT, cursor.getDouble(cursor.getColumnIndex(COLUMN_TAKAFUL_ZAKAT)));
            values.put(COLUMN_ROAD_TAX, cursor.getDouble(cursor.getColumnIndex(COLUMN_ROAD_TAX)));
            values.put(COLUMN_PROPERTY_TAX, cursor.getDouble(cursor.getColumnIndex(COLUMN_PROPERTY_TAX)));
            values.put(COLUMN_QUIT_RENT, cursor.getDouble(cursor.getColumnIndex(COLUMN_QUIT_RENT)));
            values.put(COLUMN_OTHER_TAX, cursor.getDouble(cursor.getColumnIndex(COLUMN_OTHER_TAX)));
            values.put(COLUMN_INCOME_TAX, cursor.getDouble(cursor.getColumnIndex(COLUMN_INCOME_TAX))); // Retrieve incomeTax
        }
        cursor.close();
        return values;
    }

    // Update tax data (only updates specified fields)
    public void updateTaxData(Double totalIncome, Double taxRelief, Double monthlyTaxDeduction,
                              Double takafulZakat, Double roadTax, Double propertyTax,
                              Double quitRent, Double otherTax, Double incomeTax) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getCurrentRow(); // Retrieve the current row

        // Update only the specified fields
        if (totalIncome != null) values.put(COLUMN_TOTAL_INCOME, totalIncome);
        if (taxRelief != null) values.put(COLUMN_TAX_RELIEF, taxRelief);
        if (monthlyTaxDeduction != null) values.put(COLUMN_MONTHLY_TAX_DEDUCTION, monthlyTaxDeduction);
        if (takafulZakat != null) values.put(COLUMN_TAKAFUL_ZAKAT, takafulZakat);
        if (roadTax != null) values.put(COLUMN_ROAD_TAX, roadTax);
        if (propertyTax != null) values.put(COLUMN_PROPERTY_TAX, propertyTax);
        if (quitRent != null) values.put(COLUMN_QUIT_RENT, quitRent);
        if (otherTax != null) values.put(COLUMN_OTHER_TAX, otherTax);
        if (incomeTax != null) values.put(COLUMN_INCOME_TAX, incomeTax); // Update incomeTax

        // Update the first row (assuming there's only one row in the table)
        db.update(TABLE_TAX, values, COLUMN_ID + " = ?", new String[]{"1"});
    }

    // Retrieve tax data
    public Cursor getTaxData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_TAX, null, null, null, null, null, null);
    }
}