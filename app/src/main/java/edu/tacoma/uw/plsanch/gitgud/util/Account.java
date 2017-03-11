package edu.tacoma.uw.plsanch.gitgud.util;

/**
 * The Account is a data object that holds two strings
 * a user's username and their password
 */
public class Account {

    //the user's username
    private String email;
    //the user's password
    private String password;

    /**
     * Account is a constructor
     * @param email is the user's username
     * @param password is the user's password
     */
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * setEmail sets the user's email
     * @param email is the user's username
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * setPassword sets the user's password
     * @param password is the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * getEmail returns the user's username
     * @return a held string
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * getPassword returns the user's password
     * @return a held string
     */
    public String getPassword() {
        return this.password;
    }
}
