package ch.santo.howoldis.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import ch.santo.howoldis.R;
import ch.santo.howoldis.business.DateCalculator;

/**
 * Created by Oliver Santschi on 25.01.14.
 */
public class DetailViewSantschi extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailviewsantschi);

        //get the textView-Element from the view
        TextView t=(TextView)findViewById(R.id.alterJael);
        t.setText(DateCalculator.getAge());
    }
}