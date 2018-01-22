package dao;

import models.Cafe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oCafeDaoTest {
    private Connection conn;
    private Sql2oReviewDao reviewDao;
    private Sql2oCafeDao cafeDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        cafeDao = new Sql2oCafeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addSetsNewId() throws Exception {
        Cafe cafe = setupCafe();
        int originalCafeId = cafe.getId();
        cafeDao.add(cafe);
        assertNotEquals(originalCafeId, cafe.getId());
    }

    @Test
    public void getAllGetsAll() throws Exception {
        Cafe cafe = setupCafe();
        Cafe cafe1 = setupCafe1();
        cafeDao.add(cafe);
        cafeDao.add(cafe1);
        assertEquals(2, cafeDao.getAll().size());
    }

    @Test
    public void findByIdFindsById() throws Exception {
        Cafe cafe = setupCafe();
        Cafe cafe1 = setupCafe1();
        cafeDao.add(cafe);
        cafeDao.add(cafe1);
        assertEquals(cafe, cafeDao.findById(cafe.getId()));
    }

    @Test
    public void editEditsAllInfo() throws Exception {
        Cafe cafe = setupCafe();
        Cafe cafe1 = setupCafe1();
        cafeDao.add(cafe);
        cafeDao.add(cafe1);
        String originalName = cafe.getName();
        cafeDao.edit(1, "poop", "123 Pee St", "12321", "333-444-5555", "http://colonslash.com", "dude@dude.com");
        assertNotEquals(originalName, cafeDao.findById(1));
    }

    @Test
    public void deleteById() throws Exception {
        Cafe cafe = setupCafe();
        Cafe cafe1 = setupCafe1();
        cafeDao.add(cafe);
        cafeDao.add(cafe1);
        cafeDao.deleteById(1);
        assertEquals(1, cafeDao.getAll().size());
    }

    public Cafe setupCafe (){
        return new Cafe("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
    }

    public Cafe setupCafe1() {
        return new Cafe("Poop", "123 Sesame St", "12345", "234-567-8910", "http://poop.com", "poop@poop.com");
    }

}