package ch.santo.howoldis.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import ch.santo.howoldis.R;
import ch.santo.howoldis.business.DateCalculator;
import org.joda.time.LocalDate;

/**
 * Created by Oliver Santschi on 25.01.14.
 */
public class DetailViewSantschi extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailviewsantschi);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //get the textView-Element from the view
        TextView t=(TextView)findViewById(R.id.alterJael);
        t.setText(DateCalculator.getAge(new LocalDate (2006, 7, 11)));

        t=(TextView)findViewById(R.id.alterAlva);
        t.setText(DateCalculator.getAge(new LocalDate (2008, 3, 13)));

        t=(TextView)findViewById(R.id.alterLina);
        t.setText(DateCalculator.getAge(new LocalDate (2010, 10, 2)));

        t=(TextView)findViewById(R.id.alterSiri);
        t.setText(DateCalculator.getAge(new LocalDate (2012, 3, 13)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}