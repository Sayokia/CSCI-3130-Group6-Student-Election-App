package com.example.a3130_vote;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The unit test for user class
 */
public class UserTest {

    /**
     * User constructor test.
     */
    @Test
    public void userConstructorTest(){
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertEquals("123@dal.ca",u.getEmail());
        assertEquals("Yanlin",u.getFirstName());
        assertEquals("Zhu",u.getLastName());
        assertEquals("123456",u.getPassword());
        assertEquals("sayokia",u.getUserName());
        assertEquals(false,u.getIsAdmin());

    }

    /**
     * Gets user name test.
     */
    @Test
    public void getUserNameTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertEquals("sayokia",u.getUserName());
    }

    /**
     * Sets user name test.
     */
    @Test
    public void setUserNameTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        u.setUserName("Test");
        assertEquals("Test",u.getUserName());
    }

    /**
     * Gets first name test.
     */
    @Test
    public void getFirstNameTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertEquals("Yanlin",u.getFirstName());
    }

    /**
     * Sets first name test.
     */
    @Test
    public void setFirstNameTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        u.setFirstName("Test");
        assertEquals("Test",u.getFirstName());
    }

    /**
     * Gets last name test.
     */
    @Test
    public void getLastNameTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertEquals("Zhu",u.getLastName());
    }

    /**
     * Sets last name test.
     */
    @Test
    public void setLastNameTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        u.setLastName("Test");
        assertEquals("Test",u.getLastName());
    }

    /**
     * Gets email test.
     */
    @Test
    public void getEmailTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertEquals("123@dal.ca",u.getEmail());
    }

    /**
     * Sets email test.
     */
    @Test
    public void setEmailTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        u.setEmail("test@test.ca");
        assertEquals("test@test.ca",u.getEmail());
    }

    /**
     * Gets password test.
     */
    @Test
    public void getPasswordTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertEquals("123456",u.getPassword());
    }

    /**
     * Sets password test.
     */
    @Test
    public void setPasswordTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        u.setPassword("1234567");
        assertEquals("1234567",u.getPassword());
    }

    /**
     * Is valid email test.
     */
    @Test
    public void isValidEmailTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertTrue(u.isValidEmail());
        u.setEmail("");
        assertFalse(u.isValidEmail());
        u.setEmail("123@");
        assertFalse(u.isValidEmail());
        u.setEmail("123@da");
        assertFalse(u.isValidEmail());
        u.setEmail("123@dal.");
        assertFalse(u.isValidEmail());
        u.setEmail("@dal.ca");
        assertFalse(u.isValidEmail());
        u.setEmail("123.x");
        assertFalse(u.isValidEmail());
    }

    /**
     * Is valid password test.
     */
    @Test
    public void isValidPasswordTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertTrue(u.isValidPassword());
        u.setPassword("1234567");
        assertTrue(u.isValidPassword());
        u.setPassword("123");
        assertFalse(u.isValidPassword());
    }

    /**
     * Is valid user test.
     */
    @Test
    public void isValidUserTest() {
        User u = new User("sayokia","Yanlin", "Zhu","123@dal.ca","123456");
        assertTrue(u.isValidUser());
        u.setEmail("123");
        assertFalse(u.isValidUser());
        u.setPassword("13");
        assertFalse(u.isValidUser());
        u.setEmail("qqq@dal.ca");
        assertFalse(u.isValidUser());
        u.setPassword("1234567");
        assertTrue(u.isValidUser());
    }
}