package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.util.List;

/**
 * Created by Дмитрий on 31.10.2016.**
 */
@RestController
@RequestMapping("/ajax/meals")
public class MealAjaxController extends AbstractMealController{
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam(value = "dateTime") String dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") Integer calories) {

        Meal meal = new Meal(TimeUtil.parseLocalDateTime(dateTime), description , calories);
        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }
}
