package dao;

import models.Cafe;
import models.Foodtype;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public interface FoodtypeDao {

    void add(Foodtype foodtype);
    void addFoodtypeToCafe(Cafe cafe, Foodtype foodtype);

    List<Foodtype> getAll();
    Foodtype findById(int id);

    List<Cafe> getAllCafesForAFoodtype(int id);

    void deleteById(int id);
}
