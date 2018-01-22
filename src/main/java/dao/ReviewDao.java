package dao;
import models.Review;

import java.util.List;

/**
 * Created by Guest on 1/22/18.
 */
public interface ReviewDao {

    void add(Review review);

    List<Review> getAllReviewsByCafe(int cafeId);
    List<Review> getAll();

    void deleteById(int id);

}
