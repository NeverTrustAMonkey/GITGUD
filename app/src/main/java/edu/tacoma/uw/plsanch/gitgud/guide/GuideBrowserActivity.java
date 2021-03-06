package edu.tacoma.uw.plsanch.gitgud.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.tacoma.uw.plsanch.gitgud.R;

/**
 * GuideBrowserActvity is the holder of guidefragment and the recyclerview for browsing guides
 * and also holds the content fragment when called
 */
public class GuideBrowserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        GuideFragment.OnListFragmentInteractionListener {

    //the hero search selecting spinner
    Spinner spinner;
    //the createNewGuideButton
    Button createButton;
    //the togglebutton for searching by hero
    Button toggleButton;

    //Guidefragment is used to start the guide list
    GuideFragment guideFragment;

    //boolean for whether or not the togglebutton is toggled
    boolean toggled;

    //Held information from the last guide viewed for content sharing purposes
    Guide lastViewed;

    /**
     * onCreate sets up the activtiy and grabs widgets by id
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_browser);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        createButton = (Button) findViewById(R.id.createButton);
        toggleButton = (Button) findViewById(R.id.toggleButton);
        spinner.setVisibility(View.VISIBLE);
        createButton.setVisibility(View.VISIBLE);
        toggleButton.setVisibility(View.VISIBLE);

        toggled = false;

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            guideFragment = new GuideFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.guide_container, guideFragment)
                    .commit();
        }
        lastViewed = new Guide("0","<Title of Guide>", "<Author of Guide>","<Hero of Guide>","<Content of Guide>");

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
     * is called everytime the toggle button is toggled,
     * switches between hero searching and viewing all guides
     * @param v is the calling view
     */
    public void onToggle(View v) {
        if(toggled){
            toggled = false;
            guideFragment.updateList("All");

        } else {
            toggled = true;
            updateGuideList(v);
        }
    }

    /**
     * createNewGuide is the onClick method for the createButton
     * @param v
     */
    public void createNewGuide(View v) {
        Intent intent = new Intent(this, EditGuideActivity.class);
        startActivity(intent);
    }

    /**
     * updateGuideList is called whenever search options are changed to refresh the browser
     * @param v is the calling view
     */
    public void updateGuideList(View v) {
        if(toggled){
            int position = spinner.getSelectedItemPosition();
            switch (position) {
                case 0:guideFragment.updateList("Genji");break;
                case 1:guideFragment.updateList("McCree");break;
                case 2:guideFragment.updateList("Pharah");break;
                case 3:guideFragment.updateList("Reaper");break;
                case 4:guideFragment.updateList("Soldier76");break;
                case 5:guideFragment.updateList("Sombra");break;
                case 6:guideFragment.updateList("Tracer");break;
                case 7:guideFragment.updateList("Bastion");break;
                case 8:guideFragment.updateList("Hanzo");break;
                case 9:guideFragment.updateList("Junkrat");break;
                case 10:guideFragment.updateList("Mei");break;
                case 11:guideFragment.updateList("Torbjorn");break;
                case 12:guideFragment.updateList("Widowmaker");break;
                case 13:guideFragment.updateList("D.va");break;
                case 14:guideFragment.updateList("Reinhardt");break;
                case 15:guideFragment.updateList("Roadhog");break;
                case 16:guideFragment.updateList("Winston");break;
                case 17:guideFragment.updateList("Zarya");break;
                case 18:guideFragment.updateList("Ana");break;
                case 19:guideFragment.updateList("Lucio");break;
                case 20:guideFragment.updateList("Mercy");break;
                case 21:guideFragment.updateList("Symmetra");break;
                case 22:guideFragment.updateList("Zenyatta");break;
            }
        }
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
        updateGuideList(view);
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
                .replace(R.id.activity_guide_browser, guideContentFragment)
                .addToBackStack(null)
                .commit();
    }
}
