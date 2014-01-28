package ch.santo.howoldis.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ch.santo.howoldis.R;
import ch.santo.howoldis.activities.DetailViewSantschi;
import ch.santo.howoldis.activities.DetailViewZeller;
import ch.santo.howoldis.business.DateCalculator;
import ch.santo.howoldis.persistence.PersonsContract;
import ch.santo.howoldis.persistence.PersonsDBHelper;
import org.joda.time.LocalDate;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertTestdata();



        setContentView(R.layout.main);
        //get the textView-Element from the view
        TextView t=(TextView)findViewById(R.id.testtext);
        t.setText(readTestdata());


    }

    /** Called when the user clicks the  button */
    public void buttonSantschiClick(View view) {
        Intent intent = new Intent(this, DetailViewSantschi.class);
        startActivity(intent);

    }

    /** Called when the user clicks the  button */
    public void buttonZellerClick(View view) {
        Intent intent = new Intent(this, DetailViewZeller.class);
        startActivity(intent);

    }

    private void insertTestdata() {
        // Gets the data repository in write mode
        PersonsDBHelper personsDBHelper = new PersonsDBHelper(this);
        SQLiteDatabase personDB =   personsDBHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(PersonsContract.Person.COLUMN_NAME_ENTRY_ID, 1);
        values.put(PersonsContract.Person.COLUMN_NAME_LASTNAME, "Santschi");
        values.put(PersonsContract.Person.COLUMN_NAME_PRENAME, "Oliver");

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = personDB.insert(
                PersonsContract.Person.TABLE_NAME,
                null,
                values);
    }

    private String readTestdata() {
        PersonsDBHelper personsDBHelper = new PersonsDBHelper(this);
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
                PersonsContract.Person.COLUMN_NAME_LASTNAME + " DESC";

        Cursor cursor = db.query(
                PersonsContract.Person.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        String returnString = "";
        cursor.moveToFirst();
        while (cursor.isLast()==false) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(PersonsContract.Person._ID));
            String nachname = cursor.getString(cursor.getColumnIndexOrThrow(PersonsContract.Person.COLUMN_NAME_LASTNAME));
            String vorname = cursor.getString(cursor.getColumnIndexOrThrow(PersonsContract.Person.COLUMN_NAME_PRENAME));
            cursor.moveToNext();

            returnString = returnString + String.valueOf(itemId)+" "+nachname+" "+vorname+";";
        }

        return returnString;

    }
}
