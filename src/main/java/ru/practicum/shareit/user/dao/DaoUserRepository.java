package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.Collection;

@Repository
public interface DaoUserRepository {
    UserDto create(UserDto userDto);

    UserDto update(Long userId, UserDto userDto);

    Collection<UserDto> getAll();

    UserDto get(Long userId);

    UserDto delete(Long userId);
}
