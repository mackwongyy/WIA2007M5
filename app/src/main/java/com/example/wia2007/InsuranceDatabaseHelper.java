package com.example.wia2007;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InsuranceDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "InsuranceDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_INSURANCE = "insurance";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LIFE_INSURANCE_DEDUCTIBLE = "life_insurance_deductible";
    private static final String COLUMN_MOTOR_INSURANCE_DEDUCTIBLE = "motor_insurance_deductible";
    private static final String COLUMN_PERSONAL_INSURANCE_DEDUCTIBLE = "personal_insurance_deductible";
    private static final String COLUMN_MEDICAL_INSURANCE_DEDUCTIBLE = "medical_insurance_deductible";
    private static final String COLUMN_TRAVEL_INSURANCE_DEDUCTIBLE = "travel_insurance_deductible";
    private static final String COLUMN_OTHER_INSURANCE_DEDUCTIBLE = "other_insurance_deductible";
    private static final String COLUMN_LIFE_INSURANCE_COST = "life_insurance_cost";
    private static final String COLUMN_MOTOR_INSURANCE_COST = "motor_insurance_cost";
    private static final String COLUMN_PERSONAL_INSURANCE_COST = "personal_insurance_cost";
    private static final String COLUMN_MEDICAL_INSURANCE_COST = "medical_insurance_cost";
    private static final String COLUMN_TRAVEL_INSURANCE_COST = "travel_insurance_cost";
    private static final String COLUMN_OTHER_INSURANCE_COST = "other_insurance_cost";

    public InsuranceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_INSURANCE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_LIFE_INSURANCE_DEDUCTIBLE + " REAL,"
                + COLUMN_MOTOR_INSURANCE_DEDUCTIBLE + " REAL,"
                + COLUMN_PERSONAL_INSURANCE_DEDUCTIBLE + " REAL,"
                + COLUMN_MEDICAL_INSURANCE_DEDUCTIBLE + " REAL,"
                + COLUMN_TRAVEL_INSURANCE_DEDUCTIBLE + " REAL,"
                + COLUMN_OTHER_INSURANCE_DEDUCTIBLE + " REAL,"
                + COLUMN_LIFE_INSURANCE_COST + " REAL,"
                + COLUMN_MOTOR_INSURANCE_COST + " REAL,"
                + COLUMN_PERSONAL_INSURANCE_COST + " REAL,"
                + COLUMN_MEDICAL_INSURANCE_COST + " REAL,"
                + COLUMN_TRAVEL_INSURANCE_COST + " REAL,"
                + COLUMN_OTHER_INSURANCE_COST + " REAL"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSURANCE);
        onCreate(db);
    }

    // Insert insurance data
    public long insertInsuranceData(double lifeInsuranceDeductible, double motorInsuranceDeductible,
                                    double personalInsuranceDeductible, double medicalInsuranceDeductible,
                                    double travelInsuranceDeductible, double otherInsuranceDeductible,
                                    double lifeInsuranceCost, double motorInsuranceCost,
                                    double personalInsuranceCost, double medicalInsuranceCost,
                                    double travelInsuranceCost, double otherInsuranceCost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIFE_INSURANCE_DEDUCTIBLE, lifeInsuranceDeductible);
        values.put(COLUMN_MOTOR_INSURANCE_DEDUCTIBLE, motorInsuranceDeductible);
        values.put(COLUMN_PERSONAL_INSURANCE_DEDUCTIBLE, personalInsuranceDeductible);
        values.put(COLUMN_MEDICAL_INSURANCE_DEDUCTIBLE, medicalInsuranceDeductible);
        values.put(COLUMN_TRAVEL_INSURANCE_DEDUCTIBLE, travelInsuranceDeductible);
        values.put(COLUMN_OTHER_INSURANCE_DEDUCTIBLE, otherInsuranceDeductible);
        values.put(COLUMN_LIFE_INSURANCE_COST, lifeInsuranceCost);
        values.put(COLUMN_MOTOR_INSURANCE_COST, motorInsuranceCost);
        values.put(COLUMN_PERSONAL_INSURANCE_COST, personalInsuranceCost);
        values.put(COLUMN_MEDICAL_INSURANCE_COST, medicalInsuranceCost);
        values.put(COLUMN_TRAVEL_INSURANCE_COST, travelInsuranceCost);
        values.put(COLUMN_OTHER_INSURANCE_COST, otherInsuranceCost);

        long id = db.insert(TABLE_INSURANCE, null, values);
        db.close();
        return id;
    }

    // Retrieve insurance data
    public Cursor getInsuranceData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_INSURANCE, null, null, null, null, null, null);
    }
}