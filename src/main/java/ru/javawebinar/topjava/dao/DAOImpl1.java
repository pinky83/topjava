package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.createWithExceed;

public class DAOImpl1 implements DAO{
    public static AtomicInteger counter = new AtomicInteger(0);
    private static List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    public List<MealWithExceed> getAllExceed(){
        Map<LocalDate, Integer> caloriesSumByDate = getAll().stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );


        return getAll().stream()
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > 2000))
                .collect(Collectors.toList());
    }

    @Override
    public Meal getById(Integer id) {
        for (Meal m : meals){
            if (m.getId()==id)return m;
        }
        return null;
    }

    @Override
    public void delete(Meal meal) {
       meals.remove(meal);
    }

    @Override
    public void create(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void update(Integer id, Meal newMeal) {

    }
}
