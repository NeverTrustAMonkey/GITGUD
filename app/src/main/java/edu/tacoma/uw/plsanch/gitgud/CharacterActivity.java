package edu.tacoma.uw.plsanch.gitgud;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static edu.tacoma.uw.plsanch.gitgud.R.mipmap.icon_counters_synergies;
import static edu.tacoma.uw.plsanch.gitgud.R.mipmap.icon_strengths_weaknesses;

/**
 * This Activity displays quickly accessible information on Heroes in the game Overwatch
 *
 *
 */
public class CharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    Spinner spinner2;
    TextView stats1;
    TextView stats2;
    String display1;
    String display2;
    ImageView imageView1;
    ImageView imageView2;
    ImageButton imageButton;
    String setting;//Binary switch
    Integer side;//Binary switch

    private static final String HERO_URL //Url base
            = "http://cssgate.insttech.washington.edu/~_450bteam9/hero.php?";

    /**
     * onCreate establishes the elements needed to display the hero information
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        stats1 = (TextView) findViewById(R.id.StatView1);
        stats2 = (TextView) findViewById(R.id.StatView2);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageButton = (ImageButton) findViewById(R.id.buttonImage);


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
        imageButton.setImageResource(icon_counters_synergies);
        stats1.setText(display1);
        stats2.setText(display2);
        setting = "counters";

        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(setting == "counters"){
                    setting = "strengths";
                    imageButton.setImageResource(icon_strengths_weaknesses);
                } else if(setting == "strengths"){
                    setting = "counters";
                    imageButton.setImageResource(icon_counters_synergies);
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Mode Changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        getWeb("Genji", setting, 1);
        stats2.setText(display1);
        stats1.setText(display1);

        Toast toast = Toast.makeText(getApplicationContext(), "Press the 'Counters and Synergies' Button to change display modes.", Toast.LENGTH_LONG);
        toast.show();
    }

    public void onClickListener(){

    }

    /**
     * getWeb is the helper method for accessing data from the database
     * it takes parameters that allow it to build a url for data access
     * It then calls the Async Task DownloadInfoTask
     *
     * @param theHero is the Hero being checked.
     * @param theColumn is the information about the Hero being accessed.
     * @param theSide is a binary variable that determines which side the info needs to be displayed on.
     */
    public void getWeb(String theHero, String theColumn, int theSide){

        String theURL = "";
        side = theSide;//Left:Right orientation
        theURL += HERO_URL;
        theURL += "cmd=" + theColumn;
        theURL += "&hero=" + theHero;
        DownloadInfoTask task = new DownloadInfoTask();
        task.execute(theURL);
    }

    /**
     * onItemSelected is a method that is called whenever a Spinner is set to a new value.
     *
     *
     * @param parent is the adapter that is linked
     * @param view is the spinner that is calling it
     * @param pos is the position of the spinner
     * @param id is the id of the parent
     */
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if(parent.getId() == spinner1.getId()) {
            int position1 = spinner1.getSelectedItemPosition();
            switch (position1) {
                case 0:
                    imageView1.setImageResource(R.mipmap.icon_genji);
                    getWeb("Genji", setting, 1);
                    break;
                case 1:
                    imageView1.setImageResource(R.mipmap.icon_mccree);
                    getWeb("McCree", setting, 1);
                    break;
                case 2:
                    imageView1.setImageResource(R.mipmap.icon_pharah);
                    getWeb("Pharah", setting, 1);
                    break;
                case 3:
                    imageView1.setImageResource(R.mipmap.icon_reaper);
                    getWeb("Reaper", setting, 1);
                    break;
                case 4:
                    imageView1.setImageResource(R.mipmap.icon_soldier76);
                    getWeb("Soldier76", setting, 1);
                    break;
                case 5:
                    imageView1.setImageResource(R.mipmap.icon_sombra);
                    getWeb("Sombra", setting, 1);
                    break;
                case 6:
                    imageView1.setImageResource(R.mipmap.icon_tracer);
                    getWeb("Tracer", setting, 1);
                    break;
                case 7:
                    imageView1.setImageResource(R.mipmap.icon_bastion);
                    getWeb("Bastion", setting, 1);
                    break;
                case 8:
                    imageView1.setImageResource(R.mipmap.icon_hanzo);
                    getWeb("Hanzo", setting, 1);
                    break;
                case 9:
                    imageView1.setImageResource(R.mipmap.icon_junkrat);
                    getWeb("Junkrat", setting, 1);
                    break;
                case 10:
                    imageView1.setImageResource(R.mipmap.icon_mei);
                    getWeb("Mei", setting, 1);
                    break;
                case 11:
                    imageView1.setImageResource(R.mipmap.icon_torbjorn);
                    getWeb("Torbjorn", setting, 1);
                    break;
                case 12:
                    imageView1.setImageResource(R.mipmap.icon_widowmaker);
                    getWeb("Widowmaker", setting, 1);
                    break;
                case 13:
                    imageView1.setImageResource(R.mipmap.icon_dva);
                    getWeb("D.va", setting, 1);
                    break;
                case 14:
                    imageView1.setImageResource(R.mipmap.icon_reinhardt);
                    getWeb("Reinhardt", setting, 1);
                    break;
                case 15:
                    imageView1.setImageResource(R.mipmap.icon_roadhog);
                    getWeb("Roadhog", setting, 1);
                    break;
                case 16:
                    imageView1.setImageResource(R.mipmap.icon_winston);
                    getWeb("Winston", setting, 1);
                    break;
                case 17:
                    imageView1.setImageResource(R.mipmap.icon_zarya);
                    getWeb("Zarya", setting, 1);
                    break;
                case 18:
                    imageView1.setImageResource(R.mipmap.icon_ana);
                    getWeb("Ana", setting, 1);
                    break;
                case 19:
                    imageView1.setImageResource(R.mipmap.icon_lucio);
                    getWeb("Lucio", setting, 1);
                    break;
                case 20:
                    imageView1.setImageResource(R.mipmap.icon_mercy);
                    getWeb("Mercy", setting, 1);
                    break;
                case 21:
                    imageView1.setImageResource(R.mipmap.icon_symmetra);
                    getWeb("Symmetra", setting, 1);
                    break;
                case 22:
                    imageView1.setImageResource(R.mipmap.icon_zenyatta);
                    getWeb("Zenyatta", setting, 1);
                    break;
            }

        }else if(parent.getId() == spinner2.getId()){
            //Right Side Spinner
            int position2 = spinner2.getSelectedItemPosition ();
            switch (position2) {
                case 0:
                    imageView2.setImageResource(R.mipmap.icon_genji);
                    getWeb("Genji", setting, 2);
                    break;
                case 1:
                    imageView2.setImageResource(R.mipmap.icon_mccree);
                    getWeb("McCree", setting, 2);
                    break;
                case 2:
                    imageView2.setImageResource(R.mipmap.icon_pharah);
                    getWeb("Pharah", setting, 2);
                    break;
                case 3:
                    imageView2.setImageResource(R.mipmap.icon_reaper);
                    getWeb("Reaper", setting, 2);
                    break;
                case 4:
                    imageView2.setImageResource(R.mipmap.icon_soldier76);
                    getWeb("Soldier76", setting, 2);
                    break;
                case 5:
                    imageView2.setImageResource(R.mipmap.icon_sombra);
                    getWeb("Sombra", setting, 2);
                    break;
                case 6:
                    imageView2.setImageResource(R.mipmap.icon_tracer);
                    getWeb("Tracer", setting, 2);
                    break;
                case 7:
                    imageView2.setImageResource(R.mipmap.icon_bastion);
                    getWeb("Bastion", setting, 2);
                    break;
                case 8:
                    imageView2.setImageResource(R.mipmap.icon_hanzo);
                    getWeb("Hanzo", setting, 2);
                    break;
                case 9:
                    imageView2.setImageResource(R.mipmap.icon_junkrat);
                    getWeb("Junkrat", setting, 2);
                    break;
                case 10:
                    imageView2.setImageResource(R.mipmap.icon_mei);
                    getWeb("Mei", setting, 2);
                    break;
                case 11:
                    imageView2.setImageResource(R.mipmap.icon_torbjorn);
                    getWeb("Torbjorn", setting, 2);
                    break;
                case 12:
                    imageView2.setImageResource(R.mipmap.icon_widowmaker);
                    getWeb("Widowmaker", setting, 2);
                    break;
                case 13:
                    imageView2.setImageResource(R.mipmap.icon_dva);
                    getWeb("D.va", setting, 2);
                    break;
                case 14:
                    imageView2.setImageResource(R.mipmap.icon_reinhardt);
                    getWeb("Reinhardt", setting, 2);
                    break;
                case 15:
                    imageView2.setImageResource(R.mipmap.icon_roadhog);
                    getWeb("Roadhog", setting, 2);
                    break;
                case 16:
                    imageView2.setImageResource(R.mipmap.icon_winston);
                    getWeb("Winston", setting, 2);
                    break;
                case 17:
                    imageView2.setImageResource(R.mipmap.icon_zarya);
                    getWeb("Zarya", setting, 2);
                    break;
                case 18:
                    imageView2.setImageResource(R.mipmap.icon_ana);
                    getWeb("Ana", setting, 2);
                    break;
                case 19:
                    imageView2.setImageResource(R.mipmap.icon_lucio);
                    getWeb("Lucio", setting, 2);
                    break;
                case 20:
                    imageView2.setImageResource(R.mipmap.icon_mercy);
                    getWeb("Mercy", setting, 2);
                    break;
                case 21:
                    imageView2.setImageResource(R.mipmap.icon_symmetra);
                    getWeb("Symmetra", setting, 2);
                    break;
                case 22:
                    imageView2.setImageResource(R.mipmap.icon_zenyatta);
                    getWeb("Zenyatta", setting, 2);
                    break;
            }
        }
    }

    /**
     * This method is never called deliberately
     *
     * @param parent is the adapter
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * DownloadInfoTask is an Async task that grabs information from the server
     * and parses the information for display
     *
     */
    private class DownloadInfoTask extends AsyncTask<String, Void, String> {

        /**
         * doInBackground grabs information from the server.
         *
         * @param urls is the URLs to be used.
         * @return is the string of information
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to download the hero info, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }

            return response;
        }

        /**
         * onPostExecute parses the information received from doInBackground for display
         *
         * @param result is the string of information to be parsed.
         */
        @Override
        protected void onPostExecute(String result) {
            String theDisplay;
            if(setting == "counters") {
                theDisplay = "COUNTERS:\n";
            } else {
                theDisplay = "STRENGTHS:\n";
            }
            //theDisplay = result;

            //Parsing
            theDisplay += result.substring(result.indexOf(':') + 2, result.indexOf(','));
            result = result.substring(result.indexOf(',') + 1,result.length());
            while(!result.startsWith("\"")){
                theDisplay += "\n" + result.substring(0, result.indexOf(','));
                result = result.substring(result.indexOf(',') + 1, result.length());
            }
            theDisplay = theDisplay.substring(0,theDisplay.length() - 1);

            if(setting == "counters") {
                theDisplay += "\n\nSYNERGIES:\n";
            } else {
                theDisplay += "\n\nWEAKNESSES:\n";
            }

            theDisplay += result.substring(result.indexOf(':') + 2, result.indexOf(','));
            result = result.substring(result.indexOf(',') + 1,result.length());

            while(result.contains(",")){
                theDisplay += "\n" + result.substring(0, result.indexOf(','));
                result = result.substring(result.indexOf(',') + 1, result.length());
            }
            theDisplay+= "\n" + result.substring(0, result.indexOf("\""));


            if(side == 1){
                display1 = theDisplay;
            } else {
                display2 = theDisplay;
            }

            stats1.setText(display1);
            stats2.setText(display2);//These change the text displays.
        }
    }
}
