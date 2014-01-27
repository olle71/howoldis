package ch.santo.howoldis.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oliver Santschi on 27.01.14.
 */
public class PersonsDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Person.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PersonsContract.Person.TABLE_NAME + " (" +
                    PersonsContract.Person._ID + " INTEGER PRIMARY KEY," +
                    PersonsContract.Person.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    PersonsContract.Person.COLUMN_NAME_PRENAME + TEXT_TYPE + COMMA_SEP +
                    PersonsContract.Person.COLUMN_NAME_LASTNAME + TEXT_TYPE +
                    " )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PersonsContract.Person.TABLE_NAME;

    public PersonsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}

