package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface DAO {
    public List<Meal> getAll();
    public List<MealWithExceed> getAllExceed();
    public Meal getById(Integer id);
    public void delete(Meal meal);
    public void create(Meal meal);
    public void update(Integer id, Meal newMeal);
}
