package edu.tacoma.uw.plsanch.gitgud;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import edu.tacoma.uw.plsanch.gitgud.guide.Guide;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GuideContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GuideContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideContentFragment extends Fragment {

    public final static String GUIDE_ITEM_SELECTED = "guide_selected";
    private TextView mTitleView;
    private TextView mAuthorView;
    private ImageView mIconView;
    private TextView mContentView;
    private Guide mGuide;

    Button createButton;
    Button toggleButton;
    Spinner spinner;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGuide = (Guide) getArguments().getSerializable(GUIDE_ITEM_SELECTED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_guide_content, container, false);
        mIconView = (ImageView) v.findViewById(R.id.IconView);
        mTitleView = (TextView) v.findViewById(R.id.TitleView);
        mAuthorView = (TextView) v.findViewById(R.id.AuthorView);
        mContentView = (TextView) v.findViewById(R.id.ContentView);
        updateView(mGuide);
        createButton = (Button) getActivity().findViewById(R.id.createButton);
        toggleButton = (Button) getActivity().findViewById(R.id.toggleButton);
        spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        createButton.setVisibility(View.GONE);
        toggleButton.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void onDestroyView(){
        createButton.setVisibility(View.VISIBLE);
        toggleButton.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
        super.onDestroyView();
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

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

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView((Guide) args.getSerializable(GUIDE_ITEM_SELECTED));
        }
    }

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
}
