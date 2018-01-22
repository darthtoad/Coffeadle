package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public class Sql2oReviewDao implements ReviewDao {

    private final Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (writtenby, rating, cafeid, kanttent) VALUES (:writtenBy, :rating, :cafeId, :kantTent)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                .bind(review)
                .executeUpdate()
                .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from reviews WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews")
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public List<Review> getAllReviewsByCafe(int cafeId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE cafeid = :cafeId")
                    .addParameter("cafeId", cafeId)
                    .executeAndFetch(Review.class);
        }
    }
}
