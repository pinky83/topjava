package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    //тут захардкодить список юзеров(инициализировать мапу)
    {
        List<User> users = Arrays.asList(
                new User(counter.incrementAndGet(), "Федя Николаев",
                        "soto12@mail.ru", "torba856dr", Role.ROLE_USER, Role.values()),
                new User(counter.incrementAndGet(), "Иван Матюшко",
                        "javauser@hotmail.com", "djnvgj394gr", Role.ROLE_USER, Role.values()),
                new User(counter.incrementAndGet(), "Маргарита Строганова",
                        "margo17@gmail.com", "kiso4ka2345", Role.ROLE_USER, Role.values()),
                new User(counter.incrementAndGet(), "Иван Сотников",
                        "vanya1989@ya.ru", "rtl23sderto", Role.ROLE_USER, Role.values())
        );

        users.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        if(repository.containsKey(id)) {
            LOG.info("delete " + id);
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        if(user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        LOG.info("save " + user);
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.containsKey(id) ? repository.get(id) : null;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> result = new ArrayList<>();
        result.addAll(repository.values());
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        if(!repository.isEmpty())
            for(User u : repository.values())if (u.getEmail().equalsIgnoreCase(email))return u;
        return null;
    }

    //только для тестирования - удалять!
    public static void main(String...args){
        new InMemoryUserRepositoryImpl().getAll()
                .forEach(user -> System.out.println(user.getName()));

    }
}
