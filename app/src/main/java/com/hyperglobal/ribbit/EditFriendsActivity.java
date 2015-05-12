package com.hyperglobal.ribbit;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

// This activity is a container for a ListFragment that shows the users (UsersFragment). The fragment is added in the layout file.

public class EditFriendsActivity extends ActionBarActivity implements UsersFragment.OnFragmentInteractionListener {

    public static final String TAG = EditFriendsActivity.class.getSimpleName(); // TAG for Log messages

    protected List<ParseUser> mUsers; // member variable for users

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // enable spinner for loading
        setContentView(R.layout.activity_edit_friends);
    }

    public void onFragmentInteraction(String string){

    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getFragmentManager();
        UsersFragment list = new UsersFragment();
        fragmentManager.beginTransaction().add(android.R.id.content, list).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
