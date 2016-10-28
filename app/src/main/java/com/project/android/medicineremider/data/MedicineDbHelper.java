package com.project.android.medicineremider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.android.medicineremider.data.MedicineContract.MedicineEntry;

/**
 * Created by FELIPE on 10/26/2016.
 */

public class MedicineDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MedicineDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "medicineReminder.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link MedicineDbHelper}.
     *
     * @param context of the app
     */
    public MedicineDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_MEDICINE_TABLE =  "CREATE TABLE " + MedicineEntry.TABLE_NAME + " ("
                + MedicineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MedicineEntry.COLUMN_CATEGOTY_MEDICINE + " INTEGER, "
                + MedicineEntry.COLUMN_NAME_MEDICINE + " TEXT NOT NULL, "
                + MedicineEntry.COLUMN_INDICATION_MEDICINE + " TEXT NULL, "
                + MedicineEntry.COLUMN_UNIT_MEDICINE + " TEXT NULL, "
                + MedicineEntry.COLUMN_STOCK_MEDICINE + " INTEGER NOT NULL);, ";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_MEDICINE_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
