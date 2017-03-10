package edu.tacoma.uw.plsanch.gitgud.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    Guide lastViewed;

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
        spinner.setVisibility(View.GONE);
        createButton.setVisibility(View.GONE);
        toggleButton.setVisibility(View.GONE);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_guide_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, I just found a guide on GIT-GUD for " + lastViewed.getmGuideHero() +
                    ", made by " + lastViewed.getmGuideAuthor() + ", with the title: " + lastViewed.getmGuideTitle() +
                    "\n\nAnd here is what it says:\n\n" + lastViewed.getmGuideText());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setLastViewed(Guide theGuide){
        lastViewed = theGuide;
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
