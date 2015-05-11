package com.hyperglobal.ribbit;

import android.app.AlertDialog;
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


public class EditFriendsActivity extends ListActivity {

    public static final String TAG = EditFriendsActivity.class.getSimpleName();

    protected List<ParseUser> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_edit_friends);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setProgressBarIndeterminateVisibility(true);

        ParseQuery<ParseUser> query = ParseUser.getQuery(); // new query
        query.orderByAscending(ParseConstants.KEY_USERNAME); // order results
        query.setLimit(1000); // limit results to 1000
        query.findInBackground(new FindCallback<ParseUser>() { // execute query
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null){
                    // Success
                    setProgressBarIndeterminateVisibility(false);
                    mUsers = users; // set member variable equal to retrieved list of users
                    String[] usernames = new String[mUsers.size()]; // define new string array to hold user name list
                    int i = 0; // init iteration var
                    for (ParseUser user : mUsers){ // for each user in the list mUsers...
                        usernames[i] = user.getUsername(); // assign slot in array with user name from retrieved list
                        i++; // iterate
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(
                            EditFriendsActivity.this, android.R.layout.simple_list_item_checked, usernames); // initialise new adapter, pass in context, layout (a list with checkboxes), and the username array
                    setListAdapter(adapter); // set the list adapter to the adapter we've just defined; ensure the class is extending ListActivity
                } else {
                    Log.e(TAG, e.getMessage());
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditFriendsActivity.this);
                    builder.setTitle(R.string.generic_error_title)
                            .setMessage(e.getMessage())
                            .setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
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
