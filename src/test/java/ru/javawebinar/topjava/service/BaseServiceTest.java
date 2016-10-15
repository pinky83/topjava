package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.util.exception.NotFoundException;

/**
 * Created by Дмитрий on 15.10.2016.**
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles({Profiles.DATAJPA, Profiles.POSTGRES, Profiles.PRODUCTION})
public abstract class BaseServiceTest implements BaseTest{
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Override
    public void testSave() throws Exception {

    }

    @Override
    public void testDelete() throws Exception {

    }

    @Override
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
    }

    @Override
    public void testGet() throws Exception {

    }

    @Override
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
    }

    @Override
    public void testUpdate() throws Exception {

    }

    @Override
    public void testGetAll() throws Exception {

    }
}
