package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class DaoLocalUserRepository implements DaoUserRepository {
    private final List<User> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public User create(User user) {
        user.setId(++id);
        list.add(user);

        return list.getLast();
    }

    @Override
    public User update(Long userId, User originUser) {
        for (User user : list) {
            if (user.getId().equals(userId)) {
                user.setName(originUser.getName() != null ? originUser.getName() : user.getName());
                user.setEmail(originUser.getEmail() != null ? originUser.getEmail() : user.getEmail());

                return user;
            }
        }

        throw new NotFoundException("Пользователь не найден");
    }

    @Override
    public Collection<User> getAll() {
        return list;
    }

    @Override
    public User get(Long userId) {
        return list.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    @Override
    public User delete(Long userId) {
        User user = get(userId);
        list.remove(user);

        return user;
    }
}
