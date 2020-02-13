package com.example.a3330_vote_app;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

import static org.junit.Assert.*;

public class logInActivityTest {

    public void isEmail1() {
        assertTrue(logInActivity.isEmail("123@dal.ca"));
        assertFalse(logInActivity.isEmail("hn900"));
        assertFalse(logInActivity.isEmail("12345667"));
        assertFalse(logInActivity.isEmail("123@"));
        assertFalse(logInActivity.isEmail("123@x."));
        assertFalse(logInActivity.isEmail("123.x"));
        assertFalse(logInActivity.isEmail("123.@"));
    }


    @Test
    public void passwordIsEmpty() {
        assertTrue(logInActivity.passwordIsEmpty(""));
        assertFalse(logInActivity.passwordIsEmpty("sd"));
    }

    @Test
    public void emailIsEmpty() {
        assertTrue(logInActivity.emailIsEmpty(""));
        assertFalse(logInActivity.emailIsEmpty("sd"));
    }




}