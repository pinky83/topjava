package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private User user1 = new User(1, "Федя Николаев",//хардкоженный юзер-владелец всей еды - for tests
            "soto12@mail.ru", "torba856dr", Role.ROLE_USER);
    {
        MealsUtil.MEALS.forEach((meal) -> save(meal, user1));
    }

    @Override
    public Meal save(Meal meal, User user) {//возможно, не лучший вариант
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        if(user.getId().equals(meal.getUser().getId()))repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if(repository.containsKey(id)) {
            if(repository.get(id).getUser().getId()==userId){//maybe need equals here
                repository.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if(repository.containsKey(id)) {
            if(repository.get(id).getUser().getId()==userId){//maybe need equals here
                return  repository.get(id);
            }
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {//фильтрация по юзерайди, возможно, надо это делать не здесь
        return repository.values().stream()
                                  .filter(meal -> meal.getUser().getId()== userId)
                                  .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))//потестить!
                                  .collect(Collectors.toCollection(ArrayList<Meal>::new));
    }
}
 
