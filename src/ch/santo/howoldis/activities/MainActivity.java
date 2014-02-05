package ch.santo.howoldis.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ch.santo.howoldis.R;
import ch.santo.howoldis.business.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Create Liste
        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = PersonService.findAll(this);


        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        // create an array of Strings, that will be put to our ListActivity
//        ArrayAdapter<Model> adapter = new InteractiveArrayAdapter(this,
//                getModel());
//        setListAdapter(adapter);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);


            }

            // insertTestdata();



        });
        //get the textView-Element from the view
//        TextView t=(TextView)findViewById(R.id.testtext);
//        t.setText(readTestdata());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //TODO: openSearch();
                return true;
            case R.id.action_settings:
                //TODO: openSettings();
                return true;
            case R.id.action_addTestdata:
                PersonService.insertTestdata(this);
                this.recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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





    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
