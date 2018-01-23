package dao;

import models.Cafe;
import models.Foodtype;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public interface CafeDao {

    void add (Cafe cafe);
    void addCafeToFoodtype(Cafe cafe, Foodtype foodtype);

    List<Cafe> getAll();
    List<Foodtype> getAllFoodtypesForACafe(int cafeId);

    Cafe findById(int id);


    void edit(int id, String name, String address, String zip);

    void edit(int id, String name, String address, String zip, String phone, String website, String email, String description);

    void deleteById(int id);
}
