package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal, User user) {
        LOG.info("create " + meal + " : " + user);
        return service.save(meal, user);
    }

    public void delete(int id, int userId) {
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) {
       return service.get(id, userId);
    }

    public List<MealWithExceed> getAll(int userId, int caloriesPerDay){
        LOG.info("getAll");
        return service.getAll(userId, caloriesPerDay);
    }

    public List<MealWithExceed> getFilteredAll(int userId, int caloriesPerDay
            , LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        return service.getFilteredAll(userId, caloriesPerDay, startDate, startTime, endDate, endTime);
    }

    public void update(Meal meal, User user) {
        service.update(meal, user);
    }
}
