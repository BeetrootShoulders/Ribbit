package com.hyperglobal.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.hyperglobal.ribbit.dummy.DummyContent;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class UsersFragment extends ListFragment {

    public static final String TAG = EditFriendsActivity.class.getSimpleName();

    protected List<ParseUser> mUsers;

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO: Change Adapter to display your content
        ParseQuery<ParseUser> query = ParseUser.getQuery(); // new query
        query.orderByAscending(ParseConstants.KEY_USERNAME); // order results
        query.setLimit(1000); // limit results to 1000
        query.findInBackground(new FindCallback<ParseUser>() { // execute query
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {// Success

                    mUsers = users; // set member variable equal to retrieved list of users
                    String[] usernames = new String[mUsers.size()]; // define new string array to hold user name list
                    int i = 0; // init iteration var
                    for (ParseUser user : mUsers) { // for each user in the list mUsers...
                        usernames[i] = user.getUsername(); // assign slot in array with user name from retrieved list
                        i++; // iterate
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter(
                            getActivity(), android.R.layout.simple_list_item_checked, usernames); // initialise new adapter, pass in context, layout (a list with checkboxes), and the username array
                    setListAdapter(adapter); // set the list adapter to the adapter we've just defined; ensure the class is extending ListActivity
                } else {
                    Log.e(TAG, e.getMessage());
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.generic_error_title)
                            .setMessage(e.getMessage())
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });



    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
