package edu.tacoma.uw.plsanch.gitgud.util;

import java.util.regex.Pattern;

/**
 * Created by Zachary on 2/28/2017.
 */

public class Account {

    String email;
    String password;

    public String getPassword() {
        return password;
    }

    public Account(String email, String password) {
        if(isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email not Valid");
        }
        if(isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password not Valid");
        }
    }

    public void setEmail(String email) {
        if(isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email not Valid");
        }
    }

    public void setPassword(String password) {
        if(isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password not Valid");
        }
    }



    public String getEmail() {
        return this.email;
    }

    /**
     * Email validation pattern.

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );*/

    /**
     * Validates if the given input is a valid email address.
     *
     * @param email        The email to validate.
     * @return {@code true} if the input is a valid email. {@code false} otherwise.
     */
    public static boolean isValidEmail(String email) {
        return true;// email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private final static int PASSWORD_LEN = 6;
    /**
     * Validates if the given password is valid.
     * Valid password must be at last 6 characters long
     * with at least one digit and one symbol.
     *
     * @param password        The password to validate.
     * @return {@code true} if the input is a valid password.
     * {@code false} otherwise.
     */
    public static boolean isValidPassword(String password) {
        boolean foundDigit = true, foundSymbol = true;
        /*if  (password == null ||
                password.length() < PASSWORD_LEN)
            return false;
        for (int i=0; i<password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                foundDigit = true;
            if (!Character.isLetterOrDigit(password.charAt(i)))
                foundSymbol = true;
        }*/
        return foundDigit && foundSymbol;
    }
}
