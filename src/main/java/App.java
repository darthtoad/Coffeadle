import com.google.gson.Gson;
import dao.Sql2oCafeDao;
import dao.Sql2oFoodtypeDao;
import dao.Sql2oReviewDao;
import exceptions.ApiException;
import models.Cafe;
import models.Foodtype;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

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

        get("/foodtypes", "application/json", (request, response) -> {
            response.type("application");
            return gson.toJson(foodtypeDao.getAll());
        });

        post("/foodtypes/new", "application/json", (request, response) -> {
            Foodtype foodtype = gson.fromJson(request.body(), Foodtype.class);
            foodtypeDao.add(foodtype);
            response.status(201);
            response.type("application/json");
            return gson.toJson(foodtype);
        });

        post("/cafes/:cafeId/foodtypes/:foodtypeId", "application/json", (request, response) -> {

            int cafeId = Integer.parseInt(request.params("cafeId"));
            int foodtypeId = Integer.parseInt(request.params("foodtypeId"));
            Cafe cafe = cafeDao.findById(cafeId);
            Foodtype foodtype = foodtypeDao.findById(foodtypeId);

            foodtypeDao.addFoodtypeToCafe(cafe, foodtype);
            response.status(201);
            return gson.toJson(String.format("Cafe '%s' and food '%s' are now successfully associated and you may have a nice day and play with puppies", cafe.getName(), foodtype.getName()));
        });

        get("/cafes/:id/foodtypes", "application/json", (request, response) -> {
           int cafeId = Integer.parseInt(request.params("id"));
           Cafe cafe = cafeDao.findById(cafeId);
           response.status(201);
           return gson.toJson(cafeDao.getAllFoodtypesForACafe(cafeId));
        });

        get("/foodtypes/:id/cafes", "application/json", (request, response) -> {
           int foodtypeId = Integer.parseInt(request.params("id"));
           Foodtype foodtype = foodtypeDao.findById(foodtypeId);
           response.status(201);
           return gson.toJson(foodtypeDao.getAllCafesForAFoodtype(foodtypeId));
        });

        after((req, res) ->{
            res.type("application/json");
        });

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });
    }
}
