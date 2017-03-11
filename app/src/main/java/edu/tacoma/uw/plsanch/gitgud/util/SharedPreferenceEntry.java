package edu.tacoma.uw.plsanch.gitgud.util;

/**
 * Model class containing personal information that will be
 * saved to SharedPreferences.
 */
public class SharedPreferenceEntry {

    // Name of the user.
    private boolean mIsLoggedIn = false;

    // Email address of the user.
    private final String mEmail;

    /**
     * SharedPreferenceEntry is a constructor for the class
     * @param loggedIn is a boolean for whether or not a user is already logged in
     * @param email is the username of the user
     */
    public SharedPreferenceEntry(boolean loggedIn, String email) {
        mIsLoggedIn = loggedIn;
        mEmail = email;
    }

    /**
     * isLoggedIn checks if there is a user logged in.
     * @return a held boolean
     */
    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    /**
     * getEmail retreives the user's username
     * @return a held string
     */
    public String getEmail() {
        return mEmail;
    }
}
