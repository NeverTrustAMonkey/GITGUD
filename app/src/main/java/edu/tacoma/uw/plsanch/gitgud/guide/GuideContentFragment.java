package edu.tacoma.uw.plsanch.gitgud.guide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.plsanch.gitgud.R;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferenceEntry;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferencesHelper;


/**
 * GuideContentFragment is a fragment that displays the guide content to the user
 */
public class GuideContentFragment extends Fragment {

    //argument for serializable
    public final static String GUIDE_ITEM_SELECTED = "guide_selected";
    //base url for webaccess for guide bookmarking
    private String BOOKMARK_URL = "http://cssgate.insttech.washington.edu/~_450bteam9/bookmark.php?cmd=add";

    //layouts for information displays
    private TextView mTitleView;
    private TextView mAuthorView;
    private ImageView mIconView;
    private TextView mContentView;

    //the guide being viewed
    private Guide mGuide;

    //parent view buttons
    Button createButton;
    Button toggleButton;
    Spinner spinner;

    //buttons
    Button bookmarkButton;
    Button editButton;

    //SharedPreferenceEntry to check if the user is logged in.
    SharedPreferenceEntry entry;

    /**
     * empty constructor
     */
    public GuideContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GuideContentFragment.
     */
    public static GuideContentFragment newInstance() {
        GuideContentFragment fragment = new GuideContentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * onCreate handles onfragmentinteraction for serializable
     * @param savedInstanceState is the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGuide = (Guide) getArguments().getSerializable(GUIDE_ITEM_SELECTED);
        }
    }

    /**
     * onCreateView handles the assignment of all widgets, holds the onclicklistener for bookmark button
     * hides the parent widgets, checks to see if the user viewing the guide is the author,
     * and holds the onclick for the author choosing to edit the guide.
     * @param inflater is the inflater of the view
     * @param container is the holder of the fragment
     * @param savedInstanceState is the saved instance state
     * @return a constructed view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_guide_content, container, false);

        mIconView = (ImageView) v.findViewById(R.id.IconView);
        mTitleView = (TextView) v.findViewById(R.id.TitleView);
        mAuthorView = (TextView) v.findViewById(R.id.AuthorView);
        mContentView = (TextView) v.findViewById(R.id.ContentView);
        createButton = (Button) getActivity().findViewById(R.id.createButton);
        toggleButton = (Button) getActivity().findViewById(R.id.toggleButton);
        editButton = (Button) v.findViewById(R.id.editButton);
        spinner = (Spinner) getActivity().findViewById(R.id.spinner);

        updateView(mGuide);

        bookmarkButton = (Button) v.findViewById(R.id.favoriteButton);
        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myURL = BOOKMARK_URL;
                myURL += "&name=" + entry.getEmail();
                myURL += "&id=" + mGuide.getmGuideId();
                BookMarkGuideTask task = new BookMarkGuideTask();
                task.execute(new String[]{myURL});
            }
        });


        createButton.setVisibility(View.GONE);
        toggleButton.setVisibility(View.GONE); //hides parent widgets
        spinner.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(
                sharedPreferences);
        entry = sharedPreferencesHelper.getLoginInfo();

        //shows the edit button if the user that submitted the guide is viewing it
        if(entry.getEmail().equals(mGuide.getmGuideAuthor())){
            editButton.setVisibility(View.VISIBLE);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditGuideActivity.class);
                    String theGuideId = mGuide.getmGuideId();
                    intent.putExtra("GUIDE_ID", theGuideId);
                    String theGuideTitle = mGuide.getmGuideTitle();
                    intent.putExtra("GUIDE_TITLE", theGuideTitle);
                    String theGuideText = mGuide.getmGuideText();
                    intent.putExtra("GUIDE_TEXT", theGuideText);
                    startActivity(intent);
                }
            });
        }
        //sets the last viewed guide for content sharing
        if(getActivity().getClass().getSimpleName().equals("BookmarkBrowserActivity")){
            ((BookmarkBrowserActivity) getActivity()).setLastViewed(mGuide);
        }else {
            ((GuideBrowserActivity) getActivity()).setLastViewed(mGuide);
        }
        return v;
    }

    /**
     * calls super method and shows the widgets if the parent activity is guidebrowser
     */
    @Override
    public void onDestroyView(){
        if(getActivity().getClass().getSimpleName().equals("BookmarkBrowserActivity")){

        }else {
            createButton.setVisibility(View.VISIBLE);
            toggleButton.setVisibility(View.VISIBLE); //shows parent widgets
            spinner.setVisibility(View.VISIBLE);
        }
        super.onDestroyView();
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

    /**
     * During startup, check if there are arguments passed to the fragment
     * onStart is a good place to do this because the layout has already been
     * applied to the fragment at this point so we can safely call the method
     * below that sets the article text.
     */
    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView((Guide) args.getSerializable(GUIDE_ITEM_SELECTED));
        }
    }

    /**
     * updateView sends the guide's information to the widgets to be displayed to the user.
     * @param guide is the guide being displayed
     */
    private void updateView(Guide guide) {
        if (guide != null) {
            mTitleView.setText(guide.getmGuideTitle());
            mAuthorView.setText("By: " + guide.getmGuideAuthor());
            mContentView.setText(guide.getmGuideText());
            String heroIconName = guide.getmGuideHero().toLowerCase();
            if(heroIconName.equals("genji"))mIconView.setImageResource(R.mipmap.icon_genji);
            if(heroIconName.equals("mccree"))mIconView.setImageResource(R.mipmap.icon_mccree);
            if(heroIconName.equals("pharah"))mIconView.setImageResource(R.mipmap.icon_pharah);
            if(heroIconName.equals("reaper"))mIconView.setImageResource(R.mipmap.icon_reaper);
            if(heroIconName.equals("soldier76"))mIconView.setImageResource(R.mipmap.icon_soldier76);
            if(heroIconName.equals("sombra"))mIconView.setImageResource(R.mipmap.icon_sombra);
            if(heroIconName.equals("tracer"))mIconView.setImageResource(R.mipmap.icon_tracer);
            if(heroIconName.equals("bastion"))mIconView.setImageResource(R.mipmap.icon_bastion);
            if(heroIconName.equals("hanzo"))mIconView.setImageResource(R.mipmap.icon_hanzo);
            if(heroIconName.equals("junkrat"))mIconView.setImageResource(R.mipmap.icon_junkrat);
            if(heroIconName.equals("mei"))mIconView.setImageResource(R.mipmap.icon_mei);
            if(heroIconName.equals("torbjorn"))mIconView.setImageResource(R.mipmap.icon_torbjorn);
            if(heroIconName.equals("widowmaker"))mIconView.setImageResource(R.mipmap.icon_widowmaker);
            if(heroIconName.equals("d.va"))mIconView.setImageResource(R.mipmap.icon_dva);
            if(heroIconName.equals("reinhardt"))mIconView.setImageResource(R.mipmap.icon_reinhardt);
            if(heroIconName.equals("roadhog"))mIconView.setImageResource(R.mipmap.icon_roadhog);
            if(heroIconName.equals("winston"))mIconView.setImageResource(R.mipmap.icon_winston);
            if(heroIconName.equals("zarya"))mIconView.setImageResource(R.mipmap.icon_zarya);
            if(heroIconName.equals("ana"))mIconView.setImageResource(R.mipmap.icon_ana);
            if(heroIconName.equals("lucio"))mIconView.setImageResource(R.mipmap.icon_lucio);
            if(heroIconName.equals("mercy"))mIconView.setImageResource(R.mipmap.icon_mercy);
            if(heroIconName.equals("symmetra"))mIconView.setImageResource(R.mipmap.icon_symmetra);
            if(heroIconName.equals("zenyatta"))mIconView.setImageResource(R.mipmap.icon_zenyatta);
        }
    }

    /**
     * BookMarkGuideTask is the webaccess async task that bookmarks a guide for the user
     */
    private class BookMarkGuideTask extends AsyncTask<String, Void, String> {

        /**
         * doInBackground tries the urls by accessing the internet
         * @param urls are the urls to be tried
         * @return a response build for error reporting
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to bookmark, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * informs the user that their guide has been bookmarked
         * @param result is the seult of the doInBackground
         */
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity()
                    , "Guide Bookmarked"
                    , Toast.LENGTH_LONG)
                    .show();
        }
    }
}

