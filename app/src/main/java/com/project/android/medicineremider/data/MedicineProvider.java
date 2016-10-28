package com.project.android.medicineremider.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.project.android.medicineremider.data.MedicineContract.MedicineEntry;

/**
 * Created by FELIPE on 10/28/2016.
 */

public class MedicineProvider extends ContentProvider {


    /** Tag for the log messages */
    public static final String LOG_TAG = MedicineProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the Medicine table */
    private static final int MEDICINES = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int MEDICINE_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.project.android.medicineremider/medicines/" will map to the
        // integer code {@link #PETS}. This URI is used to provide access to MULTIPLE rows
        // of the pets table.
        sUriMatcher.addURI(MedicineContract.CONTENT_AUTHORITY, MedicineContract.PATH_MEDICINE, MEDICINES);

        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #PET_ID}. This URI is used to provide access to ONE single row
        // of the pets table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.project.android.medicineremider/medicines/3" matches, but
        // "content://com.project.android.medicineremider/medicines" (without a number at the end) doesn't match.
        sUriMatcher.addURI(MedicineContract.CONTENT_AUTHORITY, MedicineContract.PATH_MEDICINE + "/#", MEDICINE_ID);
    }

    /** Database helper object */
    private MedicineDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new MedicineDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case MEDICINES:
                // For the PETS code, query the pets table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the pets table.
                cursor = database.query(MedicineEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case MEDICINE_ID:
                // For the PET_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = MedicineEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(MedicineEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MEDICINES:
                return insertMedicine(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertMedicine(Uri uri, ContentValues values) {
        // Check that the name is not null
        String name = values.getAsString(MedicineEntry.COLUMN_NAME_MEDICINE);
        if (name == null) {
            throw new IllegalArgumentException("Medicine requires a name");
        }

        // Check that the category is valid
        Integer category = values.getAsInteger(MedicineEntry.COLUMN_CATEGOTY_MEDICINE);
        if (category == null || !MedicineEntry.isValidCategory(category)) {
            throw new IllegalArgumentException("Category requires valid category");
        }

        // Check that the type is valid
        Integer typeMedicine = values.getAsInteger(MedicineEntry.COLUMN_UNIT_MEDICINE);
        if (typeMedicine == null || !MedicineEntry.isValidUnit(typeMedicine)) {
            throw new IllegalArgumentException("Category requires valid category");
        }

        // If the stock is provided, check that it's greater than or equal to 0 Quantity
        Integer stock = values.getAsInteger(MedicineEntry.COLUMN_STOCK_MEDICINE);
        if (stock != null && stock < 0) {
            throw new IllegalArgumentException("Stock requires valid quantity");
        }


        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(MedicineEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
