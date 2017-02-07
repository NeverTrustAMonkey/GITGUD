package edu.tacoma.uw.plsanch.gitgud;

import android.graphics.drawable.Icon;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class CharacterActivity extends AppCompatActivity {


    String[] HeroList = new String[]{"Genji", "McCree", "Pharah", "Soldier: 76", "Sombra", "Tracer",
            "Bastion", "Hanzo", "Junkrat", "Mei", "Torbjorn", "Widowmaker",
            "D.Va", "Reinhardt", "Roadhog", "Winston", "Zarya",
            "Ana", "Lucio", "Mercy", "Symmetra", "Zenyatta"};
    Spinner dropdown1;
    Spinner dropdown2;
    ImageView character1;
    ImageView character2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        dropdown1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, HeroList);
        dropdown1.setAdapter(adapter1);
        /*
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                System.out.println("it works...   ");
                character1.setImageResource(R.mipmap.icon_ana);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
        character1 = (ImageView) findViewById(R.id.imageView1);
        character1.setImageResource(R.mipmap.icon_overwatch);


    }

}
