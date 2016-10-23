package ru.javawebinar.topjava.repository.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javawebinar.topjava.repository.CacheUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Дмитрий on 23.10.2016.**
 */
public class CacheUtilImpl implements CacheUtil {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void clear2ndLevelHibernateCache() {
        Session s = (Session) em.getDelegate();
        SessionFactory sf = s.getSessionFactory();
//        sf.evict(User.class);
//        sf.getCache().evictEntity(User.class, BaseEntity.START_SEQ);
//        sf.getCache().evictEntityRegion(User.class);
        sf.getCache().evictQueryRegions();
        sf.getCache().evictDefaultQueryRegion();
        sf.getCache().evictCollectionRegions();
        sf.getCache().evictEntityRegions();
    }
}
