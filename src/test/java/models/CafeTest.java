package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/22/18.
 */
public class CafeTest {
    @Test
    public void getNameReturnsCorrect() throws Exception {
        Cafe testCafe = setupCafe();
        assertEquals("Poop and Pizza", testCafe.getName());
    }

    @Test
    public void setName() throws Exception {
        Cafe testCafe = setupCafe();
        testCafe.setName("Pee Pee Pizza");
        assertEquals("Pee Pee Pizza", testCafe.getName());
    }

    @Test
    public void getAddressReturnsCorrect() throws Exception {
        Cafe testCafe = setupCafe();
        assertEquals("666 Bunny Ave", testCafe.getAddress());
    }

    @Test
    public void setAddress() throws Exception {
        Cafe testCafe = setupCafe();
        testCafe.setAddress("123 Sesame St");
        assertEquals("123 Sesame St", testCafe.getAddress());
    }

    @Test
    public void getZipReturnsCorrect() throws Exception {
        Cafe testCafe = setupCafe();
        assertEquals("10198", testCafe.getZip());
    }

    @Test
    public void setZip() throws Exception {
        Cafe testCafe = setupCafe();
        testCafe.setZip("11111");
        assertEquals("11111", testCafe.getZip());
    }

    @Test
    public void getPhoneReturnsCorrect() throws Exception {
        Cafe testCafe = setupCafe();
        assertEquals("201-304-2219", testCafe.getPhone());

    }

    @Test
    public void setPhone() throws Exception {
        Cafe testCafe = setupCafe();
        testCafe.setPhone("213-432-5648");
        assertEquals("213-432-5648", testCafe.getPhone());
    }

    @Test
    public void getWebsiteReturnsCorrect() throws Exception {
        Cafe testCafe = setupCafe();
        assertEquals("http://www.pppizza.com", testCafe.getEmail());
    }

    @Test
    public void setWebsite() throws Exception {
        Cafe testCafe = setupCafe();
        testCafe.setWebsite("http://www.ppp.com");
        assertEquals("http://www.ppp.com", testCafe.getWebsite());
    }

    @Test
    public void getEmailReturnsCorrect() throws Exception {
        Cafe testCafe = setupCafe();
        assertEquals("poop@pppizza.com", testCafe.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        Cafe testCafe = setupCafe();
        testCafe.setEmail("poop@poop.com");
        assertEquals("poop@poop.com", testCafe.getEmail());
    }

//    @Test
//    public void getIdReturnsCorrect() throws Exception {
//    }
//
//    @Test
//    public void setId() throws Exception {
//    }

    public Cafe setupCafe () {
        return new Cafe("Poop and Pizza", "666 Bunny Ave", "10198", "201-304-2219", "http://www.pppizza.com", "poop@pppizza.com");
    }

    public Cafe setupCafe2 () {
        return new Cafe("I LIEK CAFI!!!", "101 Crabtree St", "10429", "222-222-2222", "http://www.cafi.com", "cafi3@cafi.com");
    }

}