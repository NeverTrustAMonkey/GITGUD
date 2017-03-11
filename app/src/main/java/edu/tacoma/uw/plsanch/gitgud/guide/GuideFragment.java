package edu.tacoma.uw.plsanch.gitgud.guide;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.plsanch.gitgud.R;

/**
 * The GuideFragment is the base design for viewing guides as fragments on the GuideBrowser
 */
public class GuideFragment extends Fragment {

    //the column-count
    private static final String ARG_COLUMN_COUNT = "column-count";
    //base url for webaccess
    private static final String GUIDE_URL
            = "http://cssgate.insttech.washington.edu/~_450bteam9/guide.php?cmd=guides&name=";
    //only one column
    private int mColumnCount = 1;
    //listener for guide interaction
    private OnListFragmentInteractionListener mListener;
    //recycler view that holds the guide items
    private RecyclerView mRecyclerView;
    //the list of guides
    private List<Guide> mGuideList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GuideFragment() {
    }

    /**
     * Standard constructor for GuideFragment
     * @param columnCount is the number of columns
     * @return the constructed fragment
     */
    public static GuideFragment newInstance(int columnCount) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
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

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * onCreateView sets up the recyclerview to display the fragments
     * @param inflater is the inflating class
     * @param container is the holder of the fragment
     * @param savedInstanceState is the saved instance state
     * @return the constructed view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            updateList("All");
        }
        return view;
    }

    /**
     * updateList calls the webaccess asynctask to download updated information on the guides
     * @param name is the name of the hero being searched for
     */
    public void updateList(String name){
        String myUrl = (GUIDE_URL + name);
        DownloadGuidesTask task = new DownloadGuidesTask();
        task.execute(new String[]{myUrl});
        Toast toast = Toast.makeText(getActivity(), "Loading Guides, Please Wait...", Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * calls the super method
     * @param context is the context
     */
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    /**
     * calls the super method
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Guide item);
    }

    /**
     * DownloadGuidesTask is the webaccess asynctask that retreives the guide information from the database
     */
    private class DownloadGuidesTask extends AsyncTask<String, Void, String> {

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
                    response = "Unable to download the list of guides, Reason: "
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
         * onPostExecute receives the status of the webaccess and communicates it to the user.
         * if everything is good it builds the view of guides
         * @param result is the result of doInBackground
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            mGuideList = new ArrayList<>();
            result = Guide.parseGuideJSON(result, mGuideList);
            // Something wrong with the JSON returned.
            if (result != null) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            // Everything is good, show the list of courses.
            if (!mGuideList.isEmpty()) {
                mRecyclerView.setAdapter(new MyGuideRecyclerViewAdapter(mGuideList, mListener));
            }
        }

    }
}
