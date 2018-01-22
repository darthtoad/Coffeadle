package dao;

import models.Cafe;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oReviewDaoTest {
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
    public void addingReviewSetsId() throws Exception {
        Cafe testCafe = setupCafe();
        cafeDao.add(testCafe);
        Review testReview = new Review("Captain Kirk", 4, testCafe.getId(), "foodcoma!");
        reviewDao.add(testReview);
        assertEquals(1, testReview.getId());
    }
    @Test
    public void deleteById() throws Exception {
        Cafe testCafe = setupCafe();
        Cafe testCafe1 = setupCafe1();
        Review testReview = new Review("Captain Kirk", 4, testCafe.getId(), "foodcoma!");
        Review testReview1 = new Review("Mr. T", 5, testCafe1.getId(), "I PITY THE FOO!");
        cafeDao.add(testCafe);
        cafeDao.add(testCafe1);
        reviewDao.add(testReview);
        reviewDao.add(testReview1);
        reviewDao.deleteById(testReview1.getId());
        assertEquals(1, reviewDao.getAll().size());
    }

    @Test
    public void getAll() throws Exception {
        Cafe testCafe = setupCafe();
        Cafe testCafe1 = setupCafe1();
        Review testReview = new Review("Captain Kirk", 4, testCafe.getId(), "foodcoma!");
        Review testReview1 = new Review("Mr. T", 5, testCafe1.getId(), "I PITY THE FOO!");
        cafeDao.add(testCafe);
        cafeDao.add(testCafe1);
        reviewDao.add(testReview);
        reviewDao.add(testReview1);
        assertEquals(2, reviewDao.getAll().size());
    }

    @Test
    public void getAllReviewsByCafe() throws Exception {
        Cafe testCafe = setupCafe();
        cafeDao.add(testCafe);
        Cafe testCafe1 = setupCafe1();
        cafeDao.add(testCafe1);
        Review testReview = new Review("Captain Kirk", 4, testCafe.getId(), "foodcoma!");
        reviewDao.add(testReview);
        Review testReview1 = new Review("Mr. T", 5, testCafe.getId(), "I PITY THE FOO!");
        reviewDao.add(testReview1);

        assertEquals(testReview, reviewDao.getAllReviewsByCafe(testCafe.getId()).get(0));
    }

    public Cafe setupCafe (){
        return new Cafe("Fish Witch", "214 NE Broadway", "97232", "503-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
    }

    public Cafe setupCafe1() {
        return new Cafe("Poop", "123 Sesame St", "12345", "234-567-8910", "http://poop.com", "poop@poop.com");
    }

}