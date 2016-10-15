package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles({Profiles.DATAJPA, Profiles.POSTGRES, Profiles.PRODUCTION})
public class DataJpaMealServiceTest extends BaseMealServiceTest{
    @AfterClass
    public static void printResult() {
        System.out.println();
        System.out.println("*********DataJpa MealService realization*******");
        System.out.printf("Test             Duration, ms%n");
        System.out.println("-----------------------------");
        System.out.println(results);
        System.out.printf("-----------------------------%n%n");
    }
}