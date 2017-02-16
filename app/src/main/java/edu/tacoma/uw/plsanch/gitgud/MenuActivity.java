package edu.tacoma.uw.plsanch.gitgud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * The Menu Activity holds Navigation buttons for the different sections of the App
 */
public class MenuActivity extends AppCompatActivity {

    /**
     * onCreate sets the content view
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


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
    public void startGuide (View view){
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }
}
