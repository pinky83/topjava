package ru.javawebinar.topjava.repository.jdbc;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.CacheUtil;

/**
 * Created by Дмитрий on 23.10.2016.**
 */
public class JdbcUtil implements CacheUtil{

    @Autowired
    CacheManager cacheManager;

    public void clear2ndLevelHibernateCache() {
        cacheManager.clearAll();
    }
}
