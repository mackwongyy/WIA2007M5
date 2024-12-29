package com.example.wia2007;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaxDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TaxDatabase.db";
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
            + COLUMN_OTHER_TAX + " REAL"
            + ")";

    public TaxDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TAX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAX);
        onCreate(db);
    }

    // Insert tax data
    public long insertTaxData(double totalIncome, double taxRelief, double monthlyTaxDeduction,
                              double takafulZakat, double roadTax, double propertyTax,
                              double quitRent, double otherTax) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOTAL_INCOME, totalIncome);
        values.put(COLUMN_TAX_RELIEF, taxRelief);
        values.put(COLUMN_MONTHLY_TAX_DEDUCTION, monthlyTaxDeduction);
        values.put(COLUMN_TAKAFUL_ZAKAT, takafulZakat);
        values.put(COLUMN_ROAD_TAX, roadTax);
        values.put(COLUMN_PROPERTY_TAX, propertyTax);
        values.put(COLUMN_QUIT_RENT, quitRent);
        values.put(COLUMN_OTHER_TAX, otherTax);
        return db.insert(TABLE_TAX, null, values);
    }

    // Retrieve tax data
    public Cursor getTaxData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_TAX, null, null, null, null, null, null);
    }
}