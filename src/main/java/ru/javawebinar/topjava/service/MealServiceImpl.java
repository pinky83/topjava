package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, User user) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.save(meal, user), meal.getDescription());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.checkNotFound(repository.delete(id, userId), "id = " + id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.get(id, userId), "id = " + id);
    }

    @Override
    public Meal update(Meal meal, User user) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.save(meal, user), meal.getDescription());
    }

    @Override
    public List<MealWithExceed> getAll(int userId, int caloriesPerDay) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getFilteredAll(int userId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);
        return  MealsUtil.getWithExceeded(repository.getAll(userId).stream()
                                 .filter(meal -> meal.getDateTime().compareTo(start)>=0)
                                 .filter(meal -> meal.getDateTime().compareTo(end)<=0)
                                 .collect(Collectors.toList()), caloriesPerDay);
        //фильтруем стримом по дате и времени и преобразуем в MealWithExceed, не тестировалось
    }

}
