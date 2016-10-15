package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles({Profiles.DATAJPA, Profiles.POSTGRES, Profiles.PRODUCTION})
public class UserServiceTest extends BaseUserServiceTest{

}