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

/**
 * BookmarkBrowserActvity is the holder of bookmarkfragment and the recyclerview for browsing guides
 * and also holds the content fragment when called
 * is a duplicate of GuideBrowser that ignores things like searching and creating new guides
 */
public class BookmarkBrowserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        GuideFragment.OnListFragmentInteractionListener {


    //the hero search selecting spinner
    Spinner spinner;
    //the createNewGuideButton
    Button createButton;
    //the togglebutton for searching by hero
    Button toggleButton;

    //Bookmarkfragment is used to start the guide list
    BookmarkFragment guideFragment;

    //Held information from the last guide viewed for content sharing purposes
    Guide lastViewed;

    /**
     * onCreate sets up the activtiy and grabs widgets by id
     * @param savedInstanceState
     */
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

    /**
     * onCreateOptionsMenu inflates the options menu view
     * @param menu is the menu calling the inflation
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_guide_browser, menu);
        return true;
    }

    /**
     * onOptionsItemSelected listens for the option to be cliked and runs an alert dialog if it is
     * @param item is the item being clicked
     * @return true
     */
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

    /**
    * Sets the last viewed guide
    * @param theGuide is the guide being viewed in the contentfragment
    */
    public void setLastViewed(Guide theGuide){
        lastViewed = theGuide;
    }

    /**
     * updates the guide list when the spinner is changed
     * @param parent is the adapterview
     * @param view is the calling view
     * @param position is the position of the spinner
     * @param id is the id of the spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * unused method
     * @param parent is the adapterview
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * opens a guidecontentfragment when a guide on the browser is clicked
     * @param guide is the guide being clicked.
     */
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
