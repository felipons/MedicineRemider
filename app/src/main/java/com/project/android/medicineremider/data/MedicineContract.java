package com.project.android.medicineremider.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by FELIPE on 10/26/2016.
 */

public class MedicineContract {

    //Empty Constructor
    public MedicineContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.project.android.medicineremider";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.project.android.medicineremider/medicine/ is a valid path for
     * looking at pet data. content://com.project.android.medicineremider/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_MEDICINE = "medicines";
    /**
     * Inner class that defines constant values for the medicines database table.
     * Each entry in the table represents a single medicine.
     */
    public static final class MedicineEntry implements BaseColumns {

        /** Name of database table for medicine */
        public final static String TABLE_NAME = "medicines";

        /**
         * Unique ID number for the medicine (only for use in the database table).
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Type of the medicine.
         * Type: TEXT
         */
        public final static String COLUMN_CATEGOTY_MEDICINE ="category";

        /**
         * Name of medicine
         *
         * Type: TEXT
         */
        public final static String COLUMN_NAME_MEDICINE = "name_medicine";

        /**
         * Indication of medicine
         * Type: TEXT
         */
        public final static String COLUMN_INDICATION_MEDICINE = "indication";

        /**
         * Indication of medicine
         * Type: TEXT
         */
        public final static String COLUMN_UNIT_MEDICINE = "unit";

        /**
         * Name of medicine
         *
         * Type: INTEGER
         */
        public final static String COLUMN_STOCK_MEDICINE = "stock_medicine";

        /**
         * Category of the Medicine.
         *
         * The only possible values are {@link #TABLET}, {@link #CAPSULE}, {@link #INJECTION},
         * {@link #DRINKABLE},{@link #ANTICONCEPTIVE},{@link #INHALER},{@link #POWDER}, {@link #CREAM}, {@link #DROPS},
         * or {@link #REMAINING}.
         *
         * Type: INTEGER
         */

        /**
         * Possible values for the Tablet of the pet.
         */
        public static final int TABLET_TABLET = 0;
        public static final int TABLET_MTABLE = 1;
        public static final int TABLET_MG = 2;

        /**
         * Returns whether or not the given gender is {@link #TABLET_TABLET}, {@link #TABLET_MTABLE},
         * or {@link #TABLET_MG}.
         */
        public static boolean isValidUnit(int unitMedicine) {
            if (unitMedicine == TABLET_TABLET || unitMedicine == TABLET_MTABLE || unitMedicine == TABLET_MG) {
                return true;
            }
            return false;
        }

        /**
         * Possible values for the category of the medicine.
         */
        public static final int TABLET = 0;
        public static final int CAPSULE = 1;
        public static final int INJECTION = 2;
        public static final int DRINKABLE = 3;
        public static final int ANTICONCEPTIVE = 4;
        public static final int INHALER = 5;
        public static final int POWDER = 6;
        public static final int CREAM  = 7;
        public static final int DROPS = 8;
        public static final int REMAINING = 9;

        /**
         * Returns whether or not the given category is {@link #TABLET}, {@link #CAPSULE}, {@link #INJECTION},
         * {@link #DRINKABLE}, {@link #ANTICONCEPTIVE}, {@link #INHALER}, {@link #POWDER}, {@link #CREAM},
         * {@link #DROPS} or {@link #REMAINING, }.
         */
        public static boolean isValidCategory(int categoryMedicine) {
            if (categoryMedicine == TABLET || categoryMedicine == CAPSULE || categoryMedicine == INJECTION ||
            categoryMedicine == DRINKABLE || categoryMedicine == ANTICONCEPTIVE || categoryMedicine == INHALER ||
            categoryMedicine == POWDER || categoryMedicine == CREAM || categoryMedicine == DROPS ||
            categoryMedicine == REMAINING) {
                return true;
            }
            return false;
        }

    }
}
