package dao;

import models.Cafe;
import models.Foodtype;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oFoodtypeDaoTest {
    private Connection conn;
    private Sql2oFoodtypeDao foodtypeDao;
    private Sql2oCafeDao cafeDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        cafeDao = new Sql2oCafeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addSetsId() throws Exception {
        Foodtype foodtype = new Foodtype("Vegan");
        foodtypeDao.add(foodtype);
        assertEquals(1, foodtype.getId());
    }

    @Test
    public void getAllGetsAllFootypes() throws Exception {
        Foodtype foodtype = new Foodtype("Mexican");
        Foodtype foodtype1 = new Foodtype("Japanese");
        foodtypeDao.add(foodtype);
        foodtypeDao.add(foodtype1);
        assertEquals(2, foodtypeDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        Foodtype foodtype = new Foodtype("Mexican");
        Foodtype foodtype1 = new Foodtype("Japanese");
        foodtypeDao.add(foodtype);
        foodtypeDao.add(foodtype1);
        foodtypeDao.deleteById(foodtype1.getId());
        assertEquals(1, foodtypeDao.getAll().size());
    }

    @Test
    public void addFoodTypeToCafeAddsTypeCorrectly() throws Exception {

        Cafe testCafe = setupCafe();
        Cafe testCafe1 = setupCafe2();

        cafeDao.add(testCafe);
        cafeDao.add(testCafe1);

        Foodtype testFoodtype = new Foodtype("Mexican");

        foodtypeDao.add(testFoodtype);

        foodtypeDao.addFoodtypeToCafe(testCafe, testFoodtype);
        foodtypeDao.addFoodtypeToCafe(testCafe1, testFoodtype);

        assertEquals(2, foodtypeDao.getAllCafesForAFoodtype(testFoodtype.getId()).size());
    }

    @Test
    public void getAllFoodtypesForACafeReturnsFoodtypesCorrectly() throws Exception {
        Foodtype testFoodtype  = new Foodtype("Seafood");
        foodtypeDao.add(testFoodtype);


        Foodtype otherFoodtype  = new Foodtype("Bar Food");
        foodtypeDao.add(otherFoodtype);

        Cafe testCafe = setupCafe();
        cafeDao.add(testCafe);
        Cafe testCafe2 = setupCafe2();
        cafeDao.add(testCafe2);

        Foodtype[] foodtypes = {testFoodtype, otherFoodtype}; //oh hi what is this?

        assertEquals(foodtypeDao.getAllCafesForAFoodtype(testCafe.getId()), Arrays.asList(foodtypes));
    }

    public Cafe setupCafe () {
        return new Cafe("Poop and Pizza", "666 Bunny Ave", "10198", "201-304-2219", "http://www.pppizza.com", "poop@pppizza.com");
    }

    public Cafe setupCafe2 () {
        return new Cafe("I LIEK CAFI!!!", "101 Crabtree St", "10429", "222-222-2222", "http://www.cafi.com", "cafi3@cafi.com");
    }

}