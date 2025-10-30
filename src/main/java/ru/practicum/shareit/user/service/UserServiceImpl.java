package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.user.dao.DaoUserRepository;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private final DaoUserRepository repository;

    public UserServiceImpl(DaoUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        return repository.create(userDto);
    }

    @Override
    public UserDto update(Long userId, UserDto userDto) {
        return repository.update(userId, userDto);
    }

    @Override
    public Collection<UserDto> getAll() {
        return repository.getAll();
    }

    @Override
    public UserDto get(Long userId) {
        return repository.get(userId);
    }

    @Override
    public UserDto delete(Long userId) {
        return repository.delete(userId);
    }
}
