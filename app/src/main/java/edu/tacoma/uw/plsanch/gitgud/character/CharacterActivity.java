package edu.tacoma.uw.plsanch.gitgud.character;

import android.graphics.Typeface;
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

import edu.tacoma.uw.plsanch.gitgud.R;

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
                    changeHero(spinner1, imageView1, 1);
                    imageButton.setImageResource(icon_strengths_weaknesses);
                } else if(setting == "strengths"){
                    setting = "counters";
                    changeHero(spinner1, imageView1, 1);
                    imageButton.setImageResource(icon_counters_synergies);
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Mode Changed", Toast.LENGTH_SHORT);
                toast.show();
                changeHero(spinner2, imageView2, 2);
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
        theURL += HERO_URL;
        theURL += "cmd=" + theColumn;
        theURL += "&hero=" + theHero;
        DownloadInfoTask task = new DownloadInfoTask(theSide);
        task.execute(theURL);
    }

    public void changeHero(Spinner theSpinner, ImageView theImageView, int theSide){
        int position = theSpinner.getSelectedItemPosition();
        switch (position) {
            case 0:
                theImageView.setImageResource(R.mipmap.icon_genji);
                getWeb("Genji", setting, theSide);
                break;
            case 1:
                theImageView.setImageResource(R.mipmap.icon_mccree);
                getWeb("McCree", setting, theSide);
                break;
            case 2:
                theImageView.setImageResource(R.mipmap.icon_pharah);
                getWeb("Pharah", setting, theSide);
                break;
            case 3:
                theImageView.setImageResource(R.mipmap.icon_reaper);
                getWeb("Reaper", setting, theSide);
                break;
            case 4:
                theImageView.setImageResource(R.mipmap.icon_soldier76);
                getWeb("Soldier76", setting, theSide);
                break;
            case 5:
                theImageView.setImageResource(R.mipmap.icon_sombra);
                getWeb("Sombra", setting, theSide);
                break;
            case 6:
                theImageView.setImageResource(R.mipmap.icon_tracer);
                getWeb("Tracer", setting, theSide);
                break;
            case 7:
                theImageView.setImageResource(R.mipmap.icon_bastion);
                getWeb("Bastion", setting, theSide);
                break;
            case 8:
                theImageView.setImageResource(R.mipmap.icon_hanzo);
                getWeb("Hanzo", setting, theSide);
                break;
            case 9:
                theImageView.setImageResource(R.mipmap.icon_junkrat);
                getWeb("Junkrat", setting, theSide);
                break;
            case 10:
                theImageView.setImageResource(R.mipmap.icon_mei);
                getWeb("Mei", setting, theSide);
                break;
            case 11:
                theImageView.setImageResource(R.mipmap.icon_torbjorn);
                getWeb("Torbjorn", setting, theSide);
                break;
            case 12:
                theImageView.setImageResource(R.mipmap.icon_widowmaker);
                getWeb("Widowmaker", setting, theSide);
                break;
            case 13:
                theImageView.setImageResource(R.mipmap.icon_dva);
                getWeb("D.va", setting, theSide);
                break;
            case 14:
                theImageView.setImageResource(R.mipmap.icon_reinhardt);
                getWeb("Reinhardt", setting, theSide);
                break;
            case 15:
                theImageView.setImageResource(R.mipmap.icon_roadhog);
                getWeb("Roadhog", setting, theSide);
                break;
            case 16:
                theImageView.setImageResource(R.mipmap.icon_winston);
                getWeb("Winston", setting, theSide);
                break;
            case 17:
                theImageView.setImageResource(R.mipmap.icon_zarya);
                getWeb("Zarya", setting, theSide);
                break;
            case 18:
                theImageView.setImageResource(R.mipmap.icon_ana);
                getWeb("Ana", setting, theSide);
                break;
            case 19:
                theImageView.setImageResource(R.mipmap.icon_lucio);
                getWeb("Lucio", setting, theSide);
                break;
            case 20:
                theImageView.setImageResource(R.mipmap.icon_mercy);
                getWeb("Mercy", setting, theSide);
                break;
            case 21:
                theImageView.setImageResource(R.mipmap.icon_symmetra);
                getWeb("Symmetra", setting, theSide);
                break;
            case 22:
                theImageView.setImageResource(R.mipmap.icon_zenyatta);
                getWeb("Zenyatta", setting, theSide);
                break;
        }
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
            changeHero(spinner1, imageView1, 1);

        }else if(parent.getId() == spinner2.getId()){
            changeHero(spinner2, imageView2, 2);

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

        int side;

        public DownloadInfoTask(int theSide) {
            super();
            side = theSide;
        }

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
