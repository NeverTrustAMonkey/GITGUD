package edu.tacoma.uw.plsanch.gitgud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import edu.tacoma.uw.plsanch.gitgud.guide.Guide;

public class GuideBrowserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        GuideFragment.OnListFragmentInteractionListener {

    Spinner spinner;
    Button createButton;
    Button toggleButton;

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

        if (savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null) {
            GuideFragment courseFragment = new GuideFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.guide_container, courseFragment)
                    .commit();
        }
    }

    public void createNewGuide(View v) {
        Intent intent = new Intent(this, EditGuideActivity.class);
        startActivity(intent);
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
                .replace(R.id.activity_guide_browser, guideContentFragment)
                .addToBackStack(null)
                .commit();
    }
}
