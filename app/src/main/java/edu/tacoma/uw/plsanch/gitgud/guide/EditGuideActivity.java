package edu.tacoma.uw.plsanch.gitgud.guide;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import edu.tacoma.uw.plsanch.gitgud.R;
import edu.tacoma.uw.plsanch.gitgud.guide.Guide;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferenceEntry;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferencesHelper;

public class EditGuideActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //is the spinner for hero selection
    Spinner spinner;
    //imageview represents the hero selection
    ImageView imageView;
    //the edittext for the guide title
    EditText titleText;
    //the edittext for the guide text
    EditText contentText;

    String myURL;
    //base url for adding a guide
    String guideSubmitURL = "http://cssgate.insttech.washington.edu/~_450bteam9/addGuide.php?";
    //base url for editing an existing guide
    String guideEditURL = "http://cssgate.insttech.washington.edu/~_450bteam9/editGuide.php?";
    //boolean for whether an existing guide is being edited
    boolean editing = false;

    //string variables
    String theHero;
    String theAuthor;
    String theTitle;
    String theContent;

    String theGuideId;
    String theGuideTitle;
    String theGuideText;

    //sharedpreferences for login information
    SharedPreferenceEntry entry;

    /**
     * onCreate builds the view, grabs widget ids, and checks if the intent has extra info for guide editing
     * @param savedInstanceState is the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guide);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(
                sharedPreferences);
        entry = sharedPreferencesHelper.getLoginInfo();

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        titleText = (EditText) findViewById(R.id.editTextTitle);
        contentText = (EditText) findViewById(R.id.editTextBody);

        Intent intent = getIntent();
        if(intent.hasExtra("GUIDE_ID")){
            editing = true;
            theGuideId = intent.getStringExtra("GUIDE_ID");
            theGuideTitle = intent.getStringExtra("GUIDE_TITLE");
            theGuideText = intent.getStringExtra("GUIDE_TEXT");
            titleText.setText(theGuideTitle);
            contentText.setText(theGuideText);
        }
    }

    /**
     * is the onClick method for the Submit button
     * Submits the user entered information into the database
     * @param v is the calling view
     * @throws UnsupportedEncodingException
     */
    public void submitButtonPressed(View v) throws UnsupportedEncodingException {

        if(entry.getEmail() == ""){
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
            builder.setTitle("Not Signed In")
                    .setMessage("You must be signed in to create a guide.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        theAuthor =  entry.getEmail();
        theTitle = titleText.getText().toString();
        theContent = contentText.getText().toString();
        theAuthor = URLEncoder.encode(theAuthor, "UTF-8");
        theTitle = URLEncoder.encode(theTitle, "UTF-8");
        theContent = URLEncoder.encode(theContent, "UTF-8");
        if(editing){
            myURL = guideEditURL;
            myURL += "id=" + theGuideId;
            myURL += "&title=" + theTitle;
            myURL += "&cont=" + theContent;
        }else {
            myURL = guideSubmitURL;
            myURL += "user=" + theAuthor;
            myURL += "&hero=" + theHero;
            myURL += "&title=" + theTitle;
            myURL += "&cont=" + theContent;
        }
        //Code for AlertDialog ripped from StackOverflow
        // http://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("Submit Guide")
                .setMessage("Are you sure you want to submit this guide?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        UploadGuideTask task = new UploadGuideTask();
                        task.execute(new String[]{myURL});
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * calls changeHero()
     * @param parent is the adapterview
     * @param view is the calling view
     * @param position is the position of the spinner
     * @param id is the id of the spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        changeHero((Spinner) parent, imageView);
    }

    /**
     * is called when the spinner is changed and updates the imageview accordingly
     * @param theSpinner is the spinner calling
     * @param theImageView is the imageview being changed
     */
    public void changeHero(Spinner theSpinner, ImageView theImageView){
        int position = theSpinner.getSelectedItemPosition();
        switch (position) {
            case 0:theImageView.setImageResource(R.mipmap.icon_genji);theHero = "Genji";break;
            case 1:theImageView.setImageResource(R.mipmap.icon_mccree);theHero = "McCree";break;
            case 2:theImageView.setImageResource(R.mipmap.icon_pharah);theHero = "Pharah";break;
            case 3:theImageView.setImageResource(R.mipmap.icon_reaper);theHero = "Reaper";break;
            case 4:theImageView.setImageResource(R.mipmap.icon_soldier76);theHero = "Soldier76";break;
            case 5:theImageView.setImageResource(R.mipmap.icon_sombra);theHero = "Sombra";break;
            case 6:theImageView.setImageResource(R.mipmap.icon_tracer);theHero = "Tracer";break;
            case 7:theImageView.setImageResource(R.mipmap.icon_bastion);theHero = "Bastion";break;
            case 8:theImageView.setImageResource(R.mipmap.icon_hanzo);theHero = "Hanzo";break;
            case 9:theImageView.setImageResource(R.mipmap.icon_junkrat);theHero = "Junkrat";break;
            case 10:theImageView.setImageResource(R.mipmap.icon_mei);theHero = "Mei";break;
            case 11:theImageView.setImageResource(R.mipmap.icon_torbjorn);theHero = "Torbjorn";break;
            case 12:theImageView.setImageResource(R.mipmap.icon_widowmaker);theHero = "Widowmaker";break;
            case 13:theImageView.setImageResource(R.mipmap.icon_dva);theHero = "D.va";break;
            case 14:theImageView.setImageResource(R.mipmap.icon_reinhardt);theHero = "Reinhardt";break;
            case 15:theImageView.setImageResource(R.mipmap.icon_roadhog);theHero = "Roadhog";break;
            case 16:theImageView.setImageResource(R.mipmap.icon_winston);theHero = "Winston";break;
            case 17:theImageView.setImageResource(R.mipmap.icon_zarya);theHero = "Zarya";break;
            case 18:theImageView.setImageResource(R.mipmap.icon_ana);theHero = "Ana";break;
            case 19:theImageView.setImageResource(R.mipmap.icon_lucio);theHero = "Lucio";break;
            case 20:theImageView.setImageResource(R.mipmap.icon_mercy);theHero = "Mercy";break;
            case 21:theImageView.setImageResource(R.mipmap.icon_symmetra);theHero = "Symmetra";break;
            case 22:theImageView.setImageResource(R.mipmap.icon_zenyatta);theHero = "Zenyatta";break;
        }
    }

    /**
     * is never called deliberately
     * @param parent is the calling parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * UploadGuidesTask is the webaccess asynctask that uploads the guide information to the database
     */
    private class UploadGuideTask extends AsyncTask<String, Void, String> {

        /**
         * doInBackground tries the urls by accessing the internet
         * @param urls are the urls to be tried
         * @return a response build for error reporting
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
                    response = "Unable to upload the guide, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * onPostExecute receives the status of the webaccess and communicates it to the user.
         * if everything is good it builds the view of guides
         * @param result is the result of doInBackground
         */
        @Override
        protected void onPostExecute(String result) {

            if(result.startsWith("Unable to")){
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Guide Submitted Successfully", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Refresh Guide Browser to see Changes", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
