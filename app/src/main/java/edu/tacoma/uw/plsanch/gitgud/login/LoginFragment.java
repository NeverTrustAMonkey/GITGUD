package edu.tacoma.uw.plsanch.gitgud.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.tacoma.uw.plsanch.gitgud.R;
import edu.tacoma.uw.plsanch.gitgud.guide.BookmarkBrowserActivity;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferenceEntry;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferencesHelper;


/**
 * The LoginFragment is the fragment that replaces RegisterFragment when a user is logged in.
 */
public class LoginFragment extends Fragment {

    // unused listener
    private OnFragmentInteractionListener mListener;

    /**
     * empty constructor
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * calls the super method
     * @param savedInstanceState is the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * onCreateView holds the onClickListener for the Bookmarks button as well as inflating the fragment
     * @param inflater is the inflater for the fragment
     * @param container is the layout holding the fragment
     * @param savedInstanceState is the saved instance state
     * @return the created view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(
                sharedPreferences);
        SharedPreferenceEntry entry = sharedPreferencesHelper.getLoginInfo();

        if (entry.isLoggedIn()){
            TextView email = (TextView) v.findViewById(R.id.email_login_text);
            email.setText("You are logged in as " + entry.getEmail());
        }
        Button bookmarkButton = (Button) v.findViewById(R.id.bookmark_button);
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookmarkBrowserActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    /**
     * calls super method
     * @param context is the context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * calls super method
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
