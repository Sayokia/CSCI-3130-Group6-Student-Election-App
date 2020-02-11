package com.example.a3330_vote_app;

import com.example.a3330_vote_app.MainActivity;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainActivityTest {

    @Test
    public void isEmail1() {
        assertTrue(MainActivity.isEmail("123@dal.ca"));
        assertFalse(MainActivity.isEmail("hn900"));
        assertFalse(MainActivity.isEmail("12345667"));
    }

}