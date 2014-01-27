package ch.santo.howoldis.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import ch.santo.howoldis.R;
import ch.santo.howoldis.activities.DetailViewSantschi;
import ch.santo.howoldis.activities.DetailViewZeller;
import ch.santo.howoldis.persistence.PersonsDBHelper;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        PersonsDBHelper dbHelper = new PersonsDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int version = db.getVersion();

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
}
