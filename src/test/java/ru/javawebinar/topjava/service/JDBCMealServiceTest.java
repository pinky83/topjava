package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Дмитрий on 15.10.2016.**
 */
@ActiveProfiles({Profiles.JDBC, Profiles.POSTGRES, Profiles.PRODUCTION})
public class JDBCMealServiceTest extends BaseMealServiceTest{
    @AfterClass
    public static void printResult() {
        System.out.println();
        System.out.println("*********JDBC MealService realization*******");
        System.out.printf("Test             Duration, ms%n");
        System.out.println("-----------------------------");
        System.out.println(results);
        System.out.printf("-----------------------------%n%n");
    }
}
