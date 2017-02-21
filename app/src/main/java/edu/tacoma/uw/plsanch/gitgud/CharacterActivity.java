package edu.tacoma.uw.plsanch.gitgud;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    Integer side;//Binary switch

    TextView tv,tv2,tv3;
    Typeface tf, tf2;


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
        stats1.setText(display1);
        stats2.setText(display2);


        //custom fonts here

        //create fonts and set typefaces here
        tv = (TextView) findViewById(R.id.characters);
        tv2 = (TextView) findViewById(R.id.account);
        tv3 = (TextView) findViewById(R.id.guides);
        tf = Typeface.createFromAsset(getAssets(), "fonts/big_noodle_titling.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "fonts/big_noodle_titling_oblique.ttf");

        tv.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
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
    System.out.println(parent.getId());
        if(parent.getId() == spinner1.getId()) {//This really long number is the id of the left Spinner.
            /*This line ^^^^ will be less sloppy in later versions, but for now it works.*/
            //Left Side Spinner
            int position1 = spinner1.getSelectedItemPosition();
            ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
            switch (position1) {
                case 0:
                    imageView1.setImageResource(R.mipmap.icon_genji);
                    getWeb("Genji", "counters", 1);
                    break;
                case 1:
                    imageView1.setImageResource(R.mipmap.icon_mccree);
                    getWeb("McCree", "counters", 1);
                    break;
                case 2:
                    imageView1.setImageResource(R.mipmap.icon_pharah);
                    getWeb("Pharah", "counters", 1);
                    break;
                case 3:
                    imageView1.setImageResource(R.mipmap.icon_reaper);
                    getWeb("Reaper", "counters", 1);
                    break;
                case 4:
                    imageView1.setImageResource(R.mipmap.icon_soldier76);
                    getWeb("Soldier76", "counters", 1);
                    break;
                case 5:
                    imageView1.setImageResource(R.mipmap.icon_sombra);
                    getWeb("Sombra", "counters", 1);
                    break;
                case 6:
                    imageView1.setImageResource(R.mipmap.icon_tracer);
                    getWeb("Tracer", "counters", 1);
                    break;
                case 7:
                    imageView1.setImageResource(R.mipmap.icon_bastion);
                    getWeb("Bastion", "counters", 1);
                    break;
                case 8:
                    imageView1.setImageResource(R.mipmap.icon_hanzo);
                    getWeb("Hanzo", "counters", 1);
                    break;
                case 9:
                    imageView1.setImageResource(R.mipmap.icon_junkrat);
                    getWeb("Junkrat", "counters", 1);
                    break;
                case 10:
                    imageView1.setImageResource(R.mipmap.icon_mei);
                    getWeb("Mei", "counters", 1);
                    break;
                case 11:
                    imageView1.setImageResource(R.mipmap.icon_torbjorn);
                    getWeb("Torbjorn", "counters", 1);
                    break;
                case 12:
                    imageView1.setImageResource(R.mipmap.icon_widowmaker);
                    getWeb("Widowmaker", "counters", 1);
                    break;
                case 13:
                    imageView1.setImageResource(R.mipmap.icon_dva);
                    getWeb("D.va", "counters", 1);
                    break;
                case 14:
                    imageView1.setImageResource(R.mipmap.icon_reinhardt);
                    getWeb("Reinhardt", "counters", 1);
                    break;
                case 15:
                    imageView1.setImageResource(R.mipmap.icon_roadhog);
                    getWeb("Roadhog", "counters", 1);
                    break;
                case 16:
                    imageView1.setImageResource(R.mipmap.icon_winston);
                    getWeb("Winston", "counters", 1);
                    break;
                case 17:
                    imageView1.setImageResource(R.mipmap.icon_zarya);
                    getWeb("Zarya", "counters", 1);
                    break;
                case 18:
                    imageView1.setImageResource(R.mipmap.icon_ana);
                    getWeb("Ana", "counters", 1);
                    break;
                case 19:
                    imageView1.setImageResource(R.mipmap.icon_lucio);
                    getWeb("Lucio", "counters", 1);
                    break;
                case 20:
                    imageView1.setImageResource(R.mipmap.icon_mercy);
                    getWeb("Mercy", "counters", 1);
                    break;
                case 21:
                    imageView1.setImageResource(R.mipmap.icon_symmetra);
                    getWeb("Symmetra", "counters", 1);
                    break;
                case 22:
                    imageView1.setImageResource(R.mipmap.icon_zenyatta);
                    getWeb("Zenyatta", "counters", 1);
                    break;
            }

        }else{
            //Right Side Spinner
            int position2 = spinner2.getSelectedItemPosition ();
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
            switch (position2) {
                case 0:
                    imageView2.setImageResource(R.mipmap.icon_genji);
                    getWeb("Genji", "counters", 2);
                    break;
                case 1:
                    imageView2.setImageResource(R.mipmap.icon_mccree);
                    getWeb("McCree", "counters", 2);
                    break;
                case 2:
                    imageView2.setImageResource(R.mipmap.icon_pharah);
                    getWeb("Pharah", "counters", 2);
                    break;
                case 3:
                    imageView2.setImageResource(R.mipmap.icon_reaper);
                    getWeb("Reaper", "counters", 2);
                    break;
                case 4:
                    imageView2.setImageResource(R.mipmap.icon_soldier76);
                    getWeb("Soldier76", "counters", 2);
                    break;
                case 5:
                    imageView2.setImageResource(R.mipmap.icon_sombra);
                    getWeb("Sombra", "counters", 2);
                    break;
                case 6:
                    imageView2.setImageResource(R.mipmap.icon_tracer);
                    getWeb("Tracer", "counters", 2);
                    break;
                case 7:
                    imageView2.setImageResource(R.mipmap.icon_bastion);
                    getWeb("Bastion", "counters", 2);
                    break;
                case 8:
                    imageView2.setImageResource(R.mipmap.icon_hanzo);
                    getWeb("Hanzo", "counters", 2);
                    break;
                case 9:
                    imageView2.setImageResource(R.mipmap.icon_junkrat);
                    getWeb("Junkrat", "counters", 2);
                    break;
                case 10:
                    imageView2.setImageResource(R.mipmap.icon_mei);
                    getWeb("Mei", "counters", 2);
                    break;
                case 11:
                    imageView2.setImageResource(R.mipmap.icon_torbjorn);
                    getWeb("Torbjorn", "counters", 2);
                    break;
                case 12:
                    imageView2.setImageResource(R.mipmap.icon_widowmaker);
                    getWeb("Widowmaker", "counters", 2);
                    break;
                case 13:
                    imageView2.setImageResource(R.mipmap.icon_dva);
                    getWeb("D.va", "counters", 2);
                    break;
                case 14:
                    imageView2.setImageResource(R.mipmap.icon_reinhardt);
                    getWeb("Reinhardt", "counters", 2);
                    break;
                case 15:
                    imageView2.setImageResource(R.mipmap.icon_roadhog);
                    getWeb("Roadhog", "counters", 2);
                    break;
                case 16:
                    imageView2.setImageResource(R.mipmap.icon_winston);
                    getWeb("Winston", "counters", 2);
                    break;
                case 17:
                    imageView2.setImageResource(R.mipmap.icon_zarya);
                    getWeb("Zarya", "counters", 2);
                    break;
                case 18:
                    imageView2.setImageResource(R.mipmap.icon_ana);
                    getWeb("Ana", "counters", 2);
                    break;
                case 19:
                    imageView2.setImageResource(R.mipmap.icon_lucio);
                    getWeb("Lucio", "counters", 2);
                    break;
                case 20:
                    imageView2.setImageResource(R.mipmap.icon_mercy);
                    getWeb("Mercy", "counters", 2);
                    break;
                case 21:
                    imageView2.setImageResource(R.mipmap.icon_symmetra);
                    getWeb("Symmetra", "counters", 2);
                    break;
                case 22:
                    imageView2.setImageResource(R.mipmap.icon_zenyatta);
                    getWeb("Zenyatta", "counters", 2);
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
            String theDisplay = "COUNTERS:\n";
            //theDisplay = result;

            //Parsing
            theDisplay += result.substring(result.indexOf(':') + 2, result.indexOf(','));
            result = result.substring(result.indexOf(',') + 1,result.length());
            while(!result.startsWith("\"")){
                theDisplay += "\n" + result.substring(0, result.indexOf(','));
                result = result.substring(result.indexOf(',') + 1, result.length());
            }
            theDisplay = theDisplay.substring(0,theDisplay.length() - 1);
            theDisplay += "\n\nSYNERGIES:\n";
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
