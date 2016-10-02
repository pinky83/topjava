package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final Meal meal1 = new Meal(1000002, LocalDateTime.now(), "Завтрак", 350);
    public static final Meal meal2 = new Meal(1000002, LocalDateTime.now(), "Обед", 700);
    public static final Meal meal3 = new Meal(1000002, LocalDateTime.now(), "Ужин", 650);
    public static final Meal meal4 = new Meal(1000002, LocalDateTime.now(), "Завтрак", 500);
    public static final Meal meal5 = new Meal(1000002, LocalDateTime.now(), "Обед", 700);
    public static final Meal meal6 = new Meal(1000002, LocalDateTime.now(), "Ужин", 800);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (   Objects.equals(expected.getId(), actual.getId()) &&
                        Objects.equals(expected.getCalories(), actual.getCalories())&&
                        Objects.equals(expected.getDescription(), actual.getDescription())
                    )
    );
}
