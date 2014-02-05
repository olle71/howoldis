package ch.santo.howoldis.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ch.santo.howoldis.persistence.PersonsContract;
import ch.santo.howoldis.persistence.PersonsDBHelper;

import java.util.ArrayList;

/**
 * Created by Oliver Santschi on 05.02.14.
 */
public class PersonService {

    public static void insertTestdata(Context context) {
        // Gets the data repository in write mode
        PersonsDBHelper personsDBHelper = new PersonsDBHelper(context);
        SQLiteDatabase personDB =   personsDBHelper.getWritableDatabase();
        insertTestdataNameVorname(personDB, "Santschi", "Oliver");
        insertTestdataNameVorname(personDB, "Zeller", "Stefan");
        insertTestdataNameVorname(personDB, "Santschi", "Nicole");
        insertTestdataNameVorname(personDB, "Roth", "Konrad");

    }

    private static void insertTestdataNameVorname(SQLiteDatabase sqLiteDatabase, String lastname, String prename) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(PersonsContract.Person.COLUMN_NAME_LASTNAME, lastname);
        values.put(PersonsContract.Person.COLUMN_NAME_PRENAME, prename);

        // Insert the new row, returning the primary key value of the new row
        sqLiteDatabase.insert(
                PersonsContract.Person.TABLE_NAME,
                null,
                values);

    }


    public static ArrayList<String> findAll(Context context) {
        PersonsDBHelper personsDBHelper = new PersonsDBHelper(context);
        SQLiteDatabase db =   personsDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PersonsContract.Person._ID,
                PersonsContract.Person.COLUMN_NAME_LASTNAME,
                PersonsContract.Person.COLUMN_NAME_PRENAME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PersonsContract.Person.COLUMN_NAME_LASTNAME + " ASC";

        Cursor cursor = db.query(
                PersonsContract.Person.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        cursor.moveToFirst();
        final ArrayList<String> returnList = new ArrayList<String>();
        while (cursor.isLast()==false) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(PersonsContract.Person._ID));
            String nachname = cursor.getString(cursor.getColumnIndexOrThrow(PersonsContract.Person.COLUMN_NAME_LASTNAME));
            String vorname = cursor.getString(cursor.getColumnIndexOrThrow(PersonsContract.Person.COLUMN_NAME_PRENAME));
            cursor.moveToNext();

            returnList.add(nachname+" "+vorname+"("+itemId+")");
        }

        return returnList;

    }



}
