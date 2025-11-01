package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.Collection;

@Service
public interface UserService {
    UserDto create(UserDto userDto);

    UserDto update(Long userId, UserDto userDto);

    Collection<UserDto> getAll();

    UserDto get(Long userId);

    UserDto delete(Long userId);
}
