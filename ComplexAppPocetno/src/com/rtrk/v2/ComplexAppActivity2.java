package com.rtrk.v2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rtrk.R;

public class ComplexAppActivity2 extends ListActivity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.v2);
        /** Create an array of Strings, that will be put to our ListActivity. */
        String[] names = new String[] { "Novi Sad", "Sremska Mitrovica",
                "Vršac", "Pančevo", "Subotica", "Kikinda", "Inđija",
                "Stara Pazova", "Bačka Palanka", "Irig", "Sombor", "Zrenjanin",
                "Kula", "Debeljača", "Mali Iđoš" };
        /**
         * Create an ArrayAdapter, that will actually make the Strings above
         * appear in the ListView.
         */
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.v2_row,
                names));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        /** Get the item that was clicked. */
        Object o = this.getListAdapter().getItem(position);
        String keyword = o.toString();
        Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
                .show();
    }
}
