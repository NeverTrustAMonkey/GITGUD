package edu.tacoma.uw.plsanch.gitgud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class EditGuideActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guide);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        changeHero((Spinner) parent, imageView);
    }

    public void changeHero(Spinner theSpinner, ImageView theImageView){
        int position = theSpinner.getSelectedItemPosition();
        switch (position) {
            case 0:theImageView.setImageResource(R.mipmap.icon_genji);break;
            case 1:theImageView.setImageResource(R.mipmap.icon_mccree);break;
            case 2:theImageView.setImageResource(R.mipmap.icon_pharah);break;
            case 3:theImageView.setImageResource(R.mipmap.icon_reaper);break;
            case 4:theImageView.setImageResource(R.mipmap.icon_soldier76);break;
            case 5:theImageView.setImageResource(R.mipmap.icon_sombra);break;
            case 6:theImageView.setImageResource(R.mipmap.icon_tracer);break;
            case 7:theImageView.setImageResource(R.mipmap.icon_bastion);break;
            case 8:theImageView.setImageResource(R.mipmap.icon_hanzo);break;
            case 9:theImageView.setImageResource(R.mipmap.icon_junkrat);break;
            case 10:theImageView.setImageResource(R.mipmap.icon_mei);break;
            case 11:theImageView.setImageResource(R.mipmap.icon_torbjorn);break;
            case 12:theImageView.setImageResource(R.mipmap.icon_widowmaker);break;
            case 13:theImageView.setImageResource(R.mipmap.icon_dva);break;
            case 14:theImageView.setImageResource(R.mipmap.icon_reinhardt);break;
            case 15:theImageView.setImageResource(R.mipmap.icon_roadhog);break;
            case 16:theImageView.setImageResource(R.mipmap.icon_winston);break;
            case 17:theImageView.setImageResource(R.mipmap.icon_zarya);break;
            case 18:theImageView.setImageResource(R.mipmap.icon_ana);break;
            case 19:theImageView.setImageResource(R.mipmap.icon_lucio);break;
            case 20:theImageView.setImageResource(R.mipmap.icon_mercy);break;
            case 21:theImageView.setImageResource(R.mipmap.icon_symmetra);break;
            case 22:theImageView.setImageResource(R.mipmap.icon_zenyatta);break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
