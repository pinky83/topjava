package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        ExceptionUtil.checkNotFoundWithId((AuthorizedUser.id()==userId), userId);
        User ref = manager.getReference(User.class, userId);
        meal.setUser(ref);
            if(meal.isNew()){
                manager.persist(meal);
                return meal;
            }
            else{
                return manager.merge(meal);
            }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return manager.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = manager.find(Meal.class, id);
        return m.getUser().getId()==userId? m : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return manager.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        TypedQuery<Meal> query = manager.createQuery("SELECT m FROM Meal m WHERE m.user.id=:userId AND  m.dateTime  BETWEEN :startDate AND :endDate ORDER BY m.dateTime desc", Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate);
        return query.getResultList();
    }
}