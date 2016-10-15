package ru.javawebinar.topjava.service;

import org.junit.Test;
import ru.javawebinar.topjava.util.exception.NotFoundException;

/**
 * Created by Дмитрий on 15.10.2016.***
 */
public interface BaseTest {

    @Test
    default void testSave() throws Exception {

    }

    @Test
    default  void testDelete() throws Exception {

    }

    @Test
    default  void testDeleteNotFound() throws Exception {

    }

    @Test
    default  void testGet() throws Exception {

    }

    @Test
    default  void testGetNotFound() throws Exception{

    }

    @Test
    default  void testUpdate() throws Exception{

    }

    @Test
    default  void testGetAll() throws Exception {

    }

}
