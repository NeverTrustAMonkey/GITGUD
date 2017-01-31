package edu.tacoma.uw.plsanch.gitgud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Spinner dropdown1 = (Spinner)findViewById(R.id.spinner1);
        String[] items1 = new String[]{"Genji", "McCree", "Pharah", "Soldier: 76", "Sombra", "Tracer",
                "Bastion", "Hanzo", "Junkrat", "Mei", "Torbjorn", "Widowmaker",
                "D.Va", "Reinhardt", "Roadhog", "Winston", "Zarya",
                "Ana", "Lucio", "Mercy", "Symmetra", "Zenyatta"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        Spinner dropdown2 = (Spinner)findViewById(R.id.spinner2);
        String[] items2 = new String[]{"Genji", "McCree", "Pharah", "Soldier: 76", "Sombra", "Tracer",
                "Bastion", "Hanzo", "Junkrat", "Mei", "Torbjorn", "Widowmaker",
                "D.Va", "Reinhardt", "Roadhog", "Winston", "Zarya",
                "Ana", "Lucio", "Mercy", "Symmetra", "Zenyatta"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
    }
}
