package edu.tacoma.uw.plsanch.gitgud.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.tacoma.uw.plsanch.gitgud.R;

public class GuideBrowserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        GuideFragment.OnListFragmentInteractionListener {

    Spinner spinner;
    Button createButton;
    Button toggleButton;

    GuideFragment guideFragment;

    boolean toggled;

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
        toggled = false;

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            guideFragment = new GuideFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.guide_container, guideFragment)
                    .commit();
        }

    }

    public void onToggle(View v) {
        if(toggled){
            toggled = false;
            guideFragment.updateList("All");

        } else {
            toggled = true;
            updateGuideList(v);
        }
    }

    public void createNewGuide(View v) {
        Intent intent = new Intent(this, EditGuideActivity.class);
        startActivity(intent);
    }

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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateGuideList(view);
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
                .replace(R.id.activity_guide_browser, guideContentFragment)
                .addToBackStack(null)
                .commit();
    }
}
