package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.user.model.User;

import java.util.Collection;

@Repository
public interface DaoUserRepository {
    User create(User user);

    User update(Long userId, User user);

    Collection<User> getAll();

    User get(Long userId);

    User delete(Long userId);
}
