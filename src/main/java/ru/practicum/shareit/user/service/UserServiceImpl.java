package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.user.dao.DaoUserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final DaoUserRepository repository;

    public UserServiceImpl(DaoUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (isDoubleEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Такая электронная почта уже есть ");
        }

        return UserMapper.dtoMapper(repository.create(UserMapper.userMapper(userDto)));
    }

    @Override
    public UserDto update(Long userId, UserDto userDto) {
        if (isDoubleEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Такая электронная почта уже есть ");
        }

        return UserMapper.dtoMapper(repository.update(userId, UserMapper.userMapper(userDto)));
    }

    @Override
    public Collection<UserDto> getAll() {
        return repository.getAll().stream().map(UserMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public UserDto get(Long userId) {
        return UserMapper.dtoMapper(repository.get(userId));
    }

    @Override
    public UserDto delete(Long userId) {
        return UserMapper.dtoMapper(repository.delete(userId));
    }

    private boolean isDoubleEmail(String email) {
        return repository.getAll().stream().anyMatch(u -> u.getEmail().equals(email));
    }
}
