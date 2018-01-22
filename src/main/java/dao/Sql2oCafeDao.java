package dao;

import models.Cafe;

import java.util.ArrayList;
import java.util.List;

import models.Foodtype;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oCafeDao implements CafeDao {

    private final Sql2o sql2o;

    public Sql2oCafeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add (Cafe cafe) {
        String sql = "INSERT INTO cafes (name, address, zip, phone, website, email) VALUES (:name, :address, :zip, :phone, :website, :email)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(cafe)
                    .executeUpdate()
                    .getKey();
                cafe.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    };

    @Override
    public void addCafeToFoodtype(Cafe cafe, Foodtype foodtype) {
        String sql = "INSERT INTO cafes_foodtypes (cafeid, foodtypeid) VALUES (:cafeId, :foodtypeId)";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("cafeId", cafe.getId())
                    .addParameter("foodtypeId", foodtype.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Foodtype> getAllFoodtypesForACafe(int cafeId) {
        ArrayList <Foodtype> foodtypes = new ArrayList<>();

        String joinQuery = "SELECT foodtypeid FROM cafes_foodtypes WHERE cafeid = :cafeId";
        try (Connection con = sql2o.open()) {
            List<Integer> allFoodtypesIds = con.createQuery(joinQuery)
                    .addParameter("cafeId", cafeId)
                    .executeAndFetch(Integer.class);
            for (Integer foodId : allFoodtypesIds) {
                String foodtypeQuery = "SELECT * FROM foodtypes WHERE id = :foodtypeId";
                        foodtypes.add(
                                con.createQuery(foodtypeQuery)
                                    .addParameter("foodtypeId", foodId)
                                    .executeAndFetchFirst(Foodtype.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return foodtypes;
    }

    @Override
    public List<Cafe> getAll(){
        String sql = "SELECT * FROM cafes";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Cafe.class);
        }
    };

    @Override
    public Cafe findById(int id){
        String sql = "SELECT * FROM cafes WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Cafe.class);
        }
    };

    @Override
    public void edit(int id, String name, String address, String zip, String phone, String website, String email){
        String sql = "UPDATE cafes SET name = :name, address = :address, phone = :phone, website = :website, email = :email WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("phone", phone)
                    .addParameter("website", website)
                    .addParameter("email", email)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    };

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM cafes WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    };
}
