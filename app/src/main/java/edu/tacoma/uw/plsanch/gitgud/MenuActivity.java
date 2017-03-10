package edu.tacoma.uw.plsanch.gitgud;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.tacoma.uw.plsanch.gitgud.character.CharacterActivity;
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
