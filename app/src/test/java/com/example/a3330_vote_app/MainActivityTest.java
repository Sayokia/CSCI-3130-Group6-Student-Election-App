package com.example.a3330_vote_app;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    public void isEmail1() {
        assertTrue(MainActivity.isEmail("123@dal.ca"));
        assertFalse(MainActivity.isEmail("hn900"));
        assertFalse(MainActivity.isEmail("12345667"));
        assertFalse(MainActivity.isEmail("123@"));
        assertFalse(MainActivity.isEmail("123@x."));
        assertFalse(MainActivity.isEmail("123.x"));
        assertFalse(MainActivity.isEmail("123.@"));
    }


    @Test
    public void passwordIsEmpty() {
        assertTrue(MainActivity.passwordIsEmpty(""));
        assertFalse(MainActivity.passwordIsEmpty("sd"));
    }

    @Test
    public void emailIsEmpty() {
        assertTrue(MainActivity.emailIsEmpty(""));
        assertFalse(MainActivity.emailIsEmpty("sd"));
    }
}