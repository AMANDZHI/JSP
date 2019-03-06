package com.company.repository;

import com.company.api.UserRepository;
import com.company.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> map = new HashMap<>();
    private static UserRepository userRepository;

    {
        User user = null;
        user = new User("admin", "admin", "admin", true);
        map.put(user.getId(), user);
    }

    private UserRepositoryImpl() {
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        UserRepositoryImpl.userRepository = userRepository;
    }

    @Override
    public void save(User object) {
        map.put(object.getId(), object);
    }


    @Override
    public User update(User object) {
        return map.put(object.getId(), object);
    }

    @Override
    public boolean removeByLogin(String login) {
        User user = findByLogin(login);
        map.remove(user);
        return true;
    }

    @Override
    public Map<String, User> getMap() {
        return map;
    }

    @Override
    public User findByLogin(String login) {
        for (Map.Entry<String, User> pair: map.entrySet()) {
            if (pair.getValue().getLogin().equals(login)) {
                return pair.getValue();
            }
        }
        return null;
    }
}