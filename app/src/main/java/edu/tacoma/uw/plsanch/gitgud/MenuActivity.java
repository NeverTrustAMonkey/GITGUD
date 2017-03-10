package edu.tacoma.uw.plsanch.gitgud;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import edu.tacoma.uw.plsanch.gitgud.character.CharacterActivity;
import edu.tacoma.uw.plsanch.gitgud.guide.EditGuideActivity;
import edu.tacoma.uw.plsanch.gitgud.guide.GuideBrowserActivity;
import edu.tacoma.uw.plsanch.gitgud.login.LoginActivity;

/**
 * The Menu Activity holds Navigation buttons for the different sections of the App
 */
public class MenuActivity extends AppCompatActivity {

    /**
     * onCreate sets the content view
     *
     * @param savedInstanceState
     */
//custom fonts
    TextView tv,tv2,tv3;
    Typeface tf, tf2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
                    builder.setTitle("About this App:")
                    .setMessage("Git-Gud is an app designed to compliment the popular video game Overwatch by providing users with easy access to helpful information on how to play the game." +
                            "\n\n" +
                            "The Character page has quick tips about heroes, including their counters, synergies, strengths and weaknesses" +
                            "\n\n" +
                            "The Account page has the option to sign in which will allow users to post their own guides and also save and view bookmarked guides" +
                            "\n\n" +
                            "The Guide Browser has a selection of user submitted guides that can be sorted by hero and viewed for in depth game tips. It also is where signed in users create new guides and bookmark their favorite guides.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //don't do anything
                                }
                            })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This button listener starts the Characters Activity
     *
     * @param view is the View that is calling the method.
     */
    public void startCharacter (View view){
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
    }

    /**
     * This button listener starts the Login Activity
     *
     * @param view is the View that is calling the method.
     */
    public void startLogin (View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * This button listener starts the Guides Activity
     *
     * @param view is the View that is calling the method.
     */
    public void startGuideBrowser (View view){
        Intent intent = new Intent(this, GuideBrowserActivity.class);
        startActivity(intent);
    }
}
