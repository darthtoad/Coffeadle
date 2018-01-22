import com.google.gson.Gson;
import dao.Sql2oCafeDao;
import dao.Sql2oFoodtypeDao;
import dao.Sql2oReviewDao;
import exceptions.ApiException;
import models.Cafe;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;


/**
 * Created by Guest on 1/22/18.
 */
public class App {
    public static void main(String[] args) {
        Sql2oFoodtypeDao foodtypeDao;
        Sql2oCafeDao cafeDao;
        Sql2oReviewDao reviewDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        cafeDao = new Sql2oCafeDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();

        post("/cafes/new", "application/json", (request, response) -> {
            Cafe cafe = gson.fromJson(request.body(), Cafe.class);
            cafeDao.add(cafe);
            response.status(201);
            response.type("application/json");
            return gson.toJson(cafe);
        });

        get("/cafes", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(cafeDao.getAll());//send it back to be displayed
        });

        get("/cafes/:id", "application/json", (request, response) -> {
            int cafeId = Integer.parseInt(request.params("id"));

            Cafe cafeToFind = cafeDao.findById(cafeId);

            if (cafeToFind == null) {
                throw new ApiException(404, String.format("THERE ARE NO CAFES WITH THE ID OF %s", request.params("id")));
            }
            return gson.toJson(cafeToFind);
        });

        post("/cafes/:id/reviews/new", "application/json", (req, res) -> {
            int cafeId = Integer.parseInt(req.params("id"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setCafeId(cafeId); //why do I need to set separately?
            reviewDao.add(review);
            res.status(201);
            return gson.toJson(review);
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}
