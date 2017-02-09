package edu.tacoma.uw.plsanch.gitgud;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;



public class CharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.hero_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hero_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        //Left Side Spinner
        int position1 = spinner1.getSelectedItemPosition ();
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        switch (position1){
            case 0:imageView1.setImageResource(R.mipmap.icon_genji);break;
            case 1:imageView1.setImageResource(R.mipmap.icon_mccree);break;
            case 2:imageView1.setImageResource(R.mipmap.icon_pharah);break;
            case 3:imageView1.setImageResource(R.mipmap.icon_reaper);break;
            case 4:imageView1.setImageResource(R.mipmap.icon_soldier76);break;
            case 5:imageView1.setImageResource(R.mipmap.icon_sombra);break;
            case 6:imageView1.setImageResource(R.mipmap.icon_tracer);break;
            case 7:imageView1.setImageResource(R.mipmap.icon_bastion);break;
            case 8:imageView1.setImageResource(R.mipmap.icon_hanzo);break;
            case 9:imageView1.setImageResource(R.mipmap.icon_junkrat);break;
            case 10:imageView1.setImageResource(R.mipmap.icon_mei);break;
            case 11:imageView1.setImageResource(R.mipmap.icon_torbjorn);break;
            case 12:imageView1.setImageResource(R.mipmap.icon_widowmaker);break;
            case 13:imageView1.setImageResource(R.mipmap.icon_dva);break;
            case 14:imageView1.setImageResource(R.mipmap.icon_reinhardt);break;
            case 15:imageView1.setImageResource(R.mipmap.icon_roadhog);break;
            case 16:imageView1.setImageResource(R.mipmap.icon_winston);break;
            case 17:imageView1.setImageResource(R.mipmap.icon_zarya);break;
            case 18:imageView1.setImageResource(R.mipmap.icon_ana);break;
            case 19:imageView1.setImageResource(R.mipmap.icon_lucio);break;
            case 20:imageView1.setImageResource(R.mipmap.icon_mercy);break;
            case 21:imageView1.setImageResource(R.mipmap.icon_symmetra);break;
            case 22:imageView1.setImageResource(R.mipmap.icon_zenyatta);break;
        }

        //Right Side Spinner
        int position2 = spinner2.getSelectedItemPosition ();
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        switch (position2){
            case 0:imageView2.setImageResource(R.mipmap.icon_genji);break;
            case 1:imageView2.setImageResource(R.mipmap.icon_mccree);break;
            case 2:imageView2.setImageResource(R.mipmap.icon_pharah);break;
            case 3:imageView2.setImageResource(R.mipmap.icon_reaper);break;
            case 4:imageView2.setImageResource(R.mipmap.icon_soldier76);break;
            case 5:imageView2.setImageResource(R.mipmap.icon_sombra);break;
            case 6:imageView2.setImageResource(R.mipmap.icon_tracer);break;
            case 7:imageView2.setImageResource(R.mipmap.icon_bastion);break;
            case 8:imageView2.setImageResource(R.mipmap.icon_hanzo);break;
            case 9:imageView2.setImageResource(R.mipmap.icon_junkrat);break;
            case 10:imageView2.setImageResource(R.mipmap.icon_mei);break;
            case 11:imageView2.setImageResource(R.mipmap.icon_torbjorn);break;
            case 12:imageView2.setImageResource(R.mipmap.icon_widowmaker);break;
            case 13:imageView2.setImageResource(R.mipmap.icon_dva);break;
            case 14:imageView2.setImageResource(R.mipmap.icon_reinhardt);break;
            case 15:imageView2.setImageResource(R.mipmap.icon_roadhog);break;
            case 16:imageView2.setImageResource(R.mipmap.icon_winston);break;
            case 17:imageView2.setImageResource(R.mipmap.icon_zarya);break;
            case 18:imageView2.setImageResource(R.mipmap.icon_ana);break;
            case 19:imageView2.setImageResource(R.mipmap.icon_lucio);break;
            case 20:imageView2.setImageResource(R.mipmap.icon_mercy);break;
            case 21:imageView2.setImageResource(R.mipmap.icon_symmetra);break;
            case 22:imageView2.setImageResource(R.mipmap.icon_zenyatta);break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
