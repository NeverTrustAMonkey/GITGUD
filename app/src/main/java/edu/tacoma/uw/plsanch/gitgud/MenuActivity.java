package edu.tacoma.uw.plsanch.gitgud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void startCharacter(){
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
    }

    public void startLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void startGuides(){
        Intent intent = new Intent(this, GuidesActivity.class);
        startActivity(intent);
    }
}
