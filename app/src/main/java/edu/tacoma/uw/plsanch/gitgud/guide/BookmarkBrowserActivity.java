package edu.tacoma.uw.plsanch.gitgud.guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import edu.tacoma.uw.plsanch.gitgud.R;

public class BookmarkBrowserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        GuideFragment.OnListFragmentInteractionListener {

    Spinner spinner;
    Button createButton;
    Button toggleButton;

    BookmarkFragment guideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_browser);

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            guideFragment = new BookmarkFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bookmark_container, guideFragment)
                    .commit();
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        createButton = (Button) findViewById(R.id.createButton);
        toggleButton = (Button) findViewById(R.id.toggleButton);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onListFragmentInteraction(Guide guide) {

        GuideContentFragment guideContentFragment = new GuideContentFragment();
        Bundle args = new Bundle();
        args.putSerializable(GuideContentFragment.GUIDE_ITEM_SELECTED, guide);
        guideContentFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_bookmark_browser, guideContentFragment)
                .addToBackStack(null)
                .commit();
    }
}
