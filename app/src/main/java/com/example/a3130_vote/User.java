package com.example.a3130_vote;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create User Information for Sign up and Sign in.
 */
public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isAdmin = false;

    /**
     * Instantiates a new User.
     *
     * @param userName  the user name
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     */
    public User(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }



    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Check if email is valid
     *
     * @return the boolean
     */
    public boolean isValidEmail(){
        if (null == email || "".equals(email)) return false;
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Check if the password length is greater than 6
     *
     * @return the boolean
     */
    public boolean isValidPassword(){
        if (password.length()>=6){
            return true;
        }
        return false;
    }

    /**
     * Check for all validations
     *
     * @return the boolean
     */
    public boolean isValidUser(){
        return(isValidPassword()&&isValidEmail());
    }

}