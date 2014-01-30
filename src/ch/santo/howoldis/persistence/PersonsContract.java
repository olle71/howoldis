package ch.santo.howoldis.persistence;

import android.provider.BaseColumns;

/**
 * Created by Oliver Santschi on 27.01.14.
 */
public final class PersonsContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PersonsContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Person implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_ENTRY_ID = "personid";
        public static final String COLUMN_NAME_PRENAME = "prename";
        public static final String COLUMN_NAME_LASTNAME = "lastname";

    }
}
