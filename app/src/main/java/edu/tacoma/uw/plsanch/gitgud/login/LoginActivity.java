package edu.tacoma.uw.plsanch.gitgud.login;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.tacoma.uw.plsanch.gitgud.R;
import edu.tacoma.uw.plsanch.gitgud.util.Account;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferenceEntry;
import edu.tacoma.uw.plsanch.gitgud.util.SharedPreferencesHelper;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {

    //Holds the user's information after registration
    Account mAccount;
    //stored sign in information data object
    SharedPreferencesHelper mSharedPreferencesHelper;
    //webaccess task
    RegisterAsyncTask task;

    //base url for web access
    private String LOGIN_URL = "http://cssgate.insttech.washington.edu/~_450bteam9/login.php?";

    /**
     * onCreate checks if a user is logged in by accessing the shared preferences
     * and then starts up the appropriate fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        // Instantiate a SharedPreferencesHelper.
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        mSharedPreferencesHelper = new SharedPreferencesHelper(
                sharedPreferences);
        SharedPreferenceEntry entry = mSharedPreferencesHelper.getLoginInfo();
        task = new RegisterAsyncTask();

        if (entry.isLoggedIn()){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new LoginFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new RegisterFragment())
                    .commit();
        }
    }

    /**
     * register builds the webaccess url and calls the asynctask to register/sign in a user.
     * @param account is the object holding the user's information
     */
    public void register(Account account) {
        mAccount = account;
        String url = LOGIN_URL;
        url += "email=" + account.getEmail();
        url += "&password=" + account.getPassword();
        task.execute(url);
    }

    /**
     * onCreateOptionsMenu inflates the menu upon being clicked
     * @param menu is the menu calling for inflation
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    /**
     * onOptionsItemSelected holds the onclicklistener for the menu item
     * @param item is the item being clicked
     * @return true;
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (id == R.id.action_logout) {
            SharedPreferenceEntry entry = new SharedPreferenceEntry(false,"");
            mSharedPreferencesHelper.savePersonalInfo(entry);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RegisterFragment())
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * RegisterAsyncTask is the webaccess task that sends information to the database
     */
    private class RegisterAsyncTask extends AsyncTask<String, Void, String> {

        /**
         * doInBackground connects to the server and sends the information and receives the response
         * @param urls are the urls to access
         * @return a string response built for error reporting
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
                    response = "Unable to connect to the server: "
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
         * onPostExecute checks to see if doInBackground completed succesfully
         * Tells the user if they are logged in.
         * Saves the user's information to shared preferences if they do log in.
         * @param result is the result of the doInBackground
         */
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = (String) jsonObject.get("result");
                if (status.equals("success")) {
                    Toast.makeText(getApplicationContext(), "User successfully logged in!", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SharedPreferenceEntry entry = new SharedPreferenceEntry(
                            true, mAccount.getEmail());
                    mSharedPreferencesHelper.savePersonalInfo(entry);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to register: "
                                    + jsonObject.get("error")
                            , Toast.LENGTH_LONG)
                            .show();
                    SharedPreferenceEntry entry = new SharedPreferenceEntry(false,"");
                    mSharedPreferencesHelper.savePersonalInfo(entry);
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something wrong with the data" +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
            finish();
        }

    }


}